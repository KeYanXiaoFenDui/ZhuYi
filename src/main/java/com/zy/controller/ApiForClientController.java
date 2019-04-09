package com.zy.controller;

import com.github.pagehelper.PageHelper;
import com.zy.domain.*;
import com.zy.domain.vo.CollectStageVo;
import com.zy.domain.vo.RecStageVo;
import com.zy.domain.vo.StageRequestVo;
import com.zy.service.*;
import com.zy.util.CommonUtil;
import com.zy.util.PageBean;
import com.zy.util.RedisComponentUtil;
import com.zy.util.constant.MessageConstant;
import io.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Autowired
    public IScenarioService scenarioService;
    @Autowired
    public ICollectService collectService;
    @Autowired
    public IUserStageTypeService userStageTypeService;
    @Autowired
    public IRotationPicService rotationPicService;
    @Autowired
    public IStageService stageService;
    @Autowired
    public ICategoryService categoryService;
    @Autowired
    public IUserRequestService userRequestService;
    @Autowired
    private ICrewServiceService crewServiceService;
    @Autowired
    private IResponseInfoService responseInfoService;
    private static final Logger logger= LoggerFactory.getLogger(ApiForClientController.class);
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
            @ApiImplicitParam(name = "type", value = "登录方式(1手机号,,2邮箱)", paramType = "query", required = true, dataType = "String")
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
    @RequestMapping(value = "/auth/mailRegister",method = RequestMethod.POST)
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
            @ApiImplicitParam(name = "type", value = "验证码类型(1手机验证码,,2邮箱验证码)", paramType = "body", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "account", value = "账号", paramType = "body", required = true, dataType = "String")
    })
    @RequestMapping(value = "/auth/sendCode",method = RequestMethod.POST)
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
            @ApiImplicitParam(name = "account", value = "账号", paramType = "body", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "账号类型(1手机号码,,2邮箱)", paramType = "body", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "code", value = "验证码", paramType = "body", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", paramType = "body", required = true, dataType = "String"),
            @ApiImplicitParam(name = "reNewPassword", value = "重复新密码", paramType = "body", required = true, dataType = "String")
    })
    @RequestMapping(value = "/auth/findPassword",method = RequestMethod.POST)
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
        String newPassword = CommonUtil.getStr(request.getParameter("newPassword"),"");
        if(newPassword == null || newPassword.equals("")){return CommonUtil.ToResultHashMap(status,"newPassword为空!",null);}
        String reNewPassword = CommonUtil.getStr(request.getParameter("reNewPassword"),"");
        if(reNewPassword == null || reNewPassword.equals("")){return CommonUtil.ToResultHashMap(status,"reNewPassword为空!",null);}
        try {
            //根据账号,验证验证码是否正确
            //正确
            if(newPassword.equals(reNewPassword)){
                User u = userService.getUserByAccount(account);
                u.setPassword(newPassword);
                int result = userService.updateUser(u);
                if(result == 1){
                    status = MessageConstant.SUCCESS_CODE;
                    message = MessageConstant.SUCCESS_INFO;
                }else{
                    throw new RuntimeException("重置密码失败");
                }
            }else{
                return CommonUtil.ToResultHashMap(status,"两次输入的密码不一致",data);
            }
            //验证码错误
            //返回验证码错误
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("找回密码异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
//    /**
//     *设置新密码
//     *C端重新设置密码
//     */
//    @Transactional(rollbackFor = Exception.class)
//    @RequestMapping(value = "/setNewPassword")
//    public HashMap<String,Object> setNewPassword(HttpServletRequest request) {
//        int status = MessageConstant.ERROR_CODE;
//        String message = MessageConstant.ERROR_INFO_DEMO;
//        HashMap<String,Object> data = new HashMap<>();
//        String newPassword = CommonUtil.getStr(request.getParameter("newPassword"),"");
//        if(newPassword == null || newPassword.equals("")){return CommonUtil.ToResultHashMap(status,"newPassword为空!",null);}
//        String reNewPassword = CommonUtil.getStr(request.getParameter("reNewPassword"),"");
//        if(reNewPassword == null || reNewPassword.equals("")){return CommonUtil.ToResultHashMap(status,"reNewPassword为空!",null);}
//        try {
//        } catch (Exception e){
//            e.printStackTrace();
////            logger.error("设置新密码异常：" + e.getMessage());
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//        }
//        return CommonUtil.ToResultHashMap(status,message,data);
//    }
    /**
     *微信登录
     *C端根据微信扫码登录
     */
    @ApiOperation(value = "微信登录", notes = "C端根据微信扫码登录")
    @RequestMapping(value = "/auth/wxLogin",method = RequestMethod.POST)
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
    @ApiOperation(value = "微信登录并绑定账号", notes = "C端微信账号绑定用户账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "微信Id", paramType = "body", required = true, dataType = "String"),
            @ApiImplicitParam(name = "account", value = "账号", paramType = "body", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "账号类型(1手机号码,,2邮箱)", paramType = "body", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "body", required = true, dataType = "String")
    })
    @RequestMapping(value = "/auth/wxBindingAccount",method = RequestMethod.POST)
    public HashMap<String,Object> wxBindingAccount(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        String openId = CommonUtil.getStr(request.getParameter("openId"),"");
        if(openId == null || openId.equals("")){return CommonUtil.ToResultHashMap(status,"openId为空!",null);}
        String account = CommonUtil.getStr(request.getParameter("account"),"");
        if(account == null || account.equals("")){return CommonUtil.ToResultHashMap(status,"account为空!",null);}
        //类型	账号类型(1手机号码,,,2邮箱)
        int type = Integer.parseInt(CommonUtil.getStr(request.getParameter("type"),"-500"));
        if(type == -500){return CommonUtil.ToResultHashMap(status,"type为空!",null);}
        String password = CommonUtil.getStr(request.getParameter("password"),"");
        if(password == null || password.equals("")){return CommonUtil.ToResultHashMap(status,"password为空!",null);}
        try {
            //获取openId绑定到已有用户账号
            User u = userService.loginForC(account,password);
            if(u != null){
                u.setOpenId(openId);
                int result = userService.updateUser(u);
                if(result == 1){
                    status = MessageConstant.SUCCESS_CODE;
                    message = MessageConstant.SUCCESS_INFO;
                }else{
                    throw new RuntimeException("绑定账号失败");
                }
            }else{
                return CommonUtil.ToResultHashMap(status,"账号或密码错误!",null);
            }
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
    @ApiOperation(value = "获取轮播图", notes = "C端首页获取轮播图")
    @RequestMapping(value = "/content/getRotationPicForC",method = RequestMethod.GET)
    public HashMap<String,Object> getRotationPicForC(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        List<RotationPic> rotationPicList = rotationPicService.getRotationPicList();
        if(rotationPicList != null && rotationPicList.size() > 0){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }else if(rotationPicList.size() == 0){
            message = "没有设置轮播图";
        }
        return CommonUtil.ToResultHashMap(status,message,rotationPicList);
    }
    /**
     *场景搜索
     *C端获取场景列表
     */
    @ApiOperation(value = "场景搜索", notes = "C端获取场景列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码(默认为1)", paramType = "body", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页长度(默认为10)", paramType = "body", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "country", value = "国家级场景地区", paramType = "body", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "province", value = "省级场景地区", paramType = "body", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "city", value = "市级场景地区", paramType = "body", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "filmTypeId", value = "影视剧类型", paramType = "body", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "stageMainTypeId", value = "场景一级分类", paramType = "body", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "stageSubTypeId", value = "场景二级分类", paramType = "body", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "stageStyleId", value = "场景风格", paramType = "body", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "idOrName", value = "标题或内容", paramType = "body", required = false, dataType = "String")
    })
    @RequestMapping(value = "/stage/getStageListForC",method = RequestMethod.POST)
    public HashMap<String,Object> getStageListForC(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        int country = Integer.parseInt(CommonUtil.getStr(request.getParameter("country"),"-500"));
        int province = Integer.parseInt(CommonUtil.getStr(request.getParameter("province"),"-500"));
        int city = Integer.parseInt(CommonUtil.getStr(request.getParameter("city"),"-500"));
        int filmTypeId = Integer.parseInt(CommonUtil.getStr(request.getParameter("filmTypeId"),"-500"));
        int stageMainTypeId = Integer.parseInt(CommonUtil.getStr(request.getParameter("stageMainTypeId"),"-500"));
        int stageSubTypeId = Integer.parseInt(CommonUtil.getStr(request.getParameter("stageSubTypeId"),"-500"));
        int stageStyleId = Integer.parseInt(CommonUtil.getStr(request.getParameter("stageStyleId"),"-500"));
        //标题或内容	根据场景标题或者内容模糊搜索场景
        String idOrName = CommonUtil.getStr(request.getParameter("idOrName"),"");
//        PageBean<Map> list = new PageBean<Map>(resultList);
        StageRequestVo vo = new StageRequestVo();
        vo.setProcessStatus(-500);
        vo.setCountry(country);
        vo.setProvince(province);
        vo.setCity(city);
        vo.setFilmTypeId(filmTypeId);
        vo.setStageMainTypeId(stageMainTypeId);
        vo.setStageSubTypeId(stageSubTypeId);
        vo.setStageStyleId(stageStyleId);
        vo.setIdOrName(idOrName);
        List<Stage> resultList = stageService.getStageList(vo);
        status = MessageConstant.SUCCESS_CODE;
        message = MessageConstant.SUCCESS_INFO;
        return CommonUtil.ToResultHashMap(status,message,resultList);
    }
    /**
     *数据字典
     *C端场景数据相关类目字典
     */
    @ApiOperation(value = "数据字典", notes = "C端场景数据相关类目字典")
    @RequestMapping(value = "/stage/stageCategory",method = RequestMethod.GET)
    public HashMap<String,Object> stageCategory(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        List<Map> category1stList = categoryService.getCategoryMsg();
        status = MessageConstant.SUCCESS_CODE;
        message = MessageConstant.SUCCESS_INFO;
        return CommonUtil.ToResultHashMap(status,message,category1stList);
    }
    /**
     *场景详情
     *C端获取场景详情信息
     */
    @ApiOperation(value = "场景详情", notes = "C端获取场景详情信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stageId", value = "场景", paramType = "query", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/stage/stageDetailForC",method = RequestMethod.GET)
    public HashMap<String,Object> stageDetailForC(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int stageId = Integer.parseInt(CommonUtil.getStr(request.getParameter("stageId"),"-500"));
        if(stageId == -500){return CommonUtil.ToResultHashMap(status,"stageId为空!",null);}
        Stage s = stageService.getStage(stageId);
        if(s != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        return CommonUtil.ToResultHashMap(status,message,s);
    }

    //==========================================收藏==============================================================================
    //==========================================收藏==============================================================================
    /**
     *新增剧本
     *C端用户新增剧本方法
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "新增剧本", notes = "C端用户新增剧本方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scenarioName", value = "剧本名称", paramType = "body", required = true, dataType = "String")
    })
    @RequestMapping(value = "/collect/insertScenario",method = RequestMethod.POST)
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
            int result = scenarioService.insertScenario(scenario);
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
     *新增用户场景分类
     *C端用户新增用户场景分类
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "新增用户场景分类", notes = "C端用户新增用户场景分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scenarioId", value = "剧本Id", paramType = "body", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userStageTypeName", value = "用户场景分类名称", paramType = "body", required = true, dataType = "String")
    })
    @RequestMapping(value = "/collect/insertUserStageType",method = RequestMethod.POST)
    public HashMap<String,Object> insertUserStageType(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int scenarioId = Integer.parseInt(CommonUtil.getStr(request.getParameter("scenarioId"),"-500"));
        if(scenarioId == -500){return CommonUtil.ToResultHashMap(status,"scenarioId为空!",null);}
        String userStageTypeName = CommonUtil.getStr(request.getParameter("userStageTypeName"),"");
        if(StringUtils.isBlank(userStageTypeName)){return CommonUtil.ToResultHashMap(status,"userStageTypeName为空!",null);}

        try {
            UserStageType type = new UserStageType();
            type.setScenarioId(scenarioId);
            type.setTypeName(userStageTypeName);
            type.setCreateTime(new Date());
            type.setStatus(1);
            type.setUpdateTime(new Date());
            userStageTypeService.updateUserStageType(type);

        } catch (Exception e){
            e.printStackTrace();
            logger.error("新增用户场景分类：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *收藏场景
     *C端用户收藏场景
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "收藏场景", notes = "C端用户收藏场景")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户Id", paramType = "body", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "scenarioId", value = "剧本Id", paramType = "body", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userStageTypeId", value = "用户场景分类Id", paramType = "body", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "stageId", value = "场景Id", paramType = "body", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/collect/insertCollect",method = RequestMethod.POST)
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
            collectService.insertCollect(collect);
        } catch (Exception e){
            e.printStackTrace();
            logger.error("收藏场景：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }

//    /**
//     *收藏>获取收藏夹列表
//     *C端用户获取收藏夹列表
//     */
//    @RequestMapping(value = "/collectList")
//    public HashMap<String,Object> collectList(HttpServletRequest request) {
//        int status = MessageConstant.ERROR_CODE;
//        String message = MessageConstant.ERROR_INFO_DEMO;
//        HashMap<String,Object> data = new HashMap<>();
//        return CommonUtil.ToResultHashMap(status,message,data);
//    }
    /**
     *收藏>获取剧本列表
     *C端用户获取剧本列表
     */
    @ApiOperation(value = "收藏>获取剧本列表", notes = "C端用户获取剧本列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户Id", paramType = "query", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/collect/scenarioList",method = RequestMethod.GET)
    public HashMap<String,Object> scenarioList(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        int userId = Integer.parseInt(CommonUtil.getStr(request.getParameter("userId"),"-500"));
        if(userId == -500){return CommonUtil.ToResultHashMap(status,"userId为空!",null);}
        List<Scenario> scenarioList = scenarioService.getScenarioByUserId(userId);
        return CommonUtil.ToResultHashMap(status,message,scenarioList);
    }
    /**
     *获取用户场景分类
     *C端用户根据剧本Id获取用户场景分类
     */
    @ApiOperation(value = "获取用户场景分类", notes = "C端用户根据剧本Id获取用户场景分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scenarioId", value = "剧本Id", paramType = "query", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/collect/getUserStageType",method = RequestMethod.GET)
    public HashMap<String,Object> getUserStageType(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        int scenarioId = Integer.parseInt(CommonUtil.getStr(request.getParameter("scenarioId"),"-500"));
        if(scenarioId == -500){return CommonUtil.ToResultHashMap(status,"scenarioId为空!",null);}
        List<UserStageType> stageTypeList = userStageTypeService.getUserStageTypeByScenarioId(scenarioId);
        return CommonUtil.ToResultHashMap(status,message,stageTypeList);
    }
    /**
     *获取收藏场景
     *C端用户根据剧本Id获取收藏的场景列表
     */
    @ApiOperation(value = "获取收藏场景", notes = "C端用户根据剧本Id获取收藏的场景列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scenarioId", value = "剧本Id", paramType = "query", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/collect/getStageForCollect",method = RequestMethod.GET)
    public HashMap<String,Object> getStageForCollect(HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int scenarioId = Integer.parseInt(CommonUtil.getStr(request.getParameter("scenarioId"),"-500"));
        if(scenarioId == -500){return CommonUtil.ToResultHashMap(status,"scenarioId为空!",null);}
        User u = getUserMsg(session);
        List<CollectStageVo> list = collectService.getStageForCollect(u.getId(),scenarioId);
        status = MessageConstant.SUCCESS_CODE;
        message = MessageConstant.SUCCESS_INFO;
        return CommonUtil.ToResultHashMap(status,message,list);
    }
//    /**
//     *修改收藏夹/场景名称
//     *C端用户修改收藏夹目录方法
//     */
//    @Transactional(rollbackFor = Exception.class)
//    @RequestMapping(value = "/updateCollect")
//    public HashMap<String,Object> updateCollect(HttpServletRequest request) {
//        int status = MessageConstant.ERROR_CODE;
//        String message = MessageConstant.ERROR_INFO_DEMO;
//        HashMap<String,Object> data = new HashMap<>();
//        //修改类型	修改类型(1剧本,,2场景)
//        int type = Integer.parseInt(CommonUtil.getStr(request.getParameter("type"),"-500"));
//        if(type == -500){return CommonUtil.ToResultHashMap(status,"type为空!",null);}
//        String title = CommonUtil.getStr(request.getParameter("title"),"");
//        if(title == null || title.equals("")){return CommonUtil.ToResultHashMap(status,"title为空!",null);}
//        int collectId = Integer.parseInt(CommonUtil.getStr(request.getParameter("collectId"),"-500"));
//        if(collectId == -500){return CommonUtil.ToResultHashMap(status,"collectId为空!",null);}
//        try {
//        } catch (Exception e){
//            e.printStackTrace();
////            logger.error("修改收藏夹/场景名称异常：" + e.getMessage());
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//        }
//        return CommonUtil.ToResultHashMap(status,message,data);
//    }
    /**
     *修改剧本名称
     *C端用户修改剧本名称方法
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "修改剧本名称", notes = "C端用户修改剧本名称方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scenarioName", value = "剧本名称", paramType = "body", required = true, dataType = "String"),
            @ApiImplicitParam(name = "scenarioId", value = "剧本Id", paramType = "body", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/collect/updateScenario",method = RequestMethod.POST)
    public HashMap<String,Object> updateScenario(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        String scenarioName = CommonUtil.getStr(request.getParameter("scenarioName"),"");
        if(StringUtils.isBlank(scenarioName)){return CommonUtil.ToResultHashMap(status,"scenarioName为空!",null);}
        int scenarioId = Integer.parseInt(CommonUtil.getStr(request.getParameter("scenarioId"),"-500"));
        if(scenarioId == -500){return CommonUtil.ToResultHashMap(status,"scenarioId为空!",null);}
        try {
            Scenario scenario = scenarioService.getScenario(scenarioId);
            if(scenario!=null){
                scenario.setScenarioName(scenarioName);
                scenario.setUpdateTime(new Date());
                scenarioService.updateScenario(scenario);
            }

        } catch (Exception e){
            e.printStackTrace();
            logger.error("修改剧本名称异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *修改用户场景分类名称
     *C端用户修改用户场景分类名称
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "修改用户场景分类名称", notes = "C端用户修改用户场景分类名称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userStageTypeName", value = "用户场景分类名称", paramType = "body", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userStageTypeId", value = "用户场景分类Id", paramType = "body", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/collect/updateUserStageType",method = RequestMethod.POST)
    public HashMap<String,Object> updateUserStageType(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        String userStageTypeName = CommonUtil.getStr(request.getParameter("userStageTypeName"),"");
        if(StringUtils.isBlank(userStageTypeName)){return CommonUtil.ToResultHashMap(status,"userStageTypeName为空!",null);}
        int userStageTypeId = Integer.parseInt(CommonUtil.getStr(request.getParameter("userStageTypeId"),"-500"));
        if(userStageTypeId == -500){return CommonUtil.ToResultHashMap(status,"userStageTypeId为空!",null);}
        try {
            UserStageType type = userStageTypeService.getUserStageType(userStageTypeId);
            if(type!=null){
                type.setTypeName(userStageTypeName);
                type.setUpdateTime(new Date());
                userStageTypeService.updateUserStageType(type);
            }

        } catch (Exception e){
            e.printStackTrace();
            logger.error("修改用户场景分类名称异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
//    /**
//     *删除收藏夹剧本/场景
//     *C端删除收藏夹目录
//     */
//    @Transactional(rollbackFor = Exception.class)
//    @RequestMapping(value = "/deleteCollect")
//    public HashMap<String,Object> deleteCollect(HttpServletRequest request) {
//        int status = MessageConstant.ERROR_CODE;
//        String message = MessageConstant.ERROR_INFO_DEMO;
//        HashMap<String,Object> data = new HashMap<>();
//        //删除类型	删除类型(1剧本,,2场景)
//        int type = Integer.parseInt(CommonUtil.getStr(request.getParameter("type"),"-500"));
//        if(type == -500){return CommonUtil.ToResultHashMap(status,"type为空!",null);}
//        int collectId = Integer.parseInt(CommonUtil.getStr(request.getParameter("collectId"),"-500"));
//        if(collectId == -500){return CommonUtil.ToResultHashMap(status,"collectId为空!",null);}
//        try {
//        } catch (Exception e){
//            e.printStackTrace();
////            logger.error("删除收藏夹剧本/场景异常：" + e.getMessage());
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//        }
//        return CommonUtil.ToResultHashMap(status,message,data);
//    }
    /**
     *删除剧本
     *C端删除剧本
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "删除剧本", notes = "C端删除剧本")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scenarioId", value = "剧本Id", paramType = "body", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userId", value = "用户Id", paramType = "body", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/collect/deleteScenario",method = RequestMethod.POST)
    public HashMap<String,Object> deleteScenario(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int scenarioId = Integer.parseInt(CommonUtil.getStr(request.getParameter("scenarioId"),"-500"));
        if(scenarioId == -500){return CommonUtil.ToResultHashMap(status,"scenarioId为空!",null);}
        int userId = Integer.parseInt(CommonUtil.getStr(request.getParameter("userId"),"-500"));
        if(userId == -500){return CommonUtil.ToResultHashMap(status,"userId为空!",null);}
        try {
            collectService.deleteCollectByScenarioId(userId,scenarioId);
            userStageTypeService.deleteUserStageTypeByScenarioId(userId,scenarioId);
            scenarioService.deleteScenario(scenarioId);
        } catch (Exception e){
            e.printStackTrace();
            logger.error("删除剧本异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *删除用户场景分类
     *C端删除用户场景分类
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "删除用户场景分类", notes = "C端删除用户场景分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userStageTypeId", value = "用户场景分类Id", paramType = "body", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userId", value = "用户Id", paramType = "body", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "scenarioId", value = "剧本Id", paramType = "body", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/collect/deleteUserStageType",method = RequestMethod.POST)
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
            collectService.deleteCollectByStageTypeId(userId,scenarioId,userStageTypeId);
            userStageTypeService.deleteUserStageType(userStageTypeId);
        } catch (Exception e){
            e.printStackTrace();
            logger.error("删除用户场景分类异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *取消收藏场景
     *C端取消收藏场景
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "取消收藏场景", notes = "C端取消收藏场景")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userStageTypeId", value = "用户场景分类Id", paramType = "body", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userId", value = "用户Id", paramType = "body", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "scenarioId", value = "剧本Id", paramType = "body", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "stageId", value = "场景Id", paramType = "body", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/collect/deleteCollect",method = RequestMethod.POST)
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
            collectService.deleteCollectByStageId(userId,scenarioId,userStageTypeId,stageId);
        } catch (Exception e){
            e.printStackTrace();
            logger.error("取消收藏场景异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *编辑收藏场景
     *C端修改喜爱场景的类型
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "编辑收藏场景", notes = "C端修改喜爱场景的类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "collectId", value = "收藏Id", paramType = "body", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userStageTypeId", value = "用户场景分类Id", paramType = "body", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/collect/updateStageType",method = RequestMethod.POST)
    public HashMap<String,Object> updateStageType(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int collectId = Integer.parseInt(CommonUtil.getStr(request.getParameter("collectId"),"-500"));
        if(collectId == -500){return CommonUtil.ToResultHashMap(status,"collectId为空!",null);}
        int userStageTypeId = Integer.parseInt(CommonUtil.getStr(request.getParameter("userStageTypeId"),"-500"));
        if(userStageTypeId == -500){return CommonUtil.ToResultHashMap(status,"userStageTypeId为空!",null);}
        try {
            int result = collectService.updateStageType(collectId,userStageTypeId);
            if(result == 1){
                status = MessageConstant.SUCCESS_CODE;
                message = MessageConstant.SUCCESS_INFO;
            }
        } catch (Exception e){
            e.printStackTrace();
            logger.error("编辑收藏场景异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }

    //==========================================收藏==============================================================================
    //==========================================收藏==============================================================================

    /**
     *C端发布需求
     *C端用户发布需求
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "C端发布需求", notes = "C端用户发布需求")
    @RequestMapping(value = "/req/publishRequest",method = RequestMethod.POST)
    public HashMap<String,Object> publishRequest(@RequestBody @ApiParam(name="需求对象",value="传入json格式",required=false) UserRequest vo,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        try {
            User u = getUserMsg(session);
            Date d = new Date();
            vo.setUserId(u.getId());
            vo.setCreateTime(d);
            vo.setUpdateTime(d);
            vo.setStatus(1);
            vo.setReqStatus(1);
            int result = userRequestService.insertUserRequest(vo);
            if(result == 1){
                status = MessageConstant.SUCCESS_CODE;
                message = MessageConstant.SUCCESS_INFO;
            }
        } catch (Exception e){
            e.printStackTrace();
            logger.error("C端发布需求异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *获取一级分类数据
     */
    @ApiOperation(value = "获取一级分类数据", notes = "C端获取一级分类数据(type字段>1：影视剧类型；2：场景风格；3：场景类型; 4: 场景地区;)")
    @RequestMapping(value = "/category/getStage1stCategory",method = RequestMethod.GET)
    public HashMap<String,Object> getStage1stCategory(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        List<Category> category1stList = categoryService.getStage1stCategory();
        if(category1stList != null && category1stList.size() > 0){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        return CommonUtil.ToResultHashMap(status,message,category1stList);
    }
    /**
     *获取子分类数据
     */
    @ApiOperation(value = "获取子分类数据", notes = "C端根据一级分类类目Id,获取子类目集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "一级分类id", paramType = "query", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/category/getSubCategoryList",method = RequestMethod.GET)
    public HashMap<String,Object> getSubCategoryList(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int id = Integer.parseInt(CommonUtil.getStr(request.getParameter("id"),"-500"));
        List<Category> c = categoryService.getSubCategoryList(id);
        if(c != null && c.size() > 0){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        return CommonUtil.ToResultHashMap(status,message,c);
    }
    /**
     *发布场景
     *C端用户发布场景
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "发布场景", notes = "C端用户发布场景")
    @RequestMapping(value = "/stage/publishStage",method = RequestMethod.POST)
    public HashMap<String,Object> publishStage(@RequestBody @ApiParam(name="场景对象",value="传入json格式",required=false) Stage vo,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        try {
            User u = getUserMsg(session);
            Date d = new Date();
            vo.setUserId(u.getId());
            vo.setCreateTime(d);
            vo.setStatus(1);
            vo.setProcessStatus(1);
            int result = stageService.insertStage(vo);
            if(result == 1){
                status = MessageConstant.SUCCESS_CODE;
                message = MessageConstant.SUCCESS_INFO;
            }
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("发布场景异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }

    /**
     *更多服务
     *C端获取更多服务
     */
    @ApiOperation(value = "更多服务", notes = "C端获取更多服务")
    @RequestMapping(value = "/content/moreService",method = RequestMethod.GET)
    public HashMap<String,Object> moreService(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        List<CrewService> resultList = crewServiceService.getCrewServiceList();
        if(resultList != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        return CommonUtil.ToResultHashMap(status,message,resultList);
    }
    /**
     *获取推荐列表
     *C端用户获取推荐列表
     */
    @ApiOperation(value = "获取推荐列表", notes = "C端用户获取推荐列表")
    @RequestMapping(value = "/req/getRecommendationForC",method = RequestMethod.GET)
    public HashMap<String,Object> getRecommendationForC(HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        User u = getUserMsg(session);
        if(u == null){
            return CommonUtil.ToResultHashMap(status,"用户未登陆",data);
        }
        List<Map> list = userRequestService.getRecommendationForC(u.getId());
        status = MessageConstant.SUCCESS_CODE;
        message = MessageConstant.SUCCESS_INFO;
        return CommonUtil.ToResultHashMap(status,message,list);
    }
    /**
     *获取推荐场景列表
     *C端根据推荐Id获取推荐场景列表
     */
    @ApiOperation(value = "获取推荐场景列表", notes = "C端根据推荐Id获取推荐场景列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reqId", value = "需求Id", paramType = "query", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/req/getStageListForRecommend",method = RequestMethod.GET)
    public HashMap<String,Object> getStageListForRecommend(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int reqId = Integer.parseInt(CommonUtil.getStr(request.getParameter("reqId"),"-500"));
        if(reqId == -500){return CommonUtil.ToResultHashMap(status,"reqId为空!",null);}
        List<RecStageVo> list = userRequestService.getStageListForRecommend(reqId);
        status = MessageConstant.SUCCESS_CODE;
        message = MessageConstant.SUCCESS_INFO;
        return CommonUtil.ToResultHashMap(status,message,list);
    }
    /**
     *我的场景
     *C端查询我的场景
     */
    @ApiOperation(value = "我的场景", notes = "C端查询我的场景")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processStatus", value = "流程状态(0已驳回,,,1待审核,,,2已通过)", paramType = "query", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/stage/myStage",method = RequestMethod.GET)
    public HashMap<String,Object> myStage(HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        //场景状态	场景流程状态(0已驳回,,,1待审核,,,2已通过)
        int processStatus = Integer.parseInt(CommonUtil.getStr(request.getParameter("processStatus"),"-500"));
        if(processStatus == -500){return CommonUtil.ToResultHashMap(status,"processStatus为空!",null);}
        User u = getUserMsg(session);
        if(u == null){
            return CommonUtil.ToResultHashMap(status,"用户未登陆",data);
        }
        List<Stage> list = stageService.myStage(u.getId(),processStatus);
        return CommonUtil.ToResultHashMap(status,message,list);
    }
    /**
     *我的场景详情
     *C端查询我的场景详情
     */
    @ApiOperation(value = "我的场景详情", notes = "C端查询我的场景详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stageId", value = "场景Id", paramType = "query", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/stage/getStageDetail",method = RequestMethod.GET)
    public HashMap<String,Object> getStageDetail(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int stageId = Integer.parseInt(CommonUtil.getStr(request.getParameter("stageId"),"-500"));
        if(stageId == -500){return CommonUtil.ToResultHashMap(status,"stageId为空!",null);}
        Stage s = stageService.getStage(stageId);
        if(s != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        return CommonUtil.ToResultHashMap(status,message,s);
    }
    /**
     *终止发布我的场景
     *C端用户终止发布场景
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "终止发布我的场景", notes = "C端用户终止发布场景")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stageId", value = "场景Id", paramType = "query", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/stage/stopPublishStage",method = RequestMethod.GET)
    public HashMap<String,Object> stopPublishStage(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int stageId = Integer.parseInt(CommonUtil.getStr(request.getParameter("stageId"),"-500"));
        if(stageId == -500){return CommonUtil.ToResultHashMap(status,"stageId为空!",null);}
        try {
            status = MessageConstant.SUCCESS_CODE;
            message = "请联系工作人员";
        } catch (Exception e){
            e.printStackTrace();
            logger.error("终止发布我的场景异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *我的需求
     *C端获取我的需求列表
     */
    @ApiOperation(value = "我的需求", notes = "C端获取我的需求列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reqStatus", value = "需求状态(0已终止,,1待推荐,,,2推荐中)", paramType = "query", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/req/myRequest",method = RequestMethod.GET)
    public HashMap<String,Object> myRequest(HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        //需求状态	需求状态(0已终止,,1待推荐,,,2推荐中)
        int reqStatus = Integer.parseInt(CommonUtil.getStr(request.getParameter("reqStatus"),"-500"));
        if(reqStatus == -500){return CommonUtil.ToResultHashMap(status,"reqStatus为空!",null);}
        User u = getUserMsg(session);
        if(u == null){
            return CommonUtil.ToResultHashMap(status,"用户未登陆",data);
        }
        List<UserRequest> list = userRequestService.myRequest(u.getId(),reqStatus);
        status = MessageConstant.SUCCESS_CODE;
        message = MessageConstant.SUCCESS_INFO;
        return CommonUtil.ToResultHashMap(status,message,list);
    }
    /**
     *我的需求详情
     *C端用于查询我的需求详情
     */
    @ApiOperation(value = "我的需求详情", notes = "C端用于查询我的需求详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reqId", value = "需求Id", paramType = "query", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/req/myRequestDetail",method = RequestMethod.GET)
    public HashMap<String,Object> myRequestDetail(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int reqId = Integer.parseInt(CommonUtil.getStr(request.getParameter("reqId"),"-500"));
        if(reqId == -500){return CommonUtil.ToResultHashMap(status,"reqId为空!",null);}
        UserRequest ur = userRequestService.getUserRequest(reqId);
        status = MessageConstant.SUCCESS_CODE;
        message = MessageConstant.SUCCESS_INFO;
        return CommonUtil.ToResultHashMap(status,message,ur);
    }
    /**
     *终止发布我的需求
     *C端终止发布我的需求
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "终止发布我的需求", notes = "C端终止发布我的需求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reqId", value = "需求Id", paramType = "query", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/req/stopPublishRequest",method = RequestMethod.GET)
    public HashMap<String,Object> stopPublishRequest(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int reqId = Integer.parseInt(CommonUtil.getStr(request.getParameter("reqId"),"-500"));
        if(reqId == -500){return CommonUtil.ToResultHashMap(status,"reqId为空!",null);}
        try {
            status = MessageConstant.SUCCESS_CODE;
            message = "请联系工作人员";
        } catch (Exception e){
            e.printStackTrace();
            logger.error("终止发布我的需求异常：" + e.getMessage());
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
    public User getUserMsg(HttpSession session){
        HashMap<String,Object> userMsg = (HashMap<String,Object>)redisComponentUtil.get(session.getId());
        if(userMsg.get("user") != null){
            User u = (User) userMsg.get("user");
            return u;
        }else{
            return null;
        }
//        Cookie[] cookies = request.getCookies();
//        String cookieValue = null;
//        if (null != cookies) {
//            for (Cookie cookie : cookies) {
//                System.out.println("cookie::"+cookie.getName()+"::::"+cookie.getValue());
//            }
//        }
    }
}