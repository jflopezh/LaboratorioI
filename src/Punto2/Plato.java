package Punto2;

import java.util.ArrayList;

/**
 * Esta clase define los platos en oferta del restaurante
 * @author Felipe López, David Eslava, Paula Sánchez
 * @version 1.0.0
 */
public class Plato {
    private int ID;
    private String Nombre;
    private String Descripcion;
    private ArrayList<Producto> Ingredientes;
    private double Precio;

    /**
     * Constructor de la clase
     * @param ID Identificador del plato
     * @param Nombre Nombre del plato
     * @param Descripcion Descripcion detallada de lo que trae el plato
     * @param Precio Valor del plato
     */
    public Plato(int ID, String Nombre, String Descripcion, double Precio) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Precio = Precio;
        this.Ingredientes = new ArrayList<>();
    }
    
    /**
     * Añade un nuevo ingrediente al plato
     * @param p Producto ingrediente
     */
    public void addIngrediente(Producto p) {
        this.Ingredientes.add(p);
    }

    /**
     * Retornar el identificador del plato
     * @return Atributo ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Retorna el nombre del plato
     * @return Atributo Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * Retorna el valor a pagar por el plato
     * @return Atributo Precio
     */
    public double getPrecio() {
        return Precio;
    }
}
