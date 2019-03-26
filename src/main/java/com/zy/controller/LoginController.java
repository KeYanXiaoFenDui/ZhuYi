package com.zy.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zy.domain.Admin;
import com.zy.service.IAdminService;
import com.zy.service.IMenuService;
import com.zy.util.CommonUtil;
import com.zy.util.RedisComponentUtil;
import com.zy.util.constant.MessageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class LoginController {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    public RedisComponentUtil redisComponentUtil;
//    @GetMapping("/")
//    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String account, Model model) {
//        model.addAttribute("name", account);
//        return "index";
//    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/wellcome")
    public String wellcome(Model m, HttpServletRequest request, HttpSession session) {
        setAdminMsg(m, request, session);
        return "wellcome";
    }
    @PostMapping("/loginPost")
    public @ResponseBody HashMap<String,Object> loginPost(String account, String password, HttpSession session, HttpServletResponse response) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        HashMap<String,Object> adminMsg = new HashMap<>();
//        Map<String, Object> map = new HashMap<>();
        Admin admin = adminService.adminLogin(account,password);
        if (admin == null) {
//            map.put("success", false);
//            map.put("message", "密码错误");
            message = "密码错误";
            return CommonUtil.ToResultHashMap(status,message,data);
        }

        // 设置session
        session.setAttribute(WebSecurityConfig.SESSION_KEY, account);
        Cookie c = new Cookie("account",account);
        response.addCookie(c);
        c = new Cookie("name",admin.getName());
        response.addCookie(c);
//        session.getId()

        List<Map> resultList = menuService.getAdminParentMenu(admin.getRoleId());
        for (Map pMenu : resultList){
            //select m.id,m.title from menu m,menu_role mr where mr.menu_id = m.id and m.`status` = 1 and m.type = 2 and m.parent_menu_no = ? and mr.role_id = ? and mr.`status` = 1
            pMenu.put("children",menuService.getAdminSonMenu(CommonUtil.getStr(pMenu.get("menuNo"),""),admin.getRoleId()));
        }

        adminMsg.put("admin",admin);
        adminMsg.put("menu",resultList);
        redisComponentUtil.set(session.getId(),adminMsg);
//        map.put("success", true);
//        map.put("message", "登录成功");
        status = MessageConstant.SUCCESS_CODE;
        message = "登录成功";
        return CommonUtil.ToResultHashMap(status,message,data);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        redisComponentUtil.delete(session.getId());
        return "redirect:/login";
    }

    @GetMapping("/changePwd")
    public String changePwd(HttpSession session) {
        return "changePwd";
    }

    public void setAdminMsg(Model m, HttpServletRequest request,HttpSession session){
        HashMap<String,Object> adminMsg = (HashMap<String,Object>)redisComponentUtil.get(session.getId());
        m.addAttribute("menu",adminMsg.get("menu"));
//        Cookie[] cookies = request.getCookies();
//        String cookieValue = null;
//        if (null != cookies) {
//            for (Cookie cookie : cookies) {
//                System.out.println("cookie::"+cookie.getName()+"::::"+cookie.getValue());
//            }
//        }
    }
}