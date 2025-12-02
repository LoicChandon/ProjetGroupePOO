
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

    public Patient(String nom, String prenom, String dateDeNaissance, String numeroSecuriteSociale,
            ArrayList<Pathogene> pathogenes, ArrayList<Medicament> traitement) {
        this.idPatient = compteurId++;
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.numeroSecuriteSociale = numeroSecuriteSociale;
        this.pathogenes = pathogenes;
        this.traitement = traitement;
    }

    public Patient(String nom, String prenom, String dateDeNaissance, String numeroSecuriteSociale) {
        this.idPatient = compteurId++;
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.numeroSecuriteSociale = numeroSecuriteSociale;
    }

    
}
