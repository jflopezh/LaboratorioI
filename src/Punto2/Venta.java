package Punto2;

import java.util.ArrayList;

/**
 * Esta clase define los haberes del restaurantes, que están relacionados 
 * únicamente a la venta de platos
 * @author Felipe López, David Eslava, Paula Sánchez
 * @version 1.0.0
 */
public class Venta {
    private int NroFactura;
    private String Fecha;
    private ArrayList<Plato> Platos;

    /**
     * Constructor de la clase
     * @param NroFactura Número de la factura relacionada a la venta
     * @param Fecha Fecha en la que fue realizada la venta
     */
    public Venta(int NroFactura, String Fecha) {
        this.NroFactura = NroFactura;
        this.Fecha = Fecha;
        this.Platos = new ArrayList<>();
    }
    
    /**
     * Agrega un plato a la venta para ser facturado
     * @param p Plato vendido
     */
    public void addPlato(Plato p) {
        this.Platos.add(p);
    }

    /**
     * Retorna la fecha en la que se realizó la venta
     * @return Atributo Fecha
     */
    public String getFecha() {
        return Fecha;
    }
    
    /**
     * Calcula el total ganado con la venta, a partir de la suma de los platos
     * relacionados a esta
     * @return Total de la venta
     */
    public double calcularTotal() {
        double Total = 0;
        for (Plato p : Platos) {
            Total += p.getPrecio();
        }
        return Total;
    }
    
    /**
     * Retorna la venta escrita en terminos de una cadena para ser proyectada en 
     * pantalla, al se solicitada en registros
     * @return Cadena generada de escribir la venta en términos de sus atributos
     */
    public String getInformacion() {
        String x = "Factura " + NroFactura + "\n"
                 + "Fecha: " + Fecha + "\n\n"
                 + "Producto\t\tPrecio\n\n";
        for (Plato p : Platos) {
            x += p.getNombre() + "\t\t" + p.getPrecio() + "\n";
        }
        x += "\nTotal:\t\t" + this.calcularTotal() + "\n";
        return x;
    }
}
