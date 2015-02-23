package edu.purdue.app.weather;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.purdue.app.R;

/**
 * Created by Michael on 1/29/2015.
 */
public class WeatherForecastFragment extends Fragment {

    public static TextView cityText;
    public static TextView condDescr;
    public static TextView minTemp;
    public static TextView maxTemp;
    public static TextView temp;
    public static TextView press;
    public static TextView windSpeed;
    public static TextView windDeg;
    public static TextView unitTemp;
    public static TextView hum;
    public static TextView visibility;
    public static TextView sunrise;
    public static TextView sunset;
    public static ImageView imgView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dayforecast_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        cityText = (TextView) view.findViewById(R.id.cityText);
        condDescr = (TextView) view.findViewById(R.id.descrWeather);
        minTemp = (TextView) view.findViewById(R.id.tempMin);
        maxTemp = (TextView) view.findViewById(R.id.tempMax);
        unitTemp = (TextView) view.findViewById(R.id.tempUnit);
        temp = (TextView) view.findViewById(R.id.temp);
        hum = (TextView) view.findViewById(R.id.hum);
        press = (TextView) view.findViewById(R.id.press);
        windSpeed = (TextView) view.findViewById(R.id.windSpeed);
        windDeg = (TextView) view.findViewById(R.id.windDeg);
        visibility = (TextView) view.findViewById(R.id.visibility);
        sunrise = (TextView) view.findViewById(R.id.sunrise);
        sunset = (TextView) view.findViewById(R.id.sunset);
        imgView = (ImageView) view.findViewById(R.id.condIcon);
    }
}
