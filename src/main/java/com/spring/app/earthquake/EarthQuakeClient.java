package com.spring.app.earthquake;

import springbootapp.util.FileReader;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class EarthQuakeClient {
    
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;              
    }
    
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {      
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }

    /**
     * Prints out the Big Quakes.
     * <p>Big quakes are determined by a threshold.
     * Anything over the threshold is considered a Big quake.
     * The actual 'heavy lifting' is done is a separate method that has the list and the threshold as params.
     */
    public void bigQuakes() {
	    EarthQuakeParser parser = new EarthQuakeParser();

        String source = getSource("data/nov20quakedata.atom");
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : listBig) {
           System.out.println(qe);
        }
    }

    /**
     * Important method to get the data of earthquake
     */
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();

        String source = getSource("data/nov20quakedata.atom");

        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }
    }

    private String getSource(String file)   {
        return FileReader.getSource(file);
    }

    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        
        //Durham, NC
        //Location city = new Location(35.988, -78.907);
        //Bridgeport, CA
        Location city = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }

    }

    public void findCloseQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = getSource("data/nov20quakedata.atom");

        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.printf("Read data of size: %", list.size());

        Location jakarta = new Location(-6.211, 106.845);
        System.out.println("Finding closest quakes to Jakarta");
        List<QuakeEntry> closeList = getClosestQuakes(list, jakarta, 10);

        for(int k=0; k < closeList.size() ; k++) {
            QuakeEntry entry = closeList.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000, entry);
        }
        System.out.printf("number found: %s" , closeList.size());

    }

    /**
     * We want to get the closest x numberOfQuakes from the location param.
     * <p>
     *  The location can be any city.
     *  We want the closest quakes to the city param.
     * </p>
     */
    private List<QuakeEntry> getClosestQuakes(ArrayList<QuakeEntry> quakeData, Location location, int numberOfQuakes) {

        List<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        int minIndex = 0;
        for(int k=1; k< quakeData.size(); k++){  // This finds the closest (shortest distance) to a given point from the list.
            QuakeEntry currentIterationQuake = quakeData.get(k);

            /* finding the closest currentIterationQuake to the location value */
            if( currentIterationQuake.getLocation().distanceTo(location) <
                    quakeData.get(minIndex).getLocation().distanceTo(location)) {
                    minIndex = k;
            }
        }

        ret.add(quakeData.get(minIndex));
        return ret;
    }
}
