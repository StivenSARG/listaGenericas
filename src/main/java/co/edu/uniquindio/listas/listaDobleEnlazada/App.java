package co.edu.uniquindio.listas.listaDobleEnlazada;

public class App {
    public static void main(String[] args) {
        ListaDoble<Integer> lista = new ListaDoble<>();
        lista.agregarFinal(5);
        lista.agregarFinal(3);
        lista.agregar(1, 7); // en índice 1
        lista.imprimirLista();

        lista.modificarNodo(0, 10);
        lista.imprimirLista();

        lista.ordenarLista();
        lista.imprimirLista();

        lista.eliminar(7);
        lista.imprimirLista();
    }
}

