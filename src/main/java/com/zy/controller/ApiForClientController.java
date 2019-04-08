package com.zy.controller;

import com.github.pagehelper.PageHelper;
import com.zy.domain.Admin;
import com.zy.domain.User;
import com.zy.service.IAdminService;
import com.zy.service.IMenuService;
import com.zy.service.IUserService;
import com.zy.util.CommonUtil;
import com.zy.util.PageBean;
import com.zy.util.RedisComponentUtil;
import com.zy.util.constant.MessageConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Api("C端相关接口controller")
public class ApiForClientController {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    public RedisComponentUtil redisComponentUtil;


    //=====================================================================================================================================================================================
    //=====================================================================================================================================================================================


    /**
     *C端手机号/邮箱登录
     *C端手机号或邮箱登录
     */

    @ApiOperation(value = "C端手机号/邮箱登录", notes = "C端手机号或邮箱登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账号", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "账号类型", paramType = "query", required = true, dataType = "String")
    })
    @RequestMapping(value = "/auth/loginForC",method= RequestMethod.GET)
    public HashMap<String,Object> loginForC(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        HashMap<String,Object> userMsg = new HashMap<>();
        String account = CommonUtil.getStr(request.getParameter("account"),"");
        if(account == null || account.equals("")){return CommonUtil.ToResultHashMap(status,"account为空!",null);}
        //类型	登录方式(1手机号,,2邮箱)
        int type = Integer.parseInt(CommonUtil.getStr(request.getParameter("type"),"-500"));
        if(type == -500){return CommonUtil.ToResultHashMap(status,"type为空!",null);}
        String password = CommonUtil.getStr(request.getParameter("password"),"");
        if(password == null || password.equals("")){return CommonUtil.ToResultHashMap(status,"password为空!",null);}

        User u = userService.loginForC(account,password);
        if (u == null) {
            message = "密码错误";
            return CommonUtil.ToResultHashMap(status,message,data);
        }

        // 设置session
        session.setAttribute(WebSecurityConfig.SESSION_KEY, account);
        Cookie c = new Cookie("account",account);
        response.addCookie(c);
        c = new Cookie("name",u.getUserName());
        response.addCookie(c);

        userMsg.put("user",u);
        redisComponentUtil.set(session.getId(),userMsg);
        status = MessageConstant.SUCCESS_CODE;
        message = "登录成功";
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *手机号注册
     *C端用手机号码注册账号
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "C端手机号注册", notes = "C端用手机号码注册账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "telephone", value = "手机账号", paramType = "body", required = true, dataType = "String"),
            @ApiImplicitParam(name = "checkCode", value = "验证码", paramType = "body", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "body", required = true, dataType = "String"),
            @ApiImplicitParam(name = "rePassword", value = "重复密码", paramType = "body", required = true, dataType = "String")
    })
    @RequestMapping(value = "/auth/telephoneRegister",method = RequestMethod.POST)
    public HashMap<String,Object> telephoneRegister(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        String telephone = CommonUtil.getStr(request.getParameter("telephone"),"");
        if(telephone == null || telephone.equals("")){return CommonUtil.ToResultHashMap(status,"telephone为空!",null);}
        int checkCode = Integer.parseInt(CommonUtil.getStr(request.getParameter("checkCode"),"-500"));
        if(checkCode == -500){return CommonUtil.ToResultHashMap(status,"checkCode为空!",null);}
        String password = CommonUtil.getStr(request.getParameter("password"),"");
        if(password == null || password.equals("")){return CommonUtil.ToResultHashMap(status,"password为空!",null);}
        String rePassword = CommonUtil.getStr(request.getParameter("rePassword"),"");
        if(rePassword == null || rePassword.equals("")){return CommonUtil.ToResultHashMap(status,"rePassword为空!",null);}
        try {
            //验证校验码逻辑
            //成功
            if(!password.equals(rePassword)){
                message = "两次输入密码不一致";
                return CommonUtil.ToResultHashMap(status,message,null);
            }
            User u = new User();
            u.setAccount(telephone);
            u.setAccStatus(2);
            u.setPassword(password);
            u.setUserName(telephone);
            u.setTelephone(telephone);
            Date d = new Date();
            u.setCreateTime(d);
            u.setUpdateTime(d);
            u.setStatus(1);
            int result = userService.insertUser(u);
            if(result == 1){
                message = MessageConstant.SUCCESS_INFO;
                status = MessageConstant.SUCCESS_CODE;
            }
            //校验码不正确
            //提示校验码不对
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("手机号注册异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *邮箱注册
     *C端用邮箱注册账号
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "C端邮箱注册", notes = "C端用邮箱注册账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mail", value = "邮箱账号", paramType = "body", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "body", required = true, dataType = "String"),
            @ApiImplicitParam(name = "rePassword", value = "重复密码", paramType = "body", required = true, dataType = "String")
    })
    @RequestMapping(value = "/mailRegister",method = RequestMethod.POST)
    public HashMap<String,Object> mailRegister(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        String mail = CommonUtil.getStr(request.getParameter("mail"),"");
        if(mail == null || mail.equals("")){return CommonUtil.ToResultHashMap(status,"mail为空!",null);}
        String password = CommonUtil.getStr(request.getParameter("password"),"");
        if(password == null || password.equals("")){return CommonUtil.ToResultHashMap(status,"password为空!",null);}
        String rePassword = CommonUtil.getStr(request.getParameter("rePassword"),"");
        if(rePassword == null || rePassword.equals("")){return CommonUtil.ToResultHashMap(status,"rePassword为空!",null);}
        try {
            if(!password.equals(rePassword)){
                message = "两次输入密码不一致";
                return CommonUtil.ToResultHashMap(status,message,null);
            }
            User u = new User();
            u.setAccount(mail);
            u.setAccStatus(2);
            u.setPassword(password);
            u.setUserName(mail);
            u.setMail(mail);
            Date d = new Date();
            u.setCreateTime(d);
            u.setUpdateTime(d);
            u.setStatus(1);
            int result = userService.insertUser(u);
            if(result == 1){
                message = MessageConstant.SUCCESS_INFO;
                status = MessageConstant.SUCCESS_CODE;
            }
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("邮箱注册异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *发送验证码
     *C端发送验证码方法
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "发送验证码", notes = "C端发送验证码方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "账号类型", paramType = "body", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "account", value = "账号", paramType = "body", required = true, dataType = "String")
    })
    @RequestMapping(value = "/sendCode",method = RequestMethod.POST)
    public HashMap<String,Object> sendCode(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        //类型	验证码类型(1手机验证码,,2邮箱验证码)
        int type = Integer.parseInt(CommonUtil.getStr(request.getParameter("type"),"-500"));
        if(type == -500){return CommonUtil.ToResultHashMap(status,"type为空!",null);}
        String account = CommonUtil.getStr(request.getParameter("account"),"");
        if(account == null || account.equals("")){return CommonUtil.ToResultHashMap(status,"account为空!",null);}
        try {
            if(type == 1){//发生手机验证码

            }else if(type == 2){//发送邮箱验证码

            }
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("发生验证码异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *找回密码
     *C端找回密码方法
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "找回密码", notes = "C端找回密码方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账号类型", paramType = "body", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "type", value = "账号", paramType = "body", required = true, dataType = "String"),
            @ApiImplicitParam(name = "code", value = "账号", paramType = "body", required = true, dataType = "String")
    })
    @RequestMapping(value = "/findPassword",method = RequestMethod.POST)
    public HashMap<String,Object> findPassword(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        String account = CommonUtil.getStr(request.getParameter("account"),"");
        if(account == null || account.equals("")){return CommonUtil.ToResultHashMap(status,"account为空!",null);}
        //类型	找回密码账号类型(1手机号码,,2邮箱)
        int type = Integer.parseInt(CommonUtil.getStr(request.getParameter("type"),"-500"));
        if(type == -500){return CommonUtil.ToResultHashMap(status,"type为空!",null);}
        int code = Integer.parseInt(CommonUtil.getStr(request.getParameter("code"),"-500"));
        if(code == -500){return CommonUtil.ToResultHashMap(status,"code为空!",null);}
        try {
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("找回密码异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *设置新密码
     *C端重新设置密码
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/setNewPassword")
    public HashMap<String,Object> setNewPassword(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        String newPassword = CommonUtil.getStr(request.getParameter("newPassword"),"");
        if(newPassword == null || newPassword.equals("")){return CommonUtil.ToResultHashMap(status,"newPassword为空!",null);}
        String reNewPassword = CommonUtil.getStr(request.getParameter("reNewPassword"),"");
        if(reNewPassword == null || reNewPassword.equals("")){return CommonUtil.ToResultHashMap(status,"reNewPassword为空!",null);}
        try {
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("设置新密码异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *微信登录
     *C端根据微信扫码登录
     */
    @RequestMapping(value = "/wxLogin")
    public HashMap<String,Object> wxLogin(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *微信登录并绑定账号
     *C端微信账号绑定用户账号
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/wxBindingAccount")
    public HashMap<String,Object> wxBindingAccount(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        String account = CommonUtil.getStr(request.getParameter("account"),"");
        if(account == null || account.equals("")){return CommonUtil.ToResultHashMap(status,"account为空!",null);}
        //类型	账号类型(1手机号码,,,2邮箱)
        int type = Integer.parseInt(CommonUtil.getStr(request.getParameter("type"),"-500"));
        if(type == -500){return CommonUtil.ToResultHashMap(status,"type为空!",null);}
        String password = CommonUtil.getStr(request.getParameter("password"),"");
        if(password == null || password.equals("")){return CommonUtil.ToResultHashMap(status,"password为空!",null);}
        try {
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("微信登录并绑定账号异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *获取轮播图
     *C端首页获取轮播图
     */
    @RequestMapping(value = "/getRotationPicForC")
    public HashMap<String,Object> getRotationPicForC(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *场景搜索
     *C端获取场景列表
     */
    @RequestMapping(value = "/getStageListForC")
    public HashMap<String,Object> getStageListForC(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        int country = Integer.parseInt(CommonUtil.getStr(request.getParameter("country"),"-500"));
        int province = Integer.parseInt(CommonUtil.getStr(request.getParameter("province"),"-500"));
        int city = Integer.parseInt(CommonUtil.getStr(request.getParameter("city"),"-500"));
        int filmType = Integer.parseInt(CommonUtil.getStr(request.getParameter("filmType"),"-500"));
        int stageMainType = Integer.parseInt(CommonUtil.getStr(request.getParameter("stageMainType"),"-500"));
        int stageSubType = Integer.parseInt(CommonUtil.getStr(request.getParameter("stageSubType"),"-500"));
        int stageStyle = Integer.parseInt(CommonUtil.getStr(request.getParameter("stageStyle"),"-500"));
        //标题或内容	根据场景标题或者内容模糊搜索场景
        String titleOrContent = CommonUtil.getStr(request.getParameter("titleOrContent"),"");
//        PageBean<Map> list = new PageBean<Map>(resultList);
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *数据字典
     *C端场景数据相关类目字典
     */
    @RequestMapping(value = "/stageCategory")
    public HashMap<String,Object> stageCategory(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *场景详情
     *C端获取场景详情信息
     */
    @RequestMapping(value = "/stageDetailForC")
    public HashMap<String,Object> stageDetailForC(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int stageId = Integer.parseInt(CommonUtil.getStr(request.getParameter("stageId"),"-500"));
        if(stageId == -500){return CommonUtil.ToResultHashMap(status,"stageId为空!",null);}
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *收藏>新建剧本
     *C端新建收藏剧本
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/newScript")
    public HashMap<String,Object> newScript(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        String scriptTitle = CommonUtil.getStr(request.getParameter("scriptTitle"),"");
        if(scriptTitle == null || scriptTitle.equals("")){return CommonUtil.ToResultHashMap(status,"scriptTitle为空!",null);}
        try {
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("收藏>新建剧本异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *收藏>新建场景分类
     *C端新建收藏场景分类
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/newStageType")
    public HashMap<String,Object> newStageType(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int scriptId = Integer.parseInt(CommonUtil.getStr(request.getParameter("scriptId"),"-500"));
        if(scriptId == -500){return CommonUtil.ToResultHashMap(status,"scriptId为空!",null);}
        String stageTypeTitle = CommonUtil.getStr(request.getParameter("stageTypeTitle"),"");
        if(stageTypeTitle == null || stageTypeTitle.equals("")){return CommonUtil.ToResultHashMap(status,"stageTypeTitle为空!",null);}
        try {
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("收藏>新建场景分类异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *收藏>收藏场景
     *C端收藏喜欢的场景
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/collectStage")
    public HashMap<String,Object> collectStage(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int scriptId = Integer.parseInt(CommonUtil.getStr(request.getParameter("scriptId"),"-500"));
        if(scriptId == -500){return CommonUtil.ToResultHashMap(status,"scriptId为空!",null);}
        int stageTypeId = Integer.parseInt(CommonUtil.getStr(request.getParameter("stageTypeId"),"-500"));
        if(stageTypeId == -500){return CommonUtil.ToResultHashMap(status,"stageTypeId为空!",null);}
        int stageId = Integer.parseInt(CommonUtil.getStr(request.getParameter("stageId"),"-500"));
        if(stageId == -500){return CommonUtil.ToResultHashMap(status,"stageId为空!",null);}
        String stageName = CommonUtil.getStr(request.getParameter("stageName"),"");
        if(stageName == null || stageName.equals("")){return CommonUtil.ToResultHashMap(status,"stageName为空!",null);}
        try {
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("收藏>收藏场景异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *更多服务
     *C端获取更多服务
     */
    @RequestMapping(value = "/moreService")
    public HashMap<String,Object> moreService(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *C端发布需求
     *C端用户发布需求
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/publishRequest")
    public HashMap<String,Object> publishRequest(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
//        Map userRequest = (Map)request.getParameter("userRequest");
//        if(userRequest == null){return CommonUtil.ToResultHashMap(status,"userRequest为空!",null);}
        try {
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("C端发布需求异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *数据字典>发布需求用
     *C端用户发布需求用于获取数据字典
     */
    @RequestMapping(value = "/getCategoryForReq")
    public HashMap<String,Object> getCategoryForReq(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *发布场景
     *C端用户发布场景
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/publishStage")
    public HashMap<String,Object> publishStage(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
//        Map stage = (Map)request.getParameter("stage");
//        if(stage == null){return CommonUtil.ToResultHashMap(status,"stage为空!",null);}
        try {
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("发布场景异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *收藏>获取收藏夹列表
     *C端用户获取收藏夹列表
     */
    @RequestMapping(value = "/collectList")
    public HashMap<String,Object> collectList(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *获取收藏场景
     *C端用户根据剧本Id获取收藏的场景列表
     */
    @RequestMapping(value = "/getStageForCollect")
    public HashMap<String,Object> getStageForCollect(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int scriptId = Integer.parseInt(CommonUtil.getStr(request.getParameter("scriptId"),"-500"));
        if(scriptId == -500){return CommonUtil.ToResultHashMap(status,"scriptId为空!",null);}
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *修改收藏夹/场景名称
     *C端用户修改收藏夹目录方法
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/updateCollect")
    public HashMap<String,Object> updateCollect(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        //修改类型	修改类型(1剧本,,2场景)
        int type = Integer.parseInt(CommonUtil.getStr(request.getParameter("type"),"-500"));
        if(type == -500){return CommonUtil.ToResultHashMap(status,"type为空!",null);}
        String title = CommonUtil.getStr(request.getParameter("title"),"");
        if(title == null || title.equals("")){return CommonUtil.ToResultHashMap(status,"title为空!",null);}
        int collectId = Integer.parseInt(CommonUtil.getStr(request.getParameter("collectId"),"-500"));
        if(collectId == -500){return CommonUtil.ToResultHashMap(status,"collectId为空!",null);}
        try {
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("修改收藏夹/场景名称异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *删除收藏夹剧本/场景
     *C端删除收藏夹目录
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/deleteCollect")
    public HashMap<String,Object> deleteCollect(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        //删除类型	删除类型(1剧本,,2场景)
        int type = Integer.parseInt(CommonUtil.getStr(request.getParameter("type"),"-500"));
        if(type == -500){return CommonUtil.ToResultHashMap(status,"type为空!",null);}
        int collectId = Integer.parseInt(CommonUtil.getStr(request.getParameter("collectId"),"-500"));
        if(collectId == -500){return CommonUtil.ToResultHashMap(status,"collectId为空!",null);}
        try {
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("删除收藏夹剧本/场景异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *取消收藏
     *C端取消收藏喜爱场景
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/cancelCollectStage")
    public HashMap<String,Object> cancelCollectStage(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int stageId = Integer.parseInt(CommonUtil.getStr(request.getParameter("stageId"),"-500"));
        if(stageId == -500){return CommonUtil.ToResultHashMap(status,"stageId为空!",null);}
        try {
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("取消收藏异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *编辑收藏场景
     *C端修改喜爱场景的类型
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/updateStageType")
    public HashMap<String,Object> updateStageType(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int stageId = Integer.parseInt(CommonUtil.getStr(request.getParameter("stageId"),"-500"));
        if(stageId == -500){return CommonUtil.ToResultHashMap(status,"stageId为空!",null);}
        int stageTypeId = Integer.parseInt(CommonUtil.getStr(request.getParameter("stageTypeId"),"-500"));
        if(stageTypeId == -500){return CommonUtil.ToResultHashMap(status,"stageTypeId为空!",null);}
        try {
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("编辑收藏场景异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *获取推荐列表
     *C端用户获取推荐列表
     */
    @RequestMapping(value = "/getRecommendationForC")
    public HashMap<String,Object> getRecommendationForC(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *获取推荐场景列表
     *C端根据推荐Id获取推荐场景列表
     */
    @RequestMapping(value = "/getStageListForRecommend")
    public HashMap<String,Object> getStageListForRecommend(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int recommendationId = Integer.parseInt(CommonUtil.getStr(request.getParameter("recommendationId"),"-500"));
        if(recommendationId == -500){return CommonUtil.ToResultHashMap(status,"recommendationId为空!",null);}
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *我的场景
     *C端查询我的场景
     */
    @RequestMapping(value = "/myStage")
    public HashMap<String,Object> myStage(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        //场景状态	场景流程状态(0已驳回,,,1待审核,,,2已通过)
        int processStatus = Integer.parseInt(CommonUtil.getStr(request.getParameter("processStatus"),"-500"));
        if(processStatus == -500){return CommonUtil.ToResultHashMap(status,"processStatus为空!",null);}
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *我的场景详情
     *C端查询我的场景详情
     */
    @RequestMapping(value = "/getStageDetail")
    public HashMap<String,Object> getStageDetail(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int stageId = Integer.parseInt(CommonUtil.getStr(request.getParameter("stageId"),"-500"));
        if(stageId == -500){return CommonUtil.ToResultHashMap(status,"stageId为空!",null);}
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *终止发布我的场景
     *C端用户终止发布场景
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/stopPublishStage")
    public HashMap<String,Object> stopPublishStage(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int stageId = Integer.parseInt(CommonUtil.getStr(request.getParameter("stageId"),"-500"));
        if(stageId == -500){return CommonUtil.ToResultHashMap(status,"stageId为空!",null);}
        try {
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("终止发布我的场景异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *我的需求
     *C端获取我的需求列表
     */
    @RequestMapping(value = "/myRequest")
    public HashMap<String,Object> myRequest(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        //需求状态	需求状态(0已终止,,1待推荐,,,2推荐中)
        int reqStatus = Integer.parseInt(CommonUtil.getStr(request.getParameter("reqStatus"),"-500"));
        if(reqStatus == -500){return CommonUtil.ToResultHashMap(status,"reqStatus为空!",null);}
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *我的需求详情
     *C端用于查询我的需求详情
     */
    @RequestMapping(value = "/myRequestDetail")
    public HashMap<String,Object> myRequestDetail(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int reqId = Integer.parseInt(CommonUtil.getStr(request.getParameter("reqId"),"-500"));
        if(reqId == -500){return CommonUtil.ToResultHashMap(status,"reqId为空!",null);}
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *终止发布我的需求
     *C端终止发布我的需求
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/stopPublishRequest")
    public HashMap<String,Object> stopPublishRequest(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int reqId = Integer.parseInt(CommonUtil.getStr(request.getParameter("reqId"),"-500"));
        if(reqId == -500){return CommonUtil.ToResultHashMap(status,"reqId为空!",null);}
        try {
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("终止发布我的需求异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }



    //=====================================================================================================================================================================================
    //=====================================================================================================================================================================================

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