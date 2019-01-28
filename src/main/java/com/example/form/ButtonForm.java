package com.example.form;

import com.example.common.enums.EnumButtonType;

import java.util.List;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/1/28 23:23
 */
public class ButtonForm {
    private List<ButtonForm> sub_button;
    private String type;
    private String name;
    private String key;
    private String url;
    private String media_id;
    private String appid;
    private String pagepath;

    public List<ButtonForm> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<ButtonForm> sub_button) {
        this.sub_button = sub_button;
    }

    public String getType() {
        return type;
    }

    public void setType(EnumButtonType enumButtonType) {
        this.type = enumButtonType.getMsg();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }
}
