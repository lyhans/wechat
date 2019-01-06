package com.example.common.consts;

public class WechatUrl {

	/** 获取access_token填写client_credential */
	public final static String GRANT_TYPE="client_credential";

	/** 获取access_token接口地址 */
	public final static String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token";

	/** 上传图片接口地址 */
	public final static String UPLOAD_IMG_URL="https://api.weixin.qq.com/cgi-bin/media/uploadimg";

	/** 上传图文消息素材接口地址 */
	public final static String UPLOAD_NEWS_URL="https://api.weixin.qq.com/cgi-bin/media/uploadnews";

	/** 上传图文消息素材接口地址 */
	public final static String SEND_ALL_URL="https://api.weixin.qq.com/cgi-bin/message/mass/sendall";
//	public final static String _URL="";
//	public final static String _URL="";
//	public final static String _URL="";
}
