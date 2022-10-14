package empapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CounterService {

    @Async
    public void count() {
        for (int i = 0; i < 10; i++) {
            log.info("Counter: {}", i);

            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException ie) {
                log.error("Error by counting", ie);
            }

        }
    }

    @Scheduled(fixedRate = 5000)
    public void schedule() {
        log.info("Hello scheduling");
    }
}
