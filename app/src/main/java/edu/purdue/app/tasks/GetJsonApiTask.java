package edu.purdue.app.tasks;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import edu.purdue.app.util.Logger;

/**
 *  This is a general Async task which manages accessing and returning a jsonobject
 *  from a given URL. The status code of the response is always stored in the statusCode class
 *  variable. The raw text response from the API is always stored in the rawResponse class variable.
 *  When the Http call and json parsing is complete, if a resultlistener is provided then that
 *  callback is called on the UI thread.
 *
 *  There are a few ways you can use this class.
 *
 *  -   Create a GetJsonApiTask object from another more specialized async task. Directly call
 *      doInBackground(), then use the result and directly access the error objects as class members.
 *      (This is my preferred method)
 *
 *  -   Create a GetJsonApiTask object from anywhere and provide a ResultListener callback which
 *      will be called on the UI thread upon completion. Then just start it with execute(). Note
 *      that with this method there is no easy way to determine if an error code other than 200 is
 *      returned from the call IF this error code does not result in an exception being thrown.
 *
 *  -   Subclass GetJsonApiTask, call super.doInBackground() in your doInBackground(), do something
 *      with the JSONObject, then pass your result through a callback instead of the inevitable
 *      JSONObject return from your doInBackground() (I personally don't like this way as your
 *      subclass doInBackground() has to return a JSONObject even though it really shouldn't, but
 *      its an option).
 *
 *  See GetDiningMenusTask for an example usage of this class.
 *
 *  Error handling is provided in several ways.
 *
 *  -   If there was an error with the network call itself, an exception object will be stored
 *      in the error class variable and null is returned from doInBackground(). There is no
 *      guarantee that statusCode or rawResponse will be set.
 *
 *  -   If there's an error with json parsing, null is returned from doInBackground(), the json
 *      exception is stored in error (see point 1) and the raw response is stored in rawResponse
 *      (as always)
 *
 *  -   If the server returns a non-200 status code, the code is stored in statusCode (as always)
 *      and json parsing is attempted. If it succeeds then it is returned. Otherwise, see point 2.
 *
 *  A helper method isInError() is provided which acts as a catch-all to check whether or not
 *  anything unusual happened with this call. If it returns true, you should investigate as per
 *  the bullet points above.
 *
 *  Created by mike@hockerman.com on 1/22/15.
 */
public class GetJsonApiTask extends AsyncTask<String, Void, JSONObject> {

    /** Callback interface which is passed the result and an exception if one is thrown */
    public interface ResultListener {
        public void onResult(JSONObject result, Exception ex);
    }

    /** The status code of the http response from the server. */
    protected int statusCode = 0;

    /** The raw string response from the api server. */
    protected String rawResponse = null;

    /** The error, if any, encountered during the entire doInBackground() method. */
    protected Exception error = null;

    /** The optional callback that is called when the network call is complete */
    protected ResultListener callback;

    /** Default constructor */
    public GetJsonApiTask() {}

    /** Constructor that provides a callback */
    public GetJsonApiTask(ResultListener callback) {
        this.callback = callback;
    }

    /** Downloads and returns a json object for the url in urls[0]
     *  @param urls the url to download from. if more than 1 is provided, only the first will be accessed.
     *  @return the json object from the api
     *  if the return object is null, check the class variable error for the exception object */
    @Override
    protected JSONObject doInBackground(String... urls) {

        try {

            // Execute the http request
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(urls[0]);
            HttpResponse response = client.execute(get);

            // Set the response code as a class variable
            statusCode = response.getStatusLine().getStatusCode();

            // Set the raw response
            rawResponse = EntityUtils.toString(response.getEntity());

            // Parse the json
            JSONObject obj = new JSONObject(rawResponse);
            return obj;

        } catch (ClientProtocolException ex) {
            Logger.e(ex);
            error = ex;
        } catch (JSONException ex) {
            Logger.e(ex);
            error = ex;
        } catch (IOException ex) {
            Logger.e(ex);
            error = ex;
        }

        return null;

    }

    /** UI thread callback if a callback object is provided */
    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        if (this.callback != null) {
            callback.onResult(jsonObject, error);
        }
    }

    /** Returns whether or not the network call made after super.doInBackground() ended in any
     *  error. This checks both network errors which throw exceptions as well as non-200 http
     *  status return codes.
     * @return whether an error occurred */
    protected boolean isInError() {

        // If an error is set, then obviously something went wrong
        if (error != null) {
            return true;
        }

        // If the http status code is not 200, something probably went wrong
        if (statusCode < 200 || statusCode >= 300) {
            return true;
        }

        return false;

    }

}
