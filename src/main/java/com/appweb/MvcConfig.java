package com.appweb;

import com.agent.AgentManager;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.mongodb.Mongo;

@SuppressWarnings("deprecation")
@EnableMongoRepositories(basePackageClasses = LogsRepository.class)
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	LogsRepository logsRepository;

	@Autowired
	Mongo mongo;
	
	@Autowired
	MongoDbFactory mongoDbFactory;

	@Bean
	public ModelAndView getModelAndView() {
		return new ModelAndView("listeLogs");
	}

	@Bean
	public Gson getGson() {
		return new Gson();
	}

	CommandLineRunner commandLineRunner(LogsRepository logsRepository) {
		return strings -> {
			logsRepository.save(new Logs(8, "Logs du nouveau endroit line runner"));
			logsRepository.save(new Logs(3, "Il devrait y avoir un log d'id 2 au dessus"));
			logsRepository.save(new Logs(4, "J'ajoute un log d'id 4 en ayant mis le logs repositorty autowired dans la config "));
		};
	}

	// @Override
	// protected String getDatabaseName() {
	// // TODO Auto-generated method stub
	// return mongoDB;
	// }

	@Bean
	@Description("Stock général des agents")
	public AgentManager getAgentManager() {
		return new AgentManager();
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