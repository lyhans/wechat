package com.gladfish.application;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatApplicationTests {

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
		buttonForm1.setUrl("http://123.206.9.129:9999/");
		menuForm.setButton(Lists.newArrayList(buttonForm1));
		wechatService.menuCreate(2L,menuForm);
	}
}
