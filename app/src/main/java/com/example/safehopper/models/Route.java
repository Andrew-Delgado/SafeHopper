package com.example.safehopper.models;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private String name;
    private String distance;    //in feet
    private String imageURL;
    private List<LatLng> routeWaypoints = new ArrayList<>();

    public Route(){
    }

    public Route(String newName, String newDistace,
                 String newImage, List<LatLng> waypoints){

        name = newName;
        distance = newDistace;
        imageURL = newImage;
        routeWaypoints = waypoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<LatLng> getRouteWaypoints(){
        return routeWaypoints;
    }

    public void addPoint(LatLng point){

        routeWaypoints.add(point);
        turnToJson();
    }

    public void removeLastPoint(){
        if(routeWaypoints.size() != 0)
            routeWaypoints.remove(routeWaypoints.size()-1);
    }

    public String turnToJson(){

        Gson routeObj = new Gson();
        String json = routeObj.toJson(new Route(name, distance, imageURL, routeWaypoints));
        Log.d("method: turnToJson-->Json of object",json);

        try {
            GsonBuilder gbuilder = new GsonBuilder();
            gbuilder.registerTypeAdapter(Route.class, new RouteDeserializer());

            Gson customGson = gbuilder.create();

            Route route = customGson.fromJson(json,Route.class);

            Log.d("JSON-ROUTE-STUFF-TURN_TO_JSON-PART-2", route.toString());

        }catch(Exception e){
            Log.e("error",e.toString());
        }
        return json;
    }

    @Override
    public String toString(){
        return "name: " + name + " distance: " + distance + " imageURL: "
                + imageURL + " routeWaypoints: " + routeWaypoints.toString();
    }
}