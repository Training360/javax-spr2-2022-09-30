package empapp.gateway;

import lombok.Data;
import lombok.Value;

@Value
public class GpsCoordinateMessage {

    private long vehicleId;

    private GpsCoordinate coordinates;
}
