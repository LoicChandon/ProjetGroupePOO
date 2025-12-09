/**
 * Classe abstraite représentant la réponse immunitaire de base
 * Toutes les réponses immunitaires spécifiques en héritent
 */
public abstract class ReponseImmunitaire {
    protected float activite;
    protected float coefficientFatigue;
    protected float reactivite;
    
    /**
     * Constructeur de la réponse immunitaire
     * @param coefficientFatigue Le coefficient de fatigue f
     * @param reactivite La réactivité du système immunitaire β
     */
    public ReponseImmunitaire(float coefficientFatigue, float reactivite) {
        this.coefficientFatigue = coefficientFatigue;
        this.reactivite = reactivite;
        this.activite = 0.0f;
    }
    
    /**
     * Méthode abstraite pour faire évoluer la réponse immunitaire
     * @param charge La charge pathogène L_{t+1}
     */
    public abstract void evoluer(float charge);
    
    /**
     * Retourne l'activité immunitaire actuelle
     * @return L'activité immunitaire I_t
     */
    public float getActivite() {
        return activite;
    }
    
    /**
     * Modifie l'activité immunitaire
     * @param activite La nouvelle activité (doit être >= 0)
     */
    public void setActivite(float activite) {
        if (activite < 0) {
            this.activite = 0;
        } else {
            this.activite = activite;
        }
    }
    
    /**
     * Représentation textuelle de la réponse immunitaire
     * @return Une chaîne décrivant l'état
     */
    public String toString() {
        return String.format("Activité=%.2f, f=%.2f, β=%.2f", 
                           activite, coefficientFatigue, reactivite);
    }
}