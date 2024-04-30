package meds.medseventsgen;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MedsEventsProcessor {

    private MedsCalendarRepository calendarRepository;

    public MedsEventsProcessor(MedsCalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @RabbitListener(queues = "medications")
    public void handleMessage(Medication medication) {
        System.out.println(" [x] Received '" + medication.toString() + "'");
        CalendarEvent calendarEvent = new CalendarEvent(null,
                medication.getName(), medication.getDescription(), medication.getStartDate());

        calendarRepository.save(calendarEvent);
    }
}