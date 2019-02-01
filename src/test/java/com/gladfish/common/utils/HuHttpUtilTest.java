package com.gladfish.common.utils;

import cn.hutool.http.HttpUtil;
import org.junit.Test;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2018/12/27 22:40
 */
public class HuHttpUtilTest {

    @Test
    public void test(){
        String result3= HttpUtil.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx7309652668f4efdb&secret=7ac6c7da28a6f47d2ce248e2cfe2359b");
        System.out.println(result3);
    }

}