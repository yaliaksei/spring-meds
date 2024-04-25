package meds.api;

import org.springframework.data.annotation.Id;

public record Medication(@Id Long id, String name) {
}
