package microservice.practitioner_service.service;
import microservice.practitioner_service.model.Practitioner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PractitionerService {

    private final List<Practitioner> practitioners = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1); // Pour générer des IDs uniques

    // Constructeur pour initialiser quelques praticiens
    public PractitionerService() {
        practitioners.add(new Practitioner(idCounter.getAndIncrement(), "John", "Doe", "Cardiologist", "john.doe@example.com", "1234567890"));
        practitioners.add(new Practitioner(idCounter.getAndIncrement(), "Jane", "Smith", "Dermatologist", "jane.smith@example.com", "0987654321"));
        practitioners.add(new Practitioner(idCounter.getAndIncrement(), "Emily", "Johnson", "Pediatrician", "emily.johnson@example.com", "1122334455"));
    }

    // Récupérer tous les praticiens
    public List<Practitioner> getAllPractitioners() {
        return practitioners;
    }

    // Récupérer un praticien par ID
    public Optional<Practitioner> getPractitionerById(Long id) {
        return practitioners.stream().filter(practitioner -> practitioner.getId().equals(id)).findFirst();
    }

    // Ajouter un nouveau praticien
    public Practitioner addPractitioner(Practitioner practitioner) {
        practitioner.setId(idCounter.getAndIncrement()); // Générer un ID unique
        practitioners.add(practitioner);
        return practitioner;
    }

    // Mettre à jour un praticien existant
    public Practitioner updatePractitioner(Long id, Practitioner practitionerDetails) {
        Practitioner practitioner = getPractitionerById(id).orElseThrow(() -> new RuntimeException("Practitioner not found"));
        practitioner.setFirstName(practitionerDetails.getFirstName());
        practitioner.setLastName(practitionerDetails.getLastName());
        practitioner.setSpecialty(practitionerDetails.getSpecialty());
        practitioner.setEmail(practitionerDetails.getEmail());
        practitioner.setPhone(practitionerDetails.getPhone());
        return practitioner;
    }

    // Supprimer un praticien
    public void deletePractitioner(Long id) {
        practitioners.removeIf(practitioner -> practitioner.getId().equals(id));
    }
}