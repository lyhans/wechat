package com.gladfish.application;

import com.gladfish.common.config.ConfigProperties;
import com.gladfish.common.consts.ViewUrl;
import com.gladfish.common.utils.LinkUtil;
import com.gladfish.frame.exception.BizException;
import com.gladfish.work.wechat.enums.EnumButtonType;
import com.gladfish.work.wechat.form.ButtonForm;
import com.gladfish.work.wechat.form.MenuForm;
import com.gladfish.work.wechat.service.IWechatService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatApplicationTests {

	@Autowired
	private ConfigProperties configProperties;

	@Autowired
	private IWechatService wechatService;

	@Test
	public void contextLoads() {
//		String str = HttpRequestUtil.get("http://apis.juhe.cn/idcard/index?key=5d32b9502a5e290d320736eaa85c4f3c&cardno=330326198903081211");
//		System.out.println(str);
	}

	@Test
	public void menuCreate() throws BizException {
		MenuForm menuForm = new MenuForm();
		ButtonForm buttonForm1 = new ButtonForm();
		buttonForm1.setName("悦鱼官网");
		buttonForm1.setType(EnumButtonType.VIEW);
		buttonForm1.setUrl("http://www.gladfish.cn/");

		ButtonForm buttonForm2 = new ButtonForm();
		buttonForm2.setName("测试功能");
		buttonForm2.setType(EnumButtonType.VIEW);
		Map<String,Object> _params = new HashMap<>();
		_params.put("domain",configProperties.getDomain());
		_params.put("appid",configProperties.getAppid());
		_params.put("uri",LinkUtil.createUrl(ViewUrl.CREATE_HTML_URL,_params));
		String url = LinkUtil.createUrl(ViewUrl.USER_INFO_URL,_params);
		buttonForm2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx540a3fb596ce9dc5&redirect_uri=http://2349390gv5.imwork.net/browse/showcreateview&response_type=code&scope=snsapi_base");

		menuForm.setButton(Lists.newArrayList(buttonForm1,buttonForm2));
//		wechatService.menuCreate(menuForm);
		System.out.println("----------------"+url);
	}
}
