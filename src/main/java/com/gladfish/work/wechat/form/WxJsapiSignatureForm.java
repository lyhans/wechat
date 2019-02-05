package com.gladfish.work.wechat.form;

import java.io.Serializable;

/**
 * jspai signature.
 *
 * @author Daniel Qian
 */
public class WxJsapiSignatureForm implements Serializable {
  private static final long serialVersionUID = -1116808193154384804L;

  private String appId;

  private String nonceStr;

  private long timestamp;

  private String url;

  private String signature;

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getNonceStr() {
    return nonceStr;
  }

  public void setNonceStr(String nonceStr) {
    this.nonceStr = nonceStr;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }
}
