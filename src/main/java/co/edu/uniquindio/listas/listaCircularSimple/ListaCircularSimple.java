package co.edu.uniquindio.listas.listaCircularSimple;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaCircularSimple<T> implements Iterable<T> {
    private NodoCircular<T> cabeza;
    private NodoCircular<T> cola;
    private int tamaño;

    public ListaCircularSimple() {
        cabeza = null;
        cola = null;
        tamaño = 0;
    }

    // ✅ agregar al final
    public void agregarFinal(T dato) {
        NodoCircular<T> nuevo = new NodoCircular<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
            cola.setSiguiente(cabeza); // apunta al primero
        } else {
            cola.setSiguiente(nuevo);
            cola = nuevo;
            cola.setSiguiente(cabeza);
        }
        tamaño++;
    }

    // ✅ agregar en posición específica
    public void agregar(int indice, T dato) {
        if (indice < 0 || indice > tamaño) throw new IndexOutOfBoundsException();
        NodoCircular<T> nuevo = new NodoCircular<>(dato);

        if (indice == 0) {
            if (cabeza == null) {
                cabeza = nuevo;
                cola = nuevo;
                cola.setSiguiente(cabeza);
            } else {
                nuevo.setSiguiente(cabeza);
                cabeza = nuevo;
                cola.setSiguiente(cabeza);
            }
        } else if (indice == tamaño) {
            agregarFinal(dato);
            return;
        } else {
            NodoCircular<T> anterior = obtenerNodo(indice - 1);
            nuevo.setSiguiente(anterior.getSiguiente());
            anterior.setSiguiente(nuevo);
        }
        tamaño++;
    }

    // ✅ obtener nodo
    private NodoCircular<T> obtenerNodo(int indice) {
        if (indice < 0 || indice >= tamaño) throw new IndexOutOfBoundsException();
        NodoCircular<T> aux = cabeza;
        for (int i = 0; i < indice; i++) {
            aux = aux.getSiguiente();
        }
        return aux;
    }

    // ✅ obtener valor de un nodo
    public T obtenerValorNodo(int indice) {
        return obtenerNodo(indice).getDato();
    }

    // ✅ obtener posición de un nodo (por valor)
    public int obtenerPosicionNodo(T valor) {
        if (cabeza == null) return -1;
        NodoCircular<T> aux = cabeza;
        int indice = 0;
        do {
            if (aux.getDato().equals(valor)) return indice;
            aux = aux.getSiguiente();
            indice++;
        } while (aux != cabeza);
        return -1;
    }

    // ✅ índice válido
    public boolean indiceValido(int indice) {
        return indice >= 0 && indice < tamaño;
    }

    // ✅ lista vacía
    public boolean estaVacia() {
        return tamaño == 0;
    }

    // ✅ eliminar primero
    public void eliminarPrimero() {
        if (cabeza == null) return;

        if (cabeza == cola) {
            cabeza = cola = null;
        } else {
            cabeza = cabeza.getSiguiente();
            cola.setSiguiente(cabeza);
        }
        tamaño--;
    }

    // ✅ eliminar último
    public void eliminarUltimo() {
        if (cabeza == null) return;

        if (cabeza == cola) {
            cabeza = cola = null;
        } else {
            NodoCircular<T> aux = cabeza;
            while (aux.getSiguiente() != cola) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(cabeza);
            cola = aux;
        }
        tamaño--;
    }

    // ✅ eliminar por valor
    public boolean eliminar(T valor) {
        if (cabeza == null) return false;

        if (cabeza.getDato().equals(valor)) {
            eliminarPrimero();
            return true;
        }

        NodoCircular<T> aux = cabeza;
        while (aux.getSiguiente() != cabeza && !aux.getSiguiente().getDato().equals(valor)) {
            aux = aux.getSiguiente();
        }

        if (aux.getSiguiente() == cabeza) return false;

        if (aux.getSiguiente() == cola) {
            cola = aux;
        }

        aux.setSiguiente(aux.getSiguiente().getSiguiente());
        tamaño--;
        return true;
    }

    // ✅ modificar nodo
    public void modificarNodo(int indice, T nuevoValor) {
        obtenerNodo(indice).setDato(nuevoValor);
    }

    // ✅ ordenar lista (requiere que T sea Comparable)
    @SuppressWarnings("unchecked")
    public void ordenarLista() {
        if (cabeza == null || cabeza.getSiguiente() == cabeza) return;

        for (int i = 0; i < tamaño; i++) {
            NodoCircular<T> actual = cabeza;
            NodoCircular<T> siguiente = cabeza.getSiguiente();
            for (int j = 0; j < tamaño - 1; j++) {
                Comparable<T> a = (Comparable<T>) actual.getDato();
                T b = siguiente.getDato();
                if (a.compareTo(b) > 0) {
                    T temp = actual.getDato();
                    actual.setDato(siguiente.getDato());
                    siguiente.setDato(temp);
                }
                actual = siguiente;
                siguiente = siguiente.getSiguiente();
            }
        }
    }

    // ✅ imprimir lista
    public void imprimirLista() {
        if (cabeza == null) {
            System.out.println("Lista vacía");
            return;
        }
        NodoCircular<T> aux = cabeza;
        do {
            System.out.print(aux.getDato() + " -> ");
            aux = aux.getSiguiente();
        } while (aux != cabeza);
        System.out.println("(vuelve al inicio)");
    }

    // ✅ borrar lista completa
    public void borrarLista() {
        cabeza = cola = null;
        tamaño = 0;
    }

    // ✅ tamaño
    public int tamaño() {
        return tamaño;
    }

    // ✅ iterator
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoCircular<T> actual = cabeza;
            private boolean primeraVuelta = (cabeza != null);

            @Override
            public boolean hasNext() {
                return actual != null && primeraVuelta;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T dato = actual.getDato();
                actual = actual.getSiguiente();
                if (actual == cabeza) primeraVuelta = false;
                return dato;
            }
        };
    }
}
