package com.shpp.p2p.cs.ykohuch.assignment7;
import com.shpp.cs.a.simple.SimpleProgram;
import javax.swing.*;
import java.awt.event.*;

/** This program implements the viewer for
 the baby-name database described in the assignment handout.
 */
public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    /*text input field*/
	private JTextField textField;

    /*button "Clear"*/
    private JButton clearButton;

    /*Variable that allows to work with the class NameSurferGraph*/
    private NameSurferGraph graph;

    /*a variable that allows you to use the txt file as a database*/
    private NameSurferDataBase dataBase;


    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        /*here is the label on the window*/
        JLabel name = new JLabel("Name");
        add(name, NORTH);

        /*here is the text field on the window
        * and adds action listeners*/
        textField = new JTextField(40);
        add(textField, NORTH);
        textField.addActionListener(this);

        /*here is the button "Graph" on the window
        * * and adds action listeners*/
        JButton graphButton = new JButton("Graph");
        add(graphButton, NORTH);
        graphButton.addActionListener(this);

        /*here is the button "Clear" on the window
        * * and adds action listeners*/
        clearButton = new JButton("Clear");
        add(clearButton, NORTH);
        clearButton.addActionListener(this);

        /*add the graph on the screen*/
        graph = new NameSurferGraph();
        add(graph);

        /*associate a variable dataBase with a text file in NameSurferDataBase*/
        dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
    }

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == clearButton) graph.clear();
        else {
            String findName = textField.getText().toLowerCase();
            NameSurferEntry name = dataBase.findEntry(findName);
            if (name != null) graph.addEntry(name);
        }
    }
}
