package meds.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController("/meds")
public class MedsApiController {

    private final Log log = LogFactory.getLog(getClass());

    private final MedsRepository medsRepository;

    private MedsApiController(MedsRepository medsRepository) {
        this.medsRepository = medsRepository;
    }

    @GetMapping("/{medId}")
    private ResponseEntity<Medication> findMedById(@PathVariable Long medId) {
        Optional<Medication> medicationOptional = medsRepository.findById(medId);

        if(medicationOptional.isPresent()) {
            return ResponseEntity.ok(medicationOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    private

    @PostMapping
    private ResponseEntity<Void> createMed(@RequestBody Medication medication, UriComponentsBuilder ucb) {
        Medication newMedication = medsRepository.save(medication);

        log.info("New medication created: " + newMedication.id() + ":" + newMedication.name());

        URI location = ucb.path("/meds/{id}").buildAndExpand(newMedication.id()).toUri();
        return ResponseEntity.created(location).build();
    }
}
