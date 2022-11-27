package Lab_1_perseptron;

import java.util.ArrayList;
import java.util.List;

public class Perseptron {
    private float n = 0.01F;
    public List<Float> updateWeight (float x, float y, float result, float w1, float w2) {
        if ((x * w1) + (y * w2) != result){
            w1 = w1 + (n * x * y);
            w2 = w2 + (n * x * y);
            List<Float> list = new ArrayList<>();
            list.add(w1);
            list.add(w2);
            return list;
        } else {
            List<Float> list = new ArrayList<>();
            list.add(w1);
            list.add(w2);
            return list;
        }
    }
}
