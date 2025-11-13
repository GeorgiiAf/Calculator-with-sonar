import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Test
    public void addValues() {
        assertEquals(16, 0.1, Calculator.addValues(12, 4));
    }

    @Test
    public void subtractValue() {
        assertEquals(-8, Calculator.subtractValue(-12, -4), 0.01);

    }

    @Test
    public void multiplyValues() {
        assertEquals(24, Calculator.multiplyValues(12, 2), 0.01);
    }

    @Test
    public void divideValues() {
        assertEquals(6,  Calculator.divideValues(12, 2), 0.01);
    }
}