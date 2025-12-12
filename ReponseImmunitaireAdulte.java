
public class ReponseImmunitaireAdulte extends ReponseImmunitaire {

    public ReponseImmunitaireAdulte(float coefficientFatigue, float reactivite) {
        super(coefficientFatigue, reactivite);
    }

    @Override
    public void evoluer(float charge) {
        // Équation (4): I_{t+1} = max(0, I_t + β*L_{t+1} - f*I_t)
        float nouvelleActivite = activite + (reactivite * charge) - (coefficientFatigue * activite);
        
        if (nouvelleActivite < 0) {
            activite = 0;
        } else {
            activite = nouvelleActivite;
        }
    }
}