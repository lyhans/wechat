package com.gladfish.work.browse.service.impl;

import com.gladfish.common.consts.ViewUrl;
import com.gladfish.common.utils.LinkUtil;
import com.gladfish.work.browse.form.ViewHtmlForm;
import com.gladfish.work.browse.mapper.ViewHtmlEntityMapper;
import com.gladfish.work.browse.model.ViewHtmlEntity;
import com.gladfish.work.browse.service.IViewHtmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/1/27 21:50
 */
@Service
public class ViewHtmlServiceImpl implements IViewHtmlService {

    @Autowired
    private ViewHtmlEntityMapper viewHtmlEntityMapper;

    @Override
    public String createViewHtml(Long userId, String wechatUserId, String linkUrl, Boolean createType) {
        ViewHtmlEntity viewHtmlEntity = new ViewHtmlEntity();
        viewHtmlEntity.setUserId(userId);
        viewHtmlEntity.setWechatUserId(wechatUserId);
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
    public ViewHtmlForm getViewHtmlFormByUuid(String uuid) {
        ViewHtmlEntity viewHtmlEntity = viewHtmlEntityMapper.selectByUuid(uuid);
        return convertViewHtmlEntity2ViewHtmlForm(viewHtmlEntity);
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
        viewHtmlForm.setShowLimit(viewHtmlEntity.getShowLimit());
        viewHtmlForm.setCreateType(viewHtmlEntity.getCreateType());
        viewHtmlForm.setResourceUsage(viewHtmlEntity.getResourceUsage());
        viewHtmlForm.setViewCount(viewHtmlEntity.getViewCount());
        viewHtmlForm.setCreatTime(viewHtmlEntity.getCreatTime());
        viewHtmlForm.setUpdateTime(viewHtmlEntity.getUpdateTime());
        viewHtmlForm.setIsDel(viewHtmlEntity.getIsDel());
        return viewHtmlForm;
    }
}
