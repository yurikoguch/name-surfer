package com.shpp.p2p.cs.ykohuch.assignment7;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

public class NameSurferEntry implements NameSurferConstants {

    /*Information from data base*/
    private String dataInfo;

    /*Name entered by the user*/
    private String name;

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        dataInfo = line;
        name = getName();
    }

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        String[] nameFromBase = dataInfo.split(" ");
        return nameFromBase[0];
    }

	/* Method: getRank(decade) */

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        String [] rank = dataInfo.split(" ");
        return Integer.parseInt(rank[decade + 1]);
    }

	/* Method: toString() */

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        StringBuilder outputRow = new StringBuilder(name + " [");
        for (int i = 0; i < NDECADES; i++) {
            outputRow.append(getRank(i)).append(" ");
        }
        outputRow = new StringBuilder(outputRow.substring(0, outputRow.length() - 1) + "]");

        return outputRow.toString();
    }
}

