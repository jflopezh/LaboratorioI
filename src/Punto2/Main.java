package Punto2;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Scanner;

/**
 * Esta clase describe la interfaz a correr y realiza los procesos sistemáticos 
 * del restaurante
 * @author Felipe López, David Eslava, Paula Sánchez
 * @version 1.0.0
 */
public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static Restaurante r;
    private static Robot rb;

    /**
     * Metodo encargado de correr el programa. Carga los valores preestablecidos
     * del restaurante y proyecta el menú de opciones
     * @param args
     */
    public static void main(String[] args) {
        r = new Restaurante("A lo Paisa");

        //Cargando...
        cargarEmpleados();
        cargarInventario();
        cargarPlatos();
        cargarRegistros();
        cargarPagos();
        cargarVentas();
        try {
            rb = new Robot();
        } catch (AWTException ex) {
        }
        
        //Iniciando Menú...
        desplegarMenu();
    }

    private static void cargarEmpleados() {
        r.añadirEmpleado(new Empleado(0, "Felipe Lopez", "99041902546", "3154149014", 5000000));
        r.añadirEmpleado(new Empleado(1, "A", "99041902546", "3154149014", 5000000));
        r.añadirEmpleado(new Empleado(2, "B", "99041902546", "3154149014", 5000000));
        r.añadirEmpleado(new Empleado(3, "C", "99041902546", "3154149014", 5000000));
        r.añadirEmpleado(new Empleado(4, "D", "99041902546", "3154149014", 5000000));
    }

    private static void cargarInventario() {
        r.añadirProductoInventario(new ProductoInventario(0, "Tomate - Libra", 20));
        r.añadirProductoInventario(new ProductoInventario(1, "A", 14));
        r.añadirProductoInventario(new ProductoInventario(2, "B", 10));
        r.añadirProductoInventario(new ProductoInventario(3, "C", 20));
        r.añadirProductoInventario(new ProductoInventario(4, "D", 9));
        r.añadirProductoInventario(new ProductoInventario(5, "E", 18));
        r.añadirProductoInventario(new ProductoInventario(6, "F", 113));
        r.añadirProductoInventario(new ProductoInventario(7, "G", 72));
    }

    private static void cargarPlatos() {
        Plato p1 = new Plato(0, "Bandeja Paisa", "Este plato se sirve en una vajilla amplia,"
                + " de varias piezas, y ovalada (tipo bandeja). En su presentación clásica"
                + " tradicional o autóctona está compuesto por trece ingredientes invariables;"
                + " once de ellos dispuestos en las mencionadas bandejas o platos, y dos más"
                + " como acompañamiento:", 15000);
        p1.addIngrediente(new Producto(0, "Arroz"));
        p1.addIngrediente(new Producto(1, "Huevo"));
        p1.addIngrediente(new Producto(2, "Carne"));
        p1.addIngrediente(new Producto(3, "Chicharron"));
        
        Plato p2 = new Plato(1, "A", "fajlerglaeiri", 12000);
        p2.addIngrediente(new Producto(4, "A"));
        p2.addIngrediente(new Producto(5, "B"));
        
        r.añadirPlato(p1);
        r.añadirPlato(p2);
    }

    private static void cargarRegistros() {
        r.registrarEntrada(0, "22/03/2017,07:30");
        r.registrarSalida(0, "22/02/2017,22:00");
        r.registrarEntrada(4, "18/12/2016,13:00");
        r.registrarEntrada(3, "09/02/2017,08:15");
        r.registrarSalida(3, "09/02/2017,21:00");
    }

    private static void cargarPagos() {
        CompraProductos c = new CompraProductos(0, "09/02/2017", "Compra Productos", 500000);
        c.addProducto(new ProductoInventario(0, "Tomate - Libra", 20));
        c.addProducto(new ProductoInventario(1, "A", 14));
        r.registrarPago(c);
        
        r.registrarPago(new GastoRestaurante(1, "09/02/2017", "Arriendo Enero", 2000000));
        r.registrarPago(new GastoRestaurante(1, "12/02/2017", "Luz Enero", 500000));
        r.registrarPago(new GastoRestaurante(1, "09/08/2017", "Agua Julio", 500000));
        
        r.registrarPago(new PagoEmpleado(4, "09/05/2017", "Sueldo Felipe Lopez", 5000000));
    }

    private static void cargarVentas() {
        Venta v1 = new Venta(0, "10/02/2017");
        v1.addPlato(r.getPlatos().get(0));
        v1.addPlato(r.getPlatos().get(1));
        v1.addPlato(r.getPlatos().get(0));
        
        Venta v2 = new Venta(1, "09/02/2017");
        v2.addPlato(r.getPlatos().get(1));
        v2.addPlato(r.getPlatos().get(0));
        v2.addPlato(r.getPlatos().get(1));
        
        Venta v3 = new Venta(2, "10/03/2017");
        v3.addPlato(r.getPlatos().get(1));
        v3.addPlato(r.getPlatos().get(1));
        v3.addPlato(r.getPlatos().get(0));
        
        r.registrarVenta(v1);
        r.registrarVenta(v2);
        r.registrarVenta(v3);
    }

    private static void desplegarMenu() {
        int Dec = 1;
        String fecha, nombre, documento, telefono;
        double salario;
        System.out.println("Restaurante A lo Paisa");
        System.out.println("--------------------------------------------------------------");
        while (Dec != 6) {
            System.out.println("1- Acceder a Registros\n"
                    + "2- Registrar Entrada o Salida de Personal\n"
                    + "3- Registrar Gasto\n"
                    + "4- Registrar Venta\n"
                    + "5- Nuevo Empleado\n"
                    + "6- Salir del Programa\n");
            Dec = sc.nextInt();
            LimpiarPantalla();
            try {
                switch (Dec) {
                    case 1:
                        Dec = desplegarRegistros();
                        break;
                    case 2:
                        Dec = desplegarRegistroPersonal();
                        break;
                    case 3:
                        Dec = desplegarRegistroGasto();
                        break;
                    case 4:
                        System.out.print("Fecha de la venta (dd/mm/aaaa): ");
                        fecha = sc.next();
                        Venta v = new Venta(r.getContHaber(), fecha);
                        System.out.println("\nSeleccione los platos vendidos (Ej: 1,1,2,3,4,4)\n");
                        for (Plato p : r.getPlatos().values()) {
                            System.out.println(p.getID() + "- " + p.getNombre());
                        }
                        System.out.println("");
                        String[] x = sc.next().split(",");
                        for (String s : x) {
                            v.addPlato(r.getPlatos().get(Integer.parseInt(s)));
                        }
                        r.registrarVenta(v);
                        break;
                    case 5:
                        System.out.print("Nombres y apellidos: ");
                        nombre = sc.next();
                        System.out.print("Documento: ");
                        documento = sc.next();
                        System.out.print("Telefono: ");
                        telefono = sc.next();
                        System.out.print("Salario: $");
                        salario = sc.nextDouble();
                        r.añadirEmpleado(new Empleado(r.getContEmpleados(), nombre, documento, telefono, salario));
                        break;
                    case 6:
                        System.out.println("¡Hasta Pronto!");
                    default:
                        System.out.println("El valor ingresado no es valido");
                }
            } catch (Exception e) {
                System.out.println("El valor ingresado no es valido" + e);
            }
            if (Dec == 4 || Dec == 5) {
                System.out.println("\n1- Menu Principal\n"
                                 + "2- Salir del Programa\n");
                Dec = sc.nextInt();
                LimpiarPantalla();
                if (Dec == 2) {
                    Dec = 6;
                }
            }
        }
    }

    private static int desplegarRegistros() {
        int Dec;
        String año, dia, mes;
        System.out.println("1- Inventario\n"
                + "2- Entrada y Salida del Personal\n"
                + "3- Gastos Inventario (Productos) - Anual\n"
                + "4- Gastos Inventario (Productos) - Mensual\n"
                + "5- Gastos Inventario (Productos) - Diario\n"
                + "6- Gastos por Concepto de Empleados (Sueldo, Prima, etc) - Anual\n"
                + "7- Gastos por Concepto de Empleados (Sueldo, Prima, etc) - Mensual\n"
                + "8- Gastos por Concepto de Empleados (Sueldo, Prima, etc) - Diario\n"
                + "9- Gastos del Restaurante (Impuestos, Arriendo, Servicios) - Anual\n"
                + "10- Gastos del Restaurante (Impuestos, Arriendo, Servicios) - Mensual\n"
                + "11- Gastos del Restaurante (Impuestos, Arriendo, Servicios) - Diario\n"
                + "12- Ventas - Anual\n"
                + "13- Ventas - Mensual\n"
                + "14- Ventas - Diario\n"
                + "15- Salir del Programa\n");
        Dec = sc.nextInt();
        LimpiarPantalla();
        try {
            switch (Dec) {
                case 1:
                    System.out.println(r.getInfoInventario());
                    break;
                case 2:
                    System.out.println(r.getRegistroEntradaSalida());
                    break;
                case 3:
                    System.out.print("Ingrese el año del cual desea ver el registro (aaaa): ");
                    año = sc.next();
                    System.out.println("");
                    System.out.println(r.getGastosInventarioAño(año));
                    break;
                case 4:
                    System.out.print("Ingrese el mes del cual desea ver el registro (mm/aaaa): ");
                    mes = sc.next();
                    System.out.println("");
                    System.out.println(r.getGastosInventarioMes(mes));
                    break;
                case 5:
                    System.out.print("Ingrese el dia del cual desea ver el registro (dd/mm/aaaa): ");
                    dia = sc.next();
                    System.out.println("");
                    System.out.println(r.getGastosInventarioDia(dia));
                    break;
                case 6:
                    System.out.print("Ingrese el año del cual desea ver el registro (aaaa): ");
                    año = sc.next();
                    System.out.println("");
                    System.out.println(r.getGastosEmpleadosAño(año));
                    break;
                case 7:
                    System.out.print("Ingrese el mes del cual desea ver el registro (mm/aaaa): ");
                    mes = sc.next();
                    System.out.println("");
                    System.out.println(r.getGastosEmpleadosMes(mes));
                    break;
                case 8:
                    System.out.print("Ingrese el dia del cual desea ver el registro (dd/mm/aaaa): ");
                    dia = sc.next();
                    System.out.println("");
                    System.out.println(r.getGastosEmpleadosDia(dia));
                    break;
                case 9:
                    System.out.print("Ingrese el año del cual desea ver el registro (aaaa): ");
                    año = sc.next();
                    System.out.println("");
                    System.out.println(r.getGastosRestauranteAño(año));
                    break;
                case 10:
                    System.out.print("Ingrese el mes del cual desea ver el registro (mm/aaaa): ");
                    mes = sc.next();
                    System.out.println("");
                    System.out.println(r.getGastosRestauranteMes(mes));
                    break;
                case 11:
                    System.out.print("Ingrese el dia del cual desea ver el registro (dd/mm/aaaa): ");
                    dia = sc.next();
                    System.out.println("");
                    System.out.println(r.getGastosRestauranteDia(dia));
                    break;
                case 12:
                    System.out.print("Ingrese el año del cual desea ver el registro (aaaa): ");
                    año = sc.next();
                    System.out.println("");
                    System.out.println(r.getGananciasAño(año));
                    break;
                case 13:
                    System.out.print("Ingrese el mes del cual desea ver el registro (mm/aaaa): ");
                    mes = sc.next();
                    System.out.println("");
                    System.out.println(r.getGananciasMes(mes));
                    break;
                case 14:
                    System.out.print("Ingrese el dia del cual desea ver el registro (dd/mm/aaaa): ");
                    dia = sc.next();
                    System.out.println("");
                    System.out.println(r.getGananciasDia(dia));
                    break;
                case 15:
                    System.out.println("¡Hasta Pronto!");
                    return 6;
                default:
                    System.out.println("El valor ingresado no es valido");
            }
        } catch (Exception e) {
            System.out.println("El valor ingresado no es valido" + e);
        }
        System.out.println("\n1- Menu Principal\n"
                + "2- Salir del Programa\n");
        Dec = sc.nextInt();
        LimpiarPantalla();
        if (Dec == 1) {
            return 1;
        } else {
            return 3;
        }
    }
    
    private static int desplegarRegistroPersonal() {
        int Dec, ID;
        String fecha;
        System.out.println("1- Registrar Entrada de Personal\n"
                + "2- Registrar Salida de Personal\n"
                + "3- Salir del Programa\n");
        Dec = sc.nextInt();
        LimpiarPantalla();
        try {
            switch (Dec) {
                case 1:
                    System.out.print("Identificador del empleado: ");
                    ID = sc.nextInt();
                    System.out.print("Fecha de entrada (dd/mm/aaaa,hora:min): ");
                    fecha = sc.next();
                    r.registrarEntrada(ID, fecha);
                    break;
                case 2:
                    System.out.print("Identificador del empleado: ");
                    ID = sc.nextInt();
                    System.out.print("Fecha de salida (dd/mm/aaaa,hora:min): ");
                    fecha = sc.next();
                    r.registrarSalida(ID, fecha);
                    break;
                case 3:
                    System.out.println("¡Hasta Pronto!");
                    return 6;
                default:
                    System.out.println("El valor ingresado no es valido");
            }
        } catch (Exception e) {
            System.out.println("El valor ingresado no es valido" + e);
        }
        System.out.println("\n1- Menu Principal\n"
                + "2- Salir del Programa\n");
        Dec = sc.nextInt();
        LimpiarPantalla();
        if (Dec == 1) {
            return 1;
        } else {
            return 6;
        }
    }
    
    private static int desplegarRegistroGasto() {
        int Dec, ID, cant, cantx;
        String fecha, descripcion;
        double total;
        System.out.println("1- Registrar Gasto por Compra de Productos (Ingreso de Productos al Inventario)\n"
                + "2- Registrar Gasto por Concepto de Empleados (Sueldo, Prima, etc)\n"
                + "3- Registrar Gasto de Restaurante (Arriendo, Impuestos, Servicios, etc)\n"
                + "4- Salir del Programa\n");
        Dec = sc.nextInt();
        LimpiarPantalla();
        try {
            switch (Dec) {
                case 1:
                    System.out.print("Fecha de la compra (dd/mm/aaaa): ");
                    fecha = sc.next();
                    System.out.print("Total de la compra: $");
                    total = sc.nextDouble();
                    System.out.print("Cantidad de productos diferentes comprados: ");
                    cantx = sc.nextInt();
                    System.out.println("");
                    CompraProductos c = new CompraProductos(r.getContDeber(), fecha, "Compra de productos", total);
                    for (int i = 0; i < cantx; i++) {
                        System.out.print("Ingrese el ID del producto " + (i+1) + ": ");
                        ID = sc.nextInt();
                        System.out.print("Ingrese la descripcion del producto " + (i+1) + ": ");
                        descripcion = sc.next();
                        System.out.print("Ingrese la cantidad del producto " + (i+1) + ": ");
                        cant = sc.nextInt();
                        System.out.println("");
                        ProductoInventario p = new ProductoInventario(ID, descripcion, cant);
                        r.añadirProductoInventario(p);
                        c.addProducto(p);
                    }
                    r.registrarPago(c);
                    break;
                case 2:
                    System.out.print("Fecha del pago (dd/mm/aaaa): ");
                    fecha = sc.next();
                    System.out.print("Descripcion del pago: ");
                    descripcion = sc.next();
                    System.out.print("Total pagado: $");
                    total = sc.nextDouble();
                    r.registrarPago(new PagoEmpleado(r.getContDeber(), fecha, descripcion, total));
                    break;
                case 3:
                    System.out.print("Fecha del pago (dd/mm/aaaa): ");
                    fecha = sc.next();
                    System.out.print("Descripcion del pago: ");
                    descripcion = sc.next();
                    System.out.print("Total pagado: $");
                    total = sc.nextDouble();
                    r.registrarPago(new GastoRestaurante(r.getContDeber(), fecha, descripcion, total));
                    break;
                case 4:
                    System.out.println("¡Hasta Pronto!");
                    return 6;
                default:
                    System.out.println("El valor ingresado no es valido");
            }
        } catch (Exception e) {
            System.out.println("El valor ingresado no es valido" + e);
        }
        System.out.println("\n1- Menu Principal\n"
                + "2- Salir del Programa\n");
        Dec = sc.nextInt();
        LimpiarPantalla();
        if (Dec == 1) {
            return 1;
        } else {
            return 6;
        }
    }
    
    

    private static void LimpiarPantalla() {
        try {
            rb.keyPress(KeyEvent.VK_CONTROL);
            rb.keyPress(KeyEvent.VK_L);
            rb.keyRelease(KeyEvent.VK_CONTROL);
            rb.keyRelease(KeyEvent.VK_L);
            Thread.sleep(500);
        } catch (InterruptedException ex) {
        }
        System.out.println("Restaurante A lo Paisa");
        System.out.println("--------------------------------------------------------------");
    }
}
