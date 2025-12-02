public class PathogeneAgressif extends Pathogene {
    @Override
    public void evoluer() {
        // Application de la formule :
        // L_{t+1} = max(0, L_t + tau_c * L_t - alpha_i * I_t - sum_m alpha_m * D_m,t * (1 - R_m))
        float L = chargePathogene;
        float Lt1 = L + tauxReplication * L - sensibiliteSystemeImmunitaire * activiteSystemeImmunitaireCourante;

        if (dosesMedicament != null && !dosesMedicament.isEmpty()) {
            for (java.util.Map.Entry<String, Float> entry : dosesMedicament.entrySet()) {
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
}