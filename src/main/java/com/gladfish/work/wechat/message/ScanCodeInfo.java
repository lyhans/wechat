package com.gladfish.work.wechat.message;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

import java.io.Serializable;

/**
 * <pre>
 *  Created by BinaryWang on 2017/5/4.
 * </pre>
 *
 * @author Binary Wang
 */
@XStreamAlias("ScanCodeInfo")
public class ScanCodeInfo implements Serializable {
  private static final long serialVersionUID = 4745181270645050122L;

  /**
   * 扫描类型，一般是qrcode.
   */
  @XStreamAlias("ScanType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String scanType;

  /**
   * 扫描结果，即二维码对应的字符串信息.
   */
  @XStreamAlias("ScanResult")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String scanResult;


  public String getScanType() {
    return scanType;
  }

  public void setScanType(String scanType) {
    this.scanType = scanType;
  }

  public String getScanResult() {
    return scanResult;
  }

  public void setScanResult(String scanResult) {
    this.scanResult = scanResult;
  }

  @Override
  public String toString() {
    return JSON.toJSONString(this);
  }

}
