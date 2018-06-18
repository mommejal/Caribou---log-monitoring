package com.caribou;
import org.springframework.web.bind.annotation.*;
import com.logs.logXMLTest;
import com.logs.GeneLog;
import com.dao.LogDao;
import com.logs.ListeDeLogs;
import com.dao.GeneLogDaoImpl;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
//import java.lang.String;
import java.util.Date;
import com.caribou.MvcConfig;

@RestController
@Controller
public class LogController {
	
	    @Autowired
		ListeDeLogs logs;
//        logs.add(new logXMLTest(new String("<![LOG[Exiting Microsoft::EmbeddedDeviceManager::Deployment::UwfManager::CheckWriteFilterProtection...]LOG]!><time=\"13:03:11.840-120\" date=\"06-02-2018\" component=\"MaintenanceCoordinator\" context=\"\" type=\"0\" thread=\"2188\" file=\"uwfmanager.cpp:501\">")));
//        logs.add(new logXMLTest(new String("<![LOG[Exiting Microsoft::EmbeddedDeviceManager::Deployment::UwfManager::GetVolumeProtectionInfos...]LOG]!><time=\"13:03:11.840-120\" date=\"06-02-2018\" component=\"MaintenanceCoordinator\" context=\"\" type=\"0\" thread=\"2188\" file=\"uwfmanager.cpp:258\">"))); 
//        logs.add(new logXMLTest(new String("<![LOG[Exiting Microsoft::EmbeddedDeviceManager::Deployment::UwfManager::SuspendProtection...]LOG]!><time=\"13:03:11.855-120\" date=\"06-02-2018\" component=\"MaintenanceCoordinator\" context=\"\" type=\"0\" thread=\"2188\" file=\"uwfmanager.cpp:124\">")));
	    @Autowired
	    ModelAndView mav;
//		ModelAndView mav = new ModelAndView("listeLogs");
	    public LogDao logdao;
	    @RequestMapping(method = RequestMethod.GET)
	    ModelAndView
	    index()
	    {
//	        ModelAndView mav = new ModelAndView("index");
//	        mav.addObject("hoa_version", "HOA v0.1 parce que je viens de LogController");
	        mav.setViewName("index");
	        return mav;
	    }
	    

	    @RequestMapping(value="hello",method = RequestMethod.GET)
	    ModelAndView
	    hello()
	    {
//	        ModelAndView mav = new ModelAndView("hellothymeleaf");
//	        mav.addAttribute("serverTime", "Il est 10h");
	        mav.addObject("hello", "hello de la part du Log Controller");
	    	logXMLTest log = new logXMLTest(new String("<![LOG[Exiting Microsoft::EmbeddedDeviceManager::Deployment::UwfManager::CheckWriteFilterProtection...]LOG]!><time=\"13:03:11.840-120\" date=\"06-02-2018\" component=\"MaintenanceCoordinator\" context=\"\" type=\"0\" thread=\"2188\" file=\"uwfmanager.cpp:501\">"));
	    	mav.addObject("logMSG" ,log.getMSG());
	        mav.setViewName("hellothymeleaf");
	        return mav;
	    }
	    	    
        @GetMapping("/greet")
        ModelAndView
        greet(@RequestParam(name="name", required=false, defaultValue="World") String name, ModelAndView model) {
            model.addObject("name", name);
            model.setViewName("greet");
            return model;
        }
        
        @GetMapping("/greeting")
        ModelAndView
        greeting(@RequestParam(name="name", required=true, defaultValue="World") String name, ModelAndView model) {
            model.addObject("name", name);
            model.setViewName("greeting");
            return model;
        }
//
	    @RequestMapping(value="/listeLogs", method=RequestMethod.GET)
	    ModelAndView
	    listeLogs(ArrayList <GeneLog> logs, ModelAndView mav) {
	    	// On veut afficher une liste de logs pour l'instant on affiche uniquement les messages
//	    	ModelAndView mav = new ModelAndView("listeLogs");
//	    	ArrayList <logXMLTest> logs = new ArrayList<logXMLTest>();
	        //Quelques logs de test
	        logs.add(new logXMLTest(new String("<![LOG[Exiting Microsoft::EmbeddedDeviceManager::Deployment::UwfManager::CheckWriteFilterProtection...]LOG]!><time=\"13:03:11.840-120\" date=\"06-02-2018\" component=\"MaintenanceCoordinator\" context=\"\" type=\"0\" thread=\"2188\" file=\"uwfmanager.cpp:501\">")));
	        logs.add(new logXMLTest(new String("<![LOG[Exiting Microsoft::EmbeddedDeviceManager::Deployment::UwfManager::GetVolumeProtectionInfos...]LOG]!><time=\"13:03:11.840-120\" date=\"06-02-2018\" component=\"MaintenanceCoordinator\" context=\"\" type=\"0\" thread=\"2188\" file=\"uwfmanager.cpp:258\">"))); 
	        logs.add(new logXMLTest(new String("<![LOG[Exiting Microsoft::EmbeddedDeviceManager::Deployment::UwfManager::SuspendProtection...]LOG]!><time=\"13:03:11.855-120\" date=\"06-02-2018\" component=\"MaintenanceCoordinator\" context=\"\" type=\"0\" thread=\"2188\" file=\"uwfmanager.cpp:124\">")));
	        //On constate qu'une méthode get sur chaque log suffit à afficher le bon truc
	        System.out.println("On passe par le addROw");
	        mav.addObject("logs",logs);
	        mav.setViewName("listeLogs");
	        return mav;
	        //	        return logdao.findAll();
	    }
	    
	    @RequestMapping(path="/listeLogs", params={"addRow"},method=RequestMethod.GET)
	    @ResponseBody
	    ModelAndView
	    /*LinkedList <GeneLog> logs, GeneLog logaajouter,*/
	    addRow(/*@RequestParam("addRow") boolean addRow,*/ ArrayList <GeneLog> logs, ModelAndView mav) {
//	    	List <GeneLog> logs = new LinkedList<GeneLog>();
//	    	if (addRow) {
	    	logs.add(new logXMLTest(new String("<![LOG[JE SUIS LE LOG RAJOUTE PAR addRow::EmbeddedDeviceManager::Deployment::UwfManager::SuspendProtection...]LOG]!><time=\"13:03:11.855-120\" date=\"06-02-2018\" component=\"MaintenanceCoordinator\" context=\"\" type=\"0\" thread=\"2188\" file=\"uwfmanager.cpp:124\">")));
//	    	}
	    	System.out.println("On passe par le addROw");
	    	mav.addObject("logs",logs);
	    	mav.setViewName("listeLogs");
	    	return mav;
	    }

//	    @RequestMapping(value="/listeLogs", params={"removeRow"})
//	    ModelAndView
//	    public String removeRow(LinkedList <GeneLog> logs, int id) {
//	    	//On enleve le log de tel id
//	        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
//	        seedStarter.getRows().remove(rowId.intValue());
//	        return "listeLogs";
//	    }
	    
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

}
