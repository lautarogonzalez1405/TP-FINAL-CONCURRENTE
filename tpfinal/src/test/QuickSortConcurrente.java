package test;

import java.util.Random;

//Clase principal que extiende de Thread para permitir ejecución concurrente
public class QuickSortConcurrente extends Thread{
	
	private int[] array;
    private int inicio, fin;
    private int profundidad;
    
    // Constructor: recibe el arreglo a ordenar, los índices y la profundidad de recursión
    public QuickSortConcurrente(int[] array, int inicio, int fin, int profundidad) {
        this.array = array;
        this.inicio = inicio;
        this.fin = fin;
        this.profundidad = profundidad;
    }
    
    // Método que se ejecuta cuando el hilo comienza
    @Override
    public void run() {
        quickSort(array, inicio, fin, profundidad);
    }

    // Intercambia dos elementos en el arreglo
    private static void intercambiar(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // Función que divide el arreglo en dos partes según un pivote
    private static int particionar(int[] arr, int inicio, int fin) {
        int pivote = arr[fin];
        int i = inicio - 1;
        for (int j = inicio; j < fin; j++) {
            if (arr[j] <= pivote) {
                intercambiar(arr, ++i, j);
            }
        }
        intercambiar(arr, ++i, fin);
        return i;
    }

    // QuickSort con control de profundidad para gestionar la creación de hilos
    private static void quickSort(int[] arr, int inicio, int fin, int profundidad) {
        if (inicio >= fin) return;

        int pivote = particionar(arr, inicio, fin);
        
        // Si todavía hay profundidad disponible, se crean dos nuevos hilos
        if (profundidad > 0) {
            Thread t1 = new QuickSortConcurrente(arr, inicio, pivote - 1, profundidad - 1);
            Thread t2 = new QuickSortConcurrente(arr, pivote + 1, fin, profundidad - 1);
            // Se inician los hilos
            t1.start();
            t2.start();
            try {
            	// Se espera a que ambos hilos terminen
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
        	// Si se alcanzó la profundidad máxima, se continúa de forma secuencial
            quickSortSecuencial(arr, inicio, pivote - 1);
            quickSortSecuencial(arr, pivote + 1, fin);
        }
    }

    // Implementación secuencial de QuickSort utilizada cuando no se crean más hilos
    private static void quickSortSecuencial(int[] arr, int inicio, int fin) {
        if (inicio >= fin) return;
        int pivote = particionar(arr, inicio, fin);
        quickSortSecuencial(arr, inicio, pivote - 1);
        quickSortSecuencial(arr, pivote + 1, fin);
    }

    // Genera un arreglo de tamaño n con valores aleatorios en el rango indicado
    private static int[] generarArregloAleatorio(int n, int rango) {
        Random random = new Random();
        int[] arreglo = new int[n];
        for (int i = 0; i < n; i++) {
            arreglo[i] = random.nextInt(rango);
        }
        return arreglo;
    }

    // Verifica si el arreglo está ordenado de forma ascendente
    private static boolean estaOrdenado(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
    // Método principal que inicia la ejecución del algoritmo
	public static void main(String[] args) {
		int n = 100_000_000;
		// Se genera un arreglo aleatorio con n elementos
        int[] arreglo = generarArregloAleatorio(n, 100_000_000);
        
        // Se detecta la cantidad de núcleos disponibles en la CPU
        int nucleos = Runtime.getRuntime().availableProcessors();
        System.out.println("Núcleos disponibles: " + nucleos);

        // Se calcula la profundidad máxima en base log2(núcleos)
        int profundidadMax = (int) (Math.log(nucleos) / Math.log(2)); // log2(nucleos)

        long tiempoInicio = System.currentTimeMillis();
        
        // Se crea y arranca el primer hilo principal con todo el arreglo
        QuickSortConcurrente tarea = new QuickSortConcurrente(arreglo, 0, arreglo.length - 1, profundidadMax);
        tarea.start();
        
        // Se espera a que termine
        try {
            tarea.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long tiempoFin = System.currentTimeMillis();

        // Se verifica que el arreglo haya quedado ordenado correctamente
        if (!estaOrdenado(arreglo)) {
            System.out.println("Error: el arreglo no está ordenado correctamente.");
        }

        // Se muestra el tiempo total de ejecución
        System.out.println("----------------------------------");
        System.out.println("Tiempo de ejecución: " + (tiempoFin - tiempoInicio) + " milisegundos");
    }
		
}


