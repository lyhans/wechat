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
@XStreamAlias("SendLocationInfo")
public class SendLocationInfo implements Serializable {
  private static final long serialVersionUID = 6633214140499161130L;

  @XStreamAlias("Location_X")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String locationX;

  @XStreamAlias("Location_Y")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String locationY;

  @XStreamAlias("Scale")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String scale;

  @XStreamAlias("Label")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String label;

  @XStreamAlias("Poiname")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String poiName;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getLocationX() {
    return locationX;
  }

  public void setLocationX(String locationX) {
    this.locationX = locationX;
  }

  public String getLocationY() {
    return locationY;
  }

  public void setLocationY(String locationY) {
    this.locationY = locationY;
  }

  public String getScale() {
    return scale;
  }

  public void setScale(String scale) {
    this.scale = scale;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getPoiName() {
    return poiName;
  }

  public void setPoiName(String poiName) {
    this.poiName = poiName;
  }

  @Override
  public String toString() {
    return JSON.toJSONString(this);
  }
}
