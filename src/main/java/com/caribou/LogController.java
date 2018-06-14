package com.caribou;
import org.springframework.web.bind.annotation.*;
import com.logs.logXMLTest;
import com.logs.GeneLog;
import com.dao.LogDao;
import com.dao.GeneLogDaoImpl;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;
//import java.lang.String;
import java.util.Date;
import com.caribou.MvcConfig;

@RestController
@Controller
public class LogController {
	
//	    @Autowired
	    public LogDao logdao;
	    @RequestMapping(method = RequestMethod.GET)
	    ModelAndView
	    index()
	    {
	        ModelAndView mav = new ModelAndView("index");
//	        mav.addObject("hoa_version", "HOA v0.1 parce que je viens de LogController");
	        mav.setViewName("index");
	        return mav;
	    }
	    

	    @RequestMapping(value="hello",method = RequestMethod.GET)
	    ModelAndView
	    hello()
	    {
	        ModelAndView mav = new ModelAndView("hellothymeleaf");
//	        mav.addObject("hello", "hello de la part du Log Controller");
	        mav.setViewName("hellothymeleaf");

	        return mav;
	    }
	    
//	    @RequestMapping(value="/log0", method=RequestMethod.GET)
//	    public String log0() {
//	        return logs[0].getData();
//	    }
//	    @RequestMapping(value = "/", method = RequestMethod.GET)
//	    public String index() {
//	        return "index";
//	    }
	    
//	    @RequestMapping(value = "hello", method = RequestMethod.GET)
//	    public String hello() {
//	        return "Helloworld.html";
//	    }
	    //Récupérer la liste complete des logs
	    @RequestMapping(value="/ListeLogs", method=RequestMethod.GET)
	    public List <GeneLog> listeLogs() {
	        return logdao.findAll();
	    }
	   
	    
	    @GetMapping(value = "/SeverityLvl/{lvl}")
	    public String afficherUnLogSeverityLvl(@PathVariable int lvl) {
	    	return (logdao.findBySeverityLvl(lvl)).toString();
	    }
	    
	    @GetMapping(value = "/AfficherUnLogEtId")
	    public String afficherUnLogEtId() {
	    	// Devra prendre en argument un log et l'afficher
	    	logXMLTest log = new logXMLTest(new String("Contenu du log d'Id="));
	        return (log.getData()+log.getID());
	    }
	    
//	    @GetMapping(value = "/afficherUnLog")
//	    public String afficherUnLog() {
//	    	// Devra prendre en arguments un log et son iD et l'afficher TODO
//	    	logXMLTest log = new logXMLTest(new String("<![LOG[Exiting Microsoft::EmbeddedDeviceManager::Deployment::UwfManager::CheckWriteFilterProtection...]LOG]!><time=\"13:03:11.840-120\" date=\"06-02-2018\" component=\"MaintenanceCoordinator\" context=\"\" type=\"0\" thread=\"2188\" file=\"uwfmanager.cpp:501\">"));
//	    	System.out.println(log.getMSG());
////	        return log.getData();
//	    	return "afficherUnLog";
////	    	sreturn log.getMSG();
//	    }
	    @RequestMapping(value="afficherUnLog",method = RequestMethod.GET)
	    ModelAndView
	    afficherUnLog()
	    {
	    	logXMLTest log = new logXMLTest(new String("<![LOG[Exiting Microsoft::EmbeddedDeviceManager::Deployment::UwfManager::CheckWriteFilterProtection...]LOG]!><time=\"13:03:11.840-120\" date=\"06-02-2018\" component=\"MaintenanceCoordinator\" context=\"\" type=\"0\" thread=\"2188\" file=\"uwfmanager.cpp:501\">"));
	        ModelAndView mav = new ModelAndView("afficherUnLog");
	        mav.addObject("hello", "hello de la part du Log Controller");
	        mav.setViewName("afficherUnLog");
	        return mav;
	    }
}
