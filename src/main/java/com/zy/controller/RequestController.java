package com.zy.controller;

import com.github.pagehelper.PageHelper;
import com.zy.domain.Menu;
import com.zy.domain.User;
import com.zy.domain.UserRequest;
import com.zy.service.IMenuService;
import com.zy.service.IStageService;
import com.zy.service.IUserRequestService;
import com.zy.service.IUserService;
import com.zy.util.CommonUtil;
import com.zy.util.PageBean;
import com.zy.util.RedisComponentUtil;
import com.zy.util.constant.MessageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/req")
public class RequestController {

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
     *获取用户需求列表
     *管理后台查询用户需求列表
     */
    @RequestMapping(value = "/getRequestList")
    public String getRequestList(Model m,HttpServletRequest request,HttpSession session) {
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
        m.addAttribute("reqStatus",reqStatus);
        m.addAttribute("filmName",filmName);
        m.addAttribute("startTime",startTime);
        m.addAttribute("endTime",endTime);
        m.addAttribute("pageTitle","需求管理");
        setAdminMsg(m, request, session);
        return "requestPage";
    }


    /**
     *获取需求详情
     *管理后台查询需求详情
     */
    @RequestMapping(value = "/getRequestDetail")
    public String getRequestDetail(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int id = Integer.parseInt(CommonUtil.getStr(request.getParameter("id"),"-500"));
        UserRequest userRequest = userRequestService.getUserRequest(id);
        if(userRequest != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        m.addAttribute("userRequestDetail",userRequest);
        m.addAttribute("pageTitle","需求管理");
        setAdminMsg(m, request, session);
        return "requestDetailPage";
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
