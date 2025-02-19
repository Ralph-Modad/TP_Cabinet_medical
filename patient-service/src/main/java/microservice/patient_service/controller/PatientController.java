package microservice.patient_service.controller;

import io.swagger.annotations.*;
import microservice.patient_service.model.Patient;
import microservice.patient_service.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@Api(value = "Patient", description = "Operations pertaining to patients in the system")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @ApiOperation(value = "View a list of all patients", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @ApiOperation(value = "Get a patient by ID", response = Patient.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved patient"),
            @ApiResponse(code = 404, message = "Patient not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(
            @ApiParam(value = "ID of the patient to retrieve", required = true) @PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Add a new patient", response = Patient.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Patient successfully created"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping
    public Patient addPatient(
            @ApiParam(value = "Patient object to be added", required = true) @RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

    @ApiOperation(value = "Update an existing patient", response = Patient.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Patient successfully updated"),
            @ApiResponse(code = 404, message = "Patient not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(
            @ApiParam(value = "ID of the patient to update", required = true) @PathVariable Long id,
            @ApiParam(value = "Updated patient object", required = true) @RequestBody Patient patientDetails) {
        return ResponseEntity.ok(patientService.updatePatient(id, patientDetails));
    }

    @ApiOperation(value = "Delete a patient by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Patient successfully deleted"),
            @ApiResponse(code = 404, message = "Patient not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(
            @ApiParam(value = "ID of the patient to delete", required = true) @PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}