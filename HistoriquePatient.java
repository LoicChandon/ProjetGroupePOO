import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HistoriquePatient {

    private int idPatientActuel;
    private List<Integer> numeroCycles;
    private List<Map<String, Float>> historiqueChargesPathogenes; // L_t
    private List<Float> historiqueActiviteImmunitaire;            // I_t
    private List<Map<String, Float>> historiqueConcentrationsMedicaments; // D_m,t

    public HistoriquePatient(int idPatient) {
        this.idPatientActuel = idPatient;
        this.numeroCycles = new ArrayList<>();
        this.historiqueChargesPathogenes = new ArrayList<>();
        this.historiqueActiviteImmunitaire = new ArrayList<>();
        this.historiqueConcentrationsMedicaments = new ArrayList<>();
    }

    /**
     * Ajoute l'état du patient pour un cycle donné.
     */
    public void ajouterEtatCycle(int cycle, Map<String, Float> charges, float activite, Map<String, Float> concentrations) {
        this.numeroCycles.add(cycle);
        this.historiqueChargesPathogenes.add(charges);
        this.historiqueActiviteImmunitaire.add(activite);
        this.historiqueConcentrationsMedicaments.add(concentrations);
    }

    // Getters pour ResultatSimulation ou l'affichage
    public int getIdPatientActuel() {
        return idPatientActuel;
    }

    public List<Map<String, Float>> getHistoriqueChargesPathogenes() {
        return historiqueChargesPathogenes;
    }
    
    public List<Float> getHistoriqueActiviteImmunitaire() {
        return historiqueActiviteImmunitaire;
    }
    
    // ... autres getters si besoin ...

    public void afficherHistorique() {
        System.out.println("--- Historique pour le Patient ID: " + idPatientActuel + " ---");
        for (int i = 0; i < numeroCycles.size(); i++) {
            System.out.println("  [Cycle " + numeroCycles.get(i) + "]");
            System.out.println("    I: " + String.format("%.4f", historiqueActiviteImmunitaire.get(i)));
            
            Map<String, Float> charges = historiqueChargesPathogenes.get(i);
            for (Map.Entry<String, Float> entry : charges.entrySet()) {
                System.out.println("    L (" + entry.getKey() + "): " + String.format("%.4f", entry.getValue()));
            }

            Map<String, Float> concentrations = historiqueConcentrationsMedicaments.get(i);
            for (Map.Entry<String, Float> entry : concentrations.entrySet()) {
                System.out.println("    D (" + entry.getKey() + "): " + String.format("%.4f", entry.getValue()));
            }
        }
        System.out.println("----------------------------------------");
    }
}