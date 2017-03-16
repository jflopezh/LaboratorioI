/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Punto2;

/**
 * Esta clase define en su totalidad los deberes del restaurante
 * @author Felipe López, David Eslava, Paula Sánchez
 * @version 1.0.0
 */
public class Pago {
    protected int ID;
    protected String Fecha;
    protected String Descripcion;
    protected double Total;

    /**
     * Constructor de la clase
     * @param ID Identificador del pago
     * @param Fecha Fecha en la que fue realizado el pago
     * @param Descripcion Caracter del pago
     * @param Total Valor total pagado
     */
    public Pago(int ID, String Fecha, String Descripcion, double Total) {
        this.ID = ID;
        this.Fecha = Fecha;
        this.Descripcion = Descripcion;
        this.Total = Total;
    }
    
    /**
     * Retorna el total del pago realizado
     * @return Atributo Total
     */
    public double calcularTotal() {
        return Total;
    }

    /**
     * Retorna la fecha en la que fue realizada el pago
     * @return Atributo Fecha
     */
    public String getFecha() {
        return Fecha;
    }
    
    /**
     * Devuelve la información del pago en una cadena para ser proyectado en pantalla
     * @return Cadena resultante de escribir el pago en términos de sus atributos
     */
    public String getInformacion() {
        return "Pago " + ID + "\n\n"
             + "Fecha: " + Fecha + "\n"
             + "Descripcion: " + Descripcion + "\n"
             + "Total: " + Total + "\n";
    }
}
