package com.gladfish.work.browse.controller;

import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.gladfish.common.config.ConfigProperties;
import com.gladfish.common.consts.ViewUrl;
import com.gladfish.common.enums.EnumErrorCode;
import com.gladfish.common.utils.LinkUtil;
import com.gladfish.frame.exception.BizException;
import com.gladfish.work.browse.form.BrowseForm;
import com.gladfish.work.browse.form.ViewHtmlForm;
import com.gladfish.work.browse.form.ViewRecordForm;
import com.gladfish.work.browse.service.IViewHtmlService;
import com.gladfish.work.wechat.form.WxJsapiSignatureForm;
import com.gladfish.work.wechat.service.IWechatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String testhtml(@PathVariable String uuid,@RequestParam(required = false) String code,Model model) throws BizException {
        if(StringUtils.isEmpty(code)){
            Map<String,Object> _params = new HashMap<>();
            _params.put("uuid",uuid);
            String url = LinkUtil.createUrl(ViewUrl.VIEW_HTML_URL,_params);
            _params.put("uri",url);
            String shareUrl = LinkUtil.createUrl(ViewUrl.USER_INFO_URL,_params);
            return "redirect:"+shareUrl;
        }
        ViewHtmlForm viewHtmlForm = viewHtmlService.browseViewHtml(uuid,code);
        String linkUrl = viewHtmlForm.getLinkUrl();
        model.addAttribute("linkUrl",linkUrl);
        if(viewHtmlForm.getSelf()) {
            List<ViewRecordForm> viewRecordForms = viewHtmlService.getViewRecords(viewHtmlForm.getId());
//        model.addAttribute("linkUrl","https://m.weibo.cn/1742566624/4335336757311166");
            BrowseForm browseForm = new BrowseForm();
            browseForm.setFriends(viewRecordForms);
            browseForm.setVip(false);
            browseForm.setCount(viewRecordForms.size());
            model.addAttribute("browseForm", browseForm);
        }
        Map<String,Object> _params = new HashMap<>();
        _params.put("uuid",uuid);
        String url = LinkUtil.createUrl(ViewUrl.VIEW_HTML_URL,_params);
        WxJsapiSignatureForm wxJsapiSignatureForm = wechatService.createJsapiSignature(url+"?code="+code+"&state=");
        model.addAttribute("appId", wxJsapiSignatureForm.getAppId());
        model.addAttribute("nonceStr", wxJsapiSignatureForm.getNonceStr());
        model.addAttribute("timestamp", wxJsapiSignatureForm.getTimestamp());
        model.addAttribute("signature", wxJsapiSignatureForm.getSignature());
        _params.put("uri",url);
//        String shareUrl = LinkUtil.createUrl(ViewUrl.USER_INFO_URL,_params);
        log.info("shareUrl="+url);
        model.addAttribute("shareUrl",url);
        model.addAttribute("title",viewHtmlForm.getTitle());
        model.addAttribute("imgUrl", viewHtmlForm.getImgUrl());
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
    public String showCreateView(@RequestParam(required = false) String code,Model model) throws BizException {
        log.info("showCreateView="+code);
        if(StringUtils.isEmpty(code)){
            Map<String,Object> _params = new HashMap<>();
            String url = LinkUtil.createUrl(ViewUrl.SHOW_CREATE_VIEW_URL,_params);
            _params.put("uri",url);
            String shareUrl = LinkUtil.createUrl(ViewUrl.USER_INFO_URL,_params);
            return "redirect:"+shareUrl;
        }
        try {
            Long userId = wechatService.getUserIdByCode(code);
            log.info("userId=" + userId);
            model.addAttribute("userId", userId);
            model.addAttribute("url","http://"+configProperties.getDomain()+"/browse/createhtml");

            List<ViewHtmlForm> list = viewHtmlService.queryViewHtmlsByUserId(userId);
            model.addAttribute("list", list);

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