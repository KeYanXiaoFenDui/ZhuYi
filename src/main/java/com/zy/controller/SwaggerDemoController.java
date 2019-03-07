package com.zy.controller;

import com.github.pagehelper.PageHelper;
import com.zy.service.IMenuService;
import com.zy.util.CommonUtil;
import com.zy.util.PageBean;
import com.zy.util.constant.MessageConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/13.
 */
@RestController
@RequestMapping("api")
@Api("swaggerDemoController相关的api")
public class SwaggerDemoController {

    @Autowired
    private IMenuService menuService;

    private static final Logger logger= LoggerFactory.getLogger(SwaggerDemoController.class);

    /**
     *管理员列表查询
     *管理后台查询管理员列表
     */
    @ApiOperation(value = "菜单列表查询", notes = "管理后台菜单列表查询接口")
    @ApiImplicitParam(name = "id", value = "学生ID", paramType = "path", required = true, dataType = "Integer")
    @RequestMapping(value = "/getMenuList/{id}", method = RequestMethod.GET)
    public HashMap<String,Object> getMenuList(HttpServletRequest request,@PathVariable int id) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        String account = CommonUtil.getStr(request.getParameter("account"),"");
        String name = CommonUtil.getStr(request.getParameter("name"),"");
        List resultList = new ArrayList();
        PageBean<Map> list = new PageBean<Map>(resultList);
        return CommonUtil.ToResultHashMap(status,message,id);
    }


}
