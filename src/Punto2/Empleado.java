package Punto2;

/**
 * Esta clase los empleados del restaurante
 * @author Felipe López, David Eslava, Paula Sánchez
 * @version 1.0.0
 */
public class Empleado {
    private  int ID;
    private String Nombre;
    private String Documento;
    private String Telefono;
    private String Labor;
    private double Salario;

    /**
     * Constructor de la clase
     * @param ID Identificador del empleado
     * @param Nombre Nombre
     * @param Documento Documento de Identidad
     * @param Telefono Telefono o medio de contacto
     * @param Salario Total ganado mensualmente
     */
    public Empleado(int ID, String Nombre, String Documento, String Telefono, double Salario) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Documento = Documento;
        this.Telefono = Telefono;
        this.Salario = Salario;
    }

    /**
     * Retorna el identificador del empleado
     * @return Atributo ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Retorna el nombre del empleado
     * @return Atributo Nombre
     */
    public String getNombre() {
        return Nombre;
    }
}
