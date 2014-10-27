package com.vmojing.web.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vmojing.mongodb.business.api.TopicBusiness;
import com.vmojing.mongodb.business.impl.TopicBusinessImpl;
import com.vmojing.mongodb.domain.Topic;
import com.vmojing.web.util.AjaxUtils;

@Controller
@RequestMapping("/topic")
public class TopicController {
	@Autowired
	TopicBusiness topicBusi;
	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}
	@ModelAttribute("topicBean")
	public Topic createFormBean() {
		return new Topic();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public void topic(){
		
	}
	@RequestMapping("/hello")
	public @ResponseBody String simple() {
		return "Hello world!";
	}
	@RequestMapping("/create")
	public String createTopic(@Validated Topic topic, BindingResult result
			,@ModelAttribute("ajaxRequest") boolean ajaxRequest
			,Model model, RedirectAttributes redirectAttrs){
		if(result.hasErrors()){
			System.out.println("error");
			return "topic";
		}
		
		System.out.println(topic);
		topicBusi.save(topic);
		String message = "Form submitted successfully.  Bound " + topic;
//		String message = "Form submitted successfully.  Bound " + formBean;
		// Success response handling
		if (ajaxRequest) {
			System.out.println("ajax");
			// prepare model for rendering success message in this request
			model.addAttribute("message", message);
			return "topic";
		} else {
			// store a success message for rendering on the next request after redirect
			// redirect back to the form to render the success message along with newly bound values
			System.out.println("go here");
			redirectAttrs.addFlashAttribute("message", message);
			return "redirect:/topic";			
		}
	}
	@RequestMapping("/test")
	public String redirect(){
		
		return "redirect:/topic";
	}
}
