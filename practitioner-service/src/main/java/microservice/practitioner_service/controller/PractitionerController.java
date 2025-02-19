package microservice.practitioner_service.controller;

import io.swagger.annotations.*;
import microservice.practitioner_service.model.Practitioner;
import microservice.practitioner_service.service.PractitionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/practitioners")
@Api(value = "Practitioners", description = "Operations pertaining to practitioners in the system")
public class PractitionerController {

    @Autowired
    private PractitionerService practitionerService;

    @ApiOperation(value = "View a list of all practitioners", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping
    public List<Practitioner> getAllPractitioners() {
        return practitionerService.getAllPractitioners();
    }

    @ApiOperation(value = "Get a practitioner by ID", response = Practitioner.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved practitioner"),
            @ApiResponse(code = 404, message = "Practitioner not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Practitioner> getPractitionerById(
            @ApiParam(value = "ID of the practitioner to retrieve", required = true) @PathVariable Long id) {
        return practitionerService.getPractitionerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Add a new practitioner", response = Practitioner.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Practitioner successfully created"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping
    public Practitioner addPractitioner(
            @ApiParam(value = "Practitioner object to be added", required = true) @RequestBody Practitioner practitioner) {
        return practitionerService.addPractitioner(practitioner);
    }

    @ApiOperation(value = "Update an existing practitioner", response = Practitioner.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Practitioner successfully updated"),
            @ApiResponse(code = 404, message = "Practitioner not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Practitioner> updatePractitioner(
            @ApiParam(value = "ID of the practitioner to update", required = true) @PathVariable Long id,
            @ApiParam(value = "Updated practitioner object", required = true) @RequestBody Practitioner practitionerDetails) {
        return ResponseEntity.ok(practitionerService.updatePractitioner(id, practitionerDetails));
    }

    @ApiOperation(value = "Delete a practitioner by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Practitioner successfully deleted"),
            @ApiResponse(code = 404, message = "Practitioner not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePractitioner(
            @ApiParam(value = "ID of the practitioner to delete", required = true) @PathVariable Long id) {
        practitionerService.deletePractitioner(id);
        return ResponseEntity.noContent().build();
    }
}