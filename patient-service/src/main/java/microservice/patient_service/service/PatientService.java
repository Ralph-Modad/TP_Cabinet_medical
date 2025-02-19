package microservice.patient_service.service;

import microservice.patient_service.model.Patient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PatientService {

    private final List<Patient> patients = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public PatientService() {
        patients.add(new Patient(idCounter.getAndIncrement(), "Alice", "Smith", "alice.smith@example.com", "1234567890"));
        patients.add(new Patient(idCounter.getAndIncrement(), "Bob", "Johnson", "bob.johnson@example.com", "0987654321"));
        patients.add(new Patient(idCounter.getAndIncrement(), "Charlie", "Brown", "charlie.brown@example.com", "1122334455"));
    }

    public List<Patient> getAllPatients() {
        return patients;
    }

    public Optional<Patient> getPatientById(Long id) {
        return patients.stream().filter(patient -> patient.getId().equals(id)).findFirst();
    }

    public Patient addPatient(Patient patient) {
        patient.setId(idCounter.getAndIncrement());
        patients.add(patient);
        return patient;
    }

    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient patient = getPatientById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        patient.setFirstName(patientDetails.getFirstName());
        patient.setLastName(patientDetails.getLastName());
        patient.setEmail(patientDetails.getEmail());
        patient.setPhone(patientDetails.getPhone());
        return patient;
    }

    public void deletePatient(Long id) {
        patients.removeIf(patient -> patient.getId().equals(id));
    }
}