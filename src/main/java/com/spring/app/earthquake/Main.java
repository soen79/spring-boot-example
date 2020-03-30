package com.spring.app.earthquake;

import java.net.URISyntaxException;

/**
 * Created by User on 12/15/2019.
 */
public class Main {

    public static void main(String[] args) throws URISyntaxException {
        EarthQuakeClient client = new EarthQuakeClient();
        //client.createCSV();
        client.bigQuakes();
        client.findCloseQuakes();
    }
}
