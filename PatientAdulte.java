public class PatientAdulte extends Patient {
    public PatientAdulte(int id, String nom, int age, float coefficientFatigue, float reactivite) {
        super(id, nom, age, coefficientFatigue, reactivite);
    }
    
    @Override
    protected void mettreAJourImmunite(float chargeTotale) {
        float nouvelleActivite = activiteImmunitaire + (reactivite * chargeTotale) 
                               - (coefficientFatigue * activiteImmunitaire);
        activiteImmunitaire = Math.max(0, nouvelleActivite);
    }
}