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

//calls dedicated macros and json macros and creates a new macros array
public class MacroMethods {
	
	protected int getNumbersOfLineBreaks(String str){
        int nr=0;
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i)=='\n'){
                nr++;
            }
        }
        return nr;
    }
	
	//returns macro object from ConcatJson.macrosJsonList where key=sstr else null
    public static Macros find(Editor editor,String sstr) {
        for (int i = 0; i < ConcatJson.macrosJsonList.length; i++) {
        	if (ConcatJson.macrosJsonList[i] != null) {
	            Macros m = ConcatJson.macrosJsonList[i];
	            if (m.stringIsThisMacro(editor, sstr)) {
	                return m;
	            }
        	}
        }
        return null;
    }
}