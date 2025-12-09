import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Simulation {
    private List<Patient> patients; 
    private int nbCycles;   
    private List<ResultatSimulation> resultats; 
    
    private int cycleActuel; 

    public Simulation(List<Patient> patients, int nbCycles) {
        this.patients = patients;
        this.nbCycles = nbCycles;
        this.resultats = new ArrayList<>();
        this.cycleActuel = 0;
    }

    public void executerSimulation() {
        this.resultats.clear(); 

        for (Patient patient : patients) {
            HistoriquePatient historique = new HistoriquePatient(patient.getIdPatient());
            this.cycleActuel = 0;
            
            for (int t = 1; t <= nbCycles; t++) {
                this.cycleActuel = t;
                executerCycle(patient, historique); 
                
                float chargeTotale = 0.0f;
                if (patient.getPathogenes() != null) {
                    for (Pathogene pathogene : patient.getPathogenes()) {
                        chargeTotale += pathogene.getChargePathogene();
                    }
                }
                if (chargeTotale < 0.001f) { 
                    StatutSimulation statut = StatutSimulation.SUCCES;
                    resultats.add(new ResultatSimulation(historique, cycleActuel, statut));
                    break;
                }
            }
            
            if (cycleActuel == nbCycles && resultats.stream().noneMatch(r -> r.getHistoriquePatient().getIdPatientActuel() == patient.getIdPatient())) {
                float chargeFinale = 0.0f;
                if (patient.getPathogenes() != null) {
                    for (Pathogene pathogene : patient.getPathogenes()) {
                        chargeFinale += pathogene.getChargePathogene();
                    }
                }
                
                StatutSimulation statut;
                if (chargeFinale < patient.getChargePathogeneInitialeTotale()) {
                    statut = StatutSimulation.PARTIELLEMENT_TRAITEE;
                } else {
                    statut = StatutSimulation.ECHEC;
                }
                resultats.add(new ResultatSimulation(historique, nbCycles, statut));
            }
        }
    }
    
    public void executerCycle(Patient patient, HistoriquePatient historique) {
        
        // --- 1. Évolution des médicaments (D_m,t+1, Équation 9) ---
        if (patient.getTraitement() != null) {
            for (Medicament med : patient.getTraitement()) {
                float dosePrise = 0.0f;
                if (cycleActuel % 3 == 0) { 
                    dosePrise = med.getDosePriseParCycle();
                }
                med.evoluer(dosePrise); // Met à jour D_m,t+1 dans l'objet Medicament
            }
        }
        
        // --- 2. Évolution des pathogènes (L_t+1, Équation 1, 2) ---
        if (patient.getPathogenes() != null) {
            for (Pathogene pathogene : patient.getPathogenes()) {
              
                pathogene.evoluer(); 
            }
        }

        // --- 3. Évolution de la réponse immunitaire (I_t+1, Équation 4, 5, 6) ---
        if (patient.getReponseImmunitaire() != null) {
            patient.getReponseImmunitaire().evoluer(patient.getPathogenes()); 
        }
        
        // --- 4. Sauvegarde de l'état (L_t+1, D_m,t+1, I_t+1) ---
        
        Map<String, Float> chargesActuelles = new HashMap<>();
        if (patient.getPathogenes() != null) {
             for (Pathogene pathogene : patient.getPathogenes()) {
                chargesActuelles.put(pathogene.getNom(), pathogene.getChargePathogene());
            }
        }

        Map<String, Float> concentrationsActuelles = new HashMap<>();
        if (patient.getTraitement() != null) {
            for (Medicament med : patient.getTraitement()) {
                concentrationsActuelles.put(med.getNom(), med.getConcentrationActuelle());
            }
        }
        
        float activiteImmunitaireActuelle = patient.getReponseImmunitaire() != null ? 
                                            patient.getReponseImmunitaire().getActivite() : 0.0f;
        
        historique.ajouterEtatCycle(
            cycleActuel, 
            chargesActuelles, 
            activiteImmunitaireActuelle, 
            concentrationsActuelles
        );
    }

    public List<ResultatSimulation> obtenirResultats() {
        return resultats;
    }
}