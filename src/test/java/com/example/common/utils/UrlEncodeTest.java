package com.example.common.utils;

import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import org.junit.Test;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/1/20 20:35
 */
public class UrlEncodeTest {

    @Test
    public void test(){
        String result3= URLUtil.encode("http://123.206.9.129");
        System.out.println(result3);
    }

}
