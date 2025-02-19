package microservice.practitioner_service.controller;

import io.swagger.annotations.Api;
import microservice.practitioner_service.model.Practitioner;
import microservice.practitioner_service.service.PractitionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/practitioners")
@Api(value = "Practitionners", description = "Operations pertaining to practitionners in the system")
public class PractitionerController {

    @Autowired
    private PractitionerService practitionerService;

    @GetMapping
    public List<Practitioner> getAllPractitioners() {
        return practitionerService.getAllPractitioners();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Practitioner> getPractitionerById(@PathVariable Long id) {
        return practitionerService.getPractitionerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Practitioner addPractitioner(@RequestBody Practitioner practitioner) {
        return practitionerService.addPractitioner(practitioner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Practitioner> updatePractitioner(@PathVariable Long id, @RequestBody Practitioner practitionerDetails) {
        return ResponseEntity.ok(practitionerService.updatePractitioner(id, practitionerDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePractitioner(@PathVariable Long id) {
        practitionerService.deletePractitioner(id);
        return ResponseEntity.noContent().build();
    }
}