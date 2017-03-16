package Punto2;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Esta clase define el restaurante
 * @author Felipe López, David Eslava, Paula Sánchez
 * @version 1.0.0
 */
public class Restaurante {
    private String Nombre;
    private TreeMap<Integer, Empleado> Empleados;
    private int ContEmpleados;
    private TreeMap<Integer, ProductoInventario> Inventario;
    private ArrayList<Pago> Deberes;
    private int ContDeber;
    private ArrayList<Venta> Haberes;
    private int ContHaber;
    private ArrayList<Registro> Registros;
    private TreeMap<Integer, Plato> Platos;

    /**
     * Constructor de la clase
     * @param Nombre Nombre del restaurante
     */
    public Restaurante(String Nombre) {
        this.Nombre = Nombre;
        this.Empleados = new TreeMap<>();
        this.Inventario = new TreeMap<>();
        this.Deberes = new ArrayList<>();
        this.Haberes = new ArrayList<>();
        this.Registros = new ArrayList<>();
        this.Platos = new TreeMap<>();
    }

    /**
     * Retorna los platos ofrecidos por el restaurante
     * @return TreeMap de los platos ofrecidos
     */
    public TreeMap<Integer, Plato> getPlatos() {
        return Platos;
    }
    
    /**
     * Agrega un plato nuevo a los ofrecidos por el restaurante
     * @param p Plato a ser añadido
     */
    public void añadirPlato(Plato p) {
        this.Platos.put(p.getID(), p);
    }

    /**
     * Retorna la cantidad de empleados que han sido registrados, esto para dar
     * un identificador nuevo a cada empleado a la vez que se registra
     * @return Atributo ContEmpleados
     */
    public int getContEmpleados() {
        return ContEmpleados;
    }

    /**
     * Devuelve los empleados registrados en el restaurante
     * @return TreeMap de empleados registrados
     */
    public TreeMap<Integer, Empleado> getEmpleados() {
        return Empleados;
    }

    /**
     * Registra un nuevo empleado
     * @param e Empleado a contratar
     */
    public void añadirEmpleado(Empleado e) {
        this.Empleados.put(e.getID(), e);
        this.ContEmpleados++;
    }
    
    /**
     * Elimina un empleado del registro
     * @param ID Empleado a ser despedido
     */
    public void removerEmpleado(int ID) {
        this.Empleados.remove(ID);
    }

    /**
     * Agrega productos al inventario
     * @param p Producto a agregar, este lleva internamente la cantidad
     */
    public void añadirProductoInventario(ProductoInventario p) {
        if (Inventario.containsKey(p.getID())) {
            this.Inventario.get(p.getID()).añadirProductos(p.getCantidad());
        } else {
            this.Inventario.put(p.getID(), p);
        }
    }

    /**
     * Remueve del inventario cierta cantidad de un producto
     * @param ID Identificador del producto
     * @param Cantidad Cantidad a remover del inventario
     */
    public void removerProductoInventario(int ID, int Cantidad) {
        if (Inventario.containsKey(ID)) {
            if (Inventario.get(ID).getCantidad() > Cantidad) {
                Inventario.get(ID).retirarProductos(Cantidad);
            } else if (Inventario.get(ID).getCantidad() == Cantidad) {
                Inventario.remove(ID);
            }
        }
    }

    /**
     * Registra un pago nuevo
     * @param p Pago a registrar
     */
    public void registrarPago(Pago p) {
        this.Deberes.add(p);
        this.ContDeber++;
    }

    /**
     * Registra una nueva venta
     * @param v Venta a registrar
     */
    public void registrarVenta(Venta v) {
        this.Haberes.add(v);
        this.ContHaber++;
    }

    /**
     * Retorna la cantidad de deberes (o pagos) que han sido registrados, esto para dar
     * un identificador nuevo a cada deber a la vez que se registra
     * @return Atributo ContDeber
     */
    public int getContDeber() {
        return ContDeber;
    }

    /**
     * Retorna la cantidad de haberes (o ventas) que han sido registradas, esto para dar
     * un identificador nuevo a cada haber a la vez que se registra
     * @return
     */
    public int getContHaber() {
        return ContHaber;
    }

    /**
     * Crea un nuevo registro para la entrada de un empleado y queda en espera
     * para registrar su salida
     * @param IDEmpleado Identificador del empleado
     * @param Fecha Fecha de entrada
     */
    public void registrarEntrada(int IDEmpleado, String Fecha) {
        Registro r = new Registro(IDEmpleado);
        r.setFechaEntrada(Fecha);
        this.Registros.add(r);
    }

    /**
     * Da valor a la fecha de salida en el registro creado cuando el empleado 
     * ingresó al restaurante
     * @param IDEmpleado Identificador del empleado
     * @param Fecha Fecha de salida
     */
    public void registrarSalida(int IDEmpleado, String Fecha) {
        for (Registro r : this.Registros) {
            if(r.getEmpleado() == IDEmpleado && r.getFechaSalida() == null) {
                r.setFechaSalida(Fecha);
            }
        }
    }
    
    /**
     * Retorna todos los registros de entrada y salida hechos en el restaurante,
     * estos en una cadena para ser proyectados al momento de ser solicitados por
     * la interfaz
     * @return Cadena resultante al escribir todos los registros en función de sus
     * atributos
     */
    public String getRegistroEntradaSalida() {
        String x = "Registros Entrada - Salida\n\nEmpleado\t\tFecha Entrada\t\tFecha Salida\n\n";
        for (Registro r : Registros) {
            x += r.getInformacion(this);
        }
        return x;
    }
    
    /**
     * Devuelve en forma de cadena la información acerca de todos los productos
     * que se encuentran dentro del inventario
     * @return Cadena generada al escribir todos los productos en función de sus
     * atributos
     */
    public String getInfoInventario() {
        String x = "Iventario\n\nID\t\tProducto\t\tCant\n\n";
        for (ProductoInventario p : Inventario.values()) {
            x += p.getInformacion();
        }
        return x;
    }
    
    /**
     * Retorna una lista de todos los gastos en cuanto a productos, registrados en
     * el año ingresado
     * @param Año Año del que se desea conocer los registros
     * @return Cadena generada al escribir todos los gastos registrados en el año
     * ingresado
     */
    public String getGastosInventarioAño(String Año) {
        String x = "Gasto Año " + Año + "\n"
                 + "--------------------------------------------------------\n";
        double total = 0;
        for (Pago d : Deberes) {
            if(d instanceof CompraProductos) {
                String[] y = d.getFecha().split("/");
                if(y[2].equals(Año)) {
                    x += d.getInformacion()
                      + "--------------------------------------------------------\n";
                    total += d.calcularTotal();
                }
            }
        }
        x += "Gasto Total Año " + Año + ": $" + total;
        return x;
    }
    
    /**
     * Retorna una lista de todos los gastos en cuanto a productos, registrados en
     * el mes ingresado
     * @param Mes Mes del que se desea conocer los registros
     * @return Cadena generada al escribir todos los gastos registrados en el mes
     * ingresado
     */
    public String getGastosInventarioMes(String Mes) {
        String x = "Gasto Mes " + Mes + "\n"
                 + "--------------------------------------------------------\n";
        double total = 0;
        for (Pago d : Deberes) {
            if(d instanceof CompraProductos) {
                String[] y = d.getFecha().split("/");
                if((y[1] + "/" + y[2]).equals(Mes)) {
                    x += d.getInformacion()
                      + "--------------------------------------------------------\n";
                    total += d.calcularTotal();
                }
            }
        }
        x += "Gasto Total Mes " + Mes + ": $" + total;
        return x;
    }
    
    /**
     * Retorna una lista de todos los gastos en cuanto a productos, registrados en
     * el día ingresado
     * @param Dia Día del que se desea conocer los registros
     * @return Cadena generada al escribir todos los gastos registrados en el día
     * ingresado
     */
    public String getGastosInventarioDia(String Dia) {
        String x = "Gasto Dia" + Dia + "\n"
                 + "--------------------------------------------------------\n";
        double total = 0;
        for (Pago d : Deberes) {
            if(d instanceof CompraProductos) {
                if(d.getFecha().equals(Dia)) {
                    x += d.getInformacion()
                      + "--------------------------------------------------------\n";
                    total += d.calcularTotal();
                }
            }
        }
        x += "Gasto Total Dia " + Dia + ": $" + total;
        return x;
    }
    
    /**
     * Devuelve una lista de todos los gastos registrados en el año ingresado,
     * relacionados con el pago legal a los empleados. 
     * @param Año Año del que se desea conocer los registros
     * @return Cadena generada al escribir todos los gastos registrados en el año
     * ingresado
     */
    public String getGastosEmpleadosAño(String Año) {
        String x = "Gasto Empleados Año " + Año + "\n"
                 + "--------------------------------------------------------\n";
        double total = 0;
        for (Pago d : Deberes) {
            if(d instanceof PagoEmpleado) {
                String[] y = d.getFecha().split("/");
                if(y[2].equals(Año)) {
                    x += d.getInformacion()
                      + "--------------------------------------------------------\n";
                    total += d.calcularTotal();
                }
            }
        }
        x += "Gasto Empleados Total Año " + Año + ": $" + total;
        return x;
    }
    
    /**
     * Devuelve una lista de todos los gastos registrados en el mes ingresado,
     * relacionados con el pago legal a los empleados. 
     * @param Mes Mes del que se desea conocer los registros
     * @return Cadena generada al escribir todos los gastos registrados en el mes
     * ingresado
     */
    public String getGastosEmpleadosMes(String Mes) {
        String x = "Gasto Empleados Mes " + Mes + "\n"
                 + "--------------------------------------------------------\n";
        double total = 0;
        for (Pago d : Deberes) {
            if(d instanceof PagoEmpleado) {
                String[] y = d.getFecha().split("/");
                if((y[1] + "/" + y[2]).equals(Mes)) {
                    x += d.getInformacion()
                      + "--------------------------------------------------------\n";
                    total += d.calcularTotal();
                }
            }
        }
        x += "Gasto Empleados Total Mes " + Mes + ": $" + total;
        return x;
    }
    
    /**
     * Devuelve una lista de todos los gastos registrados en el día ingresado,
     * relacionados con el pago legal a los empleados. 
     * @param Dia Día del que se desea conocer los registros
     * @return Cadena generada al escribir todos los gastos registrados en el día
     * ingresado
     */
    public String getGastosEmpleadosDia(String Dia) {
        String x = "Gasto Empleados Dia" + Dia + "\n"
                 + "--------------------------------------------------------\n";
        double total = 0;
        for (Pago d : Deberes) {
            if(d instanceof PagoEmpleado) {
                if(d.getFecha().equals(Dia)) {
                    x += d.getInformacion()
                      + "--------------------------------------------------------\n";
                    total += d.calcularTotal();
                }
            }
        }
        x += "Gasto Empleados Total Dia " + Dia + ": $" + total;
        return x;
    }
    
    /**
     * Obtiene una lista de todos los gastos registrados en el año ingresado,
     * relacionados con gastos del restaurante. 
     * @param Año Año del que se desea conocer los registros
     * @return Cadena generada al escribir todos los gastos registrados en el año
     * ingresado
     */
    public String getGastosRestauranteAño(String Año) {
        String x = "Gasto Restaurante Año " + Año + "\n"
                 + "--------------------------------------------------------\n";
        double total = 0;
        for (Pago d : Deberes) {
            if(d instanceof GastoRestaurante) {
                String[] y = d.getFecha().split("/");
                if(y[2].equals(Año)) {
                    x += d.getInformacion()
                      + "--------------------------------------------------------\n";
                    total += d.calcularTotal();
                }
            }
        }
        x += "Gasto Restaurante Total Año " + Año + ": $" + total;
        return x;
    }
    
