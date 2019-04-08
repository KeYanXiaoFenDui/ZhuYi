package com.zy.controller;

import com.zy.domain.Collect;
import com.zy.domain.Scenario;
import com.zy.domain.UserStageType;
import com.zy.service.ICollectService;
import com.zy.service.IScenarioService;
import com.zy.service.IUserStageTypeService;
import com.zy.util.CommonUtil;
import com.zy.util.constant.MessageConstant;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/pubapi")
public class PubController {
    @Autowired
    private IScenarioService iScenarioService;
    @Autowired
    private IUserStageTypeService iUserStageTypeService;
    @Autowired
    private ICollectService iCollectService;

    private static final Logger logger= LoggerFactory.getLogger(SwaggerDemoController.class);
    /**
     *收藏>获取剧本列表
     *C端用户获取剧本列表
     */
    @RequestMapping(value = "/scenarioList")
    public HashMap<String,Object> scenarioList(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        int userId = Integer.parseInt(CommonUtil.getStr(request.getParameter("userId"),"-500"));
        if(userId == -500){return CommonUtil.ToResultHashMap(status,"userId为空!",null);}
        List<Scenario> scenarioList = iScenarioService.getScenarioByUserId(userId);
        return CommonUtil.ToResultHashMap(status,message,scenarioList);
    }
    /**
     *获取用户场景分类
     *C端用户根据剧本Id获取用户场景分类
     */
    @RequestMapping(value = "/getUserStageType")
    public HashMap<String,Object> getUserStageType(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        int scenarioId = Integer.parseInt(CommonUtil.getStr(request.getParameter("scenarioId"),"-500"));
        if(scenarioId == -500){return CommonUtil.ToResultHashMap(status,"scenarioId为空!",null);}
        List<UserStageType> stageTypeList = iUserStageTypeService.getUserStageTypeByScenarioId(scenarioId);
        return CommonUtil.ToResultHashMap(status,message,stageTypeList);
    }
    /**
     *新增剧本
     *C端用户新增剧本方法
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/insertScenario")
    public HashMap<String,Object> insertScenario(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        String scenarioName = CommonUtil.getStr(request.getParameter("scenarioName"),"");
        if(StringUtils.isBlank(scenarioName)){return CommonUtil.ToResultHashMap(status,"scenarioName为空!",null);}
        try {
            Scenario scenario = new Scenario();
            scenario.setScenarioName(scenarioName);
            scenario.setCreateTime(new Date());
            scenario.setStatus(1);
            scenario.setUpdateTime(new Date());
            int result = iScenarioService.insertScenario(scenario);
            if (result == 1){
                status = MessageConstant.SUCCESS_CODE;
                message = MessageConstant.SUCCESS_INFO;
            }else{
                throw new RuntimeException();
            }
        } catch (Exception e){
            e.printStackTrace();
            logger.error("新增剧本异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *修改剧本名称
     *C端用户修改剧本名称方法
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/updateScenario")
    public HashMap<String,Object> updateScenario(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        String scenarioName = CommonUtil.getStr(request.getParameter("scenarioName"),"");
        if(StringUtils.isBlank(scenarioName)){return CommonUtil.ToResultHashMap(status,"scenarioName为空!",null);}
        int scenarioId = Integer.parseInt(CommonUtil.getStr(request.getParameter("scenarioId"),"-500"));
        if(scenarioId == -500){return CommonUtil.ToResultHashMap(status,"scenarioId为空!",null);}
        try {
            Scenario scenario = iScenarioService.getScenario(scenarioId);
            if(scenario!=null){
                scenario.setScenarioName(scenarioName);
                scenario.setUpdateTime(new Date());
                iScenarioService.updateScenario(scenario);
            }

        } catch (Exception e){
            e.printStackTrace();
            logger.error("修改剧本名称异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *删除剧本
     *C端删除剧本
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/deleteScenario")
    public HashMap<String,Object> deleteScenario(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int scenarioId = Integer.parseInt(CommonUtil.getStr(request.getParameter("scenarioId"),"-500"));
        if(scenarioId == -500){return CommonUtil.ToResultHashMap(status,"scenarioId为空!",null);}
        int userId = Integer.parseInt(CommonUtil.getStr(request.getParameter("userId"),"-500"));
        if(userId == -500){return CommonUtil.ToResultHashMap(status,"userId为空!",null);}
        try {
            iCollectService.deleteCollect(userId,scenarioId);
            iUserStageTypeService.deleteUserStageType(userId,scenarioId);
            iScenarioService.deleteScenario(scenarioId);
        } catch (Exception e){
            e.printStackTrace();
            logger.error("删除剧本异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *新增用户场景分类
     *C端用户新增用户场景分类
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/insertUserStageType")
    public HashMap<String,Object> insertUserStageType(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        String userStageTypeName = CommonUtil.getStr(request.getParameter("userStageTypeName"),"");
        if(StringUtils.isBlank(userStageTypeName)){return CommonUtil.ToResultHashMap(status,"userStageTypeName为空!",null);}

        try {
            UserStageType type = new UserStageType();
            type.setTypeName(userStageTypeName);
            type.setCreateTime(new Date());
            type.setStatus(1);
            type.setUpdateTime(new Date());
            iUserStageTypeService.updateUserStageType(type);

        } catch (Exception e){
            e.printStackTrace();
            logger.error("新增用户场景分类：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *修改用户场景分类名称
     *C端用户修改用户场景分类名称
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/updateUserStageType")
    public HashMap<String,Object> updateUserStageType(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        String userStageTypeName = CommonUtil.getStr(request.getParameter("userStageTypeName"),"");
        if(StringUtils.isBlank(userStageTypeName)){return CommonUtil.ToResultHashMap(status,"userStageTypeName为空!",null);}
        int userStageTypeId = Integer.parseInt(CommonUtil.getStr(request.getParameter("userStageTypeId"),"-500"));
        if(userStageTypeId == -500){return CommonUtil.ToResultHashMap(status,"userStageTypeId为空!",null);}
        try {
            UserStageType type = iUserStageTypeService.getUserStageType(userStageTypeId);
            if(type!=null){
                type.setTypeName(userStageTypeName);
                type.setUpdateTime(new Date());
                iUserStageTypeService.updateUserStageType(type);
            }

        } catch (Exception e){
            e.printStackTrace();
            logger.error("修改用户场景分类名称异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *删除用户场景分类
     *C端删除用户场景分类
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/deleteUserStageType")
    public HashMap<String,Object> deleteUserStageType(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int userStageTypeId = Integer.parseInt(CommonUtil.getStr(request.getParameter("userStageTypeId"),"-500"));
        if(userStageTypeId == -500){return CommonUtil.ToResultHashMap(status,"userStageTypeId为空!",null);}
        int userId = Integer.parseInt(CommonUtil.getStr(request.getParameter("userId"),"-500"));
        if(userId == -500){return CommonUtil.ToResultHashMap(status,"userId为空!",null);}
        int scenarioId = Integer.parseInt(CommonUtil.getStr(request.getParameter("scenarioId"),"-500"));
        if(scenarioId == -500){return CommonUtil.ToResultHashMap(status,"scenarioId为空!",null);}
        try {
            iCollectService.deleteCollect(userId,scenarioId,userStageTypeId);
            iUserStageTypeService.deleteUserStageType(userStageTypeId);
        } catch (Exception e){
            e.printStackTrace();
            logger.error("删除用户场景分类异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }

    /**
     *收藏场景
     *C端用户收藏场景
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/insertCollect")
    public HashMap<String,Object> insertCollect(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int userId = Integer.parseInt(CommonUtil.getStr(request.getParameter("userId"),"-500"));
        if(userId == -500){return CommonUtil.ToResultHashMap(status,"userId为空!",null);}
        int scenarioId = Integer.parseInt(CommonUtil.getStr(request.getParameter("scenarioId"),"-500"));
        if(scenarioId == -500){return CommonUtil.ToResultHashMap(status,"scenarioId为空!",null);}
        int userStageTypeId = Integer.parseInt(CommonUtil.getStr(request.getParameter("userStageTypeId"),"-500"));
        if(userStageTypeId == -500){return CommonUtil.ToResultHashMap(status,"userStageTypeId为空!",null);}
        int stageId = Integer.parseInt(CommonUtil.getStr(request.getParameter("stageId"),"-500"));
        if(stageId == -500){return CommonUtil.ToResultHashMap(status,"stageId为空!",null);}
        try {
            Collect collect = new Collect();
            collect.setCreateTime(new Date());
            collect.setScenarioId(scenarioId);
            collect.setStageId(stageId);
            collect.setStatus(1);
            collect.setUserId(userId);
            collect.setUserStageTypeId(userStageTypeId);
            iCollectService.insertCollect(collect);
        } catch (Exception e){
            e.printStackTrace();
            logger.error("收藏场景：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *取消收藏场景
     *C端删除用户场景分类
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/deleteCollect")
    public HashMap<String,Object> deleteCollect(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int userId = Integer.parseInt(CommonUtil.getStr(request.getParameter("userId"),"-500"));
        if(userId == -500){return CommonUtil.ToResultHashMap(status,"userId为空!",null);}
        int scenarioId = Integer.parseInt(CommonUtil.getStr(request.getParameter("scenarioId"),"-500"));
        if(scenarioId == -500){return CommonUtil.ToResultHashMap(status,"scenarioId为空!",null);}
        int userStageTypeId = Integer.parseInt(CommonUtil.getStr(request.getParameter("userStageTypeId"),"-500"));
        if(userStageTypeId == -500){return CommonUtil.ToResultHashMap(status,"userStageTypeId为空!",null);}
        int stageId = Integer.parseInt(CommonUtil.getStr(request.getParameter("stageId"),"-500"));
        if(stageId == -500){return CommonUtil.ToResultHashMap(status,"stageId为空!",null);}
        try {
            iCollectService.deleteCollect(userId,scenarioId,userStageTypeId,stageId);
        } catch (Exception e){
            e.printStackTrace();
            logger.error("取消收藏场景异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
}
