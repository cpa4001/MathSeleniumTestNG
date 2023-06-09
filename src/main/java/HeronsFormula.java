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


}
