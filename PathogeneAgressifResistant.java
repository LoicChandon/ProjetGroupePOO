import java.util.HashMap;
import java.util.Map;

public class PathogeneAgressifResistant extends PathogeneAgressif implements IResistant {
    private Map<String, Float> tauxImpactMedicament;
    
    public PathogeneAgressifResistant(float chargePathogene, String nom, Map<String, Float> resistancesMedicament,
                                    float sensibiliteSystemeImmunitaire, float sensibiliteMedicament,
                                    float tauxReplication) {
        super(chargePathogene, nom, resistancesMedicament, sensibiliteSystemeImmunitaire,
              sensibiliteMedicament, tauxReplication);
        this.tauxImpactMedicament = new HashMap<>();
    }
    
    public void setTauxImpactMedicament(String nomMedicament, float taux) {
        tauxImpactMedicament.put(nomMedicament, taux);
    }
    
    @Override
    public float calculResistanceDynamique(String nomMedicament, float dosePresente, float tauxImpact) {
        float resistanceBase = resistancesMedicament.getOrDefault(nomMedicament, 0.0f);
        float taux = tauxImpactMedicament.getOrDefault(nomMedicament, tauxImpact);
        return Math.min(1.0f, resistanceBase + taux * dosePresente);
    }
    
    @Override
    public void evoluer() {
        // Mise à jour des résistances avant le calcul de la nouvelle charge
        if (patient != null) {
            Map<Medicament, Float> doses = patient.getDosesMedicaments();
            for (Map.Entry<Medicament, Float> entry : doses.entrySet()) {
                Medicament med = entry.getKey();
                Float Dmt = entry.getValue();
                if (Dmt != null && tauxImpactMedicament.containsKey(med.getNom())) {
                    float taux = tauxImpactMedicament.get(med.getNom());
                    float resistanceActuelle = resistancesMedicament.getOrDefault(med.getNom(), 0.0f);
                    float nouvelleResistance = resistanceActuelle + taux * Dmt;
                    resistancesMedicament.put(med.getNom(), Math.min(1.0f, nouvelleResistance));
                }
            }
        }
        
        super.evoluer(); // Calcul de la nouvelle charge avec les résistances mises à jour
    }
}