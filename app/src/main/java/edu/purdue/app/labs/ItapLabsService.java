package edu.purdue.app.labs;

import java.util.List;

import edu.purdue.app.labs.model.Lab;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by david on 4/8/15.
 */
public interface ItapLabsService {
    @GET("/labs/itap")
    List<Lab> getAllItapLabs();

    @GET("/labs/itap/{building}")
    List<Lab> getLabsForBuilding(@Path("building") String building);

    @GET("/labs/itap/{building}/{room}")
    List<Lab> getLab(@Path("building") String building, @Path("room") String room);

}
