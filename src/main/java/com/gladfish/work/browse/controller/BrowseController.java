package com.gladfish.work.browse.controller;

import com.gladfish.frame.exception.BizException;
import com.gladfish.work.browse.form.ViewHtmlForm;
import com.gladfish.work.browse.form.ViewRecordForm;
import com.gladfish.work.browse.service.IViewHtmlService;
import com.gladfish.work.wechat.form.WechatUserInfoForm;
import com.gladfish.work.wechat.service.IWechatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/browse")
public class BrowseController {

    private final static Logger log = LoggerFactory.getLogger(BrowseController.class);

    @Autowired
    private IViewHtmlService viewHtmlService;

    @Autowired
    private IWechatService wechatService;

    @RequestMapping("/viewhtml/{uuid}")
    public String testhtml(@PathVariable String uuid,@RequestParam String code,Model model) throws BizException {
//        ViewHtmlForm viewHtmlForm = viewHtmlService.browseViewHtml(uuid,code);
//        model.addAttribute("linkUrl",viewHtmlForm.getLinkUrl());
        List<ViewRecordForm> viewRecordForms = viewHtmlService.getViewRecords(10L);
        model.addAttribute("linkUrl","https://m.weibo.cn/1742566624/4335336757311166");
        model.addAttribute("viewRecordForms",viewRecordForms);
        model.addAttribute("isVip",false);
        model.addAttribute("count",viewRecordForms.size());
        return "view_html";
    }

    @RequestMapping("/createhtml")
    public String createhtml(String linkUrl){
        String url = viewHtmlService.createViewHtml(1L,"test1",linkUrl,false);
        return "redirect:"+url;
    }

    @RequestMapping("/showcreateview")
    public String showCreateView(@RequestParam String code) throws BizException {
        log.info("showCreateView="+code);
//        WechatUserInfoForm wechatUserInfoForm = wechatService.getUserInfo(2L,code);
//        log.info("userInfo="+wechatUserInfoForm);
//        Integer subscribe = wechatUserInfoForm.getSubscribe();
//        if(subscribe.equals(0)){
//            return "test";
//        }
        return "create_view";
    }

}