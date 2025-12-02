
import java.util.ArrayList;
import java.util.HashMap;

public class Traitement {
    private String nom;
    private float sensibilite;
    private float tauxDisparition;
    private float doseActuelle;
    private ArrayList <DosePrescrite> prescriptions;
    private ArrayList <DosePrescrite> historique; 

    public Traitement(String nom, float sensibilite, float tauxDisparition) {
        this.nom = nom;
        this.sensibilite = sensibilite;
        this.tauxDisparition = tauxDisparition;
        this.doseActuelle = 0;
        this.prescriptions = new ArrayList<DosePrescrite>();
        this.historique = new ArrayList<DosePrescrite>();
    }

    public Traitement(String nom, float sensibilite, float tauxDisparition, float doseActuelle){
        this.nom = nom;
        this.sensibilite = sensibilite;
        this.tauxDisparition = tauxDisparition;
        this.doseActuelle = doseActuelle;
        this.prescriptions = new ArrayList<DosePrescrite>();
        this.historique = new ArrayList<DosePrescrite>();
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

    public float getDoseActuelle() {
        return doseActuelle;
    }

    public void evolutionTraitement(DosePrescrite dosePrescrite){
        this.doseActuelle = this.tauxDisparition * this.doseActuelle + dosePrescrite.getDose();
        this.prescriptions.add(dosePrescrite);
    }

    public ArrayList<DosePrescrite> getPrescriptions() {
        return prescriptions;
    }

    public ArrayList<DosePrescrite> getHistorique() {
        return historique;
    }

    
}
