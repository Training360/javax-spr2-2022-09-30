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
        var coord = random.nextDouble(-180, 180);

        var message = Double.toString(coord);
        log.info("Sending message: {}", message);

        jmsTemplate.convertAndSend("gpsQueue", message);
    }
}
