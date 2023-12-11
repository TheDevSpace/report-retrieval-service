package kz.eub.reportretrievalservice.Controller;

import kz.eub.reportretrievalservice.Domain.Abis.CallbackResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallbackController {

    @PostMapping("/callback")
    public void handleCallback(@RequestBody CallbackResponse callbackResponse) {
        // Обработка callback-а
        System.out.println("Received callback: " + callbackResponse);
    }
}
