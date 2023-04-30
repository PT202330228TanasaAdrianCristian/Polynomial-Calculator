package polinom;

import java.util.HashMap;
import java.util.Map;

public class Polynomial {

    private Map<Integer, Double> polynomialValues;

    public Polynomial(Map<Integer, Double> polynomialValues) {
        this.polynomialValues = polynomialValues;
    }


    // for (var name : collection) { do something with var }

    // for (int i = 0; i < collection.size(); i++) { var name = collection.get(i); do something }


    //.....................adunare..............................................................................
    public Polynomial add(Polynomial other) {
        Map<Integer, Double> result = new HashMap<>(this.polynomialValues);
        for (Map.Entry<Integer, Double> entry : other.polynomialValues.entrySet()) {
            Integer key = entry.getKey();
            Double value = entry.getValue();
            Double newValue = result.getOrDefault(key, 0d) + value; //in cazul in care nu gasesc val respectiva sa ii dea o val default
            result.put(key, newValue);
        }
        return new Polynomial(result);
    }

    //.............................scadere..................................
    public Polynomial subtract(Polynomial other) {
        Map<Integer, Double> result = new HashMap<>(this.polynomialValues);
        for (Map.Entry<Integer, Double> entry : other.polynomialValues.entrySet()) {
            Integer key = entry.getKey();
            Double value = entry.getValue();
            Double newValue = result.getOrDefault(key, 0d) - value;
            result.put(key, newValue);
        }
        return new Polynomial(result);
    }

    //.........................inmultire.............................................
    public Polynomial multiply(Polynomial other) {
        Map<Integer, Double> result = new HashMap<>();
        for (Map.Entry<Integer, Double> entry : this.polynomialValues.entrySet()) {
            for (Map.Entry<Integer, Double> entry2 : other.polynomialValues.entrySet()) {

                int exponent = entry.getKey() + entry2.getKey();
                Double constant = entry.getValue() * entry2.getValue();

                Double newValue = result.getOrDefault(exponent, 0d) + constant;
                result.put(exponent, newValue);
            }
        }
        return new Polynomial(result);
    }

    //..........................impartire??..........................................
    public Polynomial division(Polynomial other) {
        //TODO idk
        return null;
    }

    //.........................derivare.............................................
    public Polynomial derivative() {
        Map<Integer, Double> result = new HashMap<>();
        for (Map.Entry<Integer, Double> entry : this.polynomialValues.entrySet()) {
            if (entry.getKey() != 0) {
                Double newConstantD = entry.getValue() * entry.getKey();
                Integer newExponentD = entry.getKey() - 1;
                result.put(newExponentD, newConstantD);
            }

        }
        return new Polynomial(result);
    }

    //.....................integrare.........................................
    public Polynomial integrate() {
        Map<Integer, Double> result = new HashMap<>();
        for (Map.Entry<Integer, Double> entry : this.polynomialValues.entrySet()) {
            if (entry.getValue() != 0)  // x^n = (x^(n+1))/
            // (n+1)
            {
                Double newConstantI = entry.getValue() / (entry.getKey() + 1);
                Integer newExponentI = entry.getKey() + 1;
                result.put(newExponentI, newConstantI);

            }


        }

        return new Polynomial(result);
    }

    @Override
    public String toString() {
        String result = "";
        for (Map.Entry<Integer, Double> entry : this.polynomialValues.entrySet()) {
            int exponent = entry.getKey();
            Double constant = entry.getValue();

            if (result.isEmpty()) { //la inceputul polinomului nu pune "+"
                result += getConstantString(constant);
                if (exponent != 0) {
                    result += "x^" + exponent;
                }
            } else {
                if (constant > 0) { //daca constanta e pozitiva pune si semnul
                    result += "+";
                }
                result += getConstantString(constant) + "x^" + exponent;
            }
        }
        return result;
    }

    private String getConstantString(double constant) {
        if (constant == (int) constant) //daca constanta e nr intreg nu afisa partea fractionala
            return String.format("%d", (int) constant);
        else
            return String.valueOf(constant);
    }

}

