import java.util.Map;

public abstract class Pathogene implements IEvoluant {

    protected String nom;
    protected float chargePathogene;
    protected float tauxReplication;
    protected float sensibiliteSystemeImmunitaire;
    protected float sensibliteMedicament;
    protected Map<String, Float> resistancesMedicament;
    @Override
    public abstract void evoluer();

}
