package co.edu.uniquindio.listas.listaSimpleGenerica;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaSimple<T> implements Iterable<T> {
    private Nodo<T> cabeza;
    private int tamaño;

    public ListaSimple() {
        cabeza = null;
        tamaño = 0;
    }

    // ✅ agregar al final
    public void agregarFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<T> aux = cabeza;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }
        tamaño++;
    }

    // ✅ agregar en posición específica
    public void agregar(int indice, T dato) {
        if (indice < 0 || indice > tamaño) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        if (indice == 0) {
            nuevo.setSiguiente(cabeza);
            cabeza = nuevo;
        } else {
            Nodo<T> anterior = obtenerNodo(indice - 1);
            nuevo.setSiguiente(anterior.getSiguiente());
            anterior.setSiguiente(nuevo);
        }
        tamaño++;
    }

    // ✅ obtener valor de un nodo
    public T obtenerValorNodo(int indice) {
        return obtenerNodo(indice).getDato();
    }

    // ✅ obtener nodo
    private Nodo<T> obtenerNodo(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
        Nodo<T> aux = cabeza;
        for (int i = 0; i < indice; i++) {
            aux = aux.getSiguiente();
        }
        return aux;
    }

    // ✅ obtener posición de un nodo (por valor)
    public int obtenerPosicionNodo(T valor) {
        Nodo<T> aux = cabeza;
        int indice = 0;
        while (aux != null) {
            if (aux.getDato().equals(valor)) {
                return indice;
            }
            aux = aux.getSiguiente();
            indice++;
        }
        return -1; // no encontrado
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
        if (cabeza != null) {
            cabeza = cabeza.getSiguiente();
            tamaño--;
        }
    }

    // ✅ eliminar último
    public void eliminarUltimo() {
        if (cabeza == null) return;
        if (cabeza.getSiguiente() == null) {
            cabeza = null;
        } else {
            Nodo<T> aux = cabeza;
            while (aux.getSiguiente().getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(null);
        }
        tamaño--;
    }

    // ✅ eliminar por valor
    public boolean eliminar(T valor) {
        if (cabeza == null) return false;

        if (cabeza.getDato().equals(valor)) {
            cabeza = cabeza.getSiguiente();
            tamaño--;
            return true;
        }

        Nodo<T> aux = cabeza;
        while (aux.getSiguiente() != null && !aux.getSiguiente().getDato().equals(valor)) {
            aux = aux.getSiguiente();
        }

        if (aux.getSiguiente() != null) {
            aux.setSiguiente(aux.getSiguiente().getSiguiente());
            tamaño--;
            return true;
        }

        return false;
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
            Nodo<T> actual = cabeza;
            Nodo<T> siguiente = cabeza.getSiguiente();

            for (int j = 0; j < tamaño - 1; j++) {
                Comparable<T> a = (Comparable<T>) actual.getDato();
                T b = siguiente.getDato();

                if (a.compareTo(b) > 0) {
                    // intercambiar valores
                    T temp = actual.getDato();
                    actual.setDato(siguiente.getDato());
                    siguiente.setDato(temp);
                }

                actual = siguiente;
                siguiente = siguiente.getSiguiente();
            }
        }
    }
    // Método público para invertir la lista
    public void invertir() {
        if (cabeza == null || cabeza.getSiguiente() == null) {
            return; // lista vacía o con un solo elemento
        }
        cabeza = invertirRecursivo(cabeza, null);
    }

    // Método recursivo privado
    private Nodo<T> invertirRecursivo(Nodo<T> actual, Nodo<T> anterior) {
        if (actual == null) {
            return anterior; // nueva cabeza
        }
        Nodo<T> siguiente = actual.getSiguiente();
        actual.setSiguiente(anterior);
        return invertirRecursivo(siguiente, actual);
    }
    // ✅ imprimir lista
    public void imprimirLista() {
        Nodo<T> aux = cabeza;
        while (aux != null) {
            System.out.print(aux.getDato() + " -> ");
            aux = aux.getSiguiente();
        }
        System.out.println("null");
    }

    // ✅ borrar toda la lista
    public void borrarLista() {
        cabeza = null;
        tamaño = 0;
    }

    // ✅ tamaño de la lista
    public int tamaño() {
        return tamaño;
    }


    // ✅ iterator
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = cabeza;

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

