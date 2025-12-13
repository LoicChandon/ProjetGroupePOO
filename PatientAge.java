public class PatientAge extends Patient {
    public PatientAge(int id, String nom, int age, float coefficientFatigue, float reactivite) {
        super(id, nom, age, coefficientFatigue, reactivite);
    }
    
    @Override
    protected void mettreAJourImmunite(float chargeTotale) {
        mettreAJourImmuniteAgee(chargeTotale);
    }
}