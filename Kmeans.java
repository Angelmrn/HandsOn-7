import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class KMeans {
    private int k;
    private int maxIterations;
    private List<DataPoint> dataPoints = new ArrayList<>();
    private List<DataPoint> centroids = new ArrayList<>();

    public KMeans(int k, int maxIterations) {
        this.k = k;
        this.maxIterations = maxIterations;
    }

    public void addDataPoint(DataPoint point) {
        dataPoints.add(point);
    }

    public void Kmeans() {
        // Inicializar los centroides aleatoriamente
        initializeCentroids();
        for (int i = 0; i < maxIterations; i++) {// Itera hasta maxIterations
            assignClusters();// se asigna cada punto al cluster más cercano
            updateCentroids();// se actualiza la posición de los centroides
           
        }
    }
  

    private void initializeCentroids() {
        Random rand = new Random();
        for (int i = 0; i < k; i++) {
            int randomIndex = rand.nextInt(dataPoints.size());
            // Agregar un nuevo centroide en la posición de un punto de datos aleatorio
            centroids.add(new DataPoint(dataPoints.get(randomIndex).getFeatures().clone()));
        }
    }
    // asignar datos al cluster más cercano
    private void assignClusters() {
        for (DataPoint point : dataPoints) {
            double minDistance = Double.MAX_VALUE;
            int closestCluster = -1;

            for (int i = 0; i < centroids.size(); i++) {
                double distance = point.distanceTo(centroids.get(i));
                if (distance < minDistance) { // Si esta distancia es la menor encontrada
                    minDistance = distance;
                    closestCluster = i; // se acctualiza el cluster más cercano
                }
            }
            point.setClusterId(closestCluster); // se asigna el punto al cluster más cercano
        }
    }

    private void updateCentroids() {
        //sumas de las características de cada cluster
        double[][] newCentroids = new double[k][dataPoints.get(0).getFeatures().length];
        int[] pointsInCluster = new int[k]; // Array para contar los puntos en cada cluster
        //En cada dato acumular sus características en el cluster correspondiente
        for (DataPoint point : dataPoints) {
            int clusterId = point.getClusterId();
            double[] features = point.getFeatures();
            for (int i = 0; i < features.length; i++) {
                newCentroids[clusterId][i] += features[i];
            }
            pointsInCluster[clusterId]++;
        }

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < newCentroids[i].length; j++) {
                if (pointsInCluster[i] > 0) {
                    newCentroids[i][j] /= pointsInCluster[i];
                }
            }
            //Nuevo centroide
            centroids.set(i, new DataPoint(newCentroids[i]));
        }
    }

    public void printClusters() {
        int[] clusterSizes = new int[k];

        System.out.println("k = " + k);
        for (int i = 0; i < k; i++) {
            System.out.println();
            System.out.println("Cluster " + i + ":");
            int clusterSize = 0; 

            for (DataPoint point : dataPoints) {
                if (point.getClusterId() == i) {
                    clusterSize++;
                    
                    //Moastrar las características de cada cluster
                    /*for (double feature : point.getFeatures()) {
                        System.out.print(feature + "  ");
                    }
                    System.out.println(); */
                }
            }
            clusterSizes[i] = clusterSize; // Guardar el tamaño del clúster
            //System.out.println("Datos en el clúster " + i + ": " + clusterSize);
            System.out.println("Porcentaje de datos en el clúster " + i + ": " + (clusterSize * 100) / dataPoints.size() + "%");
            //Imprimir la posicion de los centroides
            DataPoint centroid = centroids.get(i);
            System.out.print("Posicion de Centroide: ");
            for (double feature : centroid.getFeatures()) {
            System.out.print(feature + "  ");
            }
            System.out.println();

        }
    }

    public double calculateWCSS() {
        double wcss = 0.0;
        for (DataPoint point : dataPoints) {
            int clusterId = point.getClusterId();
            DataPoint centroid = centroids.get(clusterId);
            wcss += Math.pow(point.distanceTo(centroid), 2);
        }
        return wcss;
    }
    
}

class DataPoint {
    private double[] features;
    private int clusterId = -1;

    public DataPoint(double[] features) {
        this.features = features;
    }

    public double[] getFeatures() {
        return features;
    }

    public int getClusterId() {
        return clusterId;
    }

    public void setClusterId(int clusterId) {
        this.clusterId = clusterId;
    }

    public double distanceTo(DataPoint other) {
        double sum = 0;
        for (int i = 0; i < features.length; i++) {
            sum += Math.pow(features[i] - other.features[i], 2);
        }
        return Math.sqrt(sum);
    }
}