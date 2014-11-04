package com.vmojing.mongodb.business.api;

public interface UserBusiness {
	/**
	 * 判断uid是否存在粉丝fid
	 * @param uid 博主id
	 * @param fid 粉丝id
	 * @return {@code true} if the exit 粉丝fid,{@code false} otherwise
	 */
	boolean exitFans(String uid,String fid);
}
