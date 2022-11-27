package Lab_1_perseptron;

import java.util.List;
import java.util.Scanner;

public class ReadCSVWithScanner {
    public static void main(String[] args) {
        String csvFile = "src/VAG.csv";
//        String csvFile1 = "src/VAG1.csv";
        List list = new ReadFile().getInfoFile(csvFile);
        List ab = new WorkList().getAB(list);
        float a = (float) ab.get(0);
        float b = (float) ab.get(1);
        float x = 1;
        for (int i = 0; i < 600; i++) {
            Perseptron perseptron = new Perseptron();
            List list1 = perseptron.updateWeight(i+1,(float)list.get(i), (a * x) + b, 0.1F, 0.2F);
            System.out.print(list1.get(0));
            System.out.print("/");
            System.out.println(list1.get(1));
            x++;
        }
//        List list1 = new ReadFile().getInfoFile(csvFile1);
//        for (int i = 0; i < list1.size(); i++) {
//            float trend = (a * x) + b;
//            if ((float)list1.get(i) > trend){
//                    System.out.println("Введеное число больше линии тренда на: " + ((float)list1.get(i) - trend));
//                }
//                else {
//                    System.out.println("Введеное число ниже линии тренда на: " + (trend - (float)list1.get(i)));
//                }
//                x++;
//        }




//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Если хотите закончить подсчет введите 0");
//        System.out.println("Введите число:   ");
//        if (scanner.hasNextFloat()) {
//            float number = scanner.nextFloat();
//            while (number != 0) {
//                float trend = (a * x) + b;
//                if (number > trend){
//                    System.out.println("Введеное число больше линии тренда на: " + (number - trend));
//                }
//                else {
//                    System.out.println("Введеное число ниже линии тренда на: " + (trend - number));
//                }
//                x++;
//                number = scanner.nextFloat();
//            }
//        }
//        scanner.close();
    }
}
