package com.example.service;

import com.example.form.ViewHtmlForm;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/1/27 21:44
 */
public interface IViewHtmlService {

    String createViewHtml(Long userId,String wechatUserId,String linkUrl,Boolean createType);

    ViewHtmlForm getViewHtmlFormByUuid(String uuid);
}
