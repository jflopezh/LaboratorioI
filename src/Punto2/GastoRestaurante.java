package Punto2;

/**
 * Esta clase define los deberes relacionados con gastos del restaurante, tales
 * como el arriendo, servicios, impuestos, etc.
 * @author Felipe López, David Eslava, Paula Sánchez
 * @version 1.0.0
 */
public class GastoRestaurante extends Pago {
    
    public GastoRestaurante(int ID, String Fecha, String Descripcion, double Total) {
        super(ID, Fecha, Descripcion, Total);
    }
    
}
