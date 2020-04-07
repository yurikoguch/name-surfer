package com.shpp.p2p.cs.ykohuch.assignment7;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class NameSurferDataBase implements NameSurferConstants {

    /*Hash map whose key is the name and value of the data from the database*/
    private HashMap<String, NameSurferEntry> info;

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) {
        info = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String data;
            while (true) {
                data = br.readLine();
                if (data == null) {
                    break;
                } else {
                    NameSurferEntry entry = new NameSurferEntry(data);
                    info.put(entry.getName().toLowerCase(), entry);
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
    }

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        return info.getOrDefault(name, null);
    }

}

