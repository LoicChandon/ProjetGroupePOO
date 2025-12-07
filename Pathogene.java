import java.util.Map;

public abstract class Pathogene implements IEvoluant {

    protected String nom;
    protected float chargePathogene;
    protected float tauxReplication;
    protected float sensibiliteSystemeImmunitaire;
    protected float sensibiliteMedicament;
    protected Map<String, Float> resistancesMedicament;
    protected Patient patient;


    public Pathogene(float chargePathogene, String nom, Map<String, Float> resistancesMedicament, float sensibiliteSystemeImmunitaire, float sensibiliteMedicament, float tauxReplication, Patient patient) {
        this.chargePathogene = chargePathogene;
        this.nom = nom;
        this.resistancesMedicament = resistancesMedicament;
        this.sensibiliteSystemeImmunitaire = sensibiliteSystemeImmunitaire;
        this.sensibiliteMedicament = sensibiliteMedicament;
        this.tauxReplication = tauxReplication;
        this.patient = patient;
    }
    @Override
    public abstract void evoluer();

}
