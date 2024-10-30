import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Data data = new Data();
        data.loadTrainingData();
        ArrayList<DataPoint> trainingData = data.getTrainingData();

        for(int run = 1; run <= 3; run++){
            System.out.println("-------------------------------------------");
            System.out.println("Corrida: " + run + ":");
            System.out.println("-------------------------------------------");
            KMeans kmeans = new KMeans(3, 100);  // 3 clusters, 100 iterations max

        // Agregar datos cargados desde el archivo CSV
        for (DataPoint dp : trainingData) {
            kmeans.addDataPoint(dp);
        }
        kmeans.Kmeans();
        kmeans.printClusters();
        System.out.println();
        
    }
    }
}
