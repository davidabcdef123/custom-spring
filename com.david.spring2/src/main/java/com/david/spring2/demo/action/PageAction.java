package com.david.spring2.demo.action;


import com.david.spring2.demo.service.IQueryService;
import com.david.spring2.framework.annotation.Autowried;
import com.david.spring2.framework.annotation.Controller;
import com.david.spring2.framework.annotation.RequestMapping;
import com.david.spring2.framework.annotation.RequestParam;
import com.david.spring2.framework.webmvc.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 公布接口url
 * @author Tom
 *
 */
@Controller
@RequestMapping("/")
public class PageAction {

	@Autowried
	IQueryService queryService;
	
	@RequestMapping("/first.html")
	public ModelAndView query(@RequestParam("teacher") String teacher){
		String result = queryService.query(teacher);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("teacher", teacher);
		model.put("data", result);
		model.put("token", "123456");
		return new ModelAndView("first.html",model);
	}
	
}
