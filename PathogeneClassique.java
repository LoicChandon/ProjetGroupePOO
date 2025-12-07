
import java.util.Map;


public class PathogeneClassique extends Pathogene {

    protected Patient patient;

    public PathogeneClassique(float chargePathogene, String nom, Map<String, Float> resistancesMedicament, float sensibiliteSystemeImmunitaire, float sensibliteMedicament, float tauxReplication) {
        super(chargePathogene, nom, resistancesMedicament, sensibiliteSystemeImmunitaire, sensibliteMedicament, tauxReplication);
    }

    @Override
    public void evoluer() {
        // Formule : Lt+1 = max(0,Lt +τcLt −αiIt − Somme(m∈M) Avec : αmDm,t(1 −Rm)) voir pdf
        
        float Lt = chargePathogene;
        float terme_replication = tauxReplication * Lt;
        
        // Activité du système immunitaire It
        float It = 0.0f;
        if (patient != null) {
            It = patient.getReponseImmunitaire().getActivite(); // quand getActivite() existe dans reponse immu
        }
        float terme_immunitaire = sensibiliteSystemeImmunitaire * It;
        
        // Somme des termes de medicaments : "Somme(m∈M) Avec : αmDm,t(1 −Rm)"
        float somme_medicaments = 0.0f;
        if (patient != null && patient.getTraitement() != null) {
            for (Medicament med : patient.getTraitement()) {
                float Dmt = med.getDosePrescrite(); 
                float Rm = 0.0f;
                if (resistancesMedicament != null && resistancesMedicament.containsKey(med.getNom())) {
                    Rm = resistancesMedicament.get(med.getNom());
                }
                somme_medicaments = somme_medicaments + sensibiliteMedicament * Dmt * (1 - Rm);
            }
        }
        
        float LtPlus1 = Lt + terme_replication - terme_immunitaire - somme_medicaments;
        chargePathogene = Math.max(0.0f, LtPlus1); // la charge est >= 0
    }

}
