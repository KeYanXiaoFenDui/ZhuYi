package com.zy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    @RequestMapping(value = "/")
    public String index(Model model)
    {

        return "zhuYiIndex";
    }
    @RequestMapping(value = "/newPage")
    public String newPage(Model model)
    {
        return "newPage";
    }
    @RequestMapping(value = "/template")
    public String commonFrame(Model model)
    {

        return "contentTemplate";
    }

}
