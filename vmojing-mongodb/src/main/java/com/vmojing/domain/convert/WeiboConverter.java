package com.vmojing.domain.convert;

import java.util.Date;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.vmojing.domain.Weibo;
import com.vmojing.domain.constant.WeiboConstant;

public class WeiboConverter extends Converter<Weibo>{

	@Override
	public Weibo convert(DBObject obj) {
		// TODO Auto-generated method stub
		Weibo t = new Weibo();
		t.setAttitudeCount((Integer) obj.get(WeiboConstant.ATTITUDE_COUNT));
		t.setBmiddlePicture((String) obj.get(WeiboConstant.BMIDDLE_PICTURE));
		t.setCommentCount((Integer) obj.get(WeiboConstant.COMMENT_COUNT));
		t.setContent((String) obj.get(WeiboConstant.CONTENT));
		t.setCreateAtTime((Date) obj.get(WeiboConstant.CREATE_AT_TIME));
		t.setEmotionStatus((Integer) obj.get(WeiboConstant.EMOTION_STATUS));
		t.setId((ObjectId) obj.get(WeiboConstant.ID));
		t.setLatitude((Double) obj.get(WeiboConstant.LATITUDE));
		t.setLongitude((Double) obj.get(WeiboConstant.LONGITUDE));
		t.setMonitoredTime((Date) obj.get(WeiboConstant.MONITORED_TIME));
		t.setOriginalPicture((String) obj.get(WeiboConstant.ORIGINAL_PICTURE));
		t.setPredictCommentCount((Integer) obj.get(WeiboConstant.PREDICT_COMMENT_COUNT));
		t.setPredictRetweetCount((Integer) obj.get(WeiboConstant.PREDICT_COMMENT_COUNT));
		t.setRetweetCount((Integer) obj.get(WeiboConstant.RETWEET_COUNT));
		t.setRetweetWeiboId((Long) obj.get(WeiboConstant.RETWEET_WEIBO_ID));
		t.setSource((String) obj.get(WeiboConstant.SOURCE));
		t.setStatus((Integer) obj.get(WeiboConstant.STATUS));
		t.setThumbnailPicture((String) obj.get(WeiboConstant.THUMBNAIL_PICTURE));
		t.setTopicId((ObjectId) obj.get(WeiboConstant.TOPIC_ID));
		t.setUpdateRecord((String) obj.get(WeiboConstant.UPDATE_RECORD));
		t.setUserId((Long) obj.get(WeiboConstant.USER_ID));
		t.setUserQuality((Double) obj.get(WeiboConstant.USER_QUALITY));
		t.setUserQualityRank((Integer) obj.get(WeiboConstant.USER_QUALITY_RANK));
		return t;
	}

	@Override
	protected DBObject convertToDBObject(Weibo from) {
		// TODO Auto-generated method stub
		DBObject obj = new BasicDBObject();
		obj.put(WeiboConstant.ATTITUDE_COUNT, from.getAttitudeCount());
		obj.put(WeiboConstant.BMIDDLE_PICTURE, from.getBmiddlePicture());
		obj.put(WeiboConstant.COMMENT_COUNT, from.getCommentCount());
		obj.put(WeiboConstant.CONTENT, from.getContent());
		obj.put(WeiboConstant.CREATE_AT_TIME, from.getCreateAtTime());
		obj.put(WeiboConstant.EMOTION_STATUS, from.getEmotionStatus());
		obj.put(WeiboConstant.ID, from.getId());
		obj.put(WeiboConstant.LATITUDE, from.getLatitude());
		obj.put(WeiboConstant.LONGITUDE, from.getLongitude());
		obj.put(WeiboConstant.MONITORED_TIME, from.getMonitoredTime());
		obj.put(WeiboConstant.ORIGINAL_PICTURE, from.getOriginalPicture());
		obj.put(WeiboConstant.PREDICT_COMMENT_COUNT, from.getPredictCommentCount());
		obj.put(WeiboConstant.PREDICT_RETWEET_COUNT, from.getPredictRetweetCount());
		obj.put(WeiboConstant.RETWEET_COUNT, from.getRetweetCount());
		obj.put(WeiboConstant.RETWEET_WEIBO_ID, from.getRetweetWeiboId());
		obj.put(WeiboConstant.SOURCE, from.getSource());
		obj.put(WeiboConstant.STATUS, from.getStatus());
		obj.put(WeiboConstant.THUMBNAIL_PICTURE, from.getThumbnailPicture());
		obj.put(WeiboConstant.TOPIC_ID, from.getTopicId());
		obj.put(WeiboConstant.UPDATE_RECORD, from.getUpdateRecord());
		obj.put(WeiboConstant.USER_ID, from.getUserId());
		obj.put(WeiboConstant.USER_QUALITY, from.getUserQuality());
		obj.put(WeiboConstant.USER_QUALITY_RANK, from.getUserQualityRank());
		return obj;
	}

}
