package com.zy.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zy.domain.Admin;
import com.zy.domain.Category;
import com.zy.domain.Menu;
import com.zy.enums.CategoryType;
import com.zy.service.IAdminService;
import com.zy.service.ICategoryService;
import com.zy.service.IMenuService;
import com.zy.util.CommonUtil;
import com.zy.util.PageBean;
import com.zy.util.RedisComponentUtil;
import com.zy.util.constant.MessageConstant;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
@RequestMapping("/zhuYi")
public class ZhuYiController {

    @Autowired
    private IMenuService menuService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IAdminService adminService;
    @Autowired
    public RedisComponentUtil redisComponentUtil;

    /**
     *新增菜单数据
     *手动插入菜单方法
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/insertMenu")
    public HashMap<String,Object> insertMenu(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        String title = CommonUtil.getStr(request.getParameter("title"),"");
        if(title == null || title.equals("")){return CommonUtil.ToResultHashMap(status,"title为空!",null);}
        String menuNo = CommonUtil.getStr(request.getParameter("menuNo"),"");
        if(menuNo == null || menuNo.equals("")){return CommonUtil.ToResultHashMap(status,"menuNo为空!",null);}
        String parentMenuNo = CommonUtil.getStr(request.getParameter("parentMenuNo"),"");
//        if(parentMenuNo == null || parentMenuNo.equals("")){return CommonUtil.ToResultHashMap(status,"parentMenuNo为空!",null);}
        //菜单级别	菜单级别(1父,,,2子)
        int type = Integer.parseInt(CommonUtil.getStr(request.getParameter("type"),"-500"));
        if(type == -500){return CommonUtil.ToResultHashMap(status,"type为空!",null);}

        try {
            Menu m = new Menu();
            m.setTitle(title);
            m.setMenuNo(menuNo);
            if(!parentMenuNo.equals("")){
                m.setParentMenuNo(parentMenuNo);
            }
            m.setType(type);
            m.setCreateTime(new Date());
            m.setStatus(1);
            int result = menuService.insertMenu(m);
            if (result == 1){
                status = MessageConstant.SUCCESS_CODE;
                message = MessageConstant.SUCCESS_INFO;
            }else{
                throw new RuntimeException();
            }

        } catch (Exception e){
            e.printStackTrace();
//            logger.error("新增菜单数据异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }

    /**
     *管理员列表查询
     *管理后台查询管理员列表
     */
    @RequestMapping(value = "/getAdminList")
    public String getAdminList(Model m, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        String accountOrName = CommonUtil.getStr(request.getParameter("accountOrName"),"");
        List<Map> resultList = adminService.getAdminList(accountOrName);
        PageBean<Map> list = new PageBean<Map>(resultList);
        m.addAttribute("adminList",list);
        m.addAttribute("accountOrName",accountOrName);
        m.addAttribute("pageTitle","权限管理");
        setAdminMsg(m, request, session);

//        session.setAttribute("auth_token","abcde");
//        String token = session.getAttribute("user").toString();


//        Admin a = (Admin) adminMsg.get("admin");
//        System.out.println("menu:::"+adminMsg.get("menu").toString());



//        System.out.println("Admin::"+a.toString());
//        System.out.println("tokenId::"+session.getId());
//        System.out.println("token::"+token);




        return "newPage";
//        return CommonUtil.ToResultHashMap(status,message,list);
    }
    /**
     *新增&编辑管理员
     *新增或修改管理员提交时调用接口
     */
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @RequestMapping(value = "/createAndUpdateAdmin")
    public HashMap<String,Object> createAndUpdateAdmin(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int id = Integer.parseInt(CommonUtil.getStr(request.getParameter("id"),"-500"));
        String account = CommonUtil.getStr(request.getParameter("account"),"");
        if(account == null || account.equals("")){return CommonUtil.ToResultHashMap(status,"account为空!",null);}
        String name = CommonUtil.getStr(request.getParameter("name"),"");
        if(name == null || name.equals("")){return CommonUtil.ToResultHashMap(status,"name为空!",null);}
        String password = CommonUtil.getStr(request.getParameter("password"),"");
        if(password == null || password.equals("")){return CommonUtil.ToResultHashMap(status,"password为空!",null);}
        int roleId = Integer.parseInt(CommonUtil.getStr(request.getParameter("roleId"),"-500"));
        if(roleId == -500){return CommonUtil.ToResultHashMap(status,"roleId为空!",null);}
        try {
            Admin a = new Admin();
            if (id != -500){
                a = adminService.getAdmin(id);
            }
            a.setAccStatus(1);
            a.setAccount(account);
            a.setName(name);
            a.setPassword(password);
            a.setRoleId(roleId);
            a.setCreateTime(new Date());
            a.setUpdateTime(new Date());
            a.setStatus(1);
            int result = 0;
            if(id != -500){
                result = adminService.updateAdmin(a);
            }else{
                result = adminService.insertAdmin(a);
            }
            if(result == 1){
                status = MessageConstant.SUCCESS_CODE;
                message = MessageConstant.SUCCESS_INFO;
            }
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("新增&编辑管理员异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *根据Id获取管理员信息
     *获取管理员信息(编辑用)
     */
    @ResponseBody
    @RequestMapping(value = "/getAdminMsgById")
    public HashMap<String,Object> getAdminMsgById(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int id = Integer.parseInt(CommonUtil.getStr(request.getParameter("id"),"-500"));
        if(id == -500){return CommonUtil.ToResultHashMap(status,"id为空!",null);}
        Admin a = adminService.getAdmin(id);
        if(a != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        return CommonUtil.ToResultHashMap(status,message,a);
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
