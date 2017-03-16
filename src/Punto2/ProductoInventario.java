package Punto2;

/**
 * Esta clase define los productos disponibles en el inventario del restaurante
 * @author Felipe López, David Eslava, Paula Sánchez
 * @version 1.0.0
 */
public class ProductoInventario extends Producto {
    private int Cantidad;

    /**
     * Constructor de la clase
     * @param ID Identificador del producto en el inventario
     * @param Descripcion Descripcion del producto
     * @param Cantidad Cantidad del producto dentro del inventario
     */
    public ProductoInventario(int ID, String Descripcion, int Cantidad) {
        super(ID, Descripcion);
        this.Cantidad = Cantidad;
    }
    
    /**
     * Suma productos del mismo tipo de la instancia al inventario
     * @param Cantidad Cantidad de productos a ingresar
     */
    public void añadirProductos(int Cantidad) {
        this.Cantidad += Cantidad;
    }
    
    /**
     * Retira del inventario productos del mismo tipo de la instancia 
     * @param Cantidad Cantidad de productos a retirar
     */
    public void retirarProductos(int Cantidad) {
        this.Cantidad -= Cantidad;
    }

    /**
     * Retorna la cantidad del producto dentro del inventario
     * @return Atributo Cantidad
     */
    public int getCantidad() {
        return Cantidad;
    }
    
    /**
     * Escribe en una cadena la información del producto dentro del inventario
     * @return Cadena generada al escribir el ProductoInventario en términos de 
     * sus atributos
     */
    public String getInformacion() {
        return ID + "\t\t" + Descripcion + "\t\t" + Cantidad + "\n";
    }
}
