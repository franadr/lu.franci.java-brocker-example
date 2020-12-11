package lu.franci.brockerexample.embedded;

import com.google.gson.Gson;
import lu.franci.brockerexample.StackFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class JmsConsumer {

    Logger log = LoggerFactory.getLogger(JmsConsumer.class);
    @JmsListener(destination = "${activemq.queue.name}")
    public void receive(StackFrame stackframe) {

        log.info("Received message='{}' \n size of webpage = '{}'", stackframe.toString(),stackframe.getWebpage().get().length());
    }

}
