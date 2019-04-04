package com.zy.controller;

import com.github.pagehelper.PageHelper;
import com.zy.domain.*;
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
import java.util.*;


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
    private IRecommendationService recommendationService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IRecommendationStageService recommendationStageService;
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

    /**
     *根据需求Id获取推荐记录
     */
    @RequestMapping(value = "/getRecommendationListByReqId")
    public String getRecommendationListByReqId(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        int reqId = Integer.parseInt(CommonUtil.getStr(request.getParameter("reqId"),"-500"));
        List<Map> resultList = recommendationService.getRecommendListById(reqId);
        if(resultList != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        PageBean<Map> list = new PageBean<Map>(resultList);
        m.addAttribute("recommendationList",list);
        m.addAttribute("reqId",reqId);
        m.addAttribute("pageTitle","需求管理");
        setAdminMsg(m, request, session);
        return "recommendationListPage";
    }

    /**
     *根据推荐记录获取推荐详情
     */
    @RequestMapping(value = "/getRecommendationDetail")
    public String getRecommendationDetail(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int reqId = Integer.parseInt(CommonUtil.getStr(request.getParameter("reqId"),"-500"));
        int type = Integer.parseInt(CommonUtil.getStr(request.getParameter("type"),"1"));//1返回详情页,,2返回编辑页
        List<Map> recDetail = recommendationService.getRecommendationDetail(reqId);
        List<Object> stageList = new ArrayList();
        if(recDetail != null){
            for(Map r : recDetail){
                PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
                List<Map> stages = recommendationService.getRecommendStageList(Integer.parseInt(CommonUtil.getStr(r.get("id"),"0")));
                PageBean<Map> list = new PageBean<Map>(stages);
                stageList.add(list);
            }
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        m.addAttribute("recommendationDetail",recDetail);
        m.addAttribute("stageList",stageList);
        m.addAttribute("pageTitle","需求管理");
        setAdminMsg(m, request, session);
        if(type == 1){
            return "recommendationDetailPage";
        }else{
            return "updateRecommendationPage";
        }
    }

    /**
     *管理后台获新增剧组服务
     */
    @RequestMapping(value = "/newRecommendation")
    public String newRecommendation(Model m,HttpServletRequest request,HttpSession session) {
        m.addAttribute("pageTitle","需求管理");
        setAdminMsg(m, request, session);
        return "newRecommendationPage";
    }
    /**
     *用户需求保存场景
     */
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/saveRecommendationStage")
    public HashMap<String,Object> saveRecommendationStage(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();

        int rId = Integer.parseInt(CommonUtil.getStr(request.getParameter("rId"),"-500"));
        if(rId == -500){return CommonUtil.ToResultHashMap(status,"rId为空!",null);}
        String stages = CommonUtil.getStr(request.getParameter("stages"),"");
        if(stages.equals("")){return CommonUtil.ToResultHashMap(status,"stages为空!",null);}
        Admin a = getAdminMsg(session);
        try {
            int result = 0;
            String[] stageIds = stages.split(",");
            for(String s : stageIds){
                RecommendationStage r = new RecommendationStage();
                Date d = new Date();
                r.setOperateAdminId(a.getId());
                r.setRecStageTypeId(rId);
                r.setStatus(1);
                r.setStageId(Integer.parseInt(s));
                r.setCreateTime(d);
                result += recommendationStageService.insertRecommendationStage(r);
            }

            if(result == stageIds.length){
                status = MessageConstant.SUCCESS_CODE;
                message = MessageConstant.SUCCESS_INFO;
            }else{
                throw new RuntimeException();
            }
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("删除管理员账号异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *用户需求删除场景分类场景
     */
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/deleteRecommendationStage")
    public HashMap<String,Object> deleteRecommendationStage(HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();

        int id = Integer.parseInt(CommonUtil.getStr(request.getParameter("id"),"-500"));
        if(id == -500){return CommonUtil.ToResultHashMap(status,"id为空!",null);}
        Admin a = getAdminMsg(session);
        try {
                RecommendationStage r =recommendationStageService.getRecommendationStage(id);
                r.setOperateAdminId(a.getId());
                r.setStatus(0);
                int result = recommendationStageService.updateRecommendationStage(r);
            if(result == 1){
                status = MessageConstant.SUCCESS_CODE;
                message = MessageConstant.SUCCESS_INFO;
            }else{
                throw new RuntimeException();
            }
        } catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *用户需求添加场景
     */
    @RequestMapping(value = "/addRecommendationStage")
    public String addRecommendationStage(StageRequestVo vo, Model m, int type, int reqId, int id, HttpServletRequest request, HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        vo.setProcessStatus(2);
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
        m.addAttribute("pageTitle","需求管理");
        m.addAttribute("type",type);
        m.addAttribute("reqId",reqId);
        m.addAttribute("id",id);
        setAdminMsg(m, request, session);
        return "requestAddStagePage";
    }

    /**
     *需求推荐新增场景分类
     */
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/addRecommendation")
    public HashMap<String,Object> addRecommendation(HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();

        int reqId = Integer.parseInt(CommonUtil.getStr(request.getParameter("reqId"),"-500"));
        if(reqId == -500){return CommonUtil.ToResultHashMap(status,"reqId为空!",null);}
        int userId = Integer.parseInt(CommonUtil.getStr(request.getParameter("userId"),"-500"));
        if(userId == -500){return CommonUtil.ToResultHashMap(status,"userId为空!",null);}
        Admin a = getAdminMsg(session);
        try {
            Recommendation r = new Recommendation();
            Date d = new Date();
            r.setOperateAdminId(a.getId());
            r.setStatus(1);
            r.setCreateTime(d);
            r.setUpdateTime(d);
            r.setRecommendStatus(2);
            r.setUserId(userId);
            r.setReqId(reqId);
            int result = recommendationService.insertRecommendation(r);

            if(result == 1){
                status = MessageConstant.SUCCESS_CODE;
                message = MessageConstant.SUCCESS_INFO;
            }else{
                throw new RuntimeException();
            }
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("删除管理员账号异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *更新推荐
     */
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/updateRecommendation")
    public HashMap<String,Object> updateRecommendation(HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();

        int id = Integer.parseInt(CommonUtil.getStr(request.getParameter("id"),"-500"));
        if(id == -500){return CommonUtil.ToResultHashMap(status,"id为空!",null);}
        Admin a = getAdminMsg(session);
        try {
            Recommendation r = recommendationService.getRecommendation(id);
            Date d = new Date();
            if(request.getParameter("stageTitleCh") != null){
                r.setStageTitleCh(CommonUtil.getStr(request.getParameter("stageTitleCh"),""));
            }else if(request.getParameter("stageTitleEn") != null){
                r.setStageTitleEn(CommonUtil.getStr(request.getParameter("stageTitleEn"),""));
            }
            r.setOperateAdminId(a.getId());
            r.setUpdateTime(d);
            int result = recommendationService.updateRecommendation(r);
            if(result == 1){
                status = MessageConstant.SUCCESS_CODE;
                message = MessageConstant.SUCCESS_INFO;
            }else{
                throw new RuntimeException();
            }
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("删除管理员账号异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *用户需求删除场景分类
     */
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/deleteRecommendation")
    public HashMap<String,Object> deleteRecommendation(HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();

        int id = Integer.parseInt(CommonUtil.getStr(request.getParameter("id"),"-500"));
        if(id == -500){return CommonUtil.ToResultHashMap(status,"id为空!",null);}
        Admin a = getAdminMsg(session);
        try {
            //先删除级联的子推荐场景
            recommendationStageService.updateRecommendationStageCase(a.getId(),id);
            Recommendation r =recommendationService.getRecommendation(id);
            r.setUpdateTime(new Date());
            r.setOperateAdminId(a.getId());
            r.setStatus(0);
            int result = recommendationService.updateRecommendation(r);
            if(result == 1){
                status = MessageConstant.SUCCESS_CODE;
                message = MessageConstant.SUCCESS_INFO;
            }else{
                throw new RuntimeException();
            }
        } catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
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
    public Admin getAdminMsg(HttpSession session){
        HashMap<String,Object> adminMsg = (HashMap<String,Object>)redisComponentUtil.get(session.getId());
        Admin a = (Admin) adminMsg.get("admin");
        return a;
    }
}
