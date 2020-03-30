package springbootapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springbootapp.domain.EarthQuake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by User on 12/29/2019.
 */
@RestController
public class EarthQuakeController {


    @GetMapping("/quakes")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<EarthQuake> quakes() {

        Collection<EarthQuake> ret = new ArrayList<>();

        EarthQuake q1 = new EarthQuake();
        q1.setCity("Montreal");


        Random random = new Random();
        System.out.println(random.nextLong());
        q1.setMagnitude(random.nextLong());

        EarthQuake q2 = new EarthQuake();
        q2.setCity("Vienna");
        q2.setMagnitude(random.nextLong());

        EarthQuake q3 = new EarthQuake();
        q3.setCity("Barcelona");
        q3.setMagnitude(random.nextLong());

        //-----------------------------------------------------------



        //-----------------------------------------------------------


        ret.addAll(Arrays.asList(q1,q2,q3));

        Collection<EarthQuake> response = ret.stream()
                .sorted()
                .collect(Collectors.toCollection(ArrayList<EarthQuake>::new));
        return response;
    }


}
