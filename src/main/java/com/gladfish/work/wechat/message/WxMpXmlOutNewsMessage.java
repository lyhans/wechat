package com.gladfish.work.wechat.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XStreamAlias("xml")
public class WxMpXmlOutNewsMessage extends WxMpXmlOutMessage {
  private static final long serialVersionUID = -4604402850905714772L;

  @XStreamAlias("Articles")
  protected final List<Item> articles = new ArrayList<>();
  @XStreamAlias("ArticleCount")
  protected int articleCount;

  public List<Item> getArticles() {
    return articles;
  }

  public int getArticleCount() {
    return articleCount;
  }

  public void setArticleCount(int articleCount) {
    this.articleCount = articleCount;
  }

  public WxMpXmlOutNewsMessage() {
    this.msgType = WxConsts.XmlMsgType.NEWS;
  }

  public void addArticle(Item item) {
    this.articles.add(item);
    this.articleCount = this.articles.size();
  }

  @XStreamAlias("item")
  public static class Item implements Serializable {
    private static final long serialVersionUID = -4971456355028904754L;

    @XStreamAlias("Title")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String title;

    @XStreamAlias("Description")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String description;

    @XStreamAlias("PicUrl")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String picUrl;

    @XStreamAlias("Url")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String url;

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getPicUrl() {
      return picUrl;
    }

    public void setPicUrl(String picUrl) {
      this.picUrl = picUrl;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }
  }


}
