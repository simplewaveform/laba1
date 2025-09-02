import demo.parallel.Complex;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ComplexTest {
    private Complex complex1;
    private Complex complex2;

    @Before
    public void setUp() {
        complex1 = new Complex(3.0, 4.0);  // 3 + 4i
        complex2 = new Complex(1.0, 2.0);  // 1 + 2i
    }

    @Test
    public void testMinus() {
        Complex result = complex1.minus(complex2);

        // Проверяем, что создан новый объект (не изменен оригинал)
        assertNotSame(complex1, result);
        assertNotSame(complex2, result);

        // Проверяем корректность вычислений
        assertEquals(2.0, getReal(result), 0.0001);
        assertEquals(2.0, getImaginary(result), 0.0001);
    }

    @Test
    public void testMinusWithZero() {
        Complex zero = new Complex(0, 0);
        Complex result = complex1.minus(zero);

        assertEquals(3.0, getReal(result), 0.0001);
        assertEquals(4.0, getImaginary(result), 0.0001);
    }

    @Test
    public void testMinusWithNegative() {
        Complex negative = new Complex(-1.0, -2.0);
        Complex result = complex1.minus(negative);

        assertEquals(4.0, getReal(result), 0.0001);
        assertEquals(6.0, getImaginary(result), 0.0001);
    }

    @Test
    public void testScale() {
        Complex result = complex1.scale(2.0);

        // Проверяем, что создан новый объект
        assertNotSame(complex1, result);

        // Проверяем корректность вычислений
        assertEquals(6.0, getReal(result), 0.0001);
        assertEquals(8.0, getImaginary(result), 0.0001);
    }

    @Test
    public void testScaleWithNegative() {
        Complex result = complex1.scale(-1.0);

        assertEquals(-3.0, getReal(result), 0.0001);
        assertEquals(-4.0, getImaginary(result), 0.0001);
    }

    @Test
    public void testScaleWithZero() {
        Complex result = complex1.scale(0.0);

        assertEquals(0.0, getReal(result), 0.0001);
        assertEquals(0.0, getImaginary(result), 0.0001);
    }

    @Test
    public void testScaleWithFraction() {
        Complex result = complex1.scale(0.5);

        assertEquals(1.5, getReal(result), 0.0001);
        assertEquals(2.0, getImaginary(result), 0.0001);
    }

    // Вспомогательные методы для доступа к приватным полям через reflection
    private double getReal(Complex complex) {
        try {
            java.lang.reflect.Field field = Complex.class.getDeclaredField("re");
            field.setAccessible(true);
            return (Double) field.get(complex);
        } catch (Exception e) {
            fail("Failed to access real part: " + e.getMessage());
            return 0;
        }
    }

    private double getImaginary(Complex complex) {
        try {
            java.lang.reflect.Field field = Complex.class.getDeclaredField("im");
            field.setAccessible(true);
            return (Double) field.get(complex);
        } catch (Exception e) {
            fail("Failed to access imaginary part: " + e.getMessage());
            return 0;
        }
    }
}