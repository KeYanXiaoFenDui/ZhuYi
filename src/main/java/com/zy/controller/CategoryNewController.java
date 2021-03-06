package com.zy.controller;

import com.github.pagehelper.PageHelper;
import com.zy.domain.Category;
import com.zy.enums.CategoryType;
import com.zy.service.ICategoryService;
import com.zy.util.CommonUtil;
import com.zy.util.PageBean;
import com.zy.util.RedisComponentUtil;
import com.zy.util.constant.MessageConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/category")
public class CategoryNewController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    public RedisComponentUtil redisComponentUtil;

    /**
     * 影视剧类型列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getFilmTypeList")
    public String getFilmTypeList(Model model, HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        String name = CommonUtil.getStr(request.getParameter("name"),"");
        List resultList = new ArrayList();
        if(StringUtils.isNotBlank(name)) {
            resultList = categoryService.findByNameLike(name, CategoryType.FILM_TYPE,1);
        }else{
            resultList = categoryService.findByLevelAndType(1,CategoryType.FILM_TYPE);
        }
        PageBean<Map> list = new PageBean<Map>(resultList);
//        return CommonUtil.ToResultHashMap(status,message,list);
        model.addAttribute("filmTypeList",list);
        model.addAttribute("orderList",getOrderList());
        model.addAttribute("level",1);
        model.addAttribute("name",name);
        model.addAttribute("pageTitle","分类管理");
        setAdminMsg(model, request, session);
        return "filmTypeListPage";
    }


    /**
     * 场景风格列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getStageStyleList")
    public String getStageStyleList(Model model, HttpServletRequest request,HttpSession session) {
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
//        return CommonUtil.ToResultHashMap(status,message,list);
        model.addAttribute("stageStyleList",list);
        model.addAttribute("orderList",getOrderList());
        model.addAttribute("level",1);
        model.addAttribute("name",name);
        model.addAttribute("pageTitle","分类管理");
        setAdminMsg(model, request, session);
        return "stageStyleListPage";
    }

    /**
     * 场景一级类型列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getStageTypeList")
    public String getStageTypeList(Model model,HttpServletRequest request,HttpSession session) {
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
        model.addAttribute("stageTypeList",list);
        model.addAttribute("orderList",getOrderList());
        model.addAttribute("level",1);
        model.addAttribute("name",name);
        model.addAttribute("pageTitle","分类管理");
        setAdminMsg(model, request, session);
        return "stageTypeListPage";
    }

    /**
     * 场景二级级类型列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getStageType2ndList")
    public String getStageType2ndList(Model model,HttpServletRequest request
            ,String parentId,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        String name = CommonUtil.getStr(request.getParameter("name"),"");
        List resultList = categoryService.findSubByNameLike(parentId,name,CategoryType.STAGE_TYPE);
        PageBean<Map> list = new PageBean<Map>(resultList);
        model.addAttribute("stageType2ndList",list);
        model.addAttribute("orderList",getOrderList());
        model.addAttribute("parentId",parentId);
        model.addAttribute("level",2);
        model.addAttribute("name",name);
        model.addAttribute("pageTitle","分类管理");
        setAdminMsg(model, request, session);
        return "stageType2ndListPage";
    }


    /**
     *根据Id获取分类数据
     */
    @RequestMapping(value = "/getCategoryById")
    @ResponseBody
    public HashMap<String,Object> getCategory(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int id = Integer.parseInt(CommonUtil.getStr(request.getParameter("id"),"-500"));
        Category c = categoryService.getCategory(id);
        if(c != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        return CommonUtil.ToResultHashMap(status,message,c);
    }
    /**
     *新增分类数据
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/insertCategory")
    @ResponseBody
    public HashMap<String,Object> insertCategory(Category c, HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();

        try {
            if(c!=null){
                c.setCreateTime(new Date());
                c.setStatus(1);
                int result = categoryService.insertCategory(c);
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
     *更新分类数据
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/updateCategory")
    @ResponseBody
    public HashMap<String,Object> updateCategory(Category c, HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();

        try {
            Category exists = categoryService.getCategory(c.getId());
            if(c!=null && exists!=null){
                exists.setName(c.getName());
                exists.setNameEn(c.getNameEn());
                exists.setOrder(c.getOrder());
                int result = categoryService.updateCategory(exists);
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
     *删除分类数据
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/deleteCategory/{categoryId}")
    @ResponseBody
    public HashMap<String,Object> deleteCategory(@PathVariable("categoryId")int categoryId, HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        try {
            int result = categoryService.cascadeDeleteCategory(categoryId);
            if (result >= 1){
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
     * 场景地区列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getStageAreaList")
    public String getStageAreaList(Model model,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        String name = CommonUtil.getStr(request.getParameter("name"),"");
        List resultList = new ArrayList();
        if(StringUtils.isNotBlank(name)) {
            resultList = categoryService.findByNameLike(name, CategoryType.STAGE_LOCALED,1);
        }else{
            resultList = categoryService.findByLevelAndType(1,CategoryType.STAGE_LOCALED);
        }
        PageBean<Map> list = new PageBean<Map>(resultList);
        model.addAttribute("stageAreaList",list);
        model.addAttribute("orderList",getOrderList());
        model.addAttribute("level",1);
        model.addAttribute("name",name);
        model.addAttribute("pageTitle","分类管理");
        setAdminMsg(model, request, session);
        return "stageAreaListPage";
    }

    /**
     * 场景二级场景地区列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getStageArea2ndList")
    public String getStageArea2ndList(Model model,HttpServletRequest request
            ,String parentId,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        String name = CommonUtil.getStr(request.getParameter("name"),"");
        List resultList = categoryService.findSubByNameLike(parentId,name,CategoryType.STAGE_LOCALED);
        PageBean<Map> list = new PageBean<Map>(resultList);
        model.addAttribute("stageAreaList",list);
        model.addAttribute("orderList",getOrderList());
        model.addAttribute("parentId",parentId);
        model.addAttribute("level",2);
        model.addAttribute("name",name);
        model.addAttribute("pageTitle","分类管理");
        setAdminMsg(model, request, session);
        return "stageArea2ndListPage";
    }

    /**
     * 场景三级场景地区列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getStageArea3rdList")
    public String getStageArea3ndList(Model model,HttpServletRequest request
            ,String parentId,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        String name = CommonUtil.getStr(request.getParameter("name"),"");
        List resultList = categoryService.findSubByNameLike(parentId,name,CategoryType.STAGE_LOCALED);
        PageBean<Map> list = new PageBean<Map>(resultList);
        model.addAttribute("stageAreaList",list);
        model.addAttribute("orderList",getOrderList());
        model.addAttribute("parentId",parentId);
        model.addAttribute("level",3);
        model.addAttribute("name",name);
        model.addAttribute("pageTitle","分类管理");
        setAdminMsg(model, request, session);
        return "stageArea3rdListPage";
    }

    /**
     *获取子分类数据
     */
    @RequestMapping(value = "/getSubCategoryList")
    @ResponseBody
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

    private List<String> getOrderList(){
        List<String> result = new ArrayList<>();
        for(int i=0;i<=10;i++){
            result.add(String.valueOf(i));
        }
        return result;
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
