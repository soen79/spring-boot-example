package springbootapp.delegate;

import springbootapp.domain.EarthQuake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by User on 1/1/2020.
 */
public class EarthQuakeDelegate {


    public Collection<EarthQuake> quakes() {

        Collection<EarthQuake> ret = new ArrayList<>();

        EarthQuake q1 = new EarthQuake();
        q1.setCity("Montreal");

        EarthQuake q2 = new EarthQuake();
        q2.setCity("Vienna");

        EarthQuake q3 = new EarthQuake();
        q3.setCity("Barcelona");

        ret.addAll(Arrays.asList(q1,q2,q3));

        //String[] sorted = ret.stream().sorted().toArray(String[]::new);
        //System.out.println(sorted[0] + " is before " + sorted[1]);

        Collection<EarthQuake> response = ret.stream().sorted().collect(Collectors.toCollection(ArrayList<EarthQuake>::new));
        return response;
    }
}
