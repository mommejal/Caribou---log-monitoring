package com.caribou;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.logs.GeneLog;
import com.logs.ListeDeLogs;
import com.logs.ModelAndViewLogs;
import com.logs.logXMLTest;
import com.caribou.LogsRepository;
import com.caribou.Logs;

@SuppressWarnings("deprecation")
@EnableMongoRepositories(basePackageClasses = LogsRepository.class)
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ModelAndViewLogs getModelAndViewLogs()
	// Pour l'instant j'en crée un uniquement pour liste logs
	{
		ModelAndViewLogs mavl;
		mavl = new ModelAndViewLogs();
		mavl.modelAndView = new ModelAndView("listeLogs");
		mavl.logs = new ArrayList<GeneLog>();
		mavl.logs.add(new logXMLTest("Début de la liste de logs"));
		return mavl;

	}

	@Bean
	public ModelAndView getModelAndView()
	// Pour l'instant j'en crée un uniquement pour liste logs
	{
		return new ModelAndView("listeLogs");
	}

	@Bean
	public ListeDeLogs getListeDeLogs() {
		return new ListeDeLogs();
	}

	@Bean
	CommandLineRunner commandLineRunner(LogsRepository logsRepository) {
		return strings -> {
			logsRepository.save(new Logs(1, "Contenu du premier Log"));
			logsRepository.save(new Logs(3, "Il devrait y avoir un log d'id 2 au dessus"));
		};
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