package Punto1;

import java.util.ArrayList;

/**
 * Esta clase define las pizzas dentro del sistema, tanto las disponibles como las
 * de cada orden
 * @author Felipe López, David Eslava, Paula Sánchez
 * @version 1.0.0
 */
public class Pizza {

    private final String Descripcion;
    private boolean Base; //True = Corteza Profunda, False = Crujiente
    private final ArrayList<String> Toppings;
    private int Tamaño; //Pulgadas

    /**
     * Constructor de la clase
     * @param Descripcion Nombre de la pizza
     */
    public Pizza(String Descripcion) {
        this.Descripcion = Descripcion;
        this.Toppings = new ArrayList<>();
    }

    /**
     * Constructor #2 de la clase
     * @param Descripcion Nombre de la pizza
     * @param Toppings Lista de toppings que debe llevar la pizza
     */
    public Pizza(String Descripcion, ArrayList<String> Toppings) {
        this.Descripcion = Descripcion;
        this.Toppings = Toppings;
    }

    /**
     * Método que establece el estilo de la base de la pizza, tener en cuenta:<br>
     * true = Corteza Profunda<br>
     * false = Crujiente
     * @param Base Valor que establece el estilo de la base
     */
    public void setBase(boolean Base) {
        this.Base = Base;
    }

    /**
     * Retorna en una cadena el valor del estilo de la base
     * @return Cadena equivalente al estilo de la base
     */
    public String getBase() {
        if (Base) {
            return "Corteza Profunda";
        }
        return "Crujiente";
    }

    /**
     * Método que establece el tamaño de la pizza después de ser creada. Tener
     * en cuenta que el restaurante sólo cuenta con 3 tamaños: 6, 9 y 12 pulgadas
     * @param Tamaño Valor por el cuál se reemplazará el atributo
     */
    public void setTamaño(int Tamaño) {
        this.Tamaño = Tamaño;
    }

    /**
     * Retorna el tamaño de la pizza
     * @return Valor del atributo Tamaño
     */
    public int getTamaño() {
        return Tamaño;
    }

    /**
     * Retorna el nombre o descripción de la pizza
     * @return Valor del atributo Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * Retorna los toppings que contiene la pizza en terminos de un ArrayList de 
     * cadenas
     * @return ArrayList que contiene los Toppings de la pizza
     */
    public ArrayList<String> getToppingsL() {
        return Toppings;
    }

    /**
     * Retorna los toppings de la pizza escritos en una sola cadena separados por
     * comas
     * @return Cadena de Toppings
     */
    public String getToppings() {
        String x = "";
        for (int i = 0; i < this.Toppings.size(); i++) {
            x += this.Toppings.get(i);
            if (i != this.Toppings.size() - 1) {
                x += ", ";
            }
        }
        return x;
    }

    /**
     * Añade un topping a la pizza
     * @param t Topping a ser agregado al Array
     */
    public void addTopping(String t) {
        this.Toppings.add(t);
    }

    /**
     * Calcula el precio de la pizza en función de su tamaño y la cantidad de 
     * toppings que lleve
     * @return El precio total de la pizza
     */
    public double CalcularPrecio() {
        double p = 0;
        p += (this.Toppings.size() * PizzaBaseRestaurant.PT);
        switch (this.Tamaño) {
            case 6:
                p += PizzaBaseRestaurant.PP6I;
                break;
            case 9:
                p += PizzaBaseRestaurant.PP9I;
                break;
            case 12:
                p += PizzaBaseRestaurant.PP12I;
                break;
        }
        return p;
    }

    /**
     * Retorna la pizza escrita en terminos de sus atributos incluyendo el precio
     * @return La cadena generada al escribir la pizza en terminos de sus atributos
     */
    public String toStringPrecio() {
        return "Pizza\n" + "Descripcion: " + Descripcion + "\nBase: " + this.getBase()
                + "\nToppings: " + this.getToppings() + "\nTama\u00f1o: " + Tamaño + " pulg\n"
                + "Precio: $" + this.CalcularPrecio() + "\n";
    }

    /**
     * Retorna la pizza escrita en terminos de sus atributos, pero teniendo en
     * cuenta sólo los basicos: Descripción y Toppings
     * @return La cadena generada al escribir la pizza en terminos de sus atributos
     */
    public String toStringPrefab() {
        return "Pizza: " + Descripcion + "\nToppings: " + this.getToppings() + "\n";
    }

    /**
     * Retorna la pizza escrita en terminos de sus atributos sin incluir el precio
     * @return La cadena generada al escribir la pizza en terminos de sus atributos
     */
    @Override
    public String toString() {
        return "Pizza\n" + "Descripcion: " + Descripcion + "\nBase: " + this.getBase()
                + "\nToppings: " + this.getToppings() + "\nTama\u00f1o: " + Tamaño + " pulg\n";
    }

    /**
     * Clona la pizza actual y retorna una nueva instacia de la clase con los
     * mismos valores en los atributos
     * @return Pizza con los mismos valores que la actual
     */
    public Pizza Clonar() {
        return new Pizza(Descripcion, (ArrayList<String>) Toppings.clone());
    }
}
