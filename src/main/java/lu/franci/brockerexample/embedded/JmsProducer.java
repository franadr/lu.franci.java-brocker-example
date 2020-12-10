package lu.franci.brockerexample.embedded;


import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
        StackFrame sf = new StackFrame(2, Arrays.asList("1st trace","2nd trace","etc"));

        StackFrame sf2 = new StackFrame(456,new String(Files.readAllBytes(Path.of("test.html"))));
        Gson gson = new Gson();
        jmsTemplate.convertAndSend(destination, gson.toJson(sf2,StackFrame.class));
        log.info("Sent message='{}'",sf.toString());
    }

    public class StackFrame {

        private final int size;
        private List<String> stack;
        private String webpage;

        public StackFrame(int size, List<String> stack) {
            this.size = size;
            this.stack = stack;
        }

        public StackFrame(int size, String webpage) {
            this.size = size;
            this.webpage = webpage;
            log.info("Webpage size :'{}'",webpage.length());
        }

        public int getSize() {
            return size;
        }

        public List<String> getStack() {
            return stack;
        }

        public Optional<String> getWebpage() {
            return Optional.of(webpage);
        }
    }
}
