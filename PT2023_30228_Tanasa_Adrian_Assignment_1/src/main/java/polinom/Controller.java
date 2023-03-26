package polinom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Controller implements ActionListener {

    private View view;

    public Controller(View v) {
        this.view = v;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("COMPUTE")) {
            Polynomial p1 = parsePolynomial(view.getFirstNumberTextField().getText());
            Polynomial p2 = parsePolynomial(view.getSecondNumberTextField().getText());
            String operation = String.valueOf(view.getOperationsComboBox().getSelectedItem());
            Polynomial result;
            switch (operation) {
                case "Add" -> result = p1.add(p2);
                case "Subtract" -> result = p1.subtract(p2);
                case "Multiply" -> result = p1.multiply(p2);
                case "Integrate" -> result = p1.integrate();
                case "Derivative"-> result = p1.derivative();
                default -> result = new Polynomial(Collections.emptyMap());
            }
            view.getResultValueLabel().setText(String.valueOf(result));
        }
    }

//    "2-2x+4x^3"  -> "~-2~-2x~+4x^3" -> ["2", "", "x^3"] ->

    private Polynomial parsePolynomial(String text) {
        Map<Integer, Float> polynomialValues = new HashMap<>();
        text = text.replace("+", "~+");
        text = text.replace("-", "~-");
        String[] monomials = text.split("~");
        for (String monomial : monomials) {
            parseMonomial(monomial, polynomialValues);
        }
        return new Polynomial(polynomialValues);
    }

    private static void parseMonomial(String monomial, Map<Integer, Float> polynomialValues) {
        if (monomial.isEmpty()) { //daca incepe cu minus primul String va fi "" si trebuie sa trecem la urmatorul
            return;
        }
        if (!monomial.contains("x")) {
            float newConstant = Float.parseFloat(monomial);
            polynomialValues.put(0, newConstant);
        } else {
            if (!monomial.contains("^")) {
                String constantString = monomial.replace("x", "");
                // TODO sa gestionez cazul in care monomul e doar "x" ca da empty string acum
                float newConstant = Float.parseFloat(constantString);
                polynomialValues.put(1, newConstant);
            } else {
                String[] mono = monomial.split("x\\^"); //trebuie \\ pt ca ^ e caracter special in regex
                float newConstant = Float.parseFloat(mono[0]);
                int newExponent =Integer.parseInt(mono[1]);
                polynomialValues.put(newExponent,newConstant);
            }
        }
    }
}
