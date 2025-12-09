/**
 * Réponse immunitaire pour les patients jeunes
 * Implémente l'équation (5): I_{t+1} = max(0, I_t + β*√(L_{t+1}) - f*I_t)
 */
public class ReponseImmunitaireJeune extends ReponseImmunitaire {
    
    /**
     * Constructeur pour la réponse immunitaire jeune
     * @param coefficientFatigue Le coefficient de fatigue f
     * @param reactivite La réactivité β
     */
    public ReponseImmunitaireJeune(float coefficientFatigue, float reactivite) {
        super(coefficientFatigue, reactivite);
    }
    
    /**
     * Fait évoluer la réponse immunitaire selon l'équation (5)
     * @param charge La charge pathogène L_{t+1}
     */
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