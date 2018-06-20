package com.logs;

import java.util.ArrayList;
import java.util.List;
public class ListeDeLogs {
    public List<GeneLog> listeDeLogs;

    public void ListeDeLogsWrapper() {
         this.listeDeLogs = new ArrayList<GeneLog>();
    }

    public List<GeneLog> getListeDeLogs() {
        return listeDeLogs;
    }

    public void setListeDeLogs(List<GeneLog> listeDeLogs) {
        this.listeDeLogs = listeDeLogs;
    }

    public void add(GeneLog log) {
        this.listeDeLogs.add(log);
    }
}
