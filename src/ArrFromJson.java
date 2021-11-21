/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecc;

import processing.app.ui.Editor;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


public class ArrFromJson {//Collect macros from json file and store in jsonList[]
	//begin redundant code
	protected String key;
    protected String code;
    protected int carBack;
    
	public ArrFromJson(String key, String code, int carBack) {
        this.key = key;
        this.code = code;
        this.carBack = carBack;
    }
	//end redundant code
	
	//Sets file path to macro json file. rooted in processing-3.5.4 folder
	private static String filePath = "config//CustomMacros.json";
	
	//Count used macros to determine jsonList[] size
	private static int totalEntries() {
		//see popJsonList for breakdown
		try {
    		int total=0;
            Object obj = new JSONParser().parse(new FileReader(filePath));
            JSONObject jo = (JSONObject) obj;
            
            //count macros store in total
            JSONArray ja = (JSONArray) jo.get("macros");
            Iterator itr2 = ja.iterator();
            while (itr2.hasNext()) 
            {
            	JSONObject ping = (JSONObject) itr2.next();
            	total++;
            }
            
            //count replace macros store in total
            ja = (JSONArray) jo.get("replaceMacros");
            itr2 = ja.iterator();
            while (itr2.hasNext()) 
            {
            	JSONObject ping = (JSONObject) itr2.next();
            	total++;
            }
            return total;
    	}
    	catch(Exception ex){
    		System.out.println("get from .json failed");
    		System.out.println(ex.getMessage());
    		System.out.println();
    		System.out.println();
    		return 0;
    	}
    }
	
	
	//create empty arrays to store json macros into
	private static int arrayEntries = totalEntries() + 15;
    public static Macros[] jsonList = new Macros [totalEntries()];

    
    public static void popJsonList() {
    	try {
    		// parsing file "JSONExample.json"
            Object obj = new JSONParser().parse(new FileReader(filePath));
            System.out.println("Success:");
            System.out.println("Json file found at: processing-3.5.4//" + filePath);
              
            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;
            
            //Get Macros
            JSONArray ja = (JSONArray) jo.get("macros");//select Json Array
            Iterator itr2 = ja.iterator();
            int i=0;
            while (itr2.hasNext()) 
            {//iterator across Macros[] in json file and save to jsonList
            	JSONObject ping = (JSONObject) itr2.next();
            	String key = (String) ping.get("key");
            	String code= (String) ping.get("code");
            	long carBack=(long) ping.get("carBack");
    	    	jsonList[i] = new Macros(key,code,(int)carBack);
                i++;
            }
            
            // Get replaceMacros
            ja = (JSONArray) jo.get("replaceMacros");//select Json Array
            itr2 = ja.iterator();
            while (itr2.hasNext()) 
            {//iterator across replaceMacros[] in json file and save to jsonList
            	JSONObject ping = (JSONObject) itr2.next();
            	String key = (String) ping.get("key");
            	String code= (String) ping.get("code");
            	long carBack=(long) ping.get("carBack");
    	    	jsonList[i] = new ReplaceMacros(key,code,(int)carBack);
               i++;
            } 
    	}
    	catch(Exception ex){
    		System.out.println("Get from .json failed");
    		System.out.println(ex.getMessage());
    		System.out.println();
    		System.out.println();
    	}
    }
}