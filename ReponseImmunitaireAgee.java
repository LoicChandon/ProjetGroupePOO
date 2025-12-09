/**
 * Réponse immunitaire pour les patients âgés
 * Implémente l'équation (6): I_{t+1} = max(0, I_t + β*L_{t+1} - f*I_t²)
 */
public class ReponseImmunitaireAgee extends ReponseImmunitaire {
    
    /**
     * Constructeur pour la réponse immunitaire âgée
     * @param coefficientFatigue Le coefficient de fatigue f
     * @param reactivite La réactivité β
     */
    public ReponseImmunitaireAgee(float coefficientFatigue, float reactivite) {
        super(coefficientFatigue, reactivite);
    }
    
    /**
     * Fait évoluer la réponse immunitaire selon l'équation (6)
     * @param charge La charge pathogène L_{t+1}
     */
    @Override
    public void evoluer(float charge) {
        // Équation (6): I_{t+1} = max(0, I_t + β*L_{t+1} - f*I_t²)
        float nouvelleActivite = activite + (reactivite * charge) 
                               - (coefficientFatigue * activite * activite);
        
        // max(0, ...)
        if (nouvelleActivite < 0) {
            activite = 0;
        } else {
            activite = nouvelleActivite;
        }
    }
}