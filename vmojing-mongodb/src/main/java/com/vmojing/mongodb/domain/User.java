package com.vmojing.mongodb.domain;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class User {
	@Id
	private Long id;
	
	private String screenName;            //微博昵称
	private String name;                  //友好显示名称，如Bill Gates,名称中间的空格正常显示(此特性暂不支持)
	private Integer province;                 //省份编码（参考省份编码表）
	private Integer city;                     //城市编码（参考城市编码表）
	private String location;              //地址
	private String description;           //个人描述
	private String url;                   //用户博客地址
	private String profileImageUrl;       //自定义图像
	private String profileUrl;       	  //用户的微博统一URL地址
	private String userDomain;            //用户个性化URL
	private String gender;                //性别,m--男，f--女,n--未知
	private Integer followersCount;           //粉丝数
	private Integer friendsCount;             //关注数
	private Integer statusesCount;            //微博数
	private Integer favouritesCount;          //收藏数
	private Date createdAt;               //创建时间
	private Boolean verified;             //加V标示，是否微博认证用户
	private Integer verifiedType;             //认证类型
	private Boolean allowAllActMsg;       //是否允许所有人给我发私信
	private Boolean allowAllComment;      //是否允许所有人对我的微博进行评论
	private String avatarLarge;           //大头像地址
	private Integer biFollowersCount;         //互粉数
	private String lang;                  //用户语言版本
	private String verifiedReason;		  //认证原因
	private String weihao;				  //微號
	/*--------博主粉丝-------*/
	/** 该用户关注的博主列表 */
	private List<Long> friendsList;
	
}
