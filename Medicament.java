public class Medicament implements IEvoluant {
    private String nom;
    private float sensibilite;
    private float tauxDisparition;
    private float doseAdministree;
    private float dosePresente;

    public Medicament(String nom, float sensibilite, float tauxDisparition, float doseAdministree) {
        this.nom = nom;
        this.sensibilite = sensibilite;
        this.tauxDisparition = tauxDisparition;
        this.doseAdministree = doseAdministree;
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


    public void evoluer(float apport) {
        dosePresente = dosePresente * (1 - tauxDisparition) + apport;
    } 

    // Surcharge obligatoire pour l'interface IEvoluant
    @Override
    public void evoluer() {
        evoluer(0.0f);
    }   
}
