/**
 * Part of the MacroFromJson tool for Processing
 *
 * (c) 2015
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 *
 * @author   Ness Tran http://google.ca
 * @modified 11/21/2021
 * @version  1.0.0
 */

package MacroFromJson;

import processing.app.ui.Editor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/**
 * InitMacroList class used to parse a Json file into an array of Macros.
 */
public class InitMacroList {

	/**
	 * Takes json file and parses it into a Macros[] array
	 * 
	 * @param jsonPath     relative path to json file. Ie. "config//macros.json"
	 * @param foundMessage Adds text to user message
	 * @return Array of macros found in json file
	 */
	public static Macros[] parseMacros(String jsonPath, String foundMessage) {
		Macros[] returnArray = {};
		JSONObject jo = null;
		if (!checkConfig(jsonPath)) {
			return returnArray;
		}
		try {
			// Get json object from file
			FileReader fileRead = new FileReader(jsonPath);
			Object obj = new JSONParser().parse(fileRead);
			jo = (JSONObject) obj;
			fileRead.close();
		} catch (Exception e) {
			System.out.println("InitMacroList: \n" + e);
			return returnArray;
		}
		// Parse json object and concat to returnArray
		Macros[] parsedArr = genBoolArray(jo);
		returnArray = concatMacroArrays(returnArray, parsedArr);
		parsedArr = jsonArrayParser(jo, "macros", false);
		returnArray = concatMacroArrays(returnArray, parsedArr);
		parsedArr = jsonArrayParser(jo, "replaceMacros", true);
		returnArray = concatMacroArrays(returnArray, parsedArr);

		System.out.println(foundMessage + returnArray.length + " macros found.");
		return returnArray;
	}

	/**
	 * Used to parse JSONArray from a JSONObject and returns elements as a Macros[]
	 * with Macros objects or ReplaceMacro objects
	 * 
	 * @param jo        JSONObject to be parsed. Expects "arrayName":[{"key":"for",
	 *                  "code":"syntax", "carBack":0}, ...]
	 * @param arrayName Name of array to parse in JSONObject
	 * @param isReplace If true, returns ReplaceMacros objects, else returns Macros
	 *                  objects
	 * @return Macros[] from JSONArray
	 */
	private static Macros[] jsonArrayParser(JSONObject jo, String arrayName, boolean isReplace) {
		Macros[] returnArray = {};
		try {
			JSONArray ja = (JSONArray) jo.get(arrayName);
			Iterator itr = ja.iterator();
			returnArray = new Macros[ja.size()];
			int i = 0;
			while (itr.hasNext()) {
				JSONObject thisMacro = (JSONObject) itr.next();
				String key = (String) thisMacro.get("key");
				String code = (String) thisMacro.get("code");
				long carBack = (long) thisMacro.get("carBack");

				if (!isReplace) {
					returnArray[i] = new Macros(key, code, (int) carBack);
				} else if (isReplace) {
					String imp = (String) thisMacro.get("import");
					returnArray[i] = new ReplaceMacros(key, code, (int) carBack, imp);
				}
				i++;
			}
			return returnArray;

		} catch (Exception e) {
			System.out.println("\n\nCouldn't find " + arrayName + " from macros.json: \n" + e + "\n");
			return returnArray;
		}
	}

	/**
	 * Ensures either the macros.json file exists Or prompts user to create new
	 * file.
	 */
	private static boolean checkConfig(String relativePath) {
		String myPath = "";
		try {
			File myFile = new File(relativePath);
			myPath = relativePath;
			myPath = myFile.getCanonicalPath();
			if (myFile.exists()) {
				System.out.println("Config file found at:");
				System.out.println(myPath);
				return true;
			} else {
				throw new FileNotFoundException();
			}
		} catch (Exception e) {
			System.out.println("Config file, " + Const.fileName + ", missing at: ");
			System.out.println(myPath + Const.NO_CONFIG);
			return false;
		}
	}

	/**
	 * Takes two Macros[] arrays and concatonates them into a single array If concat
	 * fails, array1 will be returned
	 * 
	 * @param array1 Expects Macros[]
	 * @param array2 Expects Macros[]
	 * @return Macros[] like {array1[0],array1[1]... array2[0], array2[1]...}
	 */
	private static Macros[] concatMacroArrays(Macros[] array1, Macros[] array2) {
		try {
			int arr1Len = array1.length;
			int arr2Len = array2.length;
			Macros[] returnArray = new Macros[arr1Len + arr2Len];
			System.arraycopy(array1, 0, returnArray, 0, arr1Len);
			System.arraycopy(array2, 0, returnArray, arr1Len, arr2Len);
			return returnArray;
		} catch (Exception e) {
			System.out.println(e);
			return array1;
		}
	}

	/**
	 * Creates Macros[] array with all unique Macros set to true in JSONObject
	 * 
	 * @param jo JSONObject to be parsed
	 * @return Macros[] with extended macros with only one possible output
	 */
	private static Macros[] genBoolArray(JSONObject jo) {
		Macros[] returnArray = {};

		// Checks code skeleton toggle
		try {
			boolean codeSkeletonToggle = (boolean) jo.get("CodeSkeletonMacro");
			if (codeSkeletonToggle) {
				Macros[] codeSkelArr = { new CodeSkeletonMacro() };
				returnArray = concatMacroArrays(returnArray, codeSkelArr);
			}
		} catch (Exception e) {
			System.out.println("\n\nCodeSkeletonMacro not found\n" + e + "\n");
		}

		// Checks function macro toggle
		try {
			boolean functionMacroToggle = (boolean) jo.get("FunctionMacros");
			if (functionMacroToggle) {
				Macros[] functMacroArr = { new FunctionMacro() };
				returnArray = concatMacroArrays(returnArray, functMacroArr);
			}
		} catch (Exception e) {
			System.out.println("\nFunctionMacros not found\n" + e + "\n");
		}

		// Checks input macro Toggle
		try {
			boolean inputToggle = (boolean) jo.get("input");
			if (inputToggle) {
				Macros[] inputMacros = {
						new ReplaceMacros("input", "JOptionPane.showInputDialog(\"\")", 2,
								"import javax.swing.JOptionPane;\n"),
						new ReplaceMacros("output", "JOptionPane.showMessageDialog(null, \"\");", 3,
								"import javax.swing.JOptionPane;\n"),
						new ReplaceMacros("inputstr", "String str = JOptionPane.showInputDialog(\"\");", 3,
								"import javax.swing.JOptionPane;\n") };
				returnArray = concatMacroArrays(returnArray, inputMacros);
			}
		} catch (Exception e) {
			System.out.println("\n\ninput not found\n" + e + "\n");
		}

		return returnArray;
	}
}