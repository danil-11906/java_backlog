package Lab_1_perseptron;

import java.util.ArrayList;
import java.util.List;

public class WorkList {
    public List<Float> getAB(List list){
        float countY = 0.0F;
        float countX = 0.0F;
        float n = (float) list.size();
        float countXY = 0.0F;
        float countSquareX = 0.0F;

        for (int i = 0; i < list.size(); i++) {
            countY = Float.sum(countY, (float) list.get(i));
        }
        countY = countY/n;
        for (int i = 1; i <= list.size(); i++) {
            countX = Float.sum(countX, (float) i);
        }
        countX = countX/n;
        for (int i = 0; i < list.size(); i++) {
            float xy = ((float)i + 1) * (float) list.get(i);
            countXY = Float.sum(countXY, xy);
        }
        countXY = countXY/n;
        for (int i = 0; i < list.size(); i++) {
            countSquareX = Float.sum(countSquareX, ((float)i + 1)*((float)i + 1));
        }
        countSquareX = countSquareX/n;

        float a = (countXY - (countX * countY))/(countSquareX - (countX * countX));
        float b = countY - (a * countX);

        List<Float> trend = new ArrayList<Float>();
        trend.add(a);
        trend.add(b);
        return trend;
    }
}
