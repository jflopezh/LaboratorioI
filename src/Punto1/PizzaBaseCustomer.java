package Punto1;

import java.util.ArrayList;

/**
 * Esta clase define los dispositivos usados en cada una de las mesas para 
 * realizar ordenes
 * @author Felipe López, David Eslava, Paula Sánchez
 * @version 1.0.0
 */
public class PizzaBaseCustomer {
    
    private final int NumeroMesa;
    private final ArrayList<Integer> Ordenes;

    /**
     * Constructor de la clase
     * @param NumeroMesa Identificador de la mesa a la que pertenece el dispositivo
     */
    public PizzaBaseCustomer(int NumeroMesa) {
        this.NumeroMesa = NumeroMesa;
        this.Ordenes = new ArrayList<>();
    }

    /**
     * Imprime la lista de las ordenes realizadas por la mesa, incluyendo todos
     * sus atributos
     * @return Decisión tomada por el usuario en el menu propuesto, en
     * función de un enero
     */
    public int MostrarOrdenes() {
        if (this.Ordenes.size() > 0) {
            System.out.println("Ordenes");
            System.out.println("--------------------------------------------------------------");
            for (int i = 0; i < Ordenes.size(); i++) {
                System.out.println(PizzaBaseRestaurant.Ordenes.get(this.Ordenes.get(i)).toStringPrecio());
            }
        } else {
            System.out.println("Aun no ha realizado ordenes. ¡Realice una!");
            System.out.println("--------------------------------------------------------------");
        }
        return DisplayMenu(false);
    }

    /**
     * Crea una interfaz interactiva a traves de la consola para la realización
     * de una nueva orden. Incluye el pedido de la pizza y la bebida a detalle.
     * Al final imprime el resumen de la orden y retorna un menú de opciones. La
     * orden creada se añade al array que almacena todas las ordenes, ubicado
     * en la clase estática PizzaBaseRestaurant, a su vez, la orden se realaciona
     * a su respectiva mesa y a un display que se encarga de atenderla y modificar 
     * su estado constantemente según sea pertinente
     * @return Decisión tomada por el usuario en el menu propuesto, al terminar
     * de realizar la orden en función de un enero
     */
    public int NuevaOrden() {
        System.out.println("Realizando Orden");
        System.out.println("--------------------------------------------------------------");
        PizzaBaseRestaurant.CANT_ORDENES++;
        Orden o = new Orden(PizzaBaseRestaurant.CANT_ORDENES, this.NumeroMesa, SeleccionarPizza(), SeleccionarBebida());
        this.addOrden(PizzaBaseRestaurant.CANT_ORDENES);
        PizzaBaseRestaurant.addOrden(o);
        PizzaBaseRestaurant.AsignarOrden(o);
        PizzaBaseRestaurant.LimpiarPantalla();
        System.out.println("Resumen de la Orden");
        System.out.println("--------------------------------------------------------------");
        System.out.println(o.toStringPrecio());
        return DisplayMenu(true);
    }

