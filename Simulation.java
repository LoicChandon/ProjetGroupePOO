import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulation {
    private List<Patient> patients;
    private int nbCycles;
    private List<ResultatSimulation> resultats;
    private int cycleActuel;
    
    public Simulation(List<Patient> patients, int nbCycles) {
        this.patients = new ArrayList<>(patients);
        this.nbCycles = nbCycles;
        this.resultats = new ArrayList<>();
        this.cycleActuel = 0;
    }
    
    public void executerSimulation() {
        resultats.clear();
        
        for (Patient patient : patients) {
            HistoriquePatient historique = new HistoriquePatient(patient.getId());
            boolean succes = false;
            
            for (int t = 1; t <= nbCycles; t++) {
                cycleActuel = t;
                executerCycle(patient, historique);
                
                if (patient.calculerChargeTotale() < 0.001f) {
                    resultats.add(new ResultatSimulation(historique, cycleActuel, StatutSimulation.SUCCES));
                    succes = true;
                    break;
                }
            }
            
            if (!succes) {
                float chargeInitiale = !patient.getHistoriqueChargePathogene().isEmpty() ? 
                                     patient.getHistoriqueChargePathogene().get(0) : 0;
                float chargeFinale = patient.calculerChargeTotale();
                StatutSimulation statut = (chargeFinale < chargeInitiale) ? 
                                        StatutSimulation.PARTIELLEMENT_TRAITEE : StatutSimulation.ECHEC;
                resultats.add(new ResultatSimulation(historique, nbCycles, statut));
            }
        }
    }
    
    private void executerCycle(Patient patient, HistoriquePatient historique) {
        patient.executerCycle();
        
        Map<String, Float> chargesActuelles = new HashMap<>();
        for (Pathogene p : patient.getPathogenes()) {
            chargesActuelles.put(p.getNom(), p.getChargePathogene());
        }
        
        Map<String, Float> concentrationsActuelles = new HashMap<>();
        for (Medicament m : patient.getTraitement()) {
            concentrationsActuelles.put(m.getNom(), patient.getDosesMedicaments().getOrDefault(m, 0.0f));
        }
        
        historique.ajouterEtatCycle(
            cycleActuel,
            chargesActuelles,
            patient.getActiviteImmunitaire(),
            concentrationsActuelles
        );
    }
    
    public List<ResultatSimulation> obtenirResultats() {
        return new ArrayList<>(resultats);
    }
}