package polinom;

import java.util.HashMap;
import java.util.Map;

public class Polynomial {

    private final Map<Integer, Float> polynomialValues;

    public Polynomial(Map<Integer, Float> polynomialValues) {
        this.polynomialValues = polynomialValues;
    }


    // for (var name : collection) { do something with var }

    // for (int i = 0; i < collection.size(); i++) { var name = collection.get(i); do something }

    // 2 +      3x^2                 p1
    //     5x + 7x^2 + 8x^3          p2

    // 2 + 5x + 10x^2 + 8x^3        p1.add(p2)
    //.....................adunare..............................................................................
    public Polynomial add(Polynomial other) {
        Map<Integer, Float> result = new HashMap<>(this.polynomialValues);
        for (Map.Entry<Integer, Float> entry : other.polynomialValues.entrySet()) {
            Integer key = entry.getKey();
            Float value = entry.getValue();
            Float newValue = result.getOrDefault(key, 0f) + value; //in cazul in care nu gasesc val respectiva sa ii dea o val default
            result.put(key, newValue);
        }
        return new Polynomial(result);
    }
//.............................scadere..................................
    public Polynomial subtract(Polynomial other) {
        Map<Integer, Float> result = new HashMap<>(this.polynomialValues);
        for (Map.Entry<Integer, Float> entry : other.polynomialValues.entrySet()) {
            Integer key = entry.getKey();
            Float value = entry.getValue();
            Float newValue = result.getOrDefault(key, 0f) - value;
            result.put(key, newValue);
        }
        return new Polynomial(result);
    }
//.........................inmultire.............................................
    public Polynomial multiply(Polynomial other) {
        Map<Integer, Float> result = new HashMap<>();
        for (Map.Entry<Integer, Float> entry : this.polynomialValues.entrySet()) {
            for (Map.Entry<Integer, Float> entry2 : other.polynomialValues.entrySet()) {

                int exponent = entry.getKey() +entry2.getKey();
                float constant = entry.getValue() * entry2.getValue();

                Float newValue = result.getOrDefault(exponent, 0f) + constant;
                result.put(exponent, newValue);
            }
        }
        return new Polynomial(result);
    }
//..........................impartire??..........................................
    public Polynomial division(Polynomial other) {
        //TODO change to use float
        return null;
    }
//.........................derivare.............................................
    public Polynomial derivative() {
        Map<Integer, Float> result = new HashMap<>();
        for (Map.Entry<Integer, Float> entry : this.polynomialValues.entrySet())
        {
            if( entry.getKey() != 0)
            {
                Float newConstantD = entry.getValue() * entry.getKey();
                Integer newExponentD = entry.getKey() - 1;
                result.put(newExponentD, newConstantD);
            }

        }
        return new Polynomial(result);
    }
//.....................integrare.........................................
    public Polynomial integrate() {
        Map<Integer, Float> result = new HashMap<>();
        for (Map.Entry<Integer, Float> entry : this.polynomialValues.entrySet())
        {
            if (entry.getValue() != 0)  // x^n = (x^(n+1))/
                                                // (n+1)
            {
                Float newConstantI = entry.getValue() / (entry.getKey() + 1);
                Integer newExponentI = entry.getKey() + 1;
                result.put(newExponentI, newConstantI);

            }


        }

        return new Polynomial(result);
    }

    @Override
    public String toString() {
        String result = "";
        for (Map.Entry<Integer, Float> entry : this.polynomialValues.entrySet()) {
            int exponent = entry.getKey();
            float constant = entry.getValue();
            if (result.isEmpty()) { //la inceputul polinomului nu pune "+"
                result = getConstantString(constant) + "x^" + exponent;
            } else {
                if (constant > 0) { //daca constanta e pozitiva pune si semnul
                    result += "+";
                }
                //TODO gestionez cazul in care exponentul e 0 sau 1
                result += getConstantString(constant) + "x^" + exponent;
            }
        }
        return result;
    }

    private String getConstantString(float constant) {
        if(constant == (int) constant) //daca constanta e nr intreg nu afisa partea fractionala
            return String.format("%d",(int)constant);
        else
            return String.valueOf(constant);
    }

}

