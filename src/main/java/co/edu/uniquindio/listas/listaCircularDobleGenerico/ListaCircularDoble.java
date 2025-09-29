package co.edu.uniquindio.listas.listaCircularDobleGenerico;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaCircularDoble<T extends Comparable<T>> implements Iterable<T> {
    private NodoDobleCircular<T> cabeza;
    private int tamaño;

    public ListaCircularDoble() {
        cabeza = null;
        tamaño = 0;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    public int tamaño() {
        return tamaño;
    }

    private boolean indiceValido(int indice) {
        return indice >= 0 && indice < tamaño;
    }

    // --- Agregar al final ---
    public void agregarFinal(T dato) {
        NodoDobleCircular<T> nuevo = new NodoDobleCircular<>(dato);
        if (estaVacia()) {
            cabeza = nuevo;
        } else {
            NodoDobleCircular<T> ultimo = cabeza.anterior;
            nuevo.siguiente = cabeza;
            nuevo.anterior = ultimo;
            ultimo.siguiente = nuevo;
            cabeza.anterior = nuevo;
        }
        tamaño++;
    }

    // --- Agregar en posición específica ---
    public void agregar(int indice, T dato) {
        if (indice < 0 || indice > tamaño) throw new IndexOutOfBoundsException("Índice inválido");

        if (indice == tamaño) {
            agregarFinal(dato);
            return;
        }

        NodoDobleCircular<T> nuevo = new NodoDobleCircular<>(dato);
        if (indice == 0) {
            if (estaVacia()) {
                cabeza = nuevo;
            } else {
                NodoDobleCircular<T> ultimo = cabeza.anterior;
                nuevo.siguiente = cabeza;
                nuevo.anterior = ultimo;
                ultimo.siguiente = nuevo;
                cabeza.anterior = nuevo;
                cabeza = nuevo;
            }
        } else {
            NodoDobleCircular<T> actual = obtenerNodo(indice);
            NodoDobleCircular<T> anterior = actual.anterior;
            nuevo.siguiente = actual;
            nuevo.anterior = anterior;
            anterior.siguiente = nuevo;
            actual.anterior = nuevo;
        }
        tamaño++;
    }

    // --- Obtener valor de un nodo ---
    public T obtenerValorNodo(int indice) {
        return obtenerNodo(indice).dato;
    }

    // --- Obtener nodo ---
    private NodoDobleCircular<T> obtenerNodo(int indice) {
        if (!indiceValido(indice)) throw new IndexOutOfBoundsException("Índice inválido");
        NodoDobleCircular<T> actual = cabeza;
        for (int i = 0; i < indice; i++) actual = actual.siguiente;
        return actual;
    }

    // --- Obtener posición de un nodo por valor ---
    public int obtenerPosicionNodo(T dato) {
        if (estaVacia()) return -1;
        NodoDobleCircular<T> actual = cabeza;
        for (int i = 0; i < tamaño; i++) {
            if (actual.dato.equals(dato)) return i;
            actual = actual.siguiente;
        }
        return -1;
    }

    // --- Eliminar primero ---
    public void eliminarPrimero() {
        if (estaVacia()) return;
        if (tamaño == 1) {
            cabeza = null;
        } else {
            NodoDobleCircular<T> ultimo = cabeza.anterior;
            cabeza = cabeza.siguiente;
            cabeza.anterior = ultimo;
            ultimo.siguiente = cabeza;
        }
        tamaño--;
    }

    // --- Eliminar último ---
    public void eliminarUltimo() {
        if (estaVacia()) return;
        if (tamaño == 1) {
            cabeza = null;
        } else {
            NodoDobleCircular<T> ultimo = cabeza.anterior;
            NodoDobleCircular<T> penultimo = ultimo.anterior;
            penultimo.siguiente = cabeza;
            cabeza.anterior = penultimo;
        }
        tamaño--;
    }

    // --- Eliminar por valor ---
    public void eliminar(T dato) {
        if (estaVacia()) return;
        int pos = obtenerPosicionNodo(dato);
        if (pos == -1) return;
        eliminarEnPosicion(pos);
    }

    // --- Eliminar en posición específica ---
    public void eliminarEnPosicion(int indice) {
        if (!indiceValido(indice)) throw new IndexOutOfBoundsException("Índice inválido");
        if (indice == 0) {
            eliminarPrimero();
        } else if (indice == tamaño - 1) {
            eliminarUltimo();
        } else {
            NodoDobleCircular<T> actual = obtenerNodo(indice);
            NodoDobleCircular<T> anterior = actual.anterior;
            NodoDobleCircular<T> siguiente = actual.siguiente;
            anterior.siguiente = siguiente;
            siguiente.anterior = anterior;
            tamaño--;
        }
    }

    // --- Modificar nodo ---
    public void modificarNodo(int indice, T nuevoDato) {
        NodoDobleCircular<T> nodo = obtenerNodo(indice);
        nodo.dato = nuevoDato;
    }

    // --- Ordenar lista (burbuja) ---
    public void ordenarLista() {
        if (tamaño <= 1) return;
        boolean cambiado;
        do {
            cambiado = false;
            NodoDobleCircular<T> actual = cabeza;
            for (int i = 0; i < tamaño - 1; i++) {
                if (actual.dato.compareTo(actual.siguiente.dato) > 0) {
                    T temp = actual.dato;
                    actual.dato = actual.siguiente.dato;
                    actual.siguiente.dato = temp;
                    cambiado = true;
                }
                actual = actual.siguiente;
            }
        } while (cambiado);
    }

    // --- Imprimir lista ---
    public void imprimirLista() {
        if (estaVacia()) {
            System.out.println("Lista vacía");
            return;
        }
        NodoDobleCircular<T> actual = cabeza;
        for (int i = 0; i < tamaño; i++) {
            System.out.print(actual.dato + " <-> ");
            actual = actual.siguiente;
        }
        System.out.println("(vuelve al inicio)");
    }

    // --- Borrar lista ---
    public void borrarLista() {
        cabeza = null;
        tamaño = 0;
    }

    // --- Iterator ---
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoDobleCircular<T> actual = cabeza;
            private int contador = 0;

            @Override
            public boolean hasNext() {
                return contador < tamaño;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T dato = actual.dato;
                actual = actual.siguiente;
                contador++;
                return dato;
            }
        };
    }
}
