package com.dao;
import com.logs.GeneLog; // GeneLog est abstraite, ca engendrera peut etre des pbs
import com.logs.logXMLTest;
import java.util.List;

public interface LogDao {
    public List <GeneLog> findAll();
    public GeneLog findById(int id);
    public GeneLog save(GeneLog log);
    public GeneLog findBySeverityLvl(int type);
}