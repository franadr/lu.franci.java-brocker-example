package lu.franci.brockerexample.embedded;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class JmsConsumer {

    Logger log = LoggerFactory.getLogger(JmsConsumer.class);
    @JmsListener(destination = "${activemq.queue.name}")
    public void receive(String json) {
        Gson gson = new Gson();
        JmsProducer.StackFrame sf = gson.fromJson(json, JmsProducer.StackFrame.class);
        log.info("Received message='{}' \n size of webpage = '{}'", sf.toString(),sf.getWebpage().get().length());

    }

}
