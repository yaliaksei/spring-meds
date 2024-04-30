package meds.medseventsgen;

import org.springframework.data.annotation.Id;

import java.util.Date;

public record Medication(@Id Long id,
                         String name,
                         String description,
                         Date startDate,
                         Date endDate,
                         Boolean morning,
                         Boolean midday,
                         Boolean evening,
                         Boolean bedtime) {
}
