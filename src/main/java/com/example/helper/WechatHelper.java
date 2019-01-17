package com.example.helper;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.base.exception.BizException;
import com.example.common.consts.WechatUrl;
import com.example.common.enums.EnumWechatCode;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/1/4 0:02
 */
@Service
public class WechatHelper {

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
        analysisResult(result);
        return result;
    }

    /**
     * 解析错误码
     * @param result
     * @throws BizException
     */
    private static void analysisResult(String result) throws BizException{
        JSONObject jsonResult = JSONObject.parseObject(result);
        String code = jsonResult.getString("errcode");
        if(EnumWechatCode.REQUEST_SUCCESSFUL.equals(EnumWechatCode.getEnum(code))){
            return;
        }
        throw new BizException(EnumWechatCode.getEnum(code).getName());
    }

}
