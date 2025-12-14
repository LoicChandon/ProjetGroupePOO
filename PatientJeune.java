public class PatientJeune extends Patient {
    public PatientJeune(int id, String nom, int age, float coefficientFatigue, float reactivite) {
        super(id, nom, age, coefficientFatigue, reactivite);
    }
    
    @Override
    protected void mettreAJourImmunite(float chargeTotale) {
        float sqrtCharge = (chargeTotale >= 0) ? (float)Math.sqrt(chargeTotale) : 0;
        float nouvelleActivite = activiteImmunitaire + (reactivite * sqrtCharge) 
                               - (coefficientFatigue * activiteImmunitaire);
        activiteImmunitaire = Math.max(0, nouvelleActivite);
    }
}