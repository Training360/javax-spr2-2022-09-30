package empapp;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/counter")
@AllArgsConstructor
public class CounterController {

    private CounterService counterService;

    @GetMapping
    public String count() {
        counterService.count();
        return """
                {"status": "ok"}
            """;
    }
}
