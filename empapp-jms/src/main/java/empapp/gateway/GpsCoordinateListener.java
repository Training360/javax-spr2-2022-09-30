package empapp.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;

@Gateway
@Slf4j
public class GpsCoordinateListener {

    @JmsListener(destination = "gpsQueue")
    public void handleMessage(String message) {
      log.info("Message has arrived: {}", message);
    }
}
