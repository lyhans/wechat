package com.gladfish.work.wechat.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

import java.io.Serializable;

@XStreamAlias("xml")
public class WxMpXmlOutTransferKefuMessage extends WxMpXmlOutMessage {
  private static final long serialVersionUID = 1850903037285841322L;

  @XStreamAlias("TransInfo")
  protected TransInfo transInfo;

  public WxMpXmlOutTransferKefuMessage() {
    this.msgType = WxConsts.KefuMsgType.TRANSFER_CUSTOMER_SERVICE;
  }

  public TransInfo getTransInfo() {
    return transInfo;
  }

  public void setTransInfo(TransInfo transInfo) {
    this.transInfo = transInfo;
  }

  @XStreamAlias("TransInfo")
  public static class TransInfo implements Serializable {
    private static final long serialVersionUID = -6317885617135706056L;

    @XStreamAlias("KfAccount")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String kfAccount;

    public String getKfAccount() {
      return kfAccount;
    }

    public void setKfAccount(String kfAccount) {
      this.kfAccount = kfAccount;
    }
  }
}
