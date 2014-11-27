package com.vmojing.web.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vmojing.mongodb.business.api.BloggerBusiness;
import com.vmojing.mongodb.business.api.TopicBusiness;
import com.vmojing.mongodb.business.impl.TopicBusinessImpl;
import com.vmojing.mongodb.domain.Blogger;
import com.vmojing.mongodb.domain.Topic;
import com.vmojing.web.util.AjaxUtils;

@Controller
@RequestMapping("/topic")
public class TopicController {
	private static final Logger log = LoggerFactory.getLogger(BloggerController.class);
	private String listUrl = "topic/topicList";
	private String formUrl = "topic/topicForm";
	String redirectUrl = "redirect:/topic";
	@Autowired
	TopicBusiness topicBusiness;
	
	@ModelAttribute("topicBean")
	public Topic createFormBean() {
		return new Topic();
	}
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String createForm(Model model){
		model.addAttribute("action", "create");
		return formUrl;
	}
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String create(@Validated Topic topic, RedirectAttributes redirectAttributes){
		log.info("topic:"+topic);
		if (topicBusiness.save(topic)) {
				String message = "添加topic:" + topic.getTopicName() + "成功";
				redirectAttributes.addFlashAttribute("message", message);
				return redirectUrl;
		}
		log.error("topic添加出错:"+topic);
		String message = "添加Topic出错";
		redirectAttributes.addFlashAttribute("message", message);
		return redirectUrl;
	}
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model model){
		List<Topic> topics = topicBusiness.getAll();
		model.addAttribute("topics",topics);
		return listUrl;
	}
	/**
	 * 改变运行状态：开始< - >暂停
	 */
	@RequestMapping(value="transfer/{id}",method=RequestMethod.GET)
	public String transfer(@PathVariable("id") String id,RedirectAttributes redirectAttributes) {
		log.info(id);
		Topic t = topicBusiness.getById(id);
		t.setOperateStatus(1 - t.getOperateStatus());
		if (topicBusiness.save(t)) {
			String message = "操作成功";
			redirectAttributes.addFlashAttribute("message", message);
		}else{
			String message = "操作失败";
			redirectAttributes.addFlashAttribute("message", message);
		}
		
		return redirectUrl;
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") String id,RedirectAttributes redirectAttributes){
		log.info("delete:"+id);
		topicBusiness.delete(id);
		String message = "删除成功";
		redirectAttributes.addFlashAttribute("message", message);
		return redirectUrl;
	}
}
