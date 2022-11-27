package Lab_1_perseptron;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    public List getInfoFile(String file) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        List<Float> list = new ArrayList<Float>();
        try {
            int i = 7;
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] country = line.split(cvsSplitBy);
                country[i] = "0";
                list.add(Float.parseFloat(country[7]));
                i = 0;
            }
            br.close();
            list.remove(0);
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return list;
        }
    }
}
