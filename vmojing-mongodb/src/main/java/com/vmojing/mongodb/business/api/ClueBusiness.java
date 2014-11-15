package com.vmojing.mongodb.business.api;

import java.util.List;

import com.vmojing.mongodb.domain.Clue;

public interface ClueBusiness {
	boolean save(Clue c);
	List<Clue> getAll();
}
