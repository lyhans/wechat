package com.gladfish.work.wechat.form;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/1/27 0:37
 */
public class WechatUserInfoForm {
    /** 用户的唯一标识 */
    private String openid;
    /** 用户昵称 */
    private String nickname;
    /** 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知 */
    private String sex;
    /** 用户个人资料填写的省份 */
    private String province;
    /** 普通用户个人资料填写的城市 */
    private String city;
    /** 国家，如中国为CN */
    private String country;
    /** 用户头像 */
    private String headimgurl;
    /** 用户特权信息 */
    private String privilege;
    /** 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段 */
    private String unionid;
    /** 用户是否订阅该公众号标识(0为关注，1关注) */
    private Integer subscribe;
    /** 用户的语言，简体中文为zh_CN */
    private String language;
    /** 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间 */
    private Long subscribe_time;
    /** 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注 */
    private String remark;
    /** 用户所在的分组ID（兼容旧的用户分组接口 */
    private Long groupid;
    /** 用户被打上的标签ID列表 */
    private List<Long> tagid_list;
    /** 返回用户关注的渠道来源 */
    private String subscribe_scene;
    private Long qr_scene;
    private String qr_scene_str;
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Long getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(Long subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public List<Long> getTagid_list() {
        return tagid_list;
    }

    public void setTagid_list(List<Long> tagid_list) {
        this.tagid_list = tagid_list;
    }

    public String getSubscribe_scene() {
        return subscribe_scene;
    }

    public void setSubscribe_scene(String subscribe_scene) {
        this.subscribe_scene = subscribe_scene;
    }

    public Long getQr_scene() {
        return qr_scene;
    }

    public void setQr_scene(Long qr_scene) {
        this.qr_scene = qr_scene;
    }

    public String getQr_scene_str() {
        return qr_scene_str;
    }

    public void setQr_scene_str(String qr_scene_str) {
        this.qr_scene_str = qr_scene_str;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
