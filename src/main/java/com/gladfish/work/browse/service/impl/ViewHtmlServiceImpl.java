package com.gladfish.work.browse.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.gladfish.common.config.ConfigProperties;
import com.gladfish.common.consts.ViewUrl;
import com.gladfish.common.utils.HtmlUtil;
import com.gladfish.common.utils.LinkUtil;
import com.gladfish.frame.exception.BizException;
import com.gladfish.support.helper.WechatHelper;
import com.gladfish.work.browse.form.ViewHtmlForm;
import com.gladfish.work.browse.form.ViewRecordForm;
import com.gladfish.work.browse.mapper.ViewHtmlEntityMapper;
import com.gladfish.work.browse.mapper.ViewHtmlRecordEntityMapper;
import com.gladfish.work.browse.model.ViewHtmlEntity;
import com.gladfish.work.browse.model.ViewHtmlRecordEntity;
import com.gladfish.work.browse.service.IViewHtmlService;
import com.gladfish.work.pubase.mapper.PublicBaseInfoEntityMapper;
import com.gladfish.work.pubase.model.PublicBaseInfoEntity;
import com.gladfish.work.pubase.model.UserEntity;
import com.gladfish.work.pubase.service.IPublicBaseInfoService;
import com.gladfish.work.pubase.service.IUserService;
import com.gladfish.work.pubase.service.impl.PublicBaseInfoServiceImpl;
import com.gladfish.work.wechat.form.WechatOauth2TokenForm;
import com.gladfish.work.wechat.form.WechatUserInfoForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/1/27 21:50
 */
@Service
public class ViewHtmlServiceImpl implements IViewHtmlService {

    private final static Logger log = LoggerFactory.getLogger(ViewHtmlServiceImpl.class);

    @Autowired
    private IPublicBaseInfoService publicBaseInfoService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ViewHtmlEntityMapper viewHtmlEntityMapper;

    @Autowired
    private ViewHtmlRecordEntityMapper viewHtmlRecordEntityMapper;

    @Override
    public String createViewHtml(Long userId, String linkUrl, Boolean createType) {
        UserEntity userEntity = userService.getById(userId);

        String htmlStr= HtmlUtil.getHtml(linkUrl);
//        String htmlStr= HttpUtil.get("https://m.weibo.cn/1742566624/4335336757311166");
        String title = "来自"+userEntity.getName()+"的分享";
        String htmlTitle = HtmlUtil.getTitle(htmlStr);
        if(!StringUtils.isEmpty(htmlTitle)){
            title = htmlTitle;
        }
        String imgUrl = "http://upload.m4.cn/2011/0117/1295231531212.jpg";
        String htmlImg = HtmlUtil.getFirstImg(htmlStr);
        if(!StringUtils.isEmpty(htmlImg)) {
            if(htmlImg.startsWith("//")){
                htmlImg = "http:"+htmlImg;
            }
            imgUrl = htmlImg;
        }

        ViewHtmlEntity viewHtmlEntity = new ViewHtmlEntity();
        viewHtmlEntity.setUserId(userId);
        viewHtmlEntity.setWechatUserId(userEntity.getRelatedId());
        viewHtmlEntity.setTitle(title);
        viewHtmlEntity.setImgUrl(imgUrl);
        viewHtmlEntity.setLinkUrl(linkUrl);
        viewHtmlEntity.setCreateType(createType);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        viewHtmlEntity.setUuid(uuid);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("uuid",uuid);
        String url = LinkUtil.createUrl(ViewUrl.VIEW_HTML_URL,params);
        viewHtmlEntity.setUrl(url);
        viewHtmlEntityMapper.insertSelective(viewHtmlEntity);
        return url;
    }

