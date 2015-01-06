package com.vmojing.mongodb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vmojing.mongodb.domain.AccessToken;
import com.vmojing.mongodb.domain.Blogger;
import com.vmojing.mongodb.domain.Clue;
import com.vmojing.mongodb.domain.Comment;
import com.vmojing.mongodb.domain.Tag;
import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.domain.User;
import com.vmojing.mongodb.domain.Weibo;
import com.vmojing.mongodb.repository.BasicRepository;
import com.vmojing.mongodb.repository.DBConvertor;

@Configuration
public class MongoBeansConfiguration {
	/*
	 * topic
	 */
	@Bean
	DBConvertor<Topic> topicConvertor(){
		return new DBConvertor<>(Topic.class);
	}
	@Bean
	BasicRepository<Topic> topicDao(){
		return new BasicRepository<Topic>(Topic.class,topicConvertor());
	}
	/*
	 * access_token
	 */
	@Bean
	DBConvertor<AccessToken> accessTokenConvertor(){
		return new DBConvertor<>(AccessToken.class);
	}
	@Bean
	BasicRepository<AccessToken> accessTokenDao(){
		return new BasicRepository<AccessToken>(AccessToken.class,accessTokenConvertor());
	}
	/*
	 * user
	 */
	@Bean
	DBConvertor<User> userConvertor(){
		return new DBConvertor<>(User.class);
	}
	@Bean
	BasicRepository<User> userDao(){
		return new BasicRepository<User>(User.class,userConvertor());
	}
	/*
	 * clue
	 */
	@Bean
	DBConvertor<Clue> clueConvertor(){
		return new DBConvertor<>(Clue.class);
	}
	@Bean
	BasicRepository<Clue> clueDao(){
		return new BasicRepository<Clue>(Clue.class,clueConvertor());
	}
	/*
	 * blogger
	 */
	@Bean
	DBConvertor<Blogger> bloggerConvertor(){
		return new DBConvertor<>(Blogger.class);
	}
	@Bean
	BasicRepository<Blogger> bloggerDao(){
		return new BasicRepository<Blogger>(Blogger.class,bloggerConvertor());
	}
	/*
	 * weibo
	 */
	@Bean
	DBConvertor<Weibo> weiboConvertor(){
		return new DBConvertor<Weibo>(Weibo.class);
	}
	@Bean
	BasicRepository<Weibo> weiboDao(){
		return new BasicRepository<Weibo>(Weibo.class,weiboConvertor());
	}
	/*
	 * comment
	 */
	@Bean
	DBConvertor<Comment> commentConvertor(){
		return new DBConvertor<Comment>(Comment.class);
	}
	@Bean
	BasicRepository<Comment> commentDao(){
		return new BasicRepository<Comment>(Comment.class, commentConvertor());
	}
	/*
	 * tag
	 */
	@Bean
	DBConvertor<Tag> tagConvertor(){
		return new DBConvertor<Tag>(Tag.class);
	}
	@Bean
	BasicRepository<Tag> tagDao(){
		return new BasicRepository<Tag>(Tag.class,tagConvertor());
	}
}
