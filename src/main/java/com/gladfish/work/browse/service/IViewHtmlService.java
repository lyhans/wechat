package com.gladfish.work.browse.service;

import com.gladfish.frame.exception.BizException;
import com.gladfish.work.browse.form.ViewHtmlForm;
import com.gladfish.work.browse.form.ViewRecordForm;

import java.util.List;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/1/27 21:44
 */
public interface IViewHtmlService {

    /**
     * 创建浏览页面
     * @param userId
     * @param wechatUserId
     * @param linkUrl
     * @param createType
     * @return
     */
    String createViewHtml(Long userId,String wechatUserId,String linkUrl,Boolean createType);

    /**
     * 浏览指定页面
     * @param uuid 页面uuid
     * @param code 浏览者code
     * @return
     */
    ViewHtmlForm browseViewHtml(String uuid,String code) throws BizException;

    /**
     *
     * @param viewHtmlId
     * @return
     * @throws BizException
     */
    List<ViewRecordForm> getViewRecords(Long viewHtmlId) throws BizException;
}
