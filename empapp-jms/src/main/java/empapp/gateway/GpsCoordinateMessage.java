package empapp.gateway;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class GpsCoordinateMessage {

    private long vehicleId;

    private GpsCoordinate coordinates;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public GpsCoordinateMessage(@JsonProperty("vehicleId") long vehicleId, @JsonProperty("coordinates") GpsCoordinate coordinates) {
        this.vehicleId = vehicleId;
        this.coordinates = coordinates;
    }
}
