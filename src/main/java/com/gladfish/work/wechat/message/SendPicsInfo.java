package com.gladfish.work.wechat.message;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *  Created by BinaryWang on 2017/5/4.
 * </pre>
 *
 * @author Binary Wang
 */
@XStreamAlias("SendPicsInfo")
public class SendPicsInfo implements Serializable {
  private static final long serialVersionUID = -4572837013294199227L;

  @XStreamAlias("PicList")
  protected final List<Item> picList = new ArrayList<>();

  @XStreamAlias("Count")
  private Long count;

  public List<Item> getPicList() {
    return picList;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  @Override
  public String toString() {
    return JSON.toJSONString(this);
  }

  @XStreamAlias("item")
  public static class Item implements Serializable {
    private static final long serialVersionUID = 7706235740094081194L;

    @XStreamAlias("PicMd5Sum")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String picMd5Sum;

    public String getPicMd5Sum() {
      return picMd5Sum;
    }

    public void setPicMd5Sum(String picMd5Sum) {
      this.picMd5Sum = picMd5Sum;
    }

    @Override
    public String toString() {
      return JSON.toJSONString(this);
    }

  }
}
