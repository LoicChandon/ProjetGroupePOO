
import java.util.ArrayList;

public class Patient {
    protected static int compteurId = 0;
    protected int idPatient;
    protected String nom;
    protected String prenom;
    protected String dateDeNaissance;
    protected String numeroSecuriteSociale;
    protected ArrayList<Pathogene> pathogenes;
    protected ArrayList<Medicament> traitement;
    protected ReponseImmunitaire reponseImmunitaire;

    public Patient(String nom, String prenom, String dateDeNaissance, String numeroSecuriteSociale,
            ArrayList<Pathogene> pathogenes, ArrayList<Medicament> traitement, ReponseImmunitaire reponseImmunitaire) {
        this.idPatient = compteurId++;
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.numeroSecuriteSociale = numeroSecuriteSociale;
        this.pathogenes = pathogenes;
        this.traitement = traitement;
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
}
