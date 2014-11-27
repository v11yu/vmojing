package com.vmojing.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vmojing.crawler.parser.api.UserParser;
import com.vmojing.mongodb.business.api.BloggerBusiness;
import com.vmojing.mongodb.business.api.ClueBusiness;
import com.vmojing.mongodb.domain.Blogger;
import com.vmojing.mongodb.domain.Clue;
import com.vmojing.mongodb.domain.User;
import com.vmojing.mongodb.utils.IdTransferUtil;

@Controller
@RequestMapping("/blogger")
public class BloggerController {
	private static final Logger log = LoggerFactory.getLogger(BloggerController.class);
	private String formUrl = "blogger/bloggerForm";
	private String listUrl = "blogger/bloggerList";
	private String redirectUrl = "redirect:/blogger";
	@Autowired
	UserParser userParser;
	@Autowired
	BloggerBusiness bloggerBusiness;
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String createForm(Model model){
		model.addAttribute("action", "create");
		return formUrl;
	}
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String create(@RequestParam(value = "name") String name, RedirectAttributes redirectAttributes){
		Blogger blogger = new Blogger();
		User u = userParser.getUserByName(name);
		if(u != null){
			blogger.setId(u.getId());
			blogger.setUser(u);
			if (bloggerBusiness.save(blogger)) {
				String message = "添加blogger:" + blogger.getId() + "成功";
				redirectAttributes.addFlashAttribute("message", message);
				return redirectUrl;
			}
		}
		log.error("blogger添加出错:"+name);
		String message = "添加blogger出错";
		redirectAttributes.addFlashAttribute("message", message);
		return redirectUrl;
	}
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model model){
		List<Blogger> bloggers = bloggerBusiness.getAll();
		model.addAttribute("bloggers",bloggers);
		return listUrl;
	}
	/**
	 * 改变运行状态：开始< - >暂停
	 */
	@RequestMapping(value="transfer/{id}",method=RequestMethod.GET)
	public String transfer(@PathVariable("id") String id,RedirectAttributes redirectAttributes) {
		log.info(id);
		Blogger c = bloggerBusiness.getById(id);
		c.setOperateStatus(1 - c.getOperateStatus());
		if (bloggerBusiness.save(c)) {
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
		bloggerBusiness.delete(id);
		String message = "删除成功";
		redirectAttributes.addFlashAttribute("message", message);
		return redirectUrl;
	}

}
