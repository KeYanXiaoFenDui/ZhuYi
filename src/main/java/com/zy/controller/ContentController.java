package com.zy.controller;

import com.github.pagehelper.PageHelper;
import com.zy.domain.*;
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

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private IRotationPicService rotationPicService;
    @Autowired
    private ICrewServiceService crewServiceService;
    @Autowired
    private IResponseInfoService responseInfoService;
    @Autowired
    public RedisComponentUtil redisComponentUtil;


    //---------------------------------轮播图-------------------------------------------------------------------------------------------------------
    /**
     *管理后台获取轮播图列表
     */
    @RequestMapping(value = "/getRotationPicList")
    public String getRotationPicList(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();

        List<RotationPic> resultList = rotationPicService.getRotationPicList();
        if(resultList != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }

        m.addAttribute("rotationPicList",resultList);
        m.addAttribute("pageTitle","内容管理");
        setAdminMsg(m, request, session);
        return "rotationPicListPage";
    }
    /**
     *根据Id获取轮播图数据
     */
    @RequestMapping(value = "/getRotationPicById")
    @ResponseBody
    public HashMap<String,Object> getRotationPicById(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int id = Integer.parseInt(CommonUtil.getStr(request.getParameter("id"),"-500"));
        RotationPic c = rotationPicService.getRotationPic(id);
        if(c != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        return CommonUtil.ToResultHashMap(status,message,c);
    }
    /**
     *新增轮播图
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/insertRotationPic")
    @ResponseBody
    public HashMap<String,Object> insertRotationPic(RotationPic rp, HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();

        try {
            if(rp!=null){
                Date d = new Date();
                rp.setCreateTime(d);
                rp.setUpdateTime(d);
//                exists.setOperaterId();=======================================================
                rp.setStatus(1);
                int result = rotationPicService.insertRotationPic(rp);
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
     *更新轮播图
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/updateRotationPic")
    @ResponseBody
    public HashMap<String,Object> updateRotationPic(RotationPic rp, HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();

        try {
            RotationPic exists = rotationPicService.getRotationPic(rp.getId());
            if(rp!=null && exists!=null){
                exists.setPicName(rp.getPicName());
                exists.setRequestUrl(rp.getRequestUrl());
                exists.setOrder(rp.getOrder());
                exists.setPicUrl(rp.getPicUrl());
                exists.setUpdateTime(new Date());
//                exists.setOperaterId();=======================================================
                int result = rotationPicService.updateRotationPic(exists);
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
     *删除轮播图
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/deleteRotationPic")
    @ResponseBody
    public HashMap<String,Object> deleteRotationPic(int rotationPicId, HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        Admin a = getAdminMsg(session);
        try {
            int result = rotationPicService.deleteRotationPic(rotationPicId,a.getId());
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

    //---------------------------------剧组服务---------------------------------------------------------------------------------------------------------
    /**
     *管理后台获新增剧组服务
     */
    @RequestMapping(value = "/newCrewService")
    public String newCrewService(Model m,HttpServletRequest request,HttpSession session) {
        m.addAttribute("pageTitle","内容管理");
        setAdminMsg(m, request, session);
        return "newCrewServicePage";
    }
    /**
     *管理后台获取剧组服务列表
     */
    @RequestMapping(value = "/getCrewServiceList")
    public String getCrewServiceList(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        List<CrewService> resultList = crewServiceService.getCrewServiceList();
        if(resultList != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        PageBean<CrewService> list = new PageBean<CrewService>(resultList);

        m.addAttribute("crewServiceList",list);
        m.addAttribute("pageTitle","内容管理");
        setAdminMsg(m, request, session);
        return "crewServiceListPage";
    }
    /**
     *根据Id获取剧组服务数据
     */
    @RequestMapping(value = "/getCrewServiceById")
    public String getCrewServiceById(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int id = Integer.parseInt(CommonUtil.getStr(request.getParameter("id"),"-500"));
        CrewService c = crewServiceService.getCrewService(id);
        if(c != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        m.addAttribute("crewServiceMsg",c);
        m.addAttribute("pageTitle","内容管理");
        setAdminMsg(m,request,session);
        return "updateCrewServicePage";
    }
    /**
     *新增剧组服务
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/insertCrewService")
    @ResponseBody
    public HashMap<String,Object> insertCrewService(CrewService cs,HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();

        try {
            if(cs!=null){
                Date d = new Date();
                cs.setCreateTime(d);
//                cs.setOperaterId();=======================================================
                cs.setStatus(1);
                int result = crewServiceService.insertCrewService(cs);
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
     *更新剧组服务
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/updateCrewService")
    @ResponseBody
    public HashMap<String,Object> updateCrewService(CrewService cs,HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();

        try {
            if(cs!=null){
                CrewService oldCs = crewServiceService.getCrewService(cs.getId());
                oldCs.setName(cs.getName());
                oldCs.setNameEn(cs.getNameEn());
                oldCs.setDescription(cs.getDescription());
                oldCs.setDescriptionEn(cs.getDescriptionEn());
                oldCs.setOrder(cs.getOrder());
                oldCs.setServiceLogoUrl(cs.getServiceLogoUrl());
                oldCs.setServicePicUrl(cs.getServicePicUrl());
                int result = crewServiceService.updateCrewService(oldCs);
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
     *删除剧组服务
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/deleteCrewService")
    @ResponseBody
    public HashMap<String,Object> deleteCrewService(int id, HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        try {
            int result = crewServiceService.deleteCrewService(id);
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

    //-----------------------------------反馈信息-------------------------------------------------------------------------------------------------------
    /**
     *管理后台获取反馈信息列表
     */
    @RequestMapping(value = "/getResponseInfoList")
    public String getResponseInfoList(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        List<ResponseInfo> resultList = responseInfoService.getResponseInfoList();
        if(resultList != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }

        m.addAttribute("responseInfoList",resultList);
        m.addAttribute("pageTitle","内容管理");
        setAdminMsg(m, request, session);
        return "responseInfoListPage";
    }
    /**
     *更新反馈信息
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/updateResponseInfo")
    @ResponseBody
    public HashMap<String,Object> updateResponseInfo(ResponseInfo ri,HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();

        try {
            if(ri!=null){
                ResponseInfo oldRi = responseInfoService.getResponseInfo(ri.getId());
                oldRi.setContent(ri.getContent());
                int result = responseInfoService.updateResponseInfo(oldRi);
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
    private StringBuffer getStringBuffFromStream(HttpServletRequest req) {
        ServletInputStream is;
        try {
            is = req.getInputStream();
            BufferedReader bf=new BufferedReader( new InputStreamReader(req.getInputStream(),"UTF-8"));
            //最好在将字节流转换为字符流的时候 进行转码
            StringBuffer buffer=new StringBuffer();
            String line="";
            while((line=bf.readLine())!=null){
                buffer.append(line);
            }
            System.out.println("buffer");
            System.out.println(buffer);
            return buffer;

        } catch (IOException e) {
            e.printStackTrace();
            return new StringBuffer();
        }
    }
}
