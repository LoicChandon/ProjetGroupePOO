public class ResultatSimulation {
    private HistoriquePatient historiquePatient;
    private int dureeSimulation;
    private StatutSimulation statutFinal; 

    public ResultatSimulation(HistoriquePatient historiquePatient, int dureeSimulation, StatutSimulation statutFinal) {
        this.historiquePatient = historiquePatient;
        this.dureeSimulation = dureeSimulation;
        this.statutFinal = statutFinal;
    }

    // Getters
    public HistoriquePatient getHistoriquePatient() {
        return historiquePatient;
    }

    public StatutSimulation getStatutFinal() {
        return statutFinal;
    }

    // Méthode d'affichage
    public void afficherResultats() {
        System.out.println("==========================================");
        System.out.println("         RÉSULTAT DE LA SIMULATION        ");
        System.out.println("==========================================");
        System.out.println("Patient ID: " + historiquePatient.getIdPatientActuel());
        System.out.println("Durée totale: " + dureeSimulation + " cycles");
        System.out.println("Statut Final: " + statutFinal);
        historiquePatient.afficherHistorique(); 
    }
}