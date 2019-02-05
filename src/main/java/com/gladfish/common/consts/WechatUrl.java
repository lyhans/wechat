package com.gladfish.common.consts;

public class WechatUrl {

	/** 获取access_token填写client_credential */
	public final static String GRANT_TYPE="client_credential";

	/** 获取access_token接口地址 */
	public final static String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token";

	/** 获取微信服务器IP地址 */
	public final static String GET_CALLBACK_IP_URL="https://api.weixin.qq.com/cgi-bin/getcallbackip";

	/** 获取用户基本信息（包括UnionID机制） */
	public final static String USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info";

	/** 通过code换取网页授权access_token */
	public final static String OAUTH2_ACESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

	/** 拉取用户信息(需scope为 snsapi_userinfo) */
	public final static String SNS_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";

	/** 上传图片接口地址 */
	public final static String UPLOAD_IMG_URL="https://api.weixin.qq.com/cgi-bin/media/uploadimg";

	/** 上传图文消息素材接口地址 */
	public final static String UPLOAD_NEWS_URL="https://api.weixin.qq.com/cgi-bin/media/uploadnews";

	/** 上传图文消息素材接口地址 */
	public final static String SEND_ALL_URL="https://api.weixin.qq.com/cgi-bin/message/mass/sendall";

	/** 创建自定义菜单 */
	public final static String MENU_CREATE_URL="https://api.weixin.qq.com/cgi-bin/menu/create?";
//	public final static String _URL="";
//	public final static String _URL="";

	/** 微信JS接口的临时票据 */
	public final static String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
}
