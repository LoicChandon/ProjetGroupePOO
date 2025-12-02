import java.util.HashMap;
import java.util.Map;

public class Etat {
    protected float activiteSystemeImmunitaireCourante = 0.0f;
    protected Map<String, Float> dosesMedicament = new HashMap<>();
    public float getActiviteSystemeImmunitaireCourante() {
        return activiteSystemeImmunitaireCourante;
    }
    public void setActiviteSystemeImmunitaireCourante(float activiteSystemeImmunitaireCourante) {
        this.activiteSystemeImmunitaireCourante = activiteSystemeImmunitaireCourante;
    }
    public Map<String, Float> getDosesMedicament() {
        return dosesMedicament;
    }
    public void setDosesMedicament(Map<String, Float> dosesMedicament) {
        this.dosesMedicament = dosesMedicament;
    }
}