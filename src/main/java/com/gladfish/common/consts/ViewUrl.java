package com.gladfish.common.consts;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/1/27 21:57
 */
public class ViewUrl {

    public static final String SHOW_CREATE_VIEW_URL = "http://{domain}/browse/showcreateview";
    public static final String VIEW_HTML_URL = "http://{domain}/browse/viewhtml/{uuid}";
    public static final String USER_INFO_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
            "appid={appid}" +
            "&redirect_uri={uri}" +
            "&response_type=code&scope=snsapi_userinfo";

    public static final String CREATE_HTML_URL = "http://{domain}/browse/showcreateview";
}
