package com.vmojing.domain.convert;

import java.util.Date;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.vmojing.domain.TopicStatistic;
import com.vmojing.domain.constant.TopicStatisticConstant;

public class TopicStatisticConverter extends Converter<TopicStatistic>{

	@Override
	public TopicStatistic convert(DBObject obj) {
		// TODO Auto-generated method stub
		TopicStatistic t = new TopicStatistic();
		t.setBloggerCount((Integer) obj.get(TopicStatisticConstant.BLOGGER_COUNT));
		t.setCelebrityCount((Integer) obj.get(TopicStatisticConstant.CELEBRITY_COUNT));
		t.setCreateAtTime((Date) obj.get(TopicStatisticConstant.CREATE_AT_TIME));
		t.setEnterpriseCount((Integer) obj.get(TopicStatisticConstant.ENTERPRISE_COUNT));
		t.setFemaleCount((Integer) obj.get(TopicStatisticConstant.FEMALE_COUNT));
		t.setGovernmentCount((Integer) obj.get(TopicStatisticConstant.GOVERNMENT_COUNT));
		t.setId((ObjectId) obj.get(TopicStatisticConstant.ID));
		t.setMaleCount((Integer) obj.get(TopicStatisticConstant.MALE_COUNT));
		t.setMapDistribution((String) obj.get(TopicStatisticConstant.MAP_DISTRIBUTION));
		t.setMediaCount((Integer) obj.get(TopicStatisticConstant.MEDIA_COUNT));
		t.setNegativeCount((Integer) obj.get(TopicStatisticConstant.NEGATIVE_COUNT));
		t.setNeutralCount((Integer) obj.get(TopicStatisticConstant.NEUTRAL_COUNT));
		t.setOrdinaryCount((Integer) obj.get(TopicStatisticConstant.ORDINARY_COUNT));
		t.setPositiveCount((Integer) obj.get(TopicStatisticConstant.POSITIVE_COUNT));
		t.setTopicBasicId((ObjectId) obj.get(TopicStatisticConstant.TOPIC_BASIC_ID));
		t.setZombieCount((Integer) obj.get(TopicStatisticConstant.ZOMBIE_COUNT));
		return t;
	}

	@Override
	protected DBObject convertToDBObject(TopicStatistic from) {
		// TODO Auto-generated method stub
		DBObject obj = new BasicDBObject();
		obj.put(TopicStatisticConstant.BLOGGER_COUNT, from.getBloggerCount());
		obj.put(TopicStatisticConstant.CELEBRITY_COUNT,from.getCelebrityCount());
		obj.put(TopicStatisticConstant.CREATE_AT_TIME,from.getCreateAtTime());
		obj.put(TopicStatisticConstant.ENTERPRISE_COUNT,from.getEnterpriseCount());
		obj.put(TopicStatisticConstant.FEMALE_COUNT,from.getFemaleCount());
		obj.put(TopicStatisticConstant.GOVERNMENT_COUNT,from.getGovernmentCount());
		obj.put(TopicStatisticConstant.ID,from.getId());
		obj.put(TopicStatisticConstant.MALE_COUNT,from.getMaleCount());
		obj.put(TopicStatisticConstant.MAP_DISTRIBUTION,from.getMapDistribution());
		obj.put(TopicStatisticConstant.MEDIA_COUNT,from.getMediaCount());
		obj.put(TopicStatisticConstant.NEGATIVE_COUNT,from.getNegativeCount());
		obj.put(TopicStatisticConstant.NEUTRAL_COUNT,from.getNeutralCount());
		obj.put(TopicStatisticConstant.ORDINARY_COUNT,from.getOrdinaryCount());
		obj.put(TopicStatisticConstant.POSITIVE_COUNT,from.getPositiveCount());
		obj.put(TopicStatisticConstant.TOPIC_BASIC_ID,from.getTopicBasicId());
		obj.put(TopicStatisticConstant.ZOMBIE_COUNT,from.getZombieCount());
		return obj;
	}

}
