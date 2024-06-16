package Test;


import main.dsa.linear.Array.easy.PascalTriangle;
import org.junit.Assert;
import org.junit.Test;

public class PascalTriangleTest {

    PascalTriangle pascalTriangle = new PascalTriangle();

    @Test
    public void TestOne() {
        Assert.assertNotNull(pascalTriangle.soultion1(5));
    }

    @Test
    public void TestTwo() {
        Assert.assertNotNull(pascalTriangle.soultion2(7));
    }

    @Test
    public void TestThree() {
        Assert.assertNotNull(pascalTriangle.soultion1(0));
    }

    @Test
    public void TestFOur() {
        Assert.assertNotNull(pascalTriangle.soultion1(-1));
    }
}