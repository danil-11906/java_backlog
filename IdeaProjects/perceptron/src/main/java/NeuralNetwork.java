import java.io.*;
import java.util.*;

public class NeuralNetwork {

    // Веса модели
    private double w11,
            w12,
            w21,
            w22,
            v11,
            v12,
            v13,
            v21,
            v22,
            v23,
            w1,
            w2,
            w3;

    @Override
    public String toString() {
        return "";
    }

    // ошибка модели
    private double e;

    private static double x1[] = new double[100];
    private static double x2[] = new double[100];
    private static double y[] = new double[100];

    static {
        readX("/home/yuliya/Desktop/sysanalisis/xdata.csv");
        readY("/home/yuliya/Desktop/sysanalisis/ydata04.csv");
        // readTestData100();
    }

    public NeuralNetwork(double w11,
                         double w12,
                         double w21,
                         double w22,
                         double v11,
                         double v12,
                         double v13,
                         double v21,
                         double v22,
                         double v23,
                         double w1,
                         double w2,
                         double w3) {
        this.w11 = w11;
        this.w12 = w12;
        this.w21 = w21;
        this.w22 = w22;
        this.v11 = v11;
        this.v12 = v12;
        this.v13 = v13;
        this.v21 = v21;
        this.v22 = v22;
        this.v23 = v23;
        this.w1 = w1;
        this.w2 = w2;
        this.w3 = w3;
    }

    public NeuralNetwork(DataModel model) throws NumberFormatException {
        init(model);
    }

    public NeuralNetwork() {}

    public void init(DataModel model) throws NumberFormatException {
        this.w11 = Double.parseDouble(model.getW11());
        this.w12 = Double.parseDouble(model.getW12());
        this.w21 = Double.parseDouble(model.getW21());
        this.w22 = Double.parseDouble(model.getW22());
        this.v11 = Double.parseDouble(model.getV11());
        this.v12 = Double.parseDouble(model.getV12());
        this.v13 = Double.parseDouble(model.getV13());
        this.v21 = Double.parseDouble(model.getV21());
        this.v22 = Double.parseDouble(model.getV22());
        this.v23 = Double.parseDouble(model.getV23());
        this.w1 = Double.parseDouble(model.getW1());
        this.w2 = Double.parseDouble(model.getW2());
        this.w3 = Double.parseDouble(model.getW3());
    }

    // activation
    private double f(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    // forward
    public double g(double x1, double x2) {
        double h11 = f(x1 * w11 + x2 * w21);
        double h12 = f( x1 * w12 + x2 * w22);
        return f( f(h11 * v11 + h12 * v21) * w1 + f(h11 * v12 + h12 * v22) * w2 + f(h11 * v13 + h12 * v23));
    }

    // error calculation
    public double e() {
        double res = 0;
        for (int i = 0; i< 100; i++) {
            double yt = g(x1[i], x2[i]);
            res += (yt - y[i]) * (yt - y[i]);
        }
        return res;
    }

    private static List<List<String>> readCSV(String pathToCSV) {
        try (BufferedReader br =
                     new BufferedReader(
                             new FileReader(pathToCSV))) {

            List<List<String>> list = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                list.add(Arrays.asList(line.split(";")));
            }

            return list;

        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        return null;
    }

    private static void readTestData100() {
        // read test data 100
        try (BufferedReader br = new BufferedReader(new FileReader("/home/yuliya/Desktop/sysanalisis/test_data_100.csv"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                x1[i] = Double.parseDouble(values[0]);
                x2[i] = Double.parseDouble(values[1]);
                y[i] = Double.parseDouble(values[2]);
                i++;
            }
        } catch (IOException fileNotFoundException) {
            System.out.println("No such file");
            fileNotFoundException.printStackTrace();
        }

//        List<String> lstData;
//        try(BufferedReader reader = new BufferedReader(
//                new InputStreamReader(Objects.requireNonNull(NeuralNetwork.class.getClassLoader()
//                        .getResourceAsStream("/home/yuliya/Desktop/sysanalisis/test_data_100.csv"))))) {
//            String line;
//            int i = 0;
//            while ((line = reader.readLine()) != null) {
//                String[] s = line.split(";");
//                x1[i] = Double.parseDouble(s[0]);
//                x2[i] = Double.parseDouble(s[1]);
//                y[i] = Double.parseDouble(s[2]);
//                i++;
//            }
//        } catch (IOException | NumberFormatException ioException) {
//            ioException.printStackTrace();
//        }
    }

    private static void readTestData100(String path) {
        List<List<String>> data = readCSV(path);

        for (int i = 0; i < data.size(); i++) {
            List<String> line = data.get(i);
            x1[i] = Double.parseDouble(line.get(0));
            x2[i] = Double.parseDouble(line.get(1));
            y[i] = Double.parseDouble(line.get(2));
        }

    }

    private static void readX(String path) {
        List<List<String>> data = readCSV(path);

        for (int i = 0; i < data.size(); i++) {
            List<String> line = data.get(i);
            x1[i] = Double.parseDouble(line.get(0));
            x2[i] = Double.parseDouble(line.get(1));
        }
    }

    private static void readY(String path) {
        List<List<String>> data = readCSV(path);

        for (int i = 0; i < data.size(); i++) {
            List<String> line = data.get(i);
            y[i] = Double.parseDouble(line.get(0));
        }
    }

    public static void main(String[] args) {

        int iteration = 0;

        while(true) {
            Double[] weights = new Double[13];

            for (int i = 0; i < 13; i++) {
                Random r = new Random();
                double randomValue = r.nextDouble();
                weights[i] = randomValue;
            }

            NeuralNetwork nn = new NeuralNetwork(weights[0], weights[1],
                                                 weights[2], weights[3],
                                                 weights[4], weights[5],
                                                 weights[6], weights[7],
                                                 weights[8], weights[9],
                                                 weights[10], weights[11],
                                                 weights[12]);

            iteration++;

            System.out.println("Iter = " + iteration + " error = " + nn.e());

            DataModel dataModel = new DataModel(Double.toString(weights[0]),
                    Double.toString(weights[1]),
                    Double.toString(weights[2]),
                    Double.toString(weights[3]),
                    Double.toString(weights[4]),
                    Double.toString(weights[5]),
                    Double.toString(weights[6]),
                    Double.toString(weights[7]),
                    Double.toString(weights[8]),
                    Double.toString(weights[9]),
                    Double.toString(weights[10]),
                    Double.toString(weights[11]),
                    Double.toString(weights[12]),
                    Double.toString(nn.e()),
                    null);

            if (nn.e() < 0.1 || iteration > 1000) {
                System.out.println(dataModel);
                break;
            }
        }

//        NeuralNetwork nn = new NeuralNetwork(0.11, 0.21, 0.22, 1.4, 2.0, 0.0002, 2.0056, 0.017,0.934, 1.5, 1.0047, 0.12, 1.32);
//        DataModel dm = new DataModel("1.11", "0.11", "0.23", "1.41", "1.94", "0.0003", "2.016", "0.007", "0.904", "1.58", "1.017", "0.102", "1.2","","");
//        DataModel dm = new DataModel("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1","","");
//        System.out.println(dm.toString());
        //        NeuralNetwork nn = new NeuralNetwork(1.11, 0.11, 0.23, 1.41, 1.94, 0.0003, 2.016, 0.007,0.904, 1.58, 1.017, 0.102, 1.2);
//        NeuralNetwork nn = new NeuralNetwork(dm);
        //nn.readTestData100();
//        System.out.println(nn.e());

    }

}