package com.example.model;

import java.util.Date;

public class ViewHtmlEntity {
    private Long id;

    private Long userId;

    private String wechatUserId;

    private String linkUrl;

    private String url;

    private String uuid;

    private Integer showLimit;

    private Boolean createType;

    private Integer resourceUsage;

    private Integer viewCount;

    private Date creatTime;

    private Date updateTime;

    private Boolean isDel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getWechatUserId() {
        return wechatUserId;
    }

    public void setWechatUserId(String wechatUserId) {
        this.wechatUserId = wechatUserId == null ? null : wechatUserId.trim();
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Integer getShowLimit() {
        return showLimit;
    }

    public void setShowLimit(Integer showLimit) {
        this.showLimit = showLimit;
    }

    public Boolean getCreateType() {
        return createType;
    }

    public void setCreateType(Boolean createType) {
        this.createType = createType;
    }

    public Integer getResourceUsage() {
        return resourceUsage;
    }

    public void setResourceUsage(Integer resourceUsage) {
        this.resourceUsage = resourceUsage;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean del) {
        isDel = del;
    }
}