package com.example.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/ftlt")
public class FtltController {

    @Resource
    Configuration cfg;

    @RequestMapping(value = "/test")
    public String test(Model model){
        System.out.println("1111111111111");
        String w="Welcome FreeMarker!";
        Map root = new HashMap();
        root.put("w",w);
        freeMarkerContent(root);
        model.addAttribute("w","Welcome FreeMarker!");
        return "fff";
    }

    private void freeMarkerContent(Map<String,Object> root){
        try {
            Template temp = cfg.getTemplate("fff.ftl");
            //以classpath下面的static目录作为静态页面的存储目录，同时命名生成的静态html文件名称
            String path=this.getClass().getResource("/").toURI().getPath()+"static/test.html";
            System.out.println(path);
            Writer file = new FileWriter(new File(path.substring(path.indexOf("/"))));
            temp.process(root, file);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}