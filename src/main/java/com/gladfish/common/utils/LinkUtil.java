package com.gladfish.common.utils;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpUtil;
import com.gladfish.common.config.ConfigProperties;
import com.gladfish.frame.exception.BizException;
import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/1/27 22:00
 */
public class LinkUtil {

    @Autowired
    private ConfigProperties configProperties;

    public static String createUrl(String urlTemplate,Map<String,Object> _params){
        if(_params == null){
            _params = new HashMap<>();
        }
        String url = urlTemplate;
        for(String key:_params.keySet()){
            if(key != null&&_params.get(key) != null){
                url = url.replaceAll("\\{"+key+"\\}",_params.get(key).toString());
            }
        }
        return url;
    }


    public static String getSourceCodeByUrl(String url) throws BizException {
        if (url == null || "".equals(url)) {
            throw new BizException("解析失败，url为空");
        }
        String result = "";
        Map<String, String> headermMap = new HashMap<String, String>();
        headermMap
                .put("User-Agent",
                        "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36 gcdc/1.0.0");
        headermMap.put("Content-Type", "text/html;charset=utf-8");
        headermMap.put("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        result = HttpUtil.get(url);
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) throws BizException {
        getSourceCodeByUrl("http://www.cnblogs.com/fangpengchengbupter/p/7823493.html");
    }

}
