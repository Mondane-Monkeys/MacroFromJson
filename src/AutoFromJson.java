package ecc;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import processing.app.Base;
import processing.app.tools.Tool;
import processing.app.ui.Editor;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;

public class AutoFromJson implements Tool, KeyListener {

    Base base;
    Editor editor;
    // In Processing 3, the "Base" object is passed instead of an "Editor"

    public void init(Base base) {
        // Store a reference to the Processing application itself
        this.base = base;
    }

    public void run() {
        // Run this Tool on the currently active Editor window
        System.out.println("AutoFromJson is running.");
        System.out.println();
        editor = base.getActiveEditor();
        editor.getTextArea().addKeyListener(this);
        ArrFromJson.popJsonList();
    }

    public String getMenuTitle() {
        return "AutoFromJson";
    }

    @Override
    public void keyPressed(KeyEvent ke) {

        if (ke.getKeyCode() == KeyEvent.VK_SPACE && ke.getModifiers() == InputEvent.CTRL_MASK) {
            ke.consume();
            String txt = getTextBeforeCaret();
            int indent = getSpacesBeforeText(txt.length());
            Macros m = MacroMethods.find(editor, txt);           
            if (m != null) {
            	m.insert(editor, indent);
            }
        }
    }

    private int getSpacesBeforeText(int textLen) {
        int start = editor.getCaretOffset() - 1 - textLen;
        int i = start;
        String edtext = editor.getText();
        if (start >= 0) {
            char c = edtext.charAt(start);
            while (c == ' ' && i >= 0) {
                i--;
                if (i >= 0) {
                    c = editor.getText().charAt(i);
                }
            }
        }

        return start - i;
    }

    private String getTextBeforeCaret() {
        int start = editor.getCaretOffset() - 1;
        if (start >= 0) {
            int i = start;
            String edtext = editor.getText();
            char c = edtext.charAt(start);
            while ((Character.isLetterOrDigit(c)||c == '/')&& i >= 0) {
                i--;
                if (i >= 0) {
                    c = editor.getText().charAt(i);
                }
            }
            i++;
            return editor.getText().substring(i, start + 1);
        } else {
            return "";
        }
    }

    @Override
    public void keyTyped(KeyEvent ke
    ) {
    }

    @Override
    public void keyReleased(KeyEvent ke
    ) {      
    }
}
