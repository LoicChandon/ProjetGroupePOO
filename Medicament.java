public class Medicament {
    private String nom;
    private float sensibilite;
    private float tauxDisparition;
    private float dosePrescrite;

    public Medicament(String nom, float sensibilté, float tauxDisparition, float dosePrescrite) {
        this.nom = nom;
        this.sensibilite = sensibilite;
        this.tauxDisparition = tauxDisparition;
        this.dosePrescrite = dosePrescrite;
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

    public float getDosePrescrite() {
        return dosePrescrite;
    }

    

}
