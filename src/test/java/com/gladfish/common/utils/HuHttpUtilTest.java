package com.gladfish.common.utils;

import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HtmlUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2018/12/27 22:40
 */
public class HuHttpUtilTest {

    @Test
    public void test(){
        String htmlStr= HttpUtil.get("https://mparticle.uc.cn/article.html?uc_param_str=frdnsnpfvecpntnwprdssskt&btifl=100&app=uc-iflow&title_type=1&wm_id=002f9e6224e74ccea08aa1e26d8a666a&wm_cid=270129170960092160&pagetype=share&client=&uc_share_depth=1");
//        String htmlStr= HttpUtil.get("https://m.weibo.cn/1742566624/4335336757311166");
        System.out.println(htmlStr);
        List<String> titles = ReUtil.findAll("<title>(.*?)</title>",htmlStr,1);
        System.out.println(JSON.toJSONString(titles));
        List<String> imgs = ReUtil.findAll("<img src=\"(.*?)\">",htmlStr,1);
        System.out.println(JSON.toJSONString(imgs));
    }

    @Test
    public void testUtil() throws IOException, InterruptedException {
        final WebClient webClient = new WebClient(BrowserVersion.CHROME);//新建一个模拟谷歌Chrome浏览器的浏览器客户端对象

        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX

        HtmlPage page = null;
        try {
            page = webClient.getPage("https://sports.sina.cn/nba/2019-02-08/detail-ihrfqzka4288416.d.html?wm=4007");//尝试加载上面图片例子给出的网页
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            webClient.close();
        }

        webClient.waitForBackgroundJavaScript(30000);//异步JS执行需要耗时,所以这里线程要阻塞30秒,等待异步JS执行结束

        String pageXml = page.asXml();//直接将加载完成的页面转换成xml格式的字符串

        System.out.println("---------html start------");
        System.out.println(pageXml);
        System.out.println("---------html end------");
        //TODO 下面的代码就是对字符串的操作了,常规的爬虫操作,用到了比较好用的Jsoup库

        Document document = Jsoup.parse(pageXml);//获取html文档
        System.out.println(document.getElementsByTag("title").first().html());//获取元素节点等
        Elements elements = document.getElementsByTag("img");
//        for(int i =0;i<elements.size();i++){
//            ReUtil.
//            elements.get(i).attr("src")
//        }
//        System.out.println(s);
    }

}