package Punto2;

/**
 * Esta clase define los registros de entrada y salida de los empleados al restaurante.
 * El restaurante guarda diferentes registros por cada entrada y salida de cada empleado
 * @author Felipe López, David Eslava, Paula Sánchez
 * @version 1.0.0
 */
public class Registro {
    private int IDEmpleado;
    private String FechaEntrada;
    private String FechaSalida;

    /**
     * Constructor de la clase
     * @param IDEmpleado Identificador del empleado a registrar
     */
    public Registro(int IDEmpleado) {
        this.IDEmpleado = IDEmpleado;
    }

    /**
     * Registra la fecha de entrada al restaurante del empleado relacionado 
     * @param FechaEntrada Fecha de entrada
     */
    public void setFechaEntrada(String FechaEntrada) {
        this.FechaEntrada = FechaEntrada;
    }

    /**
     * Registra la fecha de salida del empleado relacionado
     * @param FechaSalida Fecha de salida
     */
    public void setFechaSalida(String FechaSalida) {
        this.FechaSalida = FechaSalida;
    }

    /**
     * Obtiene el empleado relacionado al registro
     * @return Atributo IDEmpleado
     */
    public int getEmpleado() {
        return IDEmpleado;
    }

    /**
     * Devuelve la fecha de salida del empleado relacionada al registro
     * @return Atributo FechaSalida
     */
    public String getFechaSalida() {
        return FechaSalida;
    }
    
    /**
     * Devuelve el valor del registro en términos de una cadena, para ser proyectado
     * en pantalla al ser solicitado
     * @param r Restaurante, se solicita para tener acceso a los empleados, puesto
     * que la clase Registro no se relaciona con la empresa bidireccionalmente
     * @return
     */
    public String getInformacion(Restaurante r) {
        Empleado e = r.getEmpleados().get(IDEmpleado);
        if (FechaSalida == null) {
            return e.getNombre() + "\t\t" + FechaEntrada + "\tNo Registrada" + "\n";
        } else {
            return e.getNombre() + "\t\t" + FechaEntrada + "\t" + FechaSalida + "\n";
        }
    }
}
