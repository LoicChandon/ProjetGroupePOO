import java.util.Map;

public class Pathogene implements IEvoluant {

    protected String nom;
    protected float chargePathogene;
    protected float tauxReplication;
    protected float sensibiliteSystemeImmunitaire;
    protected float sensibliteMedicament;
    protected Map<String, Float> resistancesMedicament;
    protected Etat etatActuel;

    /*
     * Valeurs d'état courantes à fournir par l'extérieur avant d'appeler
     * `evoluer()` : activité du système immunitaire et doses administrées.
     */

    @Override
    public void evoluer() {
        // Application directe de la formule :
        // L_{t+1} = max(0, L_t + tau_c * L_t - alpha_i * I_t - sum_m alpha_m * D_m,t * (1 - R_m))

        float activiteSystemeImmunitaireCourante = etatActuel.getActiviteSystemeImmunitaireCourante();
        Map<String, Float> dosesMedicament = etatActuel.getDosesMedicament();

        float L = chargePathogene;
        float Lt1 = L + tauxReplication * L - sensibiliteSystemeImmunitaire * activiteSystemeImmunitaireCourante;

        if (dosesMedicament != null && !dosesMedicament.isEmpty()) {
            for (Map.Entry<String, Float> entry : dosesMedicament.entrySet()) {
                String med = entry.getKey();
                float D = entry.getValue() != null ? entry.getValue() : 0f;
                float R = 0f;
                if (resistancesMedicament != null && resistancesMedicament.get(med) != null) {
                    R = resistancesMedicament.get(med);
                }
                Lt1 -= sensibliteMedicament * D * (1 - R);
            }
        }

        chargePathogene = Math.max(0f, Lt1);
    }

    public float getChargePathogene() {
        return chargePathogene;
    }

    public void setChargePathogene(float charge) {
        this.chargePathogene = charge;
    }

}
