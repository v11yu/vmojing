package com.vmojing.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vmojing.mongodb.business.api.ClueBusiness;
import com.vmojing.mongodb.domain.Clue;
import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.utils.IdTransferUtil;

@Controller
@RequestMapping("/clue")
public class ClueController {
	private static final Logger log = LoggerFactory.getLogger(ClueController.class);
	private String formUrl = "clue/clueForm";
	private String listUrl = "clue/clueList";
	@Autowired
	ClueBusiness clueBusiness;
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String createForm(Model model){
		model.addAttribute("action", "create");
		return formUrl;
	}
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String create(@RequestParam(value = "url") String url, RedirectAttributes redirectAttributes){
		Clue clue = new Clue();
		//String url = "http://weibo.com/2142312651/BxT404Kbv?ref=home&rid=14_0_2666906032226306175";
		String mid = IdTransferUtil.url2Mid(url);
		String redirectUrl = "redirect:/clue";
		log.info("url:"+url);
		if(mid != null){
			String wid = IdTransferUtil.mid2Id(mid);
			clue.setId(wid);
			log.info("wid :"+wid);
			if (clueBusiness.save(clue)) {
				String message = "添加clue:" + clue.getId() + "成功";
				redirectAttributes.addFlashAttribute("message", message);
				return redirectUrl;
			}
			log.error("url出错"+url);
		}
		String message = "添加clue出错";
		redirectAttributes.addFlashAttribute("message", message);
		return redirectUrl;
	}
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model model){
		List<Clue> clues = clueBusiness.getAll();
		model.addAttribute("clues",clues);
		return listUrl;
	}
	/**
	 * 改变运行状态：开始< - >暂停
	 */
	@RequestMapping(value="transfer/{id}",method=RequestMethod.GET)
	public String transfer(){
		return listUrl;
	}
}
