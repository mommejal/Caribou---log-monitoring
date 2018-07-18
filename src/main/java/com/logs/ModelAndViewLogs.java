package com.logs;

import java.util.ArrayList;
import org.springframework.web.servlet.ModelAndView;

import com.logs.loggenerator.GeneLog;



public class ModelAndViewLogs {
    public ArrayList<GeneLog> logs;
    public ModelAndView modelAndView;
    
    public void logsVide() {
        this.logs = new ArrayList<GeneLog>();
   }

   public ArrayList<GeneLog> getLogs() {
       return logs;
   }

   public void setLogs(ArrayList<GeneLog> logs) {
       this.logs = logs;
   }
   
   public ModelAndView getModelAndView() {
       return modelAndView;
   }

   public void setModelAndView(ModelAndView mav) {
       this.modelAndView = mav;
   }
   
   public void add(GeneLog log) {
       this.logs.add(log);
   }
}
