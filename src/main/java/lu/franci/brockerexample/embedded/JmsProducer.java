package lu.franci.brockerexample.embedded;


import com.google.gson.Gson;
import lu.franci.brockerexample.StackFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class JmsProducer {

    Logger log = LoggerFactory.getLogger(JmsProducer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${activemq.queue.name}")
    String destination;

    public void send() throws IOException {
//        StackFrame sf = new StackFrame(2, Arrays.asList("1st trace","2nd trace","etc"));

        StackFrame sf2 = new StackFrame(456, new String(Files.readAllBytes(Path.of("test.html"))));
        Gson gson = new Gson();
        jmsTemplate.convertAndSend(destination, sf2);
        log.info("Sent message='{}'", sf2.toString());
    }

}
