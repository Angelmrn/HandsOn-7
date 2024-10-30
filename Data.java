import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Data {
    private ArrayList<DataPoint> trainingData;

    public Data() {
        this.trainingData = new ArrayList<>();
    }

    public void loadTrainingData(){
        String filePath = "C:\\Users\\PC DPC ELITE\\Desktop\\Tec\\Aprenidzaje Automatico\\HandsOn-7\\Mall_Customers.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    //salatar la primera linea
                    continue;
                }
                String[] values = line.split(",");
                double[] features = new double[] {
                    //Seleciconar los valores de las columnas que se desean utilizar
                    Double.parseDouble(values[2]),
                    Double.parseDouble(values[3]),
                    Double.parseDouble(values[4]),
                
                };

                trainingData.add(new DataPoint(features));

            }
        } catch (IOException excepcion) {
            excepcion.printStackTrace();
        }
    }

    public ArrayList<DataPoint> getTrainingData() {
        return trainingData;
    }
}

