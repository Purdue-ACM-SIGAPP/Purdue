package edu.purdue.app.labs.listeners;

import java.util.List;

import edu.purdue.app.labs.model.AvailableLab;
import edu.purdue.app.labs.model.Lab;

/**
 * Created by david on 9/2/14.
 */
public interface OnGetAvailableLabsListener {
    public void onGetLabs(List<AvailableLab> labs);
}
