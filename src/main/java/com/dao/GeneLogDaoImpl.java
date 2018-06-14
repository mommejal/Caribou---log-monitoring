package com.dao;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import java.util.ArrayList;
import java.util.List;
import com.logs.GeneLog;
import com.logs.logXMLTest;
import java.lang.String;
import java.util.Date;

@Repository
public class GeneLogDaoImpl implements LogDao {
	
    public static List <GeneLog> logs = new ArrayList<GeneLog>();
    static {
    	//Quelques logs de test
    	logs.add(new logXMLTest(new String("<![LOG[Exiting Microsoft::EmbeddedDeviceManager::Deployment::UwfManager::CheckWriteFilterProtection...]LOG]!><time=\"13:03:11.840-120\" date=\"06-02-2018\" component=\"MaintenanceCoordinator\" context=\"\" type=\"0\" thread=\"2188\" file=\"uwfmanager.cpp:501\">")));
        logs.add(new logXMLTest(new String("<![LOG[Exiting Microsoft::EmbeddedDeviceManager::Deployment::UwfManager::GetVolumeProtectionInfos...]LOG]!><time=\"13:03:11.840-120\" date=\"06-02-2018\" component=\"MaintenanceCoordinator\" context=\"\" type=\"0\" thread=\"2188\" file=\"uwfmanager.cpp:258\">"))); 
        logs.add(new logXMLTest(new String("<![LOG[Exiting Microsoft::EmbeddedDeviceManager::Deployment::UwfManager::SuspendProtection...]LOG]!><time=\"13:03:11.855-120\" date=\"06-02-2018\" component=\"MaintenanceCoordinator\" context=\"\" type=\"0\" thread=\"2188\" file=\"uwfmanager.cpp:124\">")));
    }
    
	@Override
    public List <GeneLog> findAll(){
		return logs;
	}
	
	@Override
    public GeneLog findById(int id){
		for (GeneLog log : logs) {
			if(log.getID() == id) {
				return log;
			}
		}
		return null;
	}
	
	@Override
    public GeneLog save(GeneLog log){
    	logs.add(log);
    	return log;
    }
	
    @Override
    public GeneLog findBySeverityLvl(int severitylvl) {
    	// Trouve le premier log de ce severity lvl
        for (GeneLog log : logs) {  
            if(log.getSeverityLvl() == severitylvl){
                return log;
            }
        }
        return null;
    }
}

