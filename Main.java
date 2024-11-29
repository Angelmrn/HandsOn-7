//Moran Flores Angel Daniel
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        Data data = new Data();
        data.loadTrainingData();
        ArrayList<DataPoint> trainingData = data.getTrainingData();
        ArrayList<Double> wcssValues = new ArrayList<>();
        int maxClusters = 10;

        for(int k = 1; k <= maxClusters; k++){
            System.out.println("-------------------------------------------");
            System.out.println("Corrida: " + k + ":");
            System.out.println("-------------------------------------------");
            KMeans kmeans = new KMeans(3, 100);  // 3 clusters, 100 iterations max
            // Agregar datos cargados desde el archivo CSV
            for (DataPoint dp : trainingData) {
            kmeans.addDataPoint(dp);
        }
    
            kmeans.Kmeans();
            kmeans.printClusters();
            System.out.println();
            double wcss = kmeans.calculateWCSS();
            wcssValues.add(wcss);
            System.out.println("k = " + k + ", WCSS = " + wcss);
        }
    
        // Imprimir la lista de WCSS para análisis
        System.out.println("\nWCSS values:");
        for (int i = 0; i < wcssValues.size(); i++) {
            System.out.println("k = " + (i + 1) + ", WCSS = " + wcssValues.get(i));
        }
    
        // Opcional: Identificar automáticamente el codo usando diferencias relativas
        System.out.println("\nSugerencia de k óptimo:");
        for (int i = 1; i < wcssValues.size() - 1; i++) {
            double drop1 = wcssValues.get(i - 1) - wcssValues.get(i);
            double drop2 = wcssValues.get(i) - wcssValues.get(i + 1);
            if (drop1 > drop2 * 1.5) { // Heurística simple
            System.out.println("-------------------------------------------");
            System.out.println("k óptimo sugerido: " + (i + 1));
            System.out.println("-------------------------------------------");
                break;
            }
        }
    }
    

}
