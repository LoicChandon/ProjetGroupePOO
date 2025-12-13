import java.util.HashMap;
import java.util.Map;

public abstract class Pathogene implements IEvoluant {
    protected String nom;
    protected float chargePathogene;
    protected float tauxReplication;
    protected float sensibiliteSystemeImmunitaire;
    protected float sensibiliteMedicament;
    protected Map<String, Float> resistancesMedicament;
    protected Patient patient;

    public Pathogene(float chargePathogene, String nom, Map<String, Float> resistancesMedicament, 
                    float sensibiliteSystemeImmunitaire, float sensibiliteMedicament, 
                    float tauxReplication) {
        this.chargePathogene = chargePathogene;
        this.nom = nom;
        this.resistancesMedicament = resistancesMedicament != null ? new HashMap<>(resistancesMedicament) : new HashMap<>();
        this.sensibiliteSystemeImmunitaire = sensibiliteSystemeImmunitaire;
        this.sensibiliteMedicament = sensibiliteMedicament;
        this.tauxReplication = tauxReplication;
        this.patient = null;
    }
    
    @Override
    public abstract void evoluer();

    public String getNom() {
        return nom;
    }
    
    public float getChargePathogene() {
        return chargePathogene;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public void setChargePathogene(float charge) {
        this.chargePathogene = Math.max(0, charge);
    }
    
    public float getSensibiliteSystemeImmunitaire() {
        return sensibiliteSystemeImmunitaire;
    }
    
    public void setSensibiliteSystemeImmunitaire(float sensibilite) {
        this.sensibiliteSystemeImmunitaire = sensibilite;
    }
    
    @Override
    public void setReactivite(float reactivite) {
        this.sensibiliteSystemeImmunitaire = reactivite;
    }
    
    public Map<String, Float> getResistancesMedicament() {
        return new HashMap<>(resistancesMedicament);
    }
    
    public void setResistanceMedicament(String medicament, float resistance) {
        resistancesMedicament.put(medicament, Math.min(1.0f, Math.max(0.0f, resistance)));
    }
}