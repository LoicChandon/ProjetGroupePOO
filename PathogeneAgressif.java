import java.util.Map;

public class PathogeneAgressif extends Pathogene {
    
    public PathogeneAgressif(float chargePathogene, String nom, Map<String, Float> resistancesMedicament,
                           float sensibiliteSystemeImmunitaire, float sensibiliteMedicament,
                           float tauxReplication) {
        super(chargePathogene, nom, resistancesMedicament, sensibiliteSystemeImmunitaire,
              sensibiliteMedicament, tauxReplication);
    }
    
    @Override
    public void evoluer() {
        if (patient == null) return;
        
        float Lt = chargePathogene;
        float Tc = tauxReplication * Lt * Lt;
        
        float It = patient.getActiviteImmunitaire();
        float termeImmunitaire = sensibiliteSystemeImmunitaire * It;
        
        float sommeMedicaments = 0.0f;
        Map<Medicament, Float> doses = patient.getDosesMedicaments();
        
        for (Map.Entry<Medicament, Float> entry : doses.entrySet()) {
            Medicament med = entry.getKey();
            Float Dmt = entry.getValue();
            if (Dmt != null) {
                float Rm = resistancesMedicament.getOrDefault(med.getNom(), 0.0f);
                sommeMedicaments += sensibiliteMedicament * Dmt * (1 - Rm);
            }
        }
        
        float LtPlus1 = Lt + Tc - termeImmunitaire - sommeMedicaments;
        chargePathogene = Math.max(0.0f, LtPlus1);
    }
}