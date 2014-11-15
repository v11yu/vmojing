package com.vmojing.mongodb.business.api;

import java.util.List;

import com.vmojing.mongodb.domain.Blogger;

public interface BloggerBusiness {
	boolean save(Blogger c);
	List<Blogger> getAll();
}
