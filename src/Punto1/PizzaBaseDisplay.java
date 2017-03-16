package Punto1;

import java.util.ArrayList;

/**
 * Esta clase define los dispositivos usados por los cocineros o administradores
 * para atender las ordenes realizadas. La interfaz, además de permite actualizar
 * los estados de las ordenes para que los clientes puedan ver el progreso de su
 * orden, otorga la opción de cobrar al cliente el total por todas sus ordenes 
 * @author Felipe López, David Eslava, Paula Sánchez
 * @version 1.0.0
 */
public class PizzaBaseDisplay {

    private final ArrayList<Integer> Ordenes;

    /**
     * Constructor de la clase
     */
    public PizzaBaseDisplay() {
        this.Ordenes = new ArrayList<>();
    }

    /**
     * Imprime una lista de todas las ordenes relacionadas al display, luego crea
     * una interfaz interactiva con el administrativo para modificar el estado
     * de las ordenes
     * @return Decisión tomada por el usuario en el menu propuesto en función de 
     * un enero, después de haber modificado el estado de una orden
     */
    public int AdministrarOrdenes() {
        if (this.Ordenes.size() > 0) {
            System.out.println("Ordenes En Proceso");
            System.out.println("--------------------------------------------------------------");
            for (int i = 0; i < Ordenes.size(); i++) {
                System.out.println(PizzaBaseRestaurant.Ordenes.get(this.Ordenes.get(i)).toString());
            }
            System.out.print("Ingrese la ID de la orden a modificar su estado: ");
            int O = PizzaBaseRestaurant.SC.nextInt();
            PizzaBaseRestaurant.LimpiarPantalla();
            System.out.println("\n1- Mover a \"En Preparacion\"");
            System.out.println("2- Actualizar a \"Servida\" (Eliminar de la Lista)\n");
            int D = PizzaBaseRestaurant.SC.nextInt();
            PizzaBaseRestaurant.LimpiarPantalla();
            switch (D) {
                case 1:
                    PizzaBaseRestaurant.Ordenes.get(O).setEstado(1);
                    break;
                case 2:
                    PizzaBaseRestaurant.Ordenes.get(O).setEstado(2);
                    this.Ordenes.remove((Integer) O);
                    break;
                default:
                    System.out.println("La opcion ingresada no es valida");
            }
        } else {
            System.out.println("Aun no tiene ordenes asignadas");
            System.out.println("--------------------------------------------------------------");
        }
        return DisplayMenu(true);
    }

    /**
     * Permite saber al administrativo cuánto tiene que cobrar al cliente, al
     * tiempo que deja libre la lista de ordenes en la mesa respectiva para 
     * recibir a un nuevo usuario
     * @return Decisión tomada por el usuario en el menu propuesto, en
     * función de un enero
     */
    public int CobrarMesa() {
        System.out.print("Ingrese el numero de la mesa a la que desea cobrar: ");
        int M = PizzaBaseRestaurant.SC.nextInt();
        PizzaBaseRestaurant.LimpiarPantalla();
        System.out.println("La mesa debe pagar un total de $" + PizzaBaseRestaurant.Mesas.get(M).Cobrar());
        System.out.println("--------------------------------------------------------------");
        return DisplayMenu(false);
    }

    /**
     * Despliega un menú de opciones que se modifica en su primera opción, según 
     * el valor del parametro pasado
     * @param AoC - True es usado para desplegar el menú después de modificar el
     * estado de una orden<br>
     * - False se usa para desplegar el menú después de haber cobrado una mesa
     * @return Opción seleccionada por el usuario en función de un entero
     */
    private int DisplayMenu(boolean AoC) {
        boolean f = true;
        int D;
        while (f) {
            f = false;
            if (AoC) {
                System.out.println("1- Modificar Nueva Orden");
            } else {
                System.out.println("1- Cobrar a Otra Mesa");
            }
            System.out.println("2- Menu Display");
            System.out.println("3- Simular Nuevo Display");
            System.out.println("4- Menu Principal");
            System.out.println("5- Salir del Programa\n");
            D = PizzaBaseRestaurant.SC.nextInt();
            PizzaBaseRestaurant.LimpiarPantalla();
            try {
                switch (D) {
                    case 1:
                        if (AoC) {
                            this.AdministrarOrdenes();
                        } else {
                            this.CobrarMesa();
                        }
                        break;
                    case 2:
                        return -1;
                    case 3:
                        return 3;
                    case 4:
                        return 4;
                    case 5:
                        System.out.println("Hasta Pronto!");
                        return 5;
                    default:
                        System.out.println("El valor ingresado no es valido");
                        f = true;
                        break;
                }
            } catch (Exception e) {
                System.out.println("El valor ingresado no es valido: " + e);
                f = true;
            }
        }
        return 0;
    }

    /**
     * Retorna la lista de los identificadores de las ordenes relacionadas al
     * display
     * @return ArrayList de las ordenes relacionadas al display
     */
    public ArrayList<Integer> getOrdenes() {
        return Ordenes;
    }

    /**
     * Relaciona una nueva orden al display para que ésta sea atendida
     * @param ID Identificador de la orden
     */
    public void addOrden(int ID) {
        this.Ordenes.add(ID);
    }
}
