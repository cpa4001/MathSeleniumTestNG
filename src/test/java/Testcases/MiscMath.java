package Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MiscMath {

    public boolean SumOfCubesAndSquareOfSum(int n) {
        int cube = 0;
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            cube += i * i * i;
            sum += i;
        }

        return cube == (sum * sum);
    }

    public double DistanceFormula(int x1, int y1, int x2, int y2) {
        var XDiff = (x2 - x1) * (x2 - x1);
        var YDiff = (y2 - y1) * (y2 - y1);

        return Math.sqrt(XDiff + YDiff);
    }

    public double[] QuadraticFormula(double a, double b, double c) throws Exception{
        double discrim = Math.pow(b, 2) - 4 * a * c ;
        if (discrim < 0) {
            throw new Exception("No real solutions");
        }

        double firstZero = -1 * b + Math.sqrt(discrim);
        firstZero /= 2 * a;
        double secondZero = -1 * b - Math.sqrt(discrim);
        secondZero /= 2 * a;

        return new double[]{firstZero, secondZero};
    }

    @Test
    public void SumsTest() {
        Assert.assertEquals(SumOfCubesAndSquareOfSum(5), true);
        Assert.assertEquals(SumOfCubesAndSquareOfSum(18), true);
        Assert.assertEquals(SumOfCubesAndSquareOfSum(34), true);
    }

    @Test
    public void DistanceTest() {
        Assert.assertEquals(DistanceFormula(0, 0, 0, 5), 5);
        Assert.assertEquals(DistanceFormula(5, 5, 8, 8), 4.24, 0.01 );
        Assert.assertEquals(DistanceFormula(-5, -5, 8, 8), 18.38, 0.01 );
    }

    @Test
    public void QuadTest() throws Exception {
        Assert.assertEquals(QuadraticFormula(1.0, 0.0, -1.0), new double[]{1, -1}, 0.01);
        Assert.assertEquals(QuadraticFormula(-0.2, 1.2, 0.0), new double[]{0, 6}, 0.01);
        Assert.assertEquals(QuadraticFormula(0.1, 1.2, 3.2), new double[]{-4, -8}, 0.01);

    }

}
