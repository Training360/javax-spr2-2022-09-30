package empapp.gateway;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jms.annotation.JmsListener;

@Gateway
@Slf4j
@AllArgsConstructor
public class GpsCoordinateListener {

    private ApplicationEventPublisher publisher;

    @JmsListener(destination = "gpsQueue")
    public void handleMessage(GpsCoordinateMessage message) {
      log.info("Message has arrived: {}", message);
      publisher.publishEvent(message);
    }
}
