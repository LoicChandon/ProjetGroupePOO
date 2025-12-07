
import java.util.Map;

public class PathogeneAgressifResistant extends PathogeneAgressif implements IResistant {

    public PathogeneAgressifResistant(float chargePathogene, String nom, Map<String, Float> resistancesMedicament, float sensibiliteSystemeImmunitaire, float sensibiliteMedicament, float tauxReplication, Patient patient) {
        super(chargePathogene, nom, resistancesMedicament, sensibiliteSystemeImmunitaire, sensibiliteMedicament, tauxReplication, patient);
    }

    @Override
    public float calculResistanceDynamique(Medicament medicament, float dosePresente, float tauxImpactMedicament) {
        return resistancesMedicament.get(medicament.getNom()) + tauxImpactMedicament * dosePresente;
    }

    public void evoluer() {
        super.evoluer();
    }
}

