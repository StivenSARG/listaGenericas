package co.edu.uniquindio.listas.listaCircularDobleGenerico;

public class NodoDobleCircular<T> {
    T dato;
    NodoDobleCircular<T> siguiente;
    NodoDobleCircular<T> anterior;

    public NodoDobleCircular(T dato) {
        this.dato = dato;
        this.siguiente = this; // circular
        this.anterior = this;  // circular
    }
}