    /**
     * Obtiene una lista de todos los gastos registrados en el mes ingresado,
     * relacionados con gastos del restaurante. 
     * @param Mes Mes del que se desea conocer los registros
     * @return Cadena generada al escribir todos los gastos registrados en el mes
     * ingresado
     */
    public String getGastosRestauranteMes(String Mes) {
        String x = "Gasto Restaurante Mes " + Mes + "\n"
                 + "--------------------------------------------------------\n";
        double total = 0;
        for (Pago d : Deberes) {
            if(d instanceof GastoRestaurante) {
                String[] y = d.getFecha().split("/");
                if((y[1] + "/" + y[2]).equals(Mes)) {
                    x += d.getInformacion()
                      + "--------------------------------------------------------\n";
                    total += d.calcularTotal();
                }
            }
        }
        x += "Gasto Restaurante Total Mes " + Mes + ": $" + total;
        return x;
    }
    
    /**
     * Obtiene una lista de todos los gastos registrados en el dia ingresado,
     * relacionados con gastos del restaurante. 
     * @param Dia Día del que se desea conocer los registros
     * @return Cadena generada al escribir todos los gastos registrados en el día
     * ingresado
     */
    public String getGastosRestauranteDia(String Dia) {
        String x = "Gasto Restaurante Dia" + Dia + "\n"
                 + "--------------------------------------------------------\n";
        double total = 0;
        for (Pago d : Deberes) {
            if(d instanceof GastoRestaurante) {
                if(d.getFecha().equals(Dia)) {
                    x += d.getInformacion()
                      + "--------------------------------------------------------\n";
                    total += d.calcularTotal();
                }
            }
        }
        x += "Gasto Restaurante Total Dia " + Dia + ": $" + total;
        return x;
    }
    
    /**
     * Retorna la lista de todas las ventas registradas en el año ingresado
     * @param Año Año del que se desea obtener los registros
     * @return Cadena generada al escribir todas las ventas registradas en el año
     * ingresado
     */
    public String getGananciasAño(String Año) {
        String x = "Ganancias Año " + Año + "\n"
                 + "--------------------------------------------------------\n";
        double total = 0;
        for (Venta v : Haberes) {
            String[] y = v.getFecha().split("/");
            if (y[2].equals(Año)) {
                x += v.getInformacion()
                  + "--------------------------------------------------------\n";
                total += v.calcularTotal();
            }
        }
        x += "Ganancia Total Año " + Año + ": $" + total;
        return x;
    }
    
    /**
     * Retorna la lista de todas las ventas registradas en el mes ingresado
     * @param Mes Mes del que se desea obtener los registros
     * @return Cadena generada al escribir todas las ventas registradas en el mes
     * ingresado
     */
    public String getGananciasMes(String Mes) {
        String x = "Ganancias Mes " + Mes + "\n"
                 + "--------------------------------------------------------\n";
        double total = 0;
        for (Venta v : Haberes) {
            String[] y = v.getFecha().split("/");
            if ((y[1] + "/" + y[2]).equals(Mes)) {
                x += v.getInformacion()
                  + "--------------------------------------------------------\n";
                total += v.calcularTotal();
            }
        }
        x += "Ganancia Total Mes " + Mes + ": $" + total;
        return x;
    }
    
    /**
     * Retorna la lista de todas las ventas registradas en el día ingresado
     * @param Dia Día del que se desea obtener los registros
     * @return Cadena generada al escribir todas las ventas registradas en el día
     * ingresado
     */
    public String getGananciasDia(String Dia) {
        String x = "Ganancias Dia " + Dia + "\n"
                 + "--------------------------------------------------------\n";
        double total = 0;
        for (Venta v : Haberes) {
            if (v.getFecha().equals(Dia)) {
                x += v.getInformacion()
                  + "--------------------------------------------------------\n";
                total += v.calcularTotal();
            }
        }
        x += "Ganancia Total Dia " + Dia + ": $" + total;
        return x;
    }
}
