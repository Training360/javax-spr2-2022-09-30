package empapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class WebSocketController {

    @MessageMapping("/messagesDestination")
    @SendTo("/broker/employeesTopic")
    public ResponseMessage sendMessage(RequestMessage request) {
        log.info("Message has come on websocket: {}", request);
        return new ResponseMessage("Reply: " + request.getText().toUpperCase());
    }
}
