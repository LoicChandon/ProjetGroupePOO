
public abstract class ReponseImmunitaire {
    protected float activite;
    protected float coefficientFatigue;
    protected float reactivite;
    

    public ReponseImmunitaire(float coefficientFatigue, float reactivite) {
        this.coefficientFatigue = coefficientFatigue;
        this.reactivite = reactivite;
        this.activite = 0.0f;
    }

    public abstract void evoluer(float charge);
    
    public float getActivite() {
        return activite;
    }
    

    public void setActivite(float activite) {
        if (activite < 0) {
            this.activite = 0;
        } else {
            this.activite = activite;
        }
    }
@Override
    public String toString() {
        return String.format("Activité=%.2f, f=%.2f, β=%.2f", 
                           activite, coefficientFatigue, reactivite);
    }
}