package com.zy.controller;

import com.github.pagehelper.PageHelper;
import com.zy.domain.Category;
import com.zy.domain.Menu;
import com.zy.domain.Stage;
import com.zy.domain.User;
import com.zy.domain.vo.StageAuditVo;
import com.zy.domain.vo.StageRequestVo;
import com.zy.service.*;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/stage")
public class StageController {

    @Autowired
    private IMenuService menuService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRequestService userRequestService;
    @Autowired
    private IStageService stageService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    public RedisComponentUtil redisComponentUtil;


    /**
     *获取场景列表
     *管理后台查询场景列表
     */
    @RequestMapping(value = "/getStageList")
    public String getStageList(StageRequestVo vo,Model m, HttpServletRequest request, HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        if(vo.getProcessStatus() == 0){vo.setProcessStatus(-500);}
        List<Stage> resultList = stageService.getStageList(vo);
        List<Category> category1stList = categoryService.getStage1stCategory();
        if(resultList != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        PageBean<Stage> list = new PageBean<Stage>(resultList);
        m.addAttribute("stageList",list);
        m.addAttribute("stageRequestVo",vo);
        m.addAttribute("category1stList",category1stList);
        m.addAttribute("pageTitle","场景管理");
        setAdminMsg(m, request, session);
        return "stageListPage";
    }
    /**
     *管理后台新增场景
     */
    @RequestMapping(value = "/newStage")
    public String newStage(Model m,HttpServletRequest request,HttpSession session) {
        List<Category> category1stList = categoryService.getStage1stCategory();
        m.addAttribute("category1stList",category1stList);
        m.addAttribute("pageTitle","场景管理");
        setAdminMsg(m, request, session);
        return "newStagePage";
    }

    /**
     *新增场景
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/insertStage")
    @ResponseBody
    public HashMap<String,Object> insertStage(Stage s, HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();

        try {
            if(s!=null){
                Date d = new Date();
                s.setCreateTime(d);
//                s.setUpdateTime(d);
//                exists.setOperaterId();=======================================================
                s.setStatus(1);
                int result = stageService.insertStage(s);
                if (result == 1){
                    status = MessageConstant.SUCCESS_CODE;
                    message = MessageConstant.SUCCESS_INFO;
                }else{
                    throw new RuntimeException();
                }
            }

        } catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }

    /**
     *获取场景详情
     *管理后台查询场景详情
     */
    @RequestMapping(value = "/getStageDetail")
    public String getStageDetail(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int id = Integer.parseInt(CommonUtil.getStr(request.getParameter("id"),"-500"));
        Stage stage = stageService.getStage(id);
        if(stage != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        m.addAttribute("stageDetail",stage);
        m.addAttribute("pageTitle","场景管理");
        setAdminMsg(m, request, session);
        return "stageDetailPage";
    }
    /**
     *获取场景审核详情
     *管理后台查询场景审核详情
     */
    @RequestMapping(value = "/getStageAuditDetail")
    public String getStageAuditDetail(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int id = Integer.parseInt(CommonUtil.getStr(request.getParameter("id"),"-500"));
        StageAuditVo stage = stageService.getStageAudit(id);
        if(stage != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        m.addAttribute("stageAuditDetail",stage);
        m.addAttribute("pageTitle","场景管理");
        setAdminMsg(m, request, session);
        return "stageAuditDetailPage";
    }


    /**
     *获取场景审核列表
     *管理后台查询场景审核列表
     */
    @RequestMapping(value = "/getStageAuditList")
    public String getStageAuditList(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        int processStatus = Integer.parseInt(CommonUtil.getStr(request.getParameter("processStatus"),"-500"));
        String idOrName = CommonUtil.getStr(request.getParameter("idOrName"),"");
        List<Map> resultList = stageService.getUserStageList(-500,processStatus,idOrName);
        if(resultList != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        PageBean<Map> list = new PageBean<Map>(resultList);
        m.addAttribute("stageAuditList",list);
        m.addAttribute("processStatus",processStatus);
        m.addAttribute("idOrName",idOrName);
        m.addAttribute("pageTitle","场景管理");
        setAdminMsg(m, request, session);
        return "stageAuditListPage";
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
