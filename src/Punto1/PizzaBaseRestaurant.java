package Punto1;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Esta clase define el sistema de ordenes en el restaurante entero, sus variables
 * globales son configurables para proveer la adaptación del sistema a cualquier
 * escala del mismo.
 * @author Felipe López, David Eslava, Paula Sánchez
 * @version 1.0.0
 */
public class PizzaBaseRestaurant {
    
    /**
     * Robot usado para simular la acción "CTRL+L" para limpiar el output
     */
    private static Robot r;
    
    public static final Scanner SC = new Scanner(System.in);

    /**
     * Precio por Topping
     */
    public static final double PT = 2000;

    /**
     * Precio Pizza 6 pulg
     */
    public static final double PP6I = 10000;

    /**
     * Precio Pizza 9 pulg
     */
    public static final double PP9I = 17000;

    /**
     * Precio Pizza 12 pulg
     */
    public static final double PP12I = 25000;

    /**
     * Precio Bebida Pequeña
     */
    public static final double PBP = 3000;

    /**
     * Precio Bebida Grande
     */
    public static final double PBG = 5000;

    /**
     * Pizzas Prefabricadas
     */
    public static final TreeMap<Integer, Pizza> Prefab = new TreeMap<>();

    /**
     * Bebidas a la Venta
     */
    public static final TreeMap<Integer, String> Bebidas = new TreeMap<>();

    /**
     * Toppings Disponibles
     */
    public static final TreeMap<Integer, String> Toppings = new TreeMap<>();

    /**
     * Cantidad de dispositivos para la realización de pedidos, cada uno
     * asociado a una mesa
     */
    public static final TreeMap<Integer, PizzaBaseCustomer> Mesas = new TreeMap<>();

    /**
     * Cantidad de dispositivos para la visualización de pedidos y modificación
     * de sus estados
     */
    public static final ArrayList<PizzaBaseDisplay> Displays = new ArrayList<>();

    /**
     * Todas las ordenes realizadas en el restaurante
     */
    public static final TreeMap<Integer, Orden> Ordenes = new TreeMap<>();

    /**
     * Cantidad de Ordenes Realizadas
     */
    public static int CANT_ORDENES;

    /**
     * Cantidad de mesas para la creación de dispositivos de realización de
     * pedidos
     */
    private static final int CANT_MESAS = 5;

    /**
     * Cantidad de lectores que habrán en el restaurante para la atención a los
     * pedidos
     */
    private static final int CANT_DISPLAYS = 2;

    /**
     * Método que ejecuta todo el programa. En éste, se simula el sistema dentro
     * de los diferentes dispositivos. Mientras el programa esté en curso, almacena
     * todas las ordenes relacionadas a cada display y cada mesa. En principio,
     * simula la carga de una base de datos, en la que se establecen los productos
     * ofrecidos por el restaurante, seguidamente inicializa los dispositivos
     * (Displays y mesas) para finalmente desplegar la interfaz de creación y
     * administración de ordenes
     * @param args
     */
    public static void main(String[] args) {
        //Cargando...
        CargarPrefab();
        CargarToppings();
        CargarBebidas();
        try {
            r = new Robot();
        } catch (AWTException ex) {}

        //Creando dispositivos necesarios para la realización y lectura de ordenes...
        InicializarDispositivos();

        //Iniciando Menu de Opciones...
        IniciarMenu();
    }

    /**
     * Asigna la orden parámetro al display que tenga menos ordenes en curso detro
     * de su lista
     * @param o Orden a relacionar con algún display
     */
    public static void AsignarOrden(Orden o) {
        int y = 10000;
        int x = 0;
        for (int i = 0; i < Displays.size(); i++) {
            if (Displays.get(i).getOrdenes().size() < y) {
                y = Displays.get(i).getOrdenes().size();
                x = i;
            }
        }
        Displays.get(x).addOrden(o.getID());
        addOrden(o);
    }

