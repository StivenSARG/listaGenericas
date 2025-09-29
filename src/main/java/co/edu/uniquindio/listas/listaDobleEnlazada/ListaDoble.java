package co.edu.uniquindio.listas.listaDobleEnlazada;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaDoble<T> implements Iterable<T> {
    private NodoDoble<T> cabeza;
    private NodoDoble<T> cola;
    private int tamaño;

    public ListaDoble() {
        cabeza = null;
        cola = null;
        tamaño = 0;
    }

    // ✅ agregar al final
    public void agregarFinal(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            nuevo.setAnterior(cola);
            cola = nuevo;
        }
        tamaño++;
    }

    // ✅ agregar en posición específica
    public void agregar(int indice, T dato) {
        if (indice < 0 || indice > tamaño) throw new IndexOutOfBoundsException();
        NodoDoble<T> nuevo = new NodoDoble<>(dato);

        if (indice == 0) { // al inicio
            nuevo.setSiguiente(cabeza);
            if (cabeza != null) cabeza.setAnterior(nuevo);
            cabeza = nuevo;
            if (cola == null) cola = nuevo;
        } else if (indice == tamaño) { // al final
            agregarFinal(dato);
            return;
        } else {
            NodoDoble<T> actual = obtenerNodo(indice);
            NodoDoble<T> anterior = actual.getAnterior();
            nuevo.setSiguiente(actual);
            nuevo.setAnterior(anterior);
            anterior.setSiguiente(nuevo);
            actual.setAnterior(nuevo);
        }
        tamaño++;
    }

    // ✅ obtener nodo
    private NodoDoble<T> obtenerNodo(int indice) {
        if (indice < 0 || indice >= tamaño) throw new IndexOutOfBoundsException();
        NodoDoble<T> aux = cabeza;
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
        NodoDoble<T> aux = cabeza;
        int indice = 0;
        while (aux != null) {
            if (aux.getDato().equals(valor)) return indice;
            aux = aux.getSiguiente();
            indice++;
        }
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
            cabeza.setAnterior(null);
        }
        tamaño--;
    }

    // ✅ eliminar último
    public void eliminarUltimo() {
        if (cola == null) return;
        if (cabeza == cola) {
            cabeza = cola = null;
        } else {
            cola = cola.getAnterior();
            cola.setSiguiente(null);
        }
        tamaño--;
    }

    // ✅ eliminar por valor
    public boolean eliminar(T valor) {
        NodoDoble<T> aux = cabeza;
        while (aux != null && !aux.getDato().equals(valor)) {
            aux = aux.getSiguiente();
        }
        if (aux == null) return false;

        if (aux == cabeza) eliminarPrimero();
        else if (aux == cola) eliminarUltimo();
        else {
            aux.getAnterior().setSiguiente(aux.getSiguiente());
            aux.getSiguiente().setAnterior(aux.getAnterior());
            tamaño--;
        }
        return true;
    }

    // ✅ modificar nodo (por índice)
    public void modificarNodo(int indice, T nuevoValor) {
        obtenerNodo(indice).setDato(nuevoValor);
    }

    // ✅ ordenar lista (requiere que T sea Comparable)
    @SuppressWarnings("unchecked")
    public void ordenarLista() {
        if (cabeza == null || cabeza.getSiguiente() == null) return;

        for (int i = 0; i < tamaño; i++) {
            NodoDoble<T> actual = cabeza;
            NodoDoble<T> siguiente = cabeza.getSiguiente();
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
        NodoDoble<T> aux = cabeza;
        while (aux != null) {
            System.out.print(aux.getDato() + " <-> ");
            aux = aux.getSiguiente();
        }
        System.out.println("null");
    }

    // ✅ borrar toda la lista
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
            private NodoDoble<T> actual = cabeza;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T dato = actual.getDato();
                actual = actual.getSiguiente();
                return dato;
            }
        };
    }
}
