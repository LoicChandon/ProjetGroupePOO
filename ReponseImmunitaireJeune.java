
public class ReponseImmunitaireJeune extends ReponseImmunitaire {

    public ReponseImmunitaireJeune(float coefficientFatigue, float reactivite) {
        super(coefficientFatigue, reactivite);
    }

    @Override
    public void evoluer(float charge) {
        // Équation (5): I_{t+1} = max(0, I_t + β*√(L_{t+1}) - f*I_t)
        float sqrtCharge;
        if (charge >= 0) {
            sqrtCharge = (float) Math.sqrt(charge);
        } else {
            sqrtCharge = 0; // Si charge négative, on prend 0
        }
        
        float nouvelleActivite = activite + (reactivite * sqrtCharge) - (coefficientFatigue * activite);
        
        // max(0, ...)
        if (nouvelleActivite < 0) {
            activite = 0;
        } else {
            activite = nouvelleActivite;
        }
    }
}