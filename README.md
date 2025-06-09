# TP-FINAL-CONCURRENTE
Este repositorio contiene la implementación y análisis comparativo del algoritmo de ordenamiento **QuickSort** en sus dos variantes: **secuencial** y **concurrente**. El proyecto fue desarrollado como parte del Trabajo Práctico Final de la materia *Programación Concurrente*.

## Descripción del proyecto

El objetivo del trabajo es analizar el comportamiento y el rendimiento de QuickSort al ser implementado de forma secuencial y utilizando concurrencia mediante hilos en Java. Para ello:

- Se implementó QuickSort secuencial.
- Se implementó QuickSort concurrente extendiendo la clase `Thread` y controlando la creación de hilos mediante un parámetro de **profundidad**.
- Se realizaron pruebas con distintos volúmenes de datos (100 mil hasta 100 millones).
- Se compararon los tiempos de ejecución en cada caso.
- Se documentaron ventajas y desventajas de cada enfoque.

## Tecnologías utilizadas

- **Lenguaje:** Java 8+
- **Entorno de desarrollo:** Eclipse
- **Librerías utilizadas:**  
  - `java.lang.Thread`  
  - `java.util.Random`  
  - `Runtime.getRuntime()` para detección de núcleos disponibles

 ## Implementación concurrente
 La versión concurrente divide el arreglo utilizando el método particionar() y lanza dos nuevos hilos por cada partición, siempre y cuando no se haya alcanzado la profundidad máxima definida por log2(núcleos disponibles). Esto evita la creación excesiva de hilos, mejorando el rendimiento.
Cuando se alcanza la profundidad máxima, el algoritmo continúa de forma secuencial con quickSortSecuencial().

## Resultados esperados
El algoritmo concurrente muestra ventajas en tiempo de ejecución a partir de aproximadamente 10 millones de datos. En volúmenes menores, la sobrecarga de creación de hilos puede hacer que el secuencial sea más eficiente

## Fuentes consultadas
GeeksforGeeks. (s.f.-a). Quick Sort Algorithm. https://www.geeksforgeeks.org/quick-sort-algorithm/

GeeksforGeeks. (s.f.-b). Implement Runnable vs Extend Thread in Java. https://www.geeksforgeeks.org/implement-runnable-vs-extend-thread-in-java/

Built In. (2025). Quicksort Algorithm: An Overview. https://builtin.com/articles/quicksort

Wikipedia. (2025). Quicksort. https://es.wikipedia.org/wiki/Quicksort

Hayartt. (s.f.). Parallel QuickSort in Java. https://github.com/Hayartt/parallel-quicksort

Youcademy. (s.f.). Advantages and Disadvantages of Quick Sort Algorithm. https://youcademy.org/quick-sort-advantages-disadvantages/
