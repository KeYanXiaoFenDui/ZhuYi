package com.zy.controller;

import com.github.pagehelper.PageHelper;
import com.zy.enums.CategoryType;
import com.zy.service.ICategoryService;
import com.zy.util.CommonUtil;
import com.zy.util.PageBean;
import com.zy.util.constant.MessageConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    /**
     * 影视剧类型列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getFilmTypeList")
    public HashMap<String,Object> getFilmTypeList(HttpServletRequest request) {
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
