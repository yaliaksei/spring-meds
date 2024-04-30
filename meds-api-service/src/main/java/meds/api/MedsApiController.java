package meds.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController("/meds")
public class MedsApiController {

    private final Log log = LogFactory.getLog(getClass());

    private final MedsRepository medsRepository;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue messageQueue;

    private MedsApiController(MedsRepository medsRepository, RabbitTemplate rabbitTemplate) {
        this.medsRepository = medsRepository;
        this.rabbitTemplate = rabbitTemplate;
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
    private ResponseEntity<Iterable<Medication>> findAllMeds() {
        return ResponseEntity.ok(medsRepository.findAll());
    }

    @PostMapping
    private ResponseEntity<Void> createMed(@RequestBody Medication medication, UriComponentsBuilder ucb) {
        Medication newMedication = medsRepository.save(medication);

        log.info("New medication created: " + newMedication.getId() + ":" + newMedication.getName());

        // send event before return result
        log.info("Send message to queue " + messageQueue.getName());
        rabbitTemplate.convertAndSend(messageQueue.getName(), newMedication);

        URI location = ucb.path("/meds/{id}").buildAndExpand(newMedication.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{medId}")
    private ResponseEntity<Void> putMedication(@PathVariable Long medId, @RequestBody Medication medication) {
        Optional<Medication> existingMedication = medsRepository.findById(medId);
        if(existingMedication.isPresent()) {
            Medication updatedMedication = new Medication(medId,
                    medication.getName(), medication.getDescription(),
                    medication.getStartDate(), medication.getEndDate(),
                    medication.getMorning(), medication.getMidday(),
                    medication.getEvening(), medication.getBedtime()
                    );
            medsRepository.save(updatedMedication);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{medId}")
    private ResponseEntity<Void> deleteMed(@PathVariable Long medId) {
        medsRepository.deleteById(medId);
        return ResponseEntity.noContent().build();
    }
}
