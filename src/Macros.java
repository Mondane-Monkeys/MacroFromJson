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
 *
 * @author dahjon
 * @modifiedBy Ness Tran http://google.ca
 * @modified 11/25/2021
 * @version  1.0.0
 */

package MacroFromJson;

import processing.app.ui.Editor;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/**
 * Macros class holds methods needed to take a keyword and add code snippet to
 * editor
 */
public class Macros {

	protected String key;
	protected String code;
	protected int carBack;

	public Macros(String key, String code, int carBack) {
		this.key = key;
		this.code = code;
		this.carBack = carBack;
	}

	protected int getNumbersOfLineBreaks(String str) {
		int nr = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '\n') {
				nr++;
			}
		}
		return nr;
	}

	public static Macros find(Editor editor, String sstr) {
		for (int i = 0; i < MacroFromJson.macroList.length; i++) {
			Macros m = MacroFromJson.macroList[i];
			if (m.stringIsThisMacro(editor, sstr)) {
				return m;
			}

		}
		return null;
	}

	public boolean stringIsThisMacro(Editor editor, String sstr) {
		return key.equals(sstr);
	}

	public void insert(Editor editor, int indent) {
		int nr = getNumbersOfLineBreaks(code.substring(code.length() - carBack));
		String indentStr = new String(new char[indent]).replace('\0', ' ');
		String str = code.replaceAll("\n", "\n" + indentStr);
		editor.insertText(str);
		// System.out.println("nr = " + nr);
		int carPos = editor.getCaretOffset() - carBack - nr * indent;
		editor.getTextArea().setCaretPosition(carPos);

	}
}
