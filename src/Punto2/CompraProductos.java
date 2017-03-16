package Punto2;

import java.util.ArrayList;

/**
 * Esta clase define los deberes relacionados con la compra de productos
 * @author Felipe López, David Eslava, Paula Sánchez
 * @version 1.0.0
 */
public class CompraProductos extends Pago {
    private ArrayList<ProductoInventario> Compra;

    /**
     * Constructor de la clase
     * @param ID Identificador de la compra
     * @param Fecha Fecha en la que fue realizada la compra
     * @param Descripcion Caracter de la compra
     * @param Total Valor total pagado
     */
    public CompraProductos(int ID, String Fecha, String Descripcion, double Total) {
        super(ID, Fecha, Descripcion, Total);
        this.Compra = new ArrayList<>();
    }
    
    /**
     * Añade un nuevo producto a la compra
     * @param p Producto a añadir
     */
    public void addProducto(ProductoInventario p) {
        this.Compra.add(p);
    }
    
    /**
     * Obtiene la información de la compra para ser proyectada en pantalla
     * @return La cadena generada al escribir el pago en términos de sus atributos
     */
    @Override
    public String getInformacion() {
        String x = super.getInformacion() + "Producto Comprado\t\tCant\n\n";
        for (ProductoInventario p : Compra) {
            x += p.getDescripcion() + "\t\t" + p.getCantidad() + "\n";
        }
        return x;
    }    
}
