package com.zy.controller;

import com.github.pagehelper.PageHelper;
import com.zy.domain.Menu;
import com.zy.domain.User;
import com.zy.domain.UserRequest;
import com.zy.enums.CategoryType;
import com.zy.service.*;
import com.zy.util.CommonUtil;
import com.zy.util.PageBean;
import com.zy.util.RedisComponentUtil;
import com.zy.util.constant.MessageConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IMenuService menuService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRequestService userRequestService;
    @Autowired
    private IStageService stageService;
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
     *获取用户列表
     *管理后台查询用户列表
     */
    @RequestMapping(value = "/getUserList")
    public String getUserList(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        String nameOrAccount = CommonUtil.getStr(request.getParameter("nameOrAccount"),"");
        //起始时间	格式(yyyy-MM-dd)
        String startTime = CommonUtil.getStr(request.getParameter("startTime"),"");
        //截止时间	格式(yyyy-MM-dd)
        String endTime = CommonUtil.getStr(request.getParameter("endTime"),"");
        List<User> resultList = userService.getUserList(nameOrAccount,startTime,endTime);
        if(resultList != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        PageBean<User> list = new PageBean<User>(resultList);
        m.addAttribute("userList",list);
        m.addAttribute("nameOrAccount",nameOrAccount);
        m.addAttribute("startTime",startTime);
        m.addAttribute("endTime",endTime);
        m.addAttribute("pageTitle","用户管理");
        setAdminMsg(m, request, session);
        return "usersPage";
    }

    /**
     *获取用户需求列表
     *管理后台查询用户需求列表
     */
    @RequestMapping(value = "/getUserRequestList")
    public String getUserRequestList(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        int userId = Integer.parseInt(CommonUtil.getStr(request.getParameter("userId"),"-500"));
        int reqStatus = Integer.parseInt(CommonUtil.getStr(request.getParameter("reqStatus"),"-500"));
        String filmName = CommonUtil.getStr(request.getParameter("filmName"),"");
        //起始时间	格式(yyyy-MM-dd)
        String startTime = CommonUtil.getStr(request.getParameter("startTime"),"");
        //截止时间	格式(yyyy-MM-dd)
        String endTime = CommonUtil.getStr(request.getParameter("endTime"),"");
        List<Map> resultList = userRequestService.getUserRequestList(userId,reqStatus,filmName,startTime,endTime);
        if(resultList != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        PageBean<Map> list = new PageBean<Map>(resultList);
        m.addAttribute("userRequestList",list);
        m.addAttribute("userId",userId);
        m.addAttribute("reqStatus",reqStatus);
        m.addAttribute("filmName",filmName);
        m.addAttribute("startTime",startTime);
        m.addAttribute("endTime",endTime);
        m.addAttribute("pageTitle","用户管理");
        setAdminMsg(m, request, session);
        return "userRequestPage";
    }

    /**
     *获取用户场景列表
     *管理后台查询用户场景列表
     */
    @RequestMapping(value = "/getUserStageList")
    public String getUserStageList(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        int userId = Integer.parseInt(CommonUtil.getStr(request.getParameter("userId"),"-500"));
        int processStatus = Integer.parseInt(CommonUtil.getStr(request.getParameter("processStatus"),"-500"));
        String idOrName = CommonUtil.getStr(request.getParameter("idOrName"),"");
        List<Map> resultList = stageService.getUserStageList(userId,processStatus,idOrName);
        if(resultList != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        PageBean<Map> list = new PageBean<Map>(resultList);
        m.addAttribute("userStageList",list);
        m.addAttribute("userId",userId);
        m.addAttribute("processStatus",processStatus);
        m.addAttribute("idOrName",idOrName);
        m.addAttribute("pageTitle","用户管理");
        setAdminMsg(m, request, session);
        return "userStagePage";
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
