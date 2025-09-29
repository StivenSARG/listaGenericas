package co.edu.uniquindio.listas.listaCircularDobleGenerico;

public class App {
    public static void main(String[] args) {
        // Crear lista circular doblemente enlazada genérica
        ListaCircularDoble<Integer> lista = new ListaCircularDoble<>();

        // Agregar elementos al final
        lista.agregarFinal(5);
        lista.agregarFinal(3);
        lista.agregarFinal(8);

        // Agregar en posición específica
        lista.agregar(1, 7); // insertar 7 en índice 1
        System.out.println("Lista después de agregar elementos:");
        lista.imprimirLista();

        // Modificar nodo
        lista.modificarNodo(0, 10); // cambiar primer elemento a 10
        System.out.println("Lista después de modificar índice 0:");
        lista.imprimirLista();

        // Ordenar lista
        lista.ordenarLista();
        System.out.println("Lista después de ordenar:");
        lista.imprimirLista();

        // Eliminar por valor
        lista.eliminar(7);
        System.out.println("Lista después de eliminar el valor 7:");
        lista.imprimirLista();

        // Eliminar primero
        lista.eliminarPrimero();
        System.out.println("Lista después de eliminar primero:");
        lista.imprimirLista();

        // Eliminar último
        lista.eliminarUltimo();
        System.out.println("Lista después de eliminar último:");
        lista.imprimirLista();

        // Borrar lista
        lista.borrarLista();
        System.out.println("Lista después de borrar toda la lista:");
        lista.imprimirLista();

        // Probar agregar nuevamente después de borrar
        lista.agregarFinal(100);
        lista.agregarFinal(50);
        System.out.println("Lista después de agregar tras borrar:");
        lista.imprimirLista();
    }
}
