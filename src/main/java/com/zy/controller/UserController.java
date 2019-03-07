package com.zy.controller;

import com.github.pagehelper.PageHelper;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IMenuService menuService;


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


}
