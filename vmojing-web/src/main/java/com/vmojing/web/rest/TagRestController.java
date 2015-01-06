package com.vmojing.web.rest;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




import com.vmojing.core.parser.api.TagParser;
import com.vmojing.mongodb.domain.Tag;
import com.vmojing.web.util.MediaTypes;

/**
 * tag的Restful API的Controller.
 * 
 * @author v11
 */
@RestController
@RequestMapping(value = "/api/v1/tag")
public class TagRestController {
	@Autowired
	TagParser tagParser;
	@RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public Tag get(@PathVariable("name") String name) {
		System.out.println(name);
		//System.out.println();
		return tagParser.getTag(name);
	}
}

