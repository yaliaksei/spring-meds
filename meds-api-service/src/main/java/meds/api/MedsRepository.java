package meds.api;

import org.springframework.data.repository.CrudRepository;

public interface MedsRepository extends CrudRepository<Medication, Long> {
}
