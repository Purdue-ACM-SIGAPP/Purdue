package edu.purdue.app.labs;

import retrofit.RestAdapter;

/**
 * Created by david on 4/8/15.
 */
public class LabsApi {

    private static RestAdapter mRestAdapter;

    private static ItapLabsService mItapService;

    private static RestAdapter getRestAdapter() {
        if(mRestAdapter == null) {
            mRestAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://purdue-labs.herokuapp.com")
                    .build();

            //Initialize the adapter.
            mRestAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
        }
        return mRestAdapter;
    }

    public static ItapLabsService Itap() {
        if(mItapService == null) {
            mItapService = getRestAdapter().create(ItapLabsService.class);
        }
        return mItapService;
    }
}