    /**
     * Despliega un menú de opciones que se modifica en su primera opción, según 
     * el valor del parametro pasado
     * @param NoM - True es usado para desplegar el menú después de realizar una orden <br>
     * - False se usa para desplegar el menú después de mostrar todas las ordenes
     * de la mesa
     * @return Opción seleccionada por el usuario en función de un entero
     */
    private int DisplayMenu(boolean NoM) {
        boolean f = true;
        int D;
        while (f) {
            f = false;
            if (NoM) {
                System.out.println("1- Nueva Orden");
            } else {
                System.out.println("1- Actualizar Ordenes");
            }
            System.out.println("2- Menu Mesa");
            System.out.println("3- Simular Nueva Mesa");
            System.out.println("4- Menu Principal");
            System.out.println("5- Salir del Programa\n");
            D = PizzaBaseRestaurant.SC.nextInt();
            PizzaBaseRestaurant.LimpiarPantalla();
            try {
                switch (D) {
                    case 1:
                        if (NoM) {
                            this.NuevaOrden();
                        } else {
                            this.MostrarOrdenes();
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
     * Crea una interfaz interactiva con el usuario mediante la cual se selecciona
     * la pizza para la orden. El usuario tiene la opción de elegir una pizza
     * prefabricada de la base de datos del restaurante o simplemente idear una
     * nueva con la opción "¡Hazla tu mismo!". Luego, el usuario elige el tamaño
     * y el estilo de base de la pizza, para así finalmente añadirla a la orden.
     * @return La pizza creada por el usuario a través de la interfaz
     */
    private Pizza SeleccionarPizza() {
        Pizza p;
        System.out.println("Elige tu pizza ingresando el numero respectivo: \n");
        int i = 1;
        while (i <= PizzaBaseRestaurant.Prefab.size()) {
            System.out.println(i + "- " + PizzaBaseRestaurant.Prefab.get(i).toStringPrefab());
            i++;
        }
        System.out.println(i + "- ¡Hazla tu mismo!\n");
        int DecPizza = PizzaBaseRestaurant.SC.nextInt();
        if (DecPizza == i) {
            p = new Pizza("¡Hazla tu mismo!");
        } else {
            p = PizzaBaseRestaurant.Prefab.get(DecPizza).Clonar();
        }
        System.out.println("\nBase:\n");
        System.out.println("1- Corteza Profunda");
        System.out.println("2- Crujiente\n");
        if (PizzaBaseRestaurant.SC.nextInt() == 1) {
            p.setBase(true);
        } else {
            p.setBase(false);
        }
        System.out.println();
        if (DecPizza == i) {
            System.out.println("Elige los toppings que desees en tu pizza (Ejemplo: 2,3,4)\n");
            for (int j = 1; j <= PizzaBaseRestaurant.Toppings.size(); j++) {
                System.out.println(j + "- " + PizzaBaseRestaurant.Toppings.get(j));
            }
            System.out.println();
            for (String t : PizzaBaseRestaurant.SC.next().split(",")) {
                p.addTopping(PizzaBaseRestaurant.Toppings.get(Integer.parseInt(t)));
            }
            System.out.println();
        }
        System.out.print("Ingresa el tamaño de tu pizza(6, 9 o 12 pulg): ");
        p.setTamaño(PizzaBaseRestaurant.SC.nextInt());
        System.out.println("--------------------------------------------------------------");
        return p;
    }

    /**
     * Crea una interfaz interactiva con el usuario mediante la cual se selecciona
     * la bebida para la orden. El usuario procede a elegir una de las bebidas
     * a la venta en el restaurante, éstas se almacenan en un array en la clase
     * main, para ser modificadas a gusto por el administrador. Luego, el usuario
     * elige el tamaño de su bebida, para así finalmente añadirla a la orden.
     * @return La bebida creada por el usuario a través de la interfaz
     */
    private Bebida SeleccionarBebida() {
        Bebida b;
        System.out.println("Elige tu bebida ingresando el numero respectivo:\n");
        for (int j = 1; j <= PizzaBaseRestaurant.Bebidas.size(); j++) {
            System.out.println(j + "- " + PizzaBaseRestaurant.Bebidas.get(j));
        }
        System.out.println();
        b = new Bebida(PizzaBaseRestaurant.Bebidas.get(PizzaBaseRestaurant.SC.nextInt()));
        System.out.println("\nTamaño de la bebida:\n");
        System.out.println("1- Grande");
        System.out.println("2- Pequeña\n");
        if (PizzaBaseRestaurant.SC.nextInt() == 1) {
            b.setTamaño(true);
        } else {
            b.setTamaño(false);
        }
        return b;
    }

    /**
     * Relaciona una nueva orden a la mesa actual
     * @param ID Identificador de la orden que realiza la mesa
     */
    public void addOrden(int ID) {
        this.Ordenes.add(ID);
    }

    /**
     * Calcula el total a pagar por todas las ordenes realizadas por la mesa, luego
     * elimina todas las ordenes de la lista pra que el dispositov pueda ser
     * usado por un nuevo cliente
     * @return Total a pagar por todas las ordenes realizadas
     */
    public double Cobrar() {
        double x = 0;
        for (int i = 0; i < this.Ordenes.size(); i++) {
            x += PizzaBaseRestaurant.Ordenes.get(this.Ordenes.get(i)).getTotal();
        }
        this.Ordenes.clear();
        return x;
    }
}
