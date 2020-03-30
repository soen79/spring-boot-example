package springbootapp.domain;

public class EarthQuakeDTO {

    private String quakeCity;
    private long quakeMagnitude;

    public String getQuakeCity() {
        return quakeCity;
    }

    public void setQuakeCity(String quakeCity) {
        this.quakeCity = quakeCity;
    }

    public long getQuakeMagnitude() {
        return quakeMagnitude;
    }

    public void setQuakeMagnitude(long quakeMagnitude) {
        this.quakeMagnitude = quakeMagnitude;
    }
}
