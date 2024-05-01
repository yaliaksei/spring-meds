package meds.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class EventSender {

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    public void messagePrinter() {
        System.out.println("Print another line..");
        /*
         TODO scan calendar_events table every 5 minutes and send notifications
         for those events that are due and 'notification_flag' is false. Update
         'notification_flag' to true once notification send out to avoid multiple
         sends.
         */
    }
}
