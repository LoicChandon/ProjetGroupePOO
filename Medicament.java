public class Medicament implements IEvoluant {
    private String nom;
    private float sensibilite;
    private float tauxDisparition;
    private float dosePrescrite;
    private float dosePresente;

    public Medicament(String nom, float sensibilté, float tauxDisparition, float dosePrescrite) {
        this.nom = nom;
        this.sensibilite = sensibilite;
        this.tauxDisparition = tauxDisparition;
        this.dosePrescrite = dosePrescrite;
        this.dosePresente = 0.0f;
    }

    public String getNom() {
        return nom;
    }

    public float getSensibilite() {
        return sensibilite;
    }

    public float getTauxDisparition() {
        return tauxDisparition;
    }

    public float getDoseAdministree() {
        return doseAdministree;
    }
    
    public float getDosePresente() {
        return dosePresente;
    }

    /**
     * Fait évoluer la concentration du médicament.
     * @param administrationEffectuee true si le patient prend son médicament maintenant.
     */
    public void evoluer(boolean administrationEffectuee) {
        float apport = administrationEffectuee ? dosePrescrite : 0.0f;
        this.dosePresente = (this.tauxDisparition * this.dosePresente) + apport;
    }

    // Surcharge obligatoire pour l'interface IEvoluant
    @Override
    public void evoluer() {
        evoluer(false);
    }   
}
