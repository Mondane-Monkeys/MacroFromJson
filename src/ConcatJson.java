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
public class ConcatJson {
	
	//takes two arrays of type Macros and returns one array with all elements ordered arr1, arr2
    private static Macros[] concatenate(Macros[] arr1, Macros[] arr2) {
        int aLen = arr1.length;
        int bLen = arr2.length;

        Macros[] newArr = new Macros[aLen + bLen];
        System.arraycopy(arr1, 0, newArr, 0, aLen);
        System.arraycopy(arr2, 0, newArr, aLen, bLen);
        return newArr;
    }
    
    //stores all macros
    public static Macros[] macrosJsonList = concatenate(Macros.macrosList,ArrFromJson.jsonList);
}