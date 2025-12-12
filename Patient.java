
import java.util.ArrayList;

public class Patient {
    protected static int compteurId = 0;
    protected int idPatient;
    protected String nom;
    protected String prenom;
    protected String dateDeNaissance;
    protected String numeroSecuriteSociale;
    protected ArrayList<Pathogene> pathogenes = new ArrayList<>();
    protected ArrayList<Medicament> traitement = new ArrayList<>();
    protected ReponseImmunitaire reponseImmunitaire;

    public Patient(String nom, String prenom, String dateDeNaissance, String numeroSecuriteSociale,
            ArrayList<Pathogene> pathogenes, ArrayList<Medicament> traitement, ReponseImmunitaire reponseImmunitaire) {
        this.idPatient = compteurId++;
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.numeroSecuriteSociale = numeroSecuriteSociale;
        if (pathogenes != null) this.pathogenes = pathogenes;
        if (traitement != null) this.traitement = traitement;
        this.reponseImmunitaire = reponseImmunitaire;
    }

    public Patient(String nom, String prenom, String dateDeNaissance, String numeroSecuriteSociale) {
        this.idPatient = compteurId++;
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.numeroSecuriteSociale = numeroSecuriteSociale;
    }

    public ArrayList<Pathogene> getPathogenes() {
        return pathogenes;
    }

    public ArrayList<Medicament> getTraitement() {
        return traitement;
    }

    public ReponseImmunitaire getReponseImmunitaire() {
        return reponseImmunitaire;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void ajouterPathogene(Pathogene p) {
        this.pathogenes.add(p);
    }

    public void ajouterTraitement(Medicament m) {
        this.traitement.add(m);
    }

    public void setReponseImmunitaire(ReponseImmunitaire reponseImmunitaire) {
        this.reponseImmunitaire = reponseImmunitaire;
    }

    public void simulerCycle(int numeroCycle) {
        float chargeViraleTotale = 0.0f;
        
        for (Pathogene p : pathogenes) {
            p.evoluer();
            chargeViraleTotale += p.chargePathogene; // Accès direct si protected, sinon utiliser getChargePathogene()
        }

        MiseAJourImmunite(chargeViraleTotale);

        boolean priseMedicament = (numeroCycle % 3 == 0); 

        for (Medicament m : traitement) {
            m.evoluer(priseMedicament);
        }

        System.out.println("Cycle " + numeroCycle + " | Charge Totale: " + chargeViraleTotale);
    }
    
    public void MiseAJourImmunite(float chargeTotale) {
        if (this.reponseImmunitaire != null) {
            this.reponseImmunitaire.evoluer(chargeTotale);
        }
    }
}

