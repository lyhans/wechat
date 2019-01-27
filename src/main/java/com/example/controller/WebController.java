package com.example.controller;

import com.example.form.ViewHtmlForm;
import com.example.service.IViewHtmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class WebController {

    @Autowired
    private IViewHtmlService viewHtmlService;

    @RequestMapping("viewhtml/{uuid}")
    public String testhtml(@PathVariable String uuid,Model model){
        ViewHtmlForm viewHtmlForm = viewHtmlService.getViewHtmlFormByUuid(uuid);
        model.addAttribute("linkUrl",viewHtmlForm.getLinkUrl());
        return "viex_html";
    }

    @RequestMapping("createhtml")
    public String createhtml(String linkUrl){
        String url = viewHtmlService.createViewHtml(1L,"test1",linkUrl,false);
        return "redirect:"+url;
    }

    @RequestMapping("showcreateview")
    public String showCreateView(){
        return "create_view";
    }

}