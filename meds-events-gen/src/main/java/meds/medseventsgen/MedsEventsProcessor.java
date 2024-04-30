package meds.medseventsgen;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MedsEventsProcessor {

    @RabbitListener(queues = "medications")
    public void handleMessage(Medication medication) {
        System.out.println(" [x] Received '" + medication.toString() + "'");
    }
}