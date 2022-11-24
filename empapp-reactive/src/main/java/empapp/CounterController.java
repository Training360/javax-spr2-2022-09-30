package empapp;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/api/counter")
public class CounterController {

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> numbers() {
        return Flux.range(0, 10)
                .delayElements(Duration.ofSeconds(2))
                .map(i -> Integer.toString(i));
    }
}
