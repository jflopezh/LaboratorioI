package Punto2;

/**
 * Esta clase define los productos disponible en el restaurante
 * @author Felipe López, David Eslava, Paula Sánchez
 * @version 1.0.0
 */
public class Producto {
    protected int ID;
    protected String Descripcion;

    /**
     * Constructor de la clase
     * @param ID Identificador del producto
     * @param Descripcion Nombre del producto
     */
    public Producto(int ID, String Descripcion) {
        this.ID = ID;
        this.Descripcion = Descripcion;
    }

    /**
     * Devuelve el identificador del producto
     * @return Atributo ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Retorna la descripcion del producto
     * @return Atributo Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }
}
