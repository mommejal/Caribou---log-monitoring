package com.caribou;

import com.google.gson.Gson;
import com.logs.GeneLog;
import com.logs.ListeDeLogs;
import com.logs.logXMLTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import java.util.ArrayList;
import com.logs.ModelAndViewLogs;

@SuppressWarnings("deprecation")
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    
	@Bean
	public ModelAndViewLogs getModelAndViewLogs()
	//Pour l'instant j'en cr�e un uniquement pour liste logs
	{
		ModelAndViewLogs mavl;
		mavl = new ModelAndViewLogs();
		mavl.modelAndView = new ModelAndView("listeLogs");
		mavl.logs = new ArrayList<GeneLog>();
		mavl.logs.add(new logXMLTest("D�but de la liste de logs"));
		return mavl;
//		logs.logsVide();
//		mav.addObject("logs", logs);
		
	}
	
	@Bean
	public Gson getGson() {
		return new Gson();
	}
	
	@Bean
	public ModelAndView getModelAndView()
	//Pour l'instant j'en cr�e un uniquement pour liste logs
	{
	return new ModelAndView("listeLogs");	
	}
	
	//TODO est ce que tout ces beans ont leut place ici ??
	
	
	//TODO ListeDeLogs est une abbération, faut que je t'explique un truc là
	@Bean
	public ListeDeLogs getListeDeLogs(){
	    return new ListeDeLogs();
	}
	
	@Bean
	public String getRegexAgent() {
		return ".*apache.*";
	}
	
    @Bean
    @Description("Thymeleaf template resolver serving HTML 5")
    public ClassLoaderTemplateResolver templateResolver() {        
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();       
        templateResolver.setPrefix("templates/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");        
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("ISO-8859-1");
        return templateResolver;
    }

    @Bean
    @Description("Thymeleaf template engine with Spring integration")
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    @Description("Thymeleaf view resolver")
    public ViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("ISO-8859-1");
        return viewResolver;
    }    

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }
}