/**
 * Réponse immunitaire pour les patients adultes (standard)
 * Implémente l'équation (4): I_{t+1} = max(0, I_t + β*L_{t+1} - f*I_t)
 */
public class ReponseImmunitaireAdulte extends ReponseImmunitaire {
    
    /**
     * Constructeur pour la réponse immunitaire adulte
     * @param coefficientFatigue Le coefficient de fatigue f
     * @param reactivite La réactivité β
     */
    public ReponseImmunitaireAdulte(float coefficientFatigue, float reactivite) {
        super(coefficientFatigue, reactivite);
    }
    
    /**
     * Fait évoluer la réponse immunitaire selon l'équation (4)
     * @param charge La charge pathogène L_{t+1}
     */
    @Override
    public void evoluer(float charge) {
        // Équation (4): I_{t+1} = max(0, I_t + β*L_{t+1} - f*I_t)
        float nouvelleActivite = activite + (reactivite * charge) - (coefficientFatigue * activite);
        
        // max(0, ...)
        if (nouvelleActivite < 0) {
            activite = 0;
        } else {
            activite = nouvelleActivite;
        }
    }
}