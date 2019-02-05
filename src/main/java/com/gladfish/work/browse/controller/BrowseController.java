package com.gladfish.work.browse.controller;

import com.gladfish.common.config.ConfigProperties;
import com.gladfish.common.enums.EnumErrorCode;
import com.gladfish.frame.exception.BizException;
import com.gladfish.work.browse.form.BrowseForm;
import com.gladfish.work.browse.form.ViewHtmlForm;
import com.gladfish.work.browse.form.ViewRecordForm;
import com.gladfish.work.browse.service.IViewHtmlService;
import com.gladfish.work.wechat.form.WechatUserInfoForm;
import com.gladfish.work.wechat.form.WxJsapiSignatureForm;
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

    @Autowired
    private ConfigProperties configProperties;

    @RequestMapping("/viewhtml/{uuid}")
    public String testhtml(@PathVariable String uuid,@RequestParam String code,Model model) throws BizException {
        ViewHtmlForm viewHtmlForm = viewHtmlService.browseViewHtml(uuid,code);
        model.addAttribute("linkUrl",viewHtmlForm.getLinkUrl());
        if(viewHtmlForm.getSelf()) {
            List<ViewRecordForm> viewRecordForms = viewHtmlService.getViewRecords(viewHtmlForm.getId());
//        model.addAttribute("linkUrl","https://m.weibo.cn/1742566624/4335336757311166");
            BrowseForm browseForm = new BrowseForm();
            browseForm.setFriends(viewRecordForms);
            browseForm.setVip(false);
            browseForm.setCount(viewRecordForms.size());
            model.addAttribute("browseForm", browseForm);
        }
        String url = "http://"+configProperties.getDomain()+"/browse/viewhtml/"+uuid+"?code="+code+"&state=";
        WxJsapiSignatureForm wxJsapiSignatureForm = wechatService.createJsapiSignature(url);
        model.addAttribute("appId", wxJsapiSignatureForm.getAppId());
        model.addAttribute("nonceStr", wxJsapiSignatureForm.getNonceStr());
        model.addAttribute("timestamp", wxJsapiSignatureForm.getTimestamp());
        model.addAttribute("signature", wxJsapiSignatureForm.getSignature());
//        model.addAttribute("viewRecordForms",viewRecordForms);
//        model.addAttribute("isVip",false);
//        model.addAttribute("count",viewRecordForms.size());
        return "view_html";
    }

    @RequestMapping("/createhtml")
    public String createhtml(@RequestParam Long userId,@RequestParam String linkUrl){
        String url = viewHtmlService.createViewHtml(userId,linkUrl,false);
        return "redirect:"+url;
    }

    @RequestMapping("/showcreateview")
    public String showCreateView(@RequestParam String code,Model model) throws BizException {
        log.info("showCreateView="+code);
        try {
            Long userId = wechatService.getUserIdByCode(code);
            log.info("userId=" + userId);
            model.addAttribute("userId", userId);
        }catch (BizException e){
            if(EnumErrorCode.UNSUBSCRIBE.getCode().equals(e.getCode())){
                return "test";
            }else{
                throw e;
            }
        }
        return "create_view";
    }

}