package com.caribou;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ListeDeLogs {
	ArrayList<Logs>liste;
}
