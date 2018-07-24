package com.appweb.mavoutput;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.ContentTest;

@Controller
@Component
public class test {

	@Autowired
	public Map<String,ContentTest> content;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView index(ModelAndView mav) {
		mav.addObject("content",content);
		mav.addObject(new ContentTest("mi","faaz"));
		mav.addObject("s1","hello");
		mav.addObject("s2","");
		mav.setViewName("test");
		return mav;
	}
}
