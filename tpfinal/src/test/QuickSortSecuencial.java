package test;

import java.util.Random;

public class QuickSortSecuencial {

	 // Función que realiza la partición del arreglo
    static int particionar(int[] arr, int inicio, int fin) {
        
        // Se elige el último elemento como pivote
        int pivote = arr[fin];
        
        // Índice del elemento más pequeño (inicialmente uno antes del inicio)
        int i = inicio - 1;

        // Recorre desde el índice de inicio hasta uno antes del final
        for (int j = inicio; j <= fin - 1; j++) {
            // Si el elemento actual es menor que el pivote
            if (arr[j] < pivote) {
                i++; // Incrementa el índice del menor elemento
                intercambiar(arr, i, j); // Intercambia el actual con el menor
            }
        }

        // Coloca el pivote en su posición final
        intercambiar(arr, i + 1, fin);

        // Retorna el índice donde se ubicó el pivote
        return i + 1;
    }

    
    // Función para intercambiar dos elementos en el arreglo
    static void intercambiar(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    
    // Función principal de QuickSort
    static void quickSort(int[] arr, int inicio, int fin) {
        if (inicio < fin) {
            // Se obtiene el índice de partición (posición final del pivote)
            int indicePivote = particionar(arr, inicio, fin);

            // Llamada recursiva para la sublista izquierda del pivote
            quickSort(arr, inicio, indicePivote - 1);

            // Llamada recursiva para la sublista derecha del pivote
            quickSort(arr, indicePivote + 1, fin);
        }
    }
    
    
    // Genera un arreglo de n elementos aleatorios en el rango [0, rango)
    static int[] generarArregloAleatorio(int n, int rango) {
        Random random = new Random();
        int[] arreglo = new int[n];
        for (int i = 0; i < n; i++) {
            arreglo[i] = random.nextInt(rango);
        }
        return arreglo;
    }
    
    // Verifica si el arreglo está ordenado
    static boolean estaOrdenado(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n = 100_000_000;
        int[] arreglo = generarArregloAleatorio(n, 100_000_000); //Se genera el array con numeros aleatorios
        
        long tiempoInicio = System.currentTimeMillis();
        
       
        // Llamada al método de ordenamiento
        quickSort(arreglo, 0, n - 1);
        
        
        long tiempoFin = System.currentTimeMillis();
        
        if (!estaOrdenado(arreglo)) {
            System.out.println("Error: el arreglo no está ordenado.");
        }//Si el arreglo esta ordenado nos muestra un error
        
        // Se imprime el arreglo ordenado
        /*System.out.println("Arreglo ordenado:");
        for (int valor : arreglo) {
            System.out.print(valor + " ");
        }*/
        System.out.println("----------------------------------");
        System.out.println("Ejecución de QuickSort Secuencial");
        System.out.println("Tiempo: " + (tiempoFin - tiempoInicio) + " milisegundos");
	}

}
