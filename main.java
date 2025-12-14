import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SIMULATION DE LA RÉPONSE IMMUNITAIRE ===\n");
        
        // ===== TEST 1 : Exemple de l'énoncé =====
        System.out.println("=== TEST 1 : Exemple de l'énoncé ===");
        testExempleEnonce();
        
        // // ===== TEST 2 : Patient jeune avec pathogène agressif =====
        // System.out.println("\n\n=== TEST 2 : Patient jeune avec pathogène agressif ===");
        // testPatientJeuneAgressif();
        
        // // ===== TEST 3 : Pathogène avec résistance dynamique =====
        // System.out.println("\n\n=== TEST 3 : Pathogène avec résistance dynamique ===");
        // testResistanceDynamique();
        
        // // ===== TEST 4 : Simulation avec plusieurs patients =====
        // System.out.println("\n\n=== TEST 4 : Simulation avec plusieurs patients ===");
        // testSimulationMultiple();
    }
    
    private static void testExempleEnonce() {
        // Patient : adulte, réponse immunitaire standard (β=0.4, f=0.1)
        Patient patient = new PatientAdulte(1, "Jean Dupont", 35, 0.1f, 0.4f);
        
        // Pathogène classique
        Map<String, Float> resistances = new HashMap<>();
        resistances.put("Antiviral", 0.1f); // Rm = 0.1
        
        PathogeneClassique virus = new PathogeneClassique(
            10.0f,           // L0 = 10
            "Virus A", 
            resistances, 
            0.2f,           // αi = 0.2
            0.5f,           // αm = 0.5
            0.3f            // τc = 0.3
        );
        
        // Médicament
        Medicament antiviral = new Medicament("Antiviral", 0.5f, 0.8f, 1.0f);
        
        // Configuration
        patient.ajouterPathogene(virus);
        patient.ajouterMedicament(antiviral);
        
        // Simulation sur 20 cycles
        System.out.println("Début de la simulation (20 cycles) :");
        patient.afficherEtat(0);
        
        for (int cycle = 1; cycle <= 20; cycle++) {
            // Administration du médicament tous les 3 cycles
            if (cycle % 3 == 0) {
                patient.administrerMedicament(antiviral, antiviral.getDoseAdministree());
            }
            
            patient.executerCycle();
            
            patient.afficherEtat(cycle);

            // // Afficher tous les 5 cycles
            // if (cycle % 5 == 0 || cycle == 1 || cycle == 20) {
            //     patient.afficherEtat(cycle);
            // }
        }
    }
    
    private static void testPatientJeuneAgressif() {
        Patient jeune = new PatientJeune(2, "Lucas", 12, 0.05f, 0.3f);
        
        Map<String, Float> resistances = new HashMap<>();
        resistances.put("Antibiotique", 0.0f);
        
        PathogeneAgressif bacterie = new PathogeneAgressif(
            15.0f, 
            "Bactérie B", 
            resistances, 
            0.3f,   // αi
            0.6f,   // αm
            0.2f    // τc
        );
        
        Medicament antibiotique = new Medicament("Antibiotique", 0.6f, 0.7f, 2.0f);
        
        jeune.ajouterPathogene(bacterie);
        jeune.ajouterMedicament(antibiotique);
        
        System.out.println("Début de la simulation (15 cycles) :");
        jeune.afficherEtat(0);
        
        for (int cycle = 1; cycle <= 15; cycle++) {
            if (cycle % 2 == 0) { // Tous les 2 cycles
                jeune.administrerMedicament(antibiotique, antibiotique.getDoseAdministree());
            }
            jeune.executerCycle();
            
            if (cycle % 3 == 0 || cycle == 15) {
                jeune.afficherEtat(cycle);
            }
        }
    }
    
    private static void testResistanceDynamique() {
        Patient patient = new PatientAdulte(3, "Marie", 40, 0.1f, 0.4f);
        
        Map<String, Float> resistances = new HashMap<>();
        resistances.put("AntiviralX", 0.0f); // Résistance initiale
        
        PathogeneResistant virusResistant = new PathogeneResistant(
            20.0f, 
            "Virus Résistant", 
            resistances, 
            0.25f,  // αi
            0.7f,   // αm
            0.35f   // τc
        );
        
        // Configuration de la résistance dynamique
        virusResistant.setTauxImpactMedicament("AntiviralX", 0.05f); // δm = 0.05
        
        Medicament antiviralX = new Medicament("AntiviralX", 0.7f, 0.75f, 1.5f);
        
        patient.ajouterPathogene(virusResistant);
        patient.ajouterMedicament(antiviralX);
        
        System.out.println("Début de la simulation (25 cycles) :");
        patient.afficherEtat(0);
        
        for (int cycle = 1; cycle <= 25; cycle++) {
            if (cycle % 4 == 0) {
                patient.administrerMedicament(antiviralX, antiviralX.getDoseAdministree());
            }
            patient.executerCycle();
            
            // Afficher la résistance actuelle tous les 5 cycles
            if (cycle % 5 == 0 || cycle == 25) {
                System.out.println("Cycle " + cycle + " - Résistance actuelle: " + 
                    String.format("%.4f", virusResistant.calculResistanceDynamique("AntiviralX", 
                    antiviralX.getDosePresente(), 0.05f)));
                patient.afficherEtat(cycle);
            }
        }
    }
    
    private static void testSimulationMultiple() {
        // Création de plusieurs patients
        Patient patient1 = new PatientAdulte(1, "Paul", 30, 0.1f, 0.4f);
        Patient patient2 = new PatientJeune(2, "Emma", 8, 0.05f, 0.3f);
        Patient patient3 = new PatientAge(3, "Robert", 70, 0.15f, 0.35f);
        
        // Pathogènes communs
        Map<String, Float> resistances = new HashMap<>();
        resistances.put("MédicamentA", 0.1f);
        
        PathogeneClassique virus1 = new PathogeneClassique(10.0f, "Virus Standard", resistances, 0.2f, 0.5f, 0.3f);
        PathogeneClassique virus2 = new PathogeneClassique(15.0f, "Virus Standard", resistances, 0.2f, 0.5f, 0.3f);
        PathogeneClassique virus3 = new PathogeneClassique(12.0f, "Virus Standard", resistances, 0.2f, 0.5f, 0.3f);
        
        // Médicament
        Medicament medicament = new Medicament("MédicamentA", 0.5f, 0.8f, 1.0f);
        
        // Configuration des patients
        patient1.ajouterPathogene(virus1);
        patient1.ajouterMedicament(medicament);
        
        patient2.ajouterPathogene(virus2);
        patient2.ajouterMedicament(medicament);
        
        patient3.ajouterPathogene(virus3);
        patient3.ajouterMedicament(medicament);
        
        List<Patient> patients = new ArrayList<>();
        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);
        
        // Simulation
        Simulation simulation = new Simulation(patients, 20);
        simulation.executerSimulation();
        
        // Résultats
        List<ResultatSimulation> resultats = simulation.obtenirResultats();
        for (ResultatSimulation res : resultats) {
            res.afficherResultats();
        }
    }
}