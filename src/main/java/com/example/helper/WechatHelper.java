package com.example.helper;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.Base.Exception.BizException;
import com.example.common.consts.WechatUrl;
import com.example.common.enums.EnumWechatCode;
import com.example.form.MenuForm;
import com.example.form.WechatOauth2TokenForm;
import com.example.form.WechatUserInfoForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/1/4 0:02
 */
public class WechatHelper {

    private static final Logger log = LoggerFactory.getLogger(WechatHelper.class);

    /**
     * 获取access_token
     * @param appid 第三方用户唯一凭证
     * @param secret 第三方用户唯一凭证密钥，即appsecret
     * @return
     */
    public static String getWechatToken(String appid,String secret) throws BizException {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("grant_type",WechatUrl.GRANT_TYPE);
        params.put("appid",appid);
        params.put("secret",secret);
        String result =  HttpUtil.get(WechatUrl.ACCESS_TOKEN_URL,params);
        log.info("getWechatToken-----result:"+result);
        analysisResult(result);
        return JSON.parseObject(result).getString("access_token");
    }

    /**
     * 获取微信服务器IP地址
     * @param accessToken
     * @return
     * @throws BizException
     */
    public static List<String> getCallbackIp(String accessToken) throws BizException {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("access_token",accessToken);
        String result =  HttpUtil.get(WechatUrl.GET_CALLBACK_IP_URL,params);
        log.info("getCallbackIp-----result:"+result);
        analysisResult(result);
        return JSON.parseArray(JSON.parseObject(result).getString("ip_list"),String.class);
    }

    /**
     * 获取用户信息
     * @param accessToken
     * @param openid
     * @return
     * @throws BizException
     */
    public static WechatUserInfoForm getUserInfo(String accessToken,String openid) throws BizException{
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("access_token",accessToken);
        params.put("openid",openid);
        params.put("lang","zh_CN");
        String result =  HttpUtil.get(WechatUrl.USER_INFO_URL,params);
        log.info("getUserInfo-----result:"+result);
        analysisResult(result);
        return JSON.parseObject(result,WechatUserInfoForm.class);
    }

    /**
     * 网页授权
     * @param appid
     * @param secret
     * @param code
     * @return
     * @throws BizException
     */
    public static WechatOauth2TokenForm getOauth2Token(String appid,String secret,String code)throws BizException{
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("appid",appid);
        params.put("secret",secret);
        params.put("code",code);
        params.put("grant_type","authorization_code");
        String result =  HttpUtil.get(WechatUrl.OAUTH2_ACESS_TOKEN,params);
        log.info("getOauth2Token-----result:"+result);
        analysisResult(result);
        return JSON.parseObject(result,WechatOauth2TokenForm.class);
    }

    /**
     * 获取网页用户信息
     * @param web_access_token
     * @param openid
     * @return
     * @throws BizException
     */
    public static WechatUserInfoForm getSnsUserInfo(String web_access_token,String openid)throws BizException{
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("access_token",web_access_token);
        params.put("openid",openid);
        params.put("lang","zh_CN");
        String result =  HttpUtil.get(WechatUrl.SNS_USER_INFO_URL,params);
        log.info("getSnsUserInfo-----result:"+result);
        analysisResult(result);
        return JSON.parseObject(result,WechatUserInfoForm.class);
    }

    public static void menuCreate(String accessToken, MenuForm menuForm) throws BizException {
        Map<String,String> map = new HashMap<>();
        map.put("access_token",accessToken);
        String url = WechatUrl.MENU_CREATE_URL+HttpUtil.toParams(map);
        log.info("menuCreate-----url:"+url);
        String result = HttpUtil.post(url,JSON.toJSONString(menuForm));
        log.info("menuCreate-----result:"+result);
        analysisResult(result);
    }

    /**
     * 解析错误码
     * @param result
     * @throws BizException
     */
    private static void analysisResult(String result) throws BizException{
        JSONObject jsonResult = JSONObject.parseObject(result);
        String code = jsonResult.getString("errcode");
        if(StringUtils.isEmpty(code)){
            return;
        }
        EnumWechatCode enumWechatCode = EnumWechatCode.getEnum(code);
        if(EnumWechatCode.REQUEST_SUCCESSFUL == enumWechatCode){
            return;
        }
        throw new BizException(code,enumWechatCode.getName());
    }

}
