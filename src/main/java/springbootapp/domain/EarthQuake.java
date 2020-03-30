package springbootapp.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by User on 12/29/2019.
 */

@Accessors(fluent = true, chain = false)
//@Data
//@NoArgsConstructor
@Getter
@Setter
public class EarthQuake implements Comparable<EarthQuake>{
    private String city;
    private long magnitude;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(long magnitude) {
        this.magnitude = magnitude;
    }


    /**
     * When Comparing with Strings, one assumption you can make is to compare only by uppercase, so you don't
     * worry about if upper is more value than lower.
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(EarthQuake o) {
        return this.city.toUpperCase().compareTo(o.getCity().toUpperCase());
    }
}
