import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PairsConverter {

    public static void main(String[] args) {
        PairsConverter pairsConverter = new PairsConverter();

        File file = pairsConverter.convertCurrency(
            "C:/Users/tron5/IdeaProjects/TCandCA 2/TCandCA/USDRUB_220606_221106.txt",
            "C:/Users/tron5/IdeaProjects/TCandCA 2/TCandCA/USDRUB_220606_221106.txt");
    }

    public File convertCurrency(String from, String to) {
        try {
            List<String> resultPair = new ArrayList<>();
            List<String> pairForm = Files.readAllLines(Paths.get(from));
            List<String> pairTo = Files.readAllLines(Paths.get(to));

            if (pairForm.size() != pairTo.size()) {
                throw new IllegalArgumentException("Size is different");
            }

            resultPair.add("<TICKER>;<PER>;<DATE>;<TIME>;<OPEN>;<HIGH>;<LOW>;<CLOSE>;<VOL>");

            String tickerFrom = pairForm.get(1).split(";")[0];
            String tickerTo = pairTo.get(1).split(";")[0];

            String tickerResult = tickerFrom.substring(3) + tickerTo.substring(3);

            String resultFileName = "C:/Use" + to.substring(6).split("TCandCA/")[0] + "TCandCA/" + tickerResult + ".txt";

            for (int i = 1; i < pairForm.size(); i++) {
                String[] pFrom = pairForm.get(i).split(";");
                String[] pTo = pairTo.get(i).split(";");

                double open_ar = Double.parseDouble(pTo[4]) / Double.parseDouble(pFrom[4]);
                double close_ar = Double.parseDouble(pTo[7]) / Double.parseDouble(pFrom[7]);
                resultPair.add(
                    tickerResult + ";D;" + pTo[2] + ";0;" + String.format("%.7f", open_ar)
                        .replaceAll(",", ".") + ";" +
                        ";" +
                        ";" +
                        String.format("%.7f", close_ar).replaceAll(",", ".") + ";");

                Files.write(Paths.get(resultFileName), resultPair);
            }
            return new File(resultFileName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }
}
