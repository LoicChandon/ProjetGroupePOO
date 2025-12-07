import java.util.Map;

public class PathogeneResistant extends PathogeneClassique implements IResistant{
    public PathogeneResistant(float chargePathogene, String nom, Map<String, Float> resistancesMedicament, float sensibiliteSystemeImmunitaire, float sensibiliteMedicament, float tauxReplication, Patient patient) {
        super(chargePathogene, nom, resistancesMedicament, sensibiliteSystemeImmunitaire, sensibiliteMedicament, tauxReplication, patient);
    }

    public void evoluer() {
        super.evoluer();
    }

    @Override
    public float calculResistanceDynamique(Medicament medicament, float dosePresente, float tauxImpactMedicament) {
        return resistancesMedicament.get(medicament.getNom()) + tauxImpactMedicament * dosePresente;
    }
}
