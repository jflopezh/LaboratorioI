package Punto1;

/**
 * Esta clase define una bebida en una orden del restaurante
 * @author Felipe López, David Eslava, Paula Sánchez
 * @version 1.0.0
 */
public class Bebida {
    private final String Descripcion;
    private boolean Tamaño; //True = Grande, Flase = Pequeña 
    private double Precio;

    /**
     * Constructor de clase
     * @param Descripcion Nombre de la bebida
     */
    public Bebida(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    /**
     * Modifica el valor de el atributo tamaño
     * @param Tamaño
     */
    public void setTamaño(boolean Tamaño) {
        this.Tamaño = Tamaño;
        if(Tamaño)
           this.Precio = PizzaBaseRestaurant.PBG;
        else
           this.Precio = PizzaBaseRestaurant.PBP;
    }

    /**
     * Método que retorna el nombre de la bebida
     * @return Atributo Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * Método que retorna el tamaño de la bebida
     * @return Atributo Tamaño
     */
    public String getTamaño() {
        if(Tamaño)
            return "Grande";
        return "Peque\u00f1o";
    }

    /**
     * Retorna el precio de la bebida
     * @return Atributo Precio
     */
    public double getPrecio() {
        return Precio;
    }

    /**
     * Retorna la bebida escrita en terminos de sus atributos incluyendo el precio
     * @return La cadena generada al escribir la bebida en terminos de sus atributos
     */
    public String toStringPrecio() {
        return "Bebida\n" + "Descripcion: " + Descripcion + "\nTama\u00f1o: " + 
                this.getTamaño() + "\nPrecio: $" + Precio + "\n";
    }

    /**
     * Retorna la bebida escrita en terminos de sus atributos sin incluir el precio
     * @return La cadena generada al escribir la bebida en terminos de sus atributos
     */
    @Override
    public String toString() {
        return "Bebida\n" + "Descripcion: " + Descripcion + "\nTama\u00f1o: " + 
                this.getTamaño() + "\n";
    }
}
