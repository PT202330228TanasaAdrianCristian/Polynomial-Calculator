package polinom;

import javax.swing.*;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //7 + 5x + 9x^6
        Map<Integer, Float> map1 = Map.of(0, 2f,
                2, 3f);
        Polynomial polynomial1 = new Polynomial(map1);

        Map<Integer, Float> map2 = Map.of(1, 5f,
                2, 7f,
                3, 8f);
        Polynomial polynomial2 = new Polynomial(map2);
        Polynomial added = polynomial1.add(polynomial2);
        Polynomial subtracted = polynomial1.subtract(polynomial2);
        Polynomial multiplied = polynomial1.multiply(polynomial2);
        Polynomial derivative = polynomial1.derivative();
        Polynomial integrated = polynomial1.integrate();


        System.out.println(added);
        System.out.println(subtracted);
        System.out.println(multiplied);
        System.out.println(derivative);
        System.out.println(integrated);


        JFrame frame = new View("Simple calculator using MVC");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}


