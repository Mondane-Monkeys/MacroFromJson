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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307 USA
 *
 * @author Ness Tran http://google.ca
 * @modified 12/12/2021
 * @version 1.0.0
 */

package MacroFromJson;

import processing.app.contrib.ContributionListing;
import processing.app.ui.Editor;

import javax.management.loading.PrivateClassLoader;
import javax.swing.*;
import javax.swing.Box.Filler;
import javax.xml.transform.Templates;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Window;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.PublicKey;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;



public class GuiLineObject {
	
	JTextField keyField;
	JTextArea codeField;
	JTextField carBackField;
	JCheckBox removeKeyField;
	JTextField importField;
	JTextField groupNameField;
	JButton deleteButton;
	int ID;
	
	public GuiLineObject(JTextField keyField,
						JTextArea codeField,
						JTextField carBackField,
						JCheckBox removeKeyField,
						JTextField importField,
						JTextField groupNameField,
						JButton deleteButton,
						int ID) {
		this.keyField = keyField;
		this.codeField = codeField;
		this.carBackField = carBackField;
		this.removeKeyField = removeKeyField;
		this.importField = importField;
		this.groupNameField = groupNameField;
		this.deleteButton = deleteButton;
		this.ID = ID;
	}
	
	public JPanel addLine() {
		JPanel localPanel = new JPanel();
		localPanel.setLayout(new BoxLayout(localPanel, BoxLayout.Y_AXIS));
			JPanel MBNewLine = new JPanel();
			MBNewLine.setLayout(new BorderLayout());
			// groupNameBoxs[i] = new JComboBox<String>(choices);
			JPanel MBStart = new JPanel();
			MBStart.setLayout(new BorderLayout());
			JPanel MBCenter = new JPanel();
			MBCenter.setLayout(new BorderLayout());
			JPanel MBEnd = new JPanel();
			MBEnd.setLayout(new BorderLayout());

			// LineStart
			MBStart.add(new JScrollPane(keyField), BorderLayout.CENTER);
			MBStart.add(deleteButton, BorderLayout.SOUTH);
			// Center
			keyField.setHorizontalAlignment(JTextField.CENTER);
			carBackField.setHorizontalAlignment(JTextField.CENTER);

//			MBCenter.add(new JScrollPane(codeFields[i]));
			MBCenter.add(codeField, BorderLayout.CENTER);
			// Line end
			carBackField.setPreferredSize(new Dimension(36,20));
//			removeKeyFields[i].setPreferredSize(new Dimension());
//			groupNameField[i]
			//new JScrollPane(codeFields[i])
			MBEnd.add(carBackField, BorderLayout.LINE_START);
			MBEnd.add(removeKeyField, BorderLayout.LINE_END);
			MBEnd.add(groupNameField, BorderLayout.CENTER);
			
			//cosmetic
			MBEnd.setPreferredSize(new Dimension(200,25));
			MBEnd.setBackground(Color.CYAN);
			MBStart.setPreferredSize(new Dimension(125,25));
//			MBEnd.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			
			MBNewLine.add(MBStart, BorderLayout.LINE_START);
			MBNewLine.add(MBCenter, BorderLayout.CENTER);
			MBNewLine.add(MBEnd, BorderLayout.LINE_END);
			// macroPanel.add(groupNameBoxs[i]);
			MBNewLine.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			localPanel.add(MBNewLine);
		return localPanel;
	}
	
	public JTextField getKey() {
		return keyField;
	}
	public JTextArea getCode() {
		return codeField;
	}
	public JTextField getCarBack() {
		return carBackField;
	}
	public JCheckBox getRemoveKey() {
		return removeKeyField;
	}
	public JTextField getImp() {
		return importField;
	}
	public JTextField getGroupName() {
		return groupNameField;
	}
	public JButton getDelete() {
		return deleteButton;
	}
	
	public String getKeyVal() {
		return keyField.getText();
	}
	public String getCodeVal() {
		return codeField.getText();
	}
	public String getCarBackVal() {
		return carBackField.getText();
	}
	public boolean getRemoveKeyVal() {
		return removeKeyField.isSelected();
	}
	public String getImpVal() {
		return importField.getText();
	}
	public String getGroupNameVal() {
		return groupNameField.getText();
	}
	public int getID() {
		return ID;
	}
}
