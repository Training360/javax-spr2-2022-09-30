package empapp.gateway;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class GpsCoordinate {

    private double lat;

    private double lon;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public GpsCoordinate(@JsonProperty("lat") double lat, @JsonProperty("lon") double lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
