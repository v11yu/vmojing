package com.vmojing.mongodb.domain;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.vmojing.mongodb.annotation.BasicModel;
import com.vmojing.mongodb.annotation.ManualField;


@Document
@BasicModel
public class User {
	@Id
	private String id;
	/** 微博昵称 */
	private String screenName;
	/** 友好显示名称，如Bill Gates,名称中间的空格正常显示(此特性暂不支持) */
	private String name;
	/** 省份编码（参考省份编码表） */
	private Integer province;
	/** 城市编码（参考城市编码表） */
	private Integer city;
	/** 地址 */
	private String location;
	/** 个人描述 */
	private String description;
	/** 用户博客地址 */
	private String url;
	/** 自定义图像 */
	private String profileImageUrl;
	/** 用户的微博统一URL地址 */
	private String profileUrl;
	/** 用户个性化URL*/
	private String userDomain;
	/** 性别,m--男，f--女,n--未知 */
	private String gender;
	/** 粉丝数 */
	private Integer followersCount;
	/** 关注数 */
	private Integer friendsCount;
	/** 微博数 */
	private Integer statusesCount;
	/** 收藏数 */
	private Integer favouritesCount;
	/** 创建时间 */
	private Date createdAt;
	/** 加V标示，是否微博认证用户 */
	private Boolean verified;
	/** 认证类型 */
	private Integer verifiedType;
	/** 是否允许所有人给我发私信 */
	private Boolean allowAllActMsg;
	/** 是否允许所有人对我的微博进行评论 */
	private Boolean allowAllComment;
	/** 大头像地址 */
	private String avatarLarge;
	/** 互粉数 */
	private Integer biFollowersCount;
	/** 用户语言版本 */
	private String lang;
	/** 认证原因 */
	private String verifiedReason;
	/** 微號 */
	private String weihao;
	/** 最后更新的时间*/
	@ManualField
	private Date lastUpdateTime;
	/*--------博主粉丝-------*/
	/** 该用户关注的博主列表 */
	@ManualField
	private List<Long> friendsList;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	public String getProfileUrl() {
		return profileUrl;
	}
	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}
	public String getUserDomain() {
		return userDomain;
	}
	public void setUserDomain(String userDomain) {
		this.userDomain = userDomain;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getFollowersCount() {
		return followersCount;
	}
	public void setFollowersCount(Integer followersCount) {
		this.followersCount = followersCount;
	}
	public Integer getFriendsCount() {
		return friendsCount;
	}
	public void setFriendsCount(Integer friendsCount) {
		this.friendsCount = friendsCount;
	}
	public Integer getStatusesCount() {
		return statusesCount;
	}
	public void setStatusesCount(Integer statusesCount) {
		this.statusesCount = statusesCount;
	}
	public Integer getFavouritesCount() {
		return favouritesCount;
	}
	public void setFavouritesCount(Integer favouritesCount) {
		this.favouritesCount = favouritesCount;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Boolean getVerified() {
		return verified;
	}
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	public Integer getVerifiedType() {
		return verifiedType;
	}
	public void setVerifiedType(Integer verifiedType) {
		this.verifiedType = verifiedType;
	}
	public Boolean getAllowAllActMsg() {
		return allowAllActMsg;
	}
	public void setAllowAllActMsg(Boolean allowAllActMsg) {
		this.allowAllActMsg = allowAllActMsg;
	}
	public Boolean getAllowAllComment() {
		return allowAllComment;
	}
	public void setAllowAllComment(Boolean allowAllComment) {
		this.allowAllComment = allowAllComment;
	}
	public String getAvatarLarge() {
		return avatarLarge;
	}
	public void setAvatarLarge(String avatarLarge) {
		this.avatarLarge = avatarLarge;
	}
	public Integer getBiFollowersCount() {
		return biFollowersCount;
	}
	public void setBiFollowersCount(Integer biFollowersCount) {
		this.biFollowersCount = biFollowersCount;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getVerifiedReason() {
		return verifiedReason;
	}
	public void setVerifiedReason(String verifiedReason) {
		this.verifiedReason = verifiedReason;
	}
	public String getWeihao() {
		return weihao;
	}
	public void setWeihao(String weihao) {
		this.weihao = weihao;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public List<Long> getFriendsList() {
		return friendsList;
	}
	public void setFriendsList(List<Long> friendsList) {
		this.friendsList = friendsList;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", screenName=" + screenName + ", name="
				+ name + ", province=" + province + ", city=" + city
				+ ", location=" + location + ", description=" + description
				+ ", url=" + url + ", profileImageUrl=" + profileImageUrl
				+ ", profileUrl=" + profileUrl + ", userDomain=" + userDomain
				+ ", gender=" + gender + ", followersCount=" + followersCount
				+ ", friendsCount=" + friendsCount + ", statusesCount="
				+ statusesCount + ", favouritesCount=" + favouritesCount
				+ ", createdAt=" + createdAt + ", verified=" + verified
				+ ", verifiedType=" + verifiedType + ", allowAllActMsg="
				+ allowAllActMsg + ", allowAllComment=" + allowAllComment
				+ ", avatarLarge=" + avatarLarge + ", biFollowersCount="
				+ biFollowersCount + ", lang=" + lang + ", verifiedReason="
				+ verifiedReason + ", weihao=" + weihao + ", lastUpdateTime="
				+ lastUpdateTime + ", friendsList=" + friendsList + "]";
	}
	
}
