package empapp;

import empapp.gateway.GpsCoordinateMessage;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class GpsController {

    private List<SseEmitter> emitters = new ArrayList<>();

    @EventListener
    public void handleEvent(GpsCoordinateMessage message) {
        var toRemove = new ArrayList<SseEmitter>();
        for (var emitter: emitters) {
            try {
                emitter.send(
                        SseEmitter.event()
                        .name("gps")
                        .comment("GPS location has changed")
                        .id(UUID.randomUUID().toString())
                        .data(message)
                );
            }
            catch (IOException | IllegalStateException ioe) {
                toRemove.add(emitter);
            }
        }
        emitters.removeAll(toRemove);
    }

    @GetMapping("/api/gps")
    public SseEmitter gps() {
        var emitter = new SseEmitter();
        emitters.add(emitter);
        return emitter;
    }
}
