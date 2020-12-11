package lu.franci.brockerexample.embedded;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MyRestController {

    @Autowired
    JmsProducer jmsProducer;

    @GetMapping("/send")
    public void sendDataToJms() throws IOException {
        jmsProducer.send();
    }
}
