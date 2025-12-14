import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Patient implements IEvoluant {
    protected int id;
    protected String nom;
    protected int age;
    protected float activiteImmunitaire;
    protected float coefficientFatigue;
    protected float reactivite;
    protected List<Pathogene> pathogenes;
    protected List<Medicament> traitement;
    protected Map<Medicament, Float> dosesMedicaments;
    protected List<Float> historiqueActiviteImmunitaire;
    protected List<Float> historiqueChargePathogene;
    
    public Patient(int id, String nom, int age, float coefficientFatigue, float reactivite) {
        this.id = id;
        this.nom = nom;
        this.age = age;
        this.coefficientFatigue = coefficientFatigue;
        this.reactivite = reactivite;
        this.activiteImmunitaire = 0.0f;
        this.pathogenes = new ArrayList<>();
        this.traitement = new ArrayList<>();
        this.dosesMedicaments = new HashMap<>();
        this.historiqueActiviteImmunitaire = new ArrayList<>();
        this.historiqueChargePathogene = new ArrayList<>();
    }
    
    @Override
    public void evoluer() {
        executerCycle();
    }
    
    public void executerCycle() {
        // Mise à jour des concentrations médicamenteuses
        for (Medicament med : traitement) {
            med.evoluer(med.getDoseAdministree()); // Évolution sans nouvelle dose
            dosesMedicaments.put(med, med.getDosePresente());
        }
        
        // Évolution des pathogènes
        for (Pathogene pathogene : pathogenes) {
            pathogene.evoluer();
        }
        
        // Calcul de la charge totale et mise à jour de l'immunité
        float chargeTotale = calculerChargeTotale();
        mettreAJourImmunite(chargeTotale);
        
        // Historique
        historiqueActiviteImmunitaire.add(activiteImmunitaire);
        historiqueChargePathogene.add(chargeTotale);
    }
    
    protected abstract void mettreAJourImmunite(float chargeTotale);
    
    
    public float calculerChargeTotale() {
        float total = 0.0f;
        for (Pathogene p : pathogenes) {
            total += p.getChargePathogene();
        }
        return total;
    }
    
    public int getId() { return id; }
    public String getNom() { return nom; }
    public int getAge() { return age; }
    public float getActiviteImmunitaire() { return activiteImmunitaire; }
    public float getCoefficientFatigue() { return coefficientFatigue; }
    public float getReactivite() { return reactivite; }
    public List<Pathogene> getPathogenes() { return new ArrayList<>(pathogenes); }
    public List<Medicament> getTraitement() { return new ArrayList<>(traitement); }
    public Map<Medicament, Float> getDosesMedicaments() { return new HashMap<>(dosesMedicaments); }
    public List<Float> getHistoriqueActiviteImmunitaire() { return new ArrayList<>(historiqueActiviteImmunitaire); }
    public List<Float> getHistoriqueChargePathogene() { return new ArrayList<>(historiqueChargePathogene); }
    
    public void setActiviteImmunitaire(float activite) {
        this.activiteImmunitaire = Math.max(0, activite);
    }
    
    public void setReactivite(float reactivite) {
        this.reactivite = reactivite;
    }
    
    public void ajouterPathogene(Pathogene pathogene) {
        pathogene.setPatient(this);
        pathogenes.add(pathogene);
    }
    
    public void ajouterMedicament(Medicament medicament) {
        if (!traitement.contains(medicament)) {
            traitement.add(medicament);
            dosesMedicaments.put(medicament, medicament.getDosePresente());
        }
    }
    
    public void administrerMedicament(Medicament medicament, float dose) {
        if (traitement.contains(medicament)) {
            medicament.evoluer(dose);
            dosesMedicaments.put(medicament, medicament.getDosePresente());
        }
    }
    
    public void afficherEtat(int cycle) {
        System.out.println("=== Patient: " + nom + " (ID: " + id + ", Âge: " + age + ") ===");
        System.out.println("Cycle: " + cycle);
        System.out.println("Activité immunitaire (I): " + String.format("%.4f", activiteImmunitaire));
        System.out.println("Charge pathogène totale (L): " + String.format("%.4f", calculerChargeTotale()));
        
        System.out.println("Pathogènes (" + pathogenes.size() + "):");
        for (Pathogene p : pathogenes) {
            System.out.println("  - " + p.getNom() + ": " + String.format("%.4f", p.getChargePathogene()));
        }
        
        System.out.println("Médicaments (" + traitement.size() + "):");
        for (Medicament m : traitement) {
            System.out.println("  - " + m.getNom() + " (D): " + String.format("%.4f", dosesMedicaments.getOrDefault(m, 0.0f)));
        }
        System.out.println();
    }
    
    public void reinitialiser() {
        activiteImmunitaire = 0;
        for (Medicament med : traitement) {
            dosesMedicaments.put(med, 0.0f);
        }
        historiqueActiviteImmunitaire.clear();
        historiqueChargePathogene.clear();
    }
}