package empapp.gateway;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Random;

@Gateway
@AllArgsConstructor
@Slf4j
public class GpsCoordinateSenderGateway {

    private JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 10_000)
    public void generateGpsCoordinate() {
        var random = new Random();

        var id = 100L;
        var lat = random.nextDouble(-90, 90);
        var lon = random.nextDouble(-180, 180);

        var message = new GpsCoordinateMessage(id, new GpsCoordinate(lat, lon));
        log.info("Sending message: {}", message);

        jmsTemplate.convertAndSend("gpsQueue", message);
    }
}