    @Override
    public ViewHtmlForm browseViewHtml(String uuid,String code) throws BizException {
        ViewHtmlEntity viewHtmlEntity = viewHtmlEntityMapper.selectByUuid(uuid);
        PublicBaseInfoEntity publicBaseInfoEntity = publicBaseInfoService.getGladFishPublicBaseInfoEntity();
        WechatOauth2TokenForm wechatOauth2TokenForm = WechatHelper.getOauth2Token(publicBaseInfoEntity.getAppid(),publicBaseInfoEntity.getSecret(),code);
        String openid = wechatOauth2TokenForm.getOpenId();
        String creatorId = viewHtmlEntity.getWechatUserId();
        boolean self = true;
        if(!openid.equals(creatorId)) {
            log.info("非本人浏览，需要记录");
            self = false;
            WechatUserInfoForm wechatUserInfoForm = WechatHelper.getSnsUserInfo(wechatOauth2TokenForm.getAccessToken(), openid);
            ViewHtmlRecordEntity viewHtmlRecordEntity = new ViewHtmlRecordEntity();
            viewHtmlRecordEntity.setViewHtmlId(viewHtmlEntity.getId());
            viewHtmlRecordEntity.setOpenid(openid);
            viewHtmlRecordEntity.setNickname(wechatUserInfoForm.getNickname());
            viewHtmlRecordEntity.setHeadimgurl(wechatUserInfoForm.getHeadimgurl());
            viewHtmlRecordEntity.setUserInfo(JSON.toJSONString(wechatUserInfoForm));
            viewHtmlRecordEntityMapper.insertSelective(viewHtmlRecordEntity);
        }
        ViewHtmlForm viewHtmlForm = convertViewHtmlEntity2ViewHtmlForm(viewHtmlEntity);
        viewHtmlForm.setSelf(self);
        return viewHtmlForm;
    }

    private ViewHtmlForm convertViewHtmlEntity2ViewHtmlForm(ViewHtmlEntity viewHtmlEntity){
        if(viewHtmlEntity == null){
            return null;
        }
        ViewHtmlForm viewHtmlForm = new ViewHtmlForm();
        viewHtmlForm.setId(viewHtmlEntity.getId());
        viewHtmlForm.setUserId(viewHtmlEntity.getUserId());
        viewHtmlForm.setWechatUserId(viewHtmlEntity.getWechatUserId());
        viewHtmlForm.setLinkUrl(viewHtmlEntity.getLinkUrl());
        viewHtmlForm.setUrl(viewHtmlEntity.getUrl());
        viewHtmlForm.setUuid(viewHtmlEntity.getUuid());
        viewHtmlForm.setTitle(viewHtmlEntity.getTitle());
        viewHtmlForm.setImgUrl(viewHtmlEntity.getImgUrl());
        viewHtmlForm.setShowLimit(viewHtmlEntity.getShowLimit());
        viewHtmlForm.setCreateType(viewHtmlEntity.getCreateType());
        viewHtmlForm.setResourceUsage(viewHtmlEntity.getResourceUsage());
        viewHtmlForm.setViewCount(viewHtmlEntity.getViewCount());
        viewHtmlForm.setCreatTime(viewHtmlEntity.getCreatTime());
        viewHtmlForm.setUpdateTime(viewHtmlEntity.getUpdateTime());
        viewHtmlForm.setIsDel(viewHtmlEntity.getIsDel());
        return viewHtmlForm;
    }

    @Override
    public List<ViewRecordForm> getViewRecords(Long viewHtmlId) throws BizException {
        List<ViewRecordForm> viewRecordForms = new ArrayList<ViewRecordForm>();
        List<ViewHtmlRecordEntity> viewHtmlRecordEntities = viewHtmlRecordEntityMapper.listByViewHtmlId(viewHtmlId);
        if(CollectionUtil.isEmpty(viewHtmlRecordEntities)){
            return viewRecordForms;
        }
        for(ViewHtmlRecordEntity viewHtmlRecordEntity:viewHtmlRecordEntities){
            viewRecordForms.add(convertViewHtmlRecordEntity2ViewRecordForm(viewHtmlRecordEntity));
        }
        return viewRecordForms;
    }

    private ViewRecordForm convertViewHtmlRecordEntity2ViewRecordForm(ViewHtmlRecordEntity viewHtmlRecordEntity){
        if(viewHtmlRecordEntity == null){
            return null;
        }
        ViewRecordForm viewRecordForm = new ViewRecordForm();
        viewRecordForm.setHeadimg(viewHtmlRecordEntity.getHeadimgurl());
        viewRecordForm.setNickname(viewHtmlRecordEntity.getNickname());
        return viewRecordForm;
    }

    @Override
    public List<ViewHtmlForm> queryViewHtmlsByUserId(Long userId) throws BizException {
        List<ViewHtmlForm> viewHtmlForms = new ArrayList<>();
        List<ViewHtmlEntity> viewHtmlEntities = viewHtmlEntityMapper.selectByUserId(userId);
        if(CollectionUtil.isEmpty(viewHtmlEntities)){
            return viewHtmlForms;
        }
        for(ViewHtmlEntity viewHtmlEntity:viewHtmlEntities){
            viewHtmlForms.add(convertViewHtmlEntity2ViewHtmlForm(viewHtmlEntity));
        }
        return viewHtmlForms;
    }

}
