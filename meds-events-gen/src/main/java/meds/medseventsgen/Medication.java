package meds.medseventsgen;

import lombok.Data;

import java.util.Date;

@Data
public class Medication {
    Long id;
    String name;
    String description;
    Date startDate;
    Date endDate;
    Boolean morning;
    Boolean midday;
    Boolean evening;
    Boolean bedtime;
}
