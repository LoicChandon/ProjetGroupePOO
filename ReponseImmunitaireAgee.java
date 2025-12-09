
public class ReponseImmunitaireAgee extends ReponseImmunitaire {

    public ReponseImmunitaireAgee(float coefficientFatigue, float reactivite) {
        super(coefficientFatigue, reactivite);
    }
    

    @Override
    public void evoluer(float charge) {
        float nouvelleActivite = activite + (reactivite * charge) 
                               - (coefficientFatigue * activite * activite);
        
        if (nouvelleActivite < 0) {
            activite = 0;
        } else {
            activite = nouvelleActivite;
        }
    }
}