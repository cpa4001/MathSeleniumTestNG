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

}
