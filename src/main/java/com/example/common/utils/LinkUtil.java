package com.example.common.utils;

import cn.hutool.core.map.MapUtil;
import com.example.common.consts.ViewUrl;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/1/27 22:00
 */
public class LinkUtil {

    public static String createUrl(String urlTemplate,Map<String,Object> _params){
        if(MapUtil.isEmpty(_params)){
            return urlTemplate;
        }
        String url = "";
        for(String key:_params.keySet()){
            url = urlTemplate.replaceAll("\\{"+key+"\\}",_params.get(key).toString());
        }
        return url;
    }

    public static void main(String[] args) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("uuid","1234567890");
        System.out.println(createUrl(ViewUrl.VIEW_HTML_URL,params));
    }

}
