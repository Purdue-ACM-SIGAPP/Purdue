package edu.purdue.app.dining.data;

/**
 *  Same as DiningLocationName but for the time/meal names.
 *  Again, this should come from the API, and it did in commits past, but
 *  this just slows down the app needlessly while complicating the code.
 *  Created by mike on 2/6/15.
 */
public enum DiningTime {

    breakfast
            ("Breakfast", 1),
    lunch
            ("Lunch", 2),
    latelunch
            ("Late Lunch", 3),
    dinner
            ("Dinner", 4);

    private String printable;
    private int order;

    DiningTime(String printable, int order) {
        this.printable = printable;
        this.order = order;
    }

    public String printable() {
        return this.printable;
    }

    public int order() {
        return this.order;
    }

}
