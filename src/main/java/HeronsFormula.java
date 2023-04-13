import org.testng.Assert;
import org.testng.annotations.Test;

public class HeronsFormula {
    public double HeronsFormula(double a, double b, double c) throws Exception{
        if (a > (b + c)) {
            throw new Exception("one side can not be bigger than the other two combined");
        } else if (b > (a + c)){
            throw new Exception("one side can not be bigger than the other two combined");
        } else if (c > (a + b)) {
            throw new Exception("one side can not be bigger than the other two combined");
        }
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Test
    public void HeronsTest() throws Exception {
        //https://byjus.com/herons-calculator/#:~:text=Heron's%20formula%20is%20a%20formula,)(s%2Db)(s%2Dc)%5D
        // equilateral
        Assert.assertEquals(HeronsFormula(2,2,2),1.732,  0.01);
        // isosceles
        Assert.assertEquals(HeronsFormula(2,2,3), 1.984,  0.01);
        // scalene
        Assert.assertEquals(HeronsFormula(2,3,4), 2.905, 0.01);
    }


}
