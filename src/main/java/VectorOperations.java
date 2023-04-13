import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VectorOperations {

    public double Magnitude(List<Double> vector) {
        double sum = 0;
        for (int i = 0; i < vector.size(); i++) {
            sum += vector.get(i) * vector.get(i);
        }
        return Math.sqrt(sum);
    }

    public int DotProduct(List<Double> A, List<Double> B) throws Exception {
        if (A.size() != B.size()){
            throw new Exception("Cannot compute dot product of vectors of different sizes");
        } else {
            int result = 0;
            for (int i = 0; i < A.size(); i++) {
                double prod = A.get(i) * B.get(i);
                result += prod;
            }
            return result;
        }
    }

    public List<Double> ScalarMultiple(List<Double> list, double scalar) {
        for (int i = 0; i < list.size(); i++){
            var init = list.get(i);
            list.set(i, init * scalar);
        }
        return list;
    }

    public List<Double> AddVector(List<Double> A, List<Double> B) throws Exception{
        if (A.size() != B.size()) {
            throw new Exception("Can not add vectors of different sizes");
        } else {
            List<Double> result = new ArrayList<Double>();
            for (int i = 0; i < A.size(); i++) {
                result.add(A.get(i) + B.get(i));
            }
            return result;
        }
    }

    public List<Double> SubtractVector(List<Double> A, List<Double> B) throws Exception{
        if (A.size() != B.size()) {
            throw new Exception("Can not add vectors of different sizes");
        } else {
            List<Double> result = new ArrayList<Double>();
            for (int i = 0; i < A.size(); i++) {
                result.add(A.get(i) - B.get(i));
            }
            return result;
        }
    }

    public double CosineBetweenVectors(List<Double> A, List<Double> B) throws Exception {
        return DotProduct(A, B) / (Magnitude(A) * Magnitude(B));
    }

    @Test
    public void MagnitudeTest() {
        Assert.assertEquals(Magnitude(Arrays.asList(1.23, 1.11, 3.56, 0.98, 2.22)), 4.61, 0.01);
        Assert.assertEquals(Magnitude(Arrays.asList(0.012, 0.0062, 0.15, 0.98, 0.234)), 1.01, 0.01);
        Assert.assertEquals(Magnitude(Arrays.asList(3.0, 4.0)), 5.0, 0.01);
    }

    @Test
    public void ScalarTest() {
        Assert.assertEquals(ScalarMultiple(Arrays.asList(1.23, 1.11, 3.56, 0.98, 2.22), 2), Arrays.asList(2.46, 2.22, 7.12, 1.96, 4.44));
        Assert.assertEquals(ScalarMultiple(Arrays.asList(0.012, 0.0062, 0.15, 0.98, 0.234), -1),  Arrays.asList(-0.012, -0.0062, -0.15, -0.98, -0.234));
        Assert.assertEquals(ScalarMultiple(Arrays.asList(3.0, 4.0), 10), Arrays.asList(30.0, 40.0));
    }

    @Test
    public void DotProdTest() throws Exception {
        Assert.assertEquals(DotProduct(Arrays.asList(1.0, 2.0, 3.0), Arrays.asList(1.0, 2.0, 3.0)), 14, 0.01);
        Assert.assertEquals(DotProduct(Arrays.asList(1.0, 0.0), Arrays.asList(0.0, 1.0)), 0, 0.01);
        Assert.assertEquals(DotProduct(Arrays.asList(2.2, 3.3, 4.3), Arrays.asList(1.2, 3.2, 2.2)), 21.0, 0.01);
    }

    @Test
    public void AddTest() throws Exception {
        Assert.assertEquals(AddVector(Arrays.asList(1.0, 2.0, 3.0), Arrays.asList(1.0, 2.0, 3.0)), Arrays.asList(2.0, 4.0, 6.0));
        Assert.assertEquals(AddVector(Arrays.asList(1.0, 0.0), Arrays.asList(0.0, 1.0)), Arrays.asList(1.0, 1.0));
        Assert.assertEquals(AddVector(Arrays.asList(2.2, 3.3, 4.3), Arrays.asList(1.2, 3.2, 2.2)), Arrays.asList(3.4, 6.5, 6.5));
    }

    @Test
    public void SubractTest() throws Exception {
        Assert.assertEquals(AddVector(Arrays.asList(1.0, 2.0, 3.0), Arrays.asList(1.0, 2.0, 3.0)), Arrays.asList(0.0, 0.0, 0.0));
        Assert.assertEquals(AddVector(Arrays.asList(1.0, 0.0), Arrays.asList(0.0, 1.0)), Arrays.asList(1.0, -1.0));
        Assert.assertEquals(AddVector(Arrays.asList(2.2, 3.3, 4.3), Arrays.asList(1.2, 3.2, 2.2)), Arrays.asList(1.0, 0.1, 2.1));
    }

    @Test
    public void CosineTest() throws Exception {
        Assert.assertEquals(CosineBetweenVectors(Arrays.asList(1.0, 2.0, 3.0), Arrays.asList(1.0, 2.0, 3.0)), 1, 0.01);
        Assert.assertEquals(CosineBetweenVectors(Arrays.asList(1.0, 0.0), Arrays.asList(0.0, 1.0)), 0, 0.01);
        Assert.assertEquals(CosineBetweenVectors(Arrays.asList(2.2, 3.3, 4.3), Arrays.asList(1.2, 3.2, 2.2)), 0.88, 0.01);
    }



}
