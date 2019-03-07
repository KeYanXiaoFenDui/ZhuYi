package com.zy.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zy.domain.Category;
import com.zy.domain.Menu;
import com.zy.enums.CategoryType;
import com.zy.service.ICategoryService;
import com.zy.service.IMenuService;
import com.zy.util.CommonUtil;
import com.zy.util.PageBean;
import com.zy.util.constant.MessageConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@RestController
@RequestMapping("/zhuYi")
public class ZhuYiController {

    @Autowired
    private IMenuService menuService;
    @Autowired
    private ICategoryService categoryService;

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
    public HashMap<String,Object> getAdminList(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        String account = CommonUtil.getStr(request.getParameter("account"),"");
        String name = CommonUtil.getStr(request.getParameter("name"),"");
        List resultList = new ArrayList();
        PageBean<Map> list = new PageBean<Map>(resultList);
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *新增&编辑管理员
     *新增或修改管理员提交时调用接口
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/createAndUpdateAdmin")
    public HashMap<String,Object> createAndUpdateAdmin(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        String account = CommonUtil.getStr(request.getParameter("account"),"");
        if(account == null || account.equals("")){return CommonUtil.ToResultHashMap(status,"account为空!",null);}
        String name = CommonUtil.getStr(request.getParameter("name"),"");
        if(name == null || name.equals("")){return CommonUtil.ToResultHashMap(status,"name为空!",null);}
        String password = CommonUtil.getStr(request.getParameter("password"),"");
        if(password == null || password.equals("")){return CommonUtil.ToResultHashMap(status,"password为空!",null);}
        int roleId = Integer.parseInt(CommonUtil.getStr(request.getParameter("roleId"),"-500"));
        if(roleId == -500){return CommonUtil.ToResultHashMap(status,"roleId为空!",null);}
        try {
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
    @RequestMapping(value = "/getAdminMsgById")
    public HashMap<String,Object> getAdminMsgById(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int id = Integer.parseInt(CommonUtil.getStr(request.getParameter("id"),"-500"));
        if(id == -500){return CommonUtil.ToResultHashMap(status,"id为空!",null);}
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *身份列表查询
     *管理后台获取管理员身份列表接口
     */
    @RequestMapping(value = "/getRoleList")
    public HashMap<String,Object> getRoleList(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        //管理员身份标题	模糊查询管理员身份数据
        String title = CommonUtil.getStr(request.getParameter("title"),"");
        List resultList = new ArrayList();
        PageBean<Map> list = new PageBean<Map>(resultList);
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *新增&编辑身份
     *管理后台新增$编辑后保存管理员角色身份接口
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/createAndUpdateRole")
    public HashMap<String,Object> createAndUpdateRole(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        String title = CommonUtil.getStr(request.getParameter("title"),"");
        if(title == null || title.equals("")){return CommonUtil.ToResultHashMap(status,"title为空!",null);}
        String moduleList = request.getParameter("moduleList");
        if(moduleList == null || title.equals("")){return CommonUtil.ToResultHashMap(status,"moduleList为空!",null);}
        try {
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("新增身份异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }

    /**
     *获取身份信息
     *获取身份信息(编辑用)
     */
    @RequestMapping(value = "/getRoleMsgById")
    public HashMap<String,Object> getRoleMsgById(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int id = Integer.parseInt(CommonUtil.getStr(request.getParameter("id"),"-500"));
        if(id == -500){return CommonUtil.ToResultHashMap(status,"id为空!",null);}
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *删除身份
     *根据Id删除身份角色
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/deleteRoleById")
    public HashMap<String,Object> deleteRoleById(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int id = Integer.parseInt(CommonUtil.getStr(request.getParameter("id"),"-500"));
        if(id == -500){return CommonUtil.ToResultHashMap(status,"id为空!",null);}
        try {
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("删除身份异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *操作日志查询
     *管理后台查询操作记录接口
     */
    @RequestMapping(value = "/getOperateLogList")
    public HashMap<String,Object> getOperateLogList(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        String nameOrMenu = CommonUtil.getStr(request.getParameter("nameOrMenu"),"");
        //起始时间	格式(yyyy-MM-dd)
        String startTime = CommonUtil.getStr(request.getParameter("startTime"),"");
        //截止时间	格式(yyyy-MM-dd)
        String endTime = CommonUtil.getStr(request.getParameter("endTime"),"");
        List resultList = new ArrayList();
        PageBean<Map> list = new PageBean<Map>(resultList);
        return CommonUtil.ToResultHashMap(status,message,list);
    }

    /**
     * 影视剧类型列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getFilmTypeList")
    public HashMap<String,Object> getFilmTypeList(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        String name = CommonUtil.getStr(request.getParameter("name"),"");
        List resultList = new ArrayList();
        if(StringUtils.isNotBlank(name)) {
            resultList = categoryService.findByNameLike(name, CategoryType.FILM_TYPE,1);
        }else{
            resultList = categoryService.findByLevelAndType(1,CategoryType.FILM_TYPE);
        }
        PageBean<Map> list = new PageBean<Map>(resultList);
        return CommonUtil.ToResultHashMap(status,message,list);
    }

    /**
     * 场景风格列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getStageStyleList")
    public HashMap<String,Object> getStageStyleList(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        String name = CommonUtil.getStr(request.getParameter("name"),"");
        List resultList = new ArrayList();
        if(StringUtils.isNotBlank(name)) {
            resultList = categoryService.findByNameLike(name, CategoryType.STAGE_STYLE,1);
        }else{
            resultList = categoryService.findByLevelAndType(1,CategoryType.STAGE_STYLE);
        }
        PageBean<Map> list = new PageBean<Map>(resultList);
        return CommonUtil.ToResultHashMap(status,message,list);
    }

    /**
     * 场景一级类型列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getStageTypeList")
    public HashMap<String,Object> getStageTypeList(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        String name = CommonUtil.getStr(request.getParameter("name"),"");
        List resultList = new ArrayList();
        if(StringUtils.isNotBlank(name)) {
            resultList = categoryService.findByNameLike(name, CategoryType.STAGE_TYPE,1);
        }else{
            resultList = categoryService.findByLevelAndType(1,CategoryType.STAGE_TYPE);
        }
        PageBean<Map> list = new PageBean<Map>(resultList);
        return CommonUtil.ToResultHashMap(status,message,list);
    }

    /**
     * 场景二级级类型列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getSubStageTypeList")
    public HashMap<String,Object> getSubStageTypeList(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        String name = CommonUtil.getStr(request.getParameter("name"),"");
        List resultList = new ArrayList();
        if(StringUtils.isNotBlank(name)) {
            resultList = categoryService.findByNameLike(name, CategoryType.STAGE_TYPE,2);
        }else{
            resultList = categoryService.findByLevelAndType(2,CategoryType.STAGE_TYPE);
        }
        PageBean<Map> list = new PageBean<Map>(resultList);
        return CommonUtil.ToResultHashMap(status,message,list);
    }
}