    /**
     * Añade una nueva orden a la lista que almacena todas las realizadas
     * en el restaurante desde todas las mesas
     * @param o
     */
    public static void addOrden(Orden o) {
        Ordenes.put(o.getID(), o);
    }

    /**
     * Carga las pizzas prefabricadas
     */
    private static void CargarPrefab() {        
        ArrayList<String> Toppings1 = new ArrayList<>(Arrays.asList("Jamon", "Piña", "Extraqueso"));
        Prefab.put(1, new Pizza("Hawaiana", Toppings1));
        ArrayList<String> Toppings2 = new ArrayList<>(Arrays.asList("Pollo", "Champiñones", "Cebolla"));
        Prefab.put(2, new Pizza("Pollo Con Champiñones", Toppings2));
        ArrayList<String> Toppings3 = new ArrayList<>(Arrays.asList("Salchicha", "Carne Molida", "Peperoni"));
        Prefab.put(3, new Pizza("Carnes", Toppings3));
        ArrayList<String> Toppings4 = new ArrayList<>(Arrays.asList("Carne Picada", "Aguacate", "Vegetales"));
        Prefab.put(4, new Pizza("Mexicana", Toppings4));
        ArrayList<String> Toppings5 = new ArrayList<>(Arrays.asList("Alchachofas", "Jamon Cocido", "Champiñones", "Tomate"));
        Prefab.put(5, new Pizza("Napolitana", Toppings5));
    }

    /**
     * Carga los toppings de los que dispone el restaurante para la venta
     */
    private static void CargarToppings() {
        Toppings.put(1, "Piña");
        Toppings.put(2, "Peperoni");
        Toppings.put(3, "Extraqueso");
        Toppings.put(4, "Jamon");
        Toppings.put(5, "Alcachofas");
        Toppings.put(6, "Aceitunas");
        Toppings.put(7, "Carne Molida");
        Toppings.put(8, "Chile");
        Toppings.put(9, "Vegetales");
        Toppings.put(10, "Champiñones");
        Toppings.put(11, "Pepinillos");
        Toppings.put(12, "Adicional de Mozarella");
        Toppings.put(13, "Pollo");
    }

    /**
     * Carga las bebidas a la venta
     */
    private static void CargarBebidas() {
        Bebidas.put(1, "Coca-Cola");
        Bebidas.put(2, "Quatro");
        Bebidas.put(3, "Sprite");
        Bebidas.put(4, "Gaseosa Uva");
        Bebidas.put(5, "Gaseosa Manzana");
        Bebidas.put(6, "Jugo de Maracuya");
        Bebidas.put(7, "Jugo de Mango");
        Bebidas.put(8, "Jugo de Mora");
        Bebidas.put(9, "Jugo de Piña");
        Bebidas.put(10, "Jugo Tropical");
    }

    /**
     * Inicializa los dispositivos (Displays e Interfaces en las mesas). Por medio
     * de las variables globales "CANT_MESAS" y "CANT_DISPLAYS" podemos modificar
     * la cantidad de dispositivos que queremos inicializar
     */
    private static void InicializarDispositivos() {
        for (int i = 1; i < CANT_MESAS + 1; i++) {
            Mesas.put(i, new PizzaBaseCustomer(i));
        }
        for (int i = 0; i < CANT_DISPLAYS; i++) {
            Displays.add(new PizzaBaseDisplay());
        }
    }

    /**
     * Crea la interfaz de simulación del sistema en la consola. Despliega un menú
     * de opciones a través de las cuales se puede navergar para hacer de 
     * administrador y de cliente para crear y atender las ordenes.
     */
    private static void IniciarMenu() {
        int Dec = 1;
        System.out.println("Restaurante Pizza Base");
        System.out.println("--------------------------------------------------------------");
        while (Dec != 0) {
            System.out.println("1- Simular mesa");
            System.out.println("2- Simular administrador/cocinero");
            System.out.println("3- Salir del Programa\n");
            Dec = SC.nextInt();
            LimpiarPantalla();
            try {
                switch (Dec) {
                    case 1:
                        Dec = SimularMesa();
                        break;
                    case 2:
                        Dec = SimularDisplay();
                        break;
                    case 3:
                        System.out.println("Hasta pronto!");
                        return;
                    default:
                        System.out.println("El valor ingresado no es valido");
                }
            } catch (Exception e) {
                System.out.println("El valor ingresado no es valido" + e);
            }
        }
    }

