package meds.medseventsgen;

import org.springframework.data.repository.CrudRepository;

public interface MedsCalendarRepository extends CrudRepository<CalendarEvent, Long> {
}
