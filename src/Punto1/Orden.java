package Punto1;

/**
 * Esta clase define las ordenes realizadas en el restaurante a travez de 
 * dispositivos en las mesas
 * @author Felipe López, David Eslava, Paula Sánchez
 * @version 1.0.0
 */
public class Orden {

    private final int ID;
    private final int Mesa;
    private final Pizza Pizza;
    private final Bebida Bebida;
    private int Estado;

    /**
     * Constructor de la clase
     * @param ID Identificador de la orden
     * @param Mesa Numero de mesa relacionada a la orden
     * @param Pizza Pizza seleccionada en la orden
     * @param Bebida Bebida seleccionada en la orden
     */
    public Orden(int ID, int Mesa, Pizza Pizza, Bebida Bebida) {
        this.ID = ID;
        this.Mesa = Mesa;
        this.Pizza = Pizza;
        this.Bebida = Bebida;
        this.Estado = 0;
    }

    /**
     * Retorna el identificador de la orden
     * @return Atributo ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Este método modifica el estado de la orden, tener en cuenta:<br>
     * 0 = En cola<br>
     * 1 = En preparación<br>
     * 2 = Servida
     * @param Estado Entero por el cual se reemplazará el atributo 
     */
    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

    /**
     * Retorna el estado de la orden en terminos de un String
     * @return Cadena equivalente al estado, el cuál está establecido en terminos 
     * de un entero
     */
    public String getEstado() {
        switch (this.Estado) {
            case 0:
                return "En cola";
            case 1:
                return "En preparacion";
            default:
                return "Servida";
        }
    }

    /**
     * Calcula el valor total a pagar por la orden, en función de la suma de los
     * precios de su bebida y su pizza
     * @return
     */
    public double getTotal() {
        return Pizza.CalcularPrecio() + Bebida.getPrecio();
    }

    /**
     * Retorna la orden escrita en terminos de sus atributos incluyendo el precio
     * @return La cadena generada al escribir la bebida en terminos de sus atributos
     */
    public String toStringPrecio() {
        return "Orden " + ID + "\nMesa: " + Mesa + "\nEstado: " + this.getEstado() + "\n"
                + "  ..........................................................  \n"
                + Pizza.toStringPrecio()
                + "  ..........................................................  \n"
                + Bebida.toStringPrecio()
                + "  ..........................................................  \n"
                + "Precio total de la orden: $" + this.getTotal() + "\n"
                + "--------------------------------------------------------------";
    }

    /**
     * Retorna la orden escrita en terminos de sus atributos sin incluir el precio
     * @return La cadena generada al escribir la bebida en terminos de sus atributos
     */
    @Override
    public String toString() {
        return "Orden " + ID + "\nMesa: " + Mesa + "\nEstado: " + this.getEstado() + "\n"
                + "  ..........................................................  \n"
                + Pizza.toString()
                + "  ..........................................................  \n"
                + Bebida.toString()
                + "--------------------------------------------------------------";
    }
}
