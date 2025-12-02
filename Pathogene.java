import java.util.Map;

public class Pathogene implements IEvoluant {

    protected String nom;
    protected float chargePathogene;
    protected float tauxReplication;
    protected float sensibiliteSystemeImmunitaire;
    protected float sensibliteMedicament;
    protected Map<String, Float> resistancesMedicament;
    @Override
    public void evoluer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'evoluer'");
    }

}
