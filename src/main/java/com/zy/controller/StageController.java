package com.zy.controller;

import com.github.pagehelper.PageHelper;
import com.zy.domain.Category;
import com.zy.domain.Stage;
import com.zy.enums.CategoryType;
import com.zy.service.ICategoryService;
import com.zy.service.IStageService;
import com.zy.util.CommonUtil;
import com.zy.util.PageBean;
import com.zy.util.constant.MessageConstant;
import com.zy.vo.StageQueryListVo;
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
import java.util.*;

@Controller
@RequestMapping("/stage")
public class StageController {
    @Autowired
    private IStageService stageService;

    /**
     * 场景列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getStageList")
    public String getStageList(Model model, StageQueryListVo listVo ,HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        String name = CommonUtil.getStr(request.getParameter("name"),"");
        List resultList = stageService.findByQueryVo(listVo);
        PageBean<Map> list = new PageBean<Map>(resultList);
//        return CommonUtil.ToResultHashMap(status,message,list);
        model.addAttribute("stageList",list);
        model.addAttribute("orderList",getOrderList());
        model.addAttribute("name",name);
        return "stageList";
    }


    /**
     *新增分类数据
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/insertCategory")
    @ResponseBody
    public HashMap<String,Object> insertCategory(Stage c, HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();

        try {
            if(c!=null){
                c.setCreateTime(new Date());
                c.setStatus(1);
                int result = stageService.insertStage(c);
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
            Stage exists = stageService.getStage(c.getId());
            if(c!=null && exists!=null){
                exists.setName(c.getName());
                exists.setNameEn(c.getNameEn());
                int result = stageService.updateStage(exists);
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
            int result = stageService.deleteStage(categoryId);
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


    private List<String> getOrderList(){
        List<String> result = new ArrayList<>();
        for(int i=0;i<=10;i++){
            result.add(String.valueOf(i));
        }
        return result;
    }
}
