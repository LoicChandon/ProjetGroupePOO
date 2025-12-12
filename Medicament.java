public class Medicament {
    private String nom;
    private float sensibilite;
    private float tauxDisparition;
    private float doseAdministree;

    public Medicament(String nom, float sensibilté, float tauxDisparition, float dosePrescrite) {
        this.nom = nom;
        this.sensibilite = sensibilite;
        this.tauxDisparition = tauxDisparition;
        this.doseAdministree = doseAdministree;
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
    
    public void evoluer(){
    }

    public void evoluer(float dosePrise){ //???
    }



}
