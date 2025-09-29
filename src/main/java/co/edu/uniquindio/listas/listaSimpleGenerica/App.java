package co.edu.uniquindio.listas.listaSimpleGenerica;

public class App {
    public static void main(String[] args) {
        ListaSimple<Integer> lista = new ListaSimple<>();
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

        // 🔹 Invertir lista
        System.out.println("Lista invertida:");
        lista.invertir();
        lista.imprimirLista();
    }
}


