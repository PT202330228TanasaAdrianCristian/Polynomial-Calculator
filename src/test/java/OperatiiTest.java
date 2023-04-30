
import org.junit.jupiter.api.Test;
import polinom.Polynomial;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class OperatiiTest {
    @Test
    public void testAdd() {
        // p1
        Map<Integer, Double> poly1 = new HashMap<>();
        poly1.put(2, 3d);
        poly1.put(1, 4d);
        poly1.put(0, 1d);
        Polynomial p1 = new Polynomial(poly1);

        // p2
        Map<Integer, Double> poly2 = new HashMap<>();
        poly2.put(3, 2d);
        poly2.put(2, 1d);
        poly2.put(0, 5d);
        Polynomial p2 = new Polynomial(poly2);

        Polynomial result = p1.add(p2);

        Map<Integer, Double> expected = new HashMap<>();
        Polynomial expected1 = new Polynomial(expected);
        assertEquals("6+4x^1+4x^2+2x^3", result.toString());
    }

    @Test
    public void testSub() {
        Map<Integer, Double> poly1 = new HashMap<>();
        poly1.put(2, 3d);
        poly1.put(1, 4d);
        poly1.put(0, 1d);
        Polynomial p1 = new Polynomial(poly1);

        Map<Integer, Double> poly2 = new HashMap<>();
        poly2.put(3, 2d);
        poly2.put(2, 1d);  //3.0x^2+4x^1+1   2.0x^
        poly2.put(0, 5d);
        Polynomial p2 = new Polynomial(poly2);

        Polynomial result = p1.subtract(p2);

        Map<Integer, Double> expected = new HashMap<>();
        Polynomial expected2 = new Polynomial(expected);
        assertEquals("-4+4x^1+2x^2-2x^3", result.toString());
    }

    @Test
    public void testMultiply() {
        Map<Integer, Double> poly1 = new HashMap<>();
        poly1.put(2, 3d);
        poly1.put(1, 4d);
        poly1.put(0, 1d);
        Polynomial p1 = new Polynomial(poly1);

        Map<Integer, Double> poly2 = new HashMap<>();
        poly2.put(3, 2d);
        poly2.put(2, 1d);  //3.0x^2+4x^1+1   2.0x^
        poly2.put(0, 5d);
        Polynomial p2 = new Polynomial(poly2);

        Polynomial result = p1.multiply(p2);

        Map<Integer, Double> expected = new HashMap<>();
        Polynomial expected3 = new Polynomial(expected);
        assertEquals("5+20x^1+16x^2+6x^3+11x^4+6x^5", result.toString());
    }

    @Test
    public void testDeriv() {
        Map<Integer, Double> poly1 = new HashMap<>();
        poly1.put(2, 3d);
        poly1.put(1, 4d);
        poly1.put(0, 1d);
        Polynomial p1 = new Polynomial(poly1);

        Polynomial result = p1.derivative();

        Map<Integer, Double> expected = new HashMap<>();
        Polynomial expected4 = new Polynomial(expected);
        assertEquals("4+6x^1", result.toString());


    }

    @Test
    public void testIntegr() {
        Map<Integer, Double> poly1 = new HashMap<>();
        poly1.put(2, 3d);
        poly1.put(1, 4d);
        poly1.put(0, 1d);
        Polynomial p1 = new Polynomial(poly1);

        Polynomial result = p1.integrate();

        Map<Integer, Double> expected = new HashMap<>();
        Polynomial expected5 = new Polynomial(expected);
        assertEquals("1x^1+2x^2+1x^3", result.toString());
    }
}