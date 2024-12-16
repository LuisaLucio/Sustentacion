package gestiondedatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author LUISA LUCIO && VALENTINA RUBIO
 */
public class GestorDatos {
    private List<Integer> lista = new ArrayList<>();
    private long tiempoEjecucion;
    private int comparaciones, intercambios;
    
    public GestorDatos() {
        tiempoEjecucion = 0;
        comparaciones = 0;
        intercambios = 0;
    }
    
    public void añadirElemento(Integer elemento) {
        lista.add(elemento);
    }

    public List<Integer> verElementosLista() {
        return lista;
    }

    public void eliminarElemento(Integer elemento) {
        lista.remove(elemento);
    }
    
    public List<Integer> getLista() {
        return lista;
    }
    
    public void cargarArchivo(String rutaArchivo) {
        lista.clear(); 
        try {
            File archivo = new File(rutaArchivo);
            Scanner scanner = new Scanner(archivo); 
            while (scanner.hasNextInt()) {
                lista.add(scanner.nextInt()); 
            }
            scanner.close(); 
            JOptionPane.showMessageDialog(null, "Los datos se han cargado de manera adecuada desde el archivo.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: El archivo no fue encontrado.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
        }
    }  
    
    public void añadirElementosAleatorios(int cantidad) {
        lista.clear();
        for (int i = 0; i < cantidad; i++) {
            lista.add((int) (Math.random() * 100 + 1));
        }
    }
    
 
    public String mostrarEstadisticas () {
        return "Estadísticas de Ejecución: \n"
                + "Tiempo de ejecución: " + tiempoEjecucion / 1_000_000 + " ms\n"
                + "Comparaciones realizadas: " + comparaciones + "\n"
                + "Intercambios realizados: " + intercambios;   
    }
    
    public void resetEstadisticas() {
        tiempoEjecucion = 0;
        comparaciones = 0;
        intercambios = 0;
    }

    
    public void bubbleSort() {
        long startTime = System.nanoTime(); 
        int n = lista.size();
        comparaciones = 0; 
        intercambios = 0;   
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comparaciones++;
                if (lista.get(j) > lista.get(j + 1)) {
                    Collections.swap(lista, j, j + 1);
                    intercambios++;
                }
            }
        }
        tiempoEjecucion = System.nanoTime() - startTime;
    }

    public void selectionSort() {
        long startTime = System.nanoTime(); 
        int n = lista.size();
        comparaciones = 0; 
        intercambios = 0;  
        
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                comparaciones++;
                if (lista.get(j) < lista.get(minIdx)) {
                    minIdx = j;
                }
            }
            Collections.swap(lista, i, minIdx);
            intercambios++;
        }
        tiempoEjecucion = System.nanoTime() - startTime;
    }

    public void insertionSort() {
        long startTime = System.nanoTime(); 
        int n = lista.size();
        comparaciones = 0; 
        intercambios = 0;   
        
        for (int i = 1; i < n; i++) {
            int key = lista.get(i);
            int j = i - 1;
            while (j >= 0 && lista.get(j) > key) {
                comparaciones++;
                lista.set(j + 1, lista.get(j));
                j--;
            }
            lista.set(j + 1, key);
            intercambios++;
        }
        tiempoEjecucion = System.nanoTime() - startTime; 
    }

    
    public void mergeSort() {
        long startTime = System.nanoTime(); 
        comparaciones = 0; 
        intercambios = 0;   
        lista = mergeSortRec(lista);
        tiempoEjecucion = System.nanoTime() - startTime; 
    }

    private List<Integer> mergeSortRec(List<Integer> input) {
        if (input.size() <= 1) {
            return input;
        }
        int mid = input.size() / 2;
        List<Integer> left = mergeSortRec(input.subList(0,0));
        List<Integer> right = mergeSortRec(input.subList(mid, input.size()));
        return merge(left, right);
    }

    private List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> merged = new ArrayList<>();
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            comparaciones++;
            if (left.get(i) <= right.get(j)) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }
        merged.addAll(left.subList(i, left.size()));
        merged.addAll(right.subList(j, right.size()));
        intercambios++;
        return merged;
    }

    // Quick Sort
    public void quickSort() {
        long startTime = System.nanoTime(); 
        comparaciones = 0; 
        intercambios = 0;   
        quickSortRec(0, lista.size() - 1);
        tiempoEjecucion = System.nanoTime() - startTime; 
    }

    private void quickSortRec(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSortRec(low, pi - 1);
            quickSortRec(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        int pivot = lista.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            comparaciones++;
            if (lista.get(j) <= pivot) {
                i++;
                Collections.swap(lista, i, j);
               intercambios++;
            }
        }
        Collections.swap(lista, i + 1, high);
        intercambios++;
        return i + 1;
    }

    // Heap Sort
    public void heapSort() {
        long startTime = System.nanoTime(); 
        comparaciones = 0; 
        intercambios = 0; 
        int n = lista.size();
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            Collections.swap(lista, 0, i);
            intercambios++;
            heapify(i, 0);
        }
        tiempoEjecucion = System.nanoTime() - startTime; 
    }

    private void heapify(int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && lista.get(left) > lista.get(largest)) {
            largest = left;
        }
        if (right < n && lista.get(right) > lista.get(largest)) {
            largest = right;
        }
        if (largest != i) {
            Collections.swap(lista, i, largest);
            intercambios++;
            heapify(n, largest);
        }
    }
}
