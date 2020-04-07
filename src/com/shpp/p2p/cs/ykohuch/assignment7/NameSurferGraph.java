package com.shpp.p2p.cs.ykohuch.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    private ArrayList<NameSurferEntry> data = new ArrayList<>();


    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        data.clear();
       update();
    }


    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        data.add(entry);
        update();
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        placeGrid();
        placeGraph();
    }

    /*this method creates a grid as a background consisting of vertical
   lines that divide decades, and horizontal lines separating the upper and lower
   boundaries and titles of decades */
    private void placeGrid() {

        /*variable to determine the starting point on the horizontal x-axis*/
        double x = getWidth() / NDECADES;

        /*variable to determine the starting point on the vertical y-axis*/
        double y = getHeight();

        /*a loop that draws vertical lines and places inscriptions of decades*/
        for (int i = 0; i < NDECADES; ++i) {

            /*placed vertical lines at the distance of iteration*/
            add(new GLine(i * x , 0, i * x , y));

            /*placed labels of iteration using a constant START_DECADE for the starting year
            * With each iteration, the value is multiplied by a decade*/
            add(new GLabel(Integer.toString(START_DECADE + (i * DECADE)),  i * x , y));
        }

        /*Placed horizontal lines*/
        add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
        add(new GLine(0, y - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE));
    }

    /*this method creates a graph*/
    private void placeGraph() {

        /*variable for the set in order to specify the direction of drawing along the axis y
        in depending on the rank of the name's popularity in a certain decade*/
        double directionY = (getHeight() - GRAPH_MARGIN_SIZE*2) /(double) MAX_RANK;
        for (NameSurferEntry entry : data) {

            /*coordinates for line
            * horizontal and vertical axis*/
            double x = 0;
            double y;
            if (entry != null) {

                /*set y axis start position depending on the rank of the name*/
                if (entry.getRank(0) == 0) {
                    y = getHeight() - GRAPH_MARGIN_SIZE;
                } else {
                    y = (entry.getRank(0) * directionY) + GRAPH_MARGIN_SIZE;
                }
                Color color = addColor(data.indexOf(entry));
                drawingLine(x, y, entry, color, directionY);
            }
        }
    }

    /*This method adds graphics to the color depending on the order of the input.
     The chart of the first record should be blue, the second one should be red,
      the third one should be magenta, and the fourth one should be black.
     The colors are then repeated in the same sequence*/
    private Color addColor(int indexOf) {
        if (indexOf % 4 == 0) return Color.BLUE;
        else if (indexOf % 4 == 1) return Color.RED;
        else if (indexOf % 4 == 2) return Color.MAGENTA;
        else return Color.BLACK;
    }

    /*this method adds a line that changes axis y in direction depending on the rank of the name's popularity*/
    private void drawingLine(double x, double y, NameSurferEntry entry, Color color, double directionY) {
        for (int i = 0; i < NDECADES - 1; ++i) {

            /*set x, and y position for axis the ending line*/
            double xEnd = (i + 1) * getWidth() / NDECADES;
            double yEnd;
            if (entry.getRank(i + 1) == 0) {
                yEnd = getHeight() - GRAPH_MARGIN_SIZE;
            } else {
                yEnd = entry.getRank(i + 1) * directionY + GRAPH_MARGIN_SIZE;
            }

            /*draw line, label and change start coordinates for next line*/
            GLine line = new GLine(x, y, xEnd, yEnd);
            line.setColor(color);
            add(line);
            nameAndRankLabel(entry.getName(), entry.getRank(i), color, x, y);
            x = xEnd;
            y = yEnd;
        }
    }

    /*this method displays the name and rank of its popularity if the rating is 0 then we replace it with * */
    private void nameAndRankLabel(String name, int rank,Color color, double x, double y) {
        GLabel label = new GLabel(name + " " + rank, x, y);
        if (rank == 0) label.setLabel(name + " *");
        label.setColor(color);
        add(label);
    }


    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
