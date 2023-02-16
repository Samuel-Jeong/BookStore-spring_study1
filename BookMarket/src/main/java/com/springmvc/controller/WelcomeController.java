package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

		@RequestMapping(value="/home", method=RequestMethod.GET)
		public String welcome(Model model) {
			model.addAttribute("greeting", "Welcome to BookMarket");
			model.addAttribute("strapline", "Welcome to Web Shopping Mall!");
			
			/**
			 * 	<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
					<beans:property name="prefix" value="/WEB-INF/views/" />
					<beans:property name="suffix" value=".jsp" />
				</beans:bean>
				
				-> /src/main/webapp/WEB-INF/views/welcome.jsp 
			 */
			return "welcome";
		}
	
	
}