    /**
     * Despliega la interfaz que simula el sistema en un dispositvo de mesa. Ésta
     * nos permite realizar nuevas ordenes y visualizar el progreso de las que ya
     * hemos realizado anteriormente señalando el costo total de cada una.
     * @return Opción seleccionada por el usuario dentro del menú en función de 
     * un entero
     */
    private static int SimularMesa() {
        System.out.print("Ingresa el numero de la mesa que quieres simular (Cantidad de Mesas = "
                + CANT_MESAS + "): ");
        int M = SC.nextInt();
        LimpiarPantalla();
        int DecM = 1;
        while (DecM != 3 && DecM != 4 && DecM != 5) {
            System.out.println("Mesa " + M + "\n");
            System.out.println("1- Mostrar Ordenes");
            System.out.println("2- Nueva Orden\n");
            DecM = SC.nextInt();
            LimpiarPantalla();
            try {
                switch (DecM) {
                    case 1:
                        DecM = Mesas.get(M).MostrarOrdenes();
                        break;
                    case 2:
                        DecM = Mesas.get(M).NuevaOrden();
                        break;
                    default:
                        System.out.println("El valor ingresado no es valido");
                        DecM = -1;
                        break;
                }
            } catch (Exception e) {
                System.out.println("El valor ingresado no es valido" + e.toString());
                DecM = -1;
            }
        }
        if(DecM == 3) {
            DecM = SimularMesa();
        }
        return DecM - 5;
    }

    /**
     * Despliega la interfaz que simula el sistema en un display. Éstos deben ser
     * usados por administradores para atender las ordenes realizadas. La interfaz
     * permite modificar el estado de todas las ordenes relacionadas a cada display
     * al igual que permite saber el total a pagar en una mesa, después de haberse
     * realizado todas sus ordenes.
     * @return Opción seleccionada por el usuario dentro del menú en función de 
     * un entero
     */
    private static int SimularDisplay() {
        System.out.print("Ingresa el numero del display que quieres simular (Cantidad de Displays = "
                + CANT_DISPLAYS + "): ");
        int D = SC.nextInt() - 1;
        LimpiarPantalla();
        int DecD = 1;
        while (DecD != 3 && DecD != 4 && DecD != 5) {
            System.out.println("Display " + (D + 1) + "\n");
            System.out.println("1- Administrar Ordenes");
            System.out.println("2- Cobrar Mesa\n");
            DecD = SC.nextInt();
            LimpiarPantalla();
            try {
                switch (DecD) {
                    case 1:
                        DecD = Displays.get(D).AdministrarOrdenes();
                        break;
                    case 2:
                        DecD = Displays.get(D).CobrarMesa();
                        break;
                    default:
                        System.out.println("El valor ingresado no es valido");
                        DecD = -1;
                        break;
                }
            } catch (Exception e) {
                System.out.println("El valor ingresado no es valido" + e);
                DecD = -1;
            }
        }
        if (DecD == 3) {
            DecD = SimularDisplay();
        }
        return DecD - 5;
    }
    
    /**
     * Limpia la consola
     */
    public static void LimpiarPantalla() {
        try {
            r.keyPress(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_L);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.keyRelease(KeyEvent.VK_L);
            Thread.sleep(500);
        } catch (InterruptedException ex) {} 
        System.out.println("Restaurante Pizza Base");
        System.out.println("--------------------------------------------------------------");
    }
}
