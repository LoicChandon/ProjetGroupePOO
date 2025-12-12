
public class TestImmun {
    public static void main(String[] args) {
        
        // Test avec les paramètres de l'exemple 3.1
        float beta = 0.4f;   // β
        float f = 0.1f;      // f
        float charge = 10.0f; // L
        
        System.out.println("Paramètres de test:");
        System.out.println("  β (réactivité) = " + beta);
        System.out.println("  f (fatigue) = " + f);
        System.out.println("  Charge L = " + charge);
        System.out.println();
        
        // Test 1: Adulte
        System.out.println("1. TEST ADULTE (équation 4):");
        ReponseImmunitaireAdulte adulte = new ReponseImmunitaireAdulte(f, beta);
        adulte.setActivite(0);
        adulte.evoluer(charge);
        
        float attenduAdulte = Math.max(0, 0 + beta*charge - f*0); // = 4.0
        System.out.println("  Activité attendue: " + attenduAdulte);
        System.out.println("  Activité obtenue: " + adulte.getActivite());
        System.out.println("   Correct: " + (Math.abs(adulte.getActivite() - attenduAdulte) < 0.001));
        
        // Test 2: Jeune
        System.out.println("\n2. TEST JEUNE (équation 5):");
        ReponseImmunitaireJeune jeune = new ReponseImmunitaireJeune(f, beta);
        jeune.setActivite(5.0f);
        jeune.evoluer(15.0f);
        
        float attenduJeune = Math.max(0, 5 + beta*(float)Math.sqrt(15) - f*5); // ≈ 6.049
        System.out.println("  Activité attendue: " + attenduJeune);
        System.out.println("  Activité obtenue: " + jeune.getActivite());
        System.out.println("   Correct: " + (Math.abs(jeune.getActivite() - attenduJeune) < 0.01));
        
        // Test 3: Âgé
        System.out.println("\n3. TEST ÂGÉ (équation 6):");
        ReponseImmunitaireAgee agee = new ReponseImmunitaireAgee(f, beta);
        agee.setActivite(5.0f);
        agee.evoluer(15.0f);
        
        float attenduAgee = Math.max(0, 5 + beta*15 - f*5*5); // = 8.5
        System.out.println("  Activité attendue: " + attenduAgee);
        System.out.println("  Activité obtenue: " + agee.getActivite());
        System.out.println("  Correct: " + (Math.abs(agee.getActivite() - attenduAgee) < 0.001));
        
    }
}