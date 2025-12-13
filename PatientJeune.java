public class PatientJeune extends Patient {
    public PatientJeune(int id, String nom, int age, float coefficientFatigue, float reactivite) {
        super(id, nom, age, coefficientFatigue, reactivite);
    }
    
    @Override
    protected void mettreAJourImmunite(float chargeTotale) {
        mettreAJourImmuniteJeune(chargeTotale);
    }
}