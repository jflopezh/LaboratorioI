package Punto3;

import becker.robots.*;

/**
 * Esta clase ejecuta el programa y da solución al laberinto propuesto dentro de
 * ella
 * @author Felipe López, Nicolás Delgado, Paula Sánchez
 * @version 1.0.0 
 */
public class Main {

    /**
     * Método que corre el programa, inicializa el laberinto y el robot con su
     * "Imaginación", para finalmente hacer que el robot recorra el laberinto.
     * @param args
     */
    public static void main(String[] args) {
        //1. Inicialización del laberinto:
        
        Laberinto l = new Laberinto();
        Robot Karel = new Robot(l.getCiudad(), 9, 1, Direction.EAST);
        
        /*2. Se crea otra instancia de la clase Robot para simular la imaginación
        de Karel, ésta instancia es un robot invisible que se mueve con gran agi-
        lidad y evalua el movimiento a realizar en cada intersección, de esta ma-
        nera se obtiene primero el recorrido en función de 0's, 1's y 2's (avanzar,
        girar a la derecha y girar a la izquierda, respectivamente) y así Karel 
        sólo realiza los movimientos relevantes y no los de evaluación en cada 
        intersección, para finalmente salir del laberinto.
        */
        
        Robot Imaginacion = new Robot(l.getCiudad(), 9, 1, Direction.EAST);
        Imaginacion.setTransparency(1);
        Imaginacion.setSpeed(99999);
        
        //2.1 Se obtiene el recorrido:
        
        String Recorrido = l.getRecorrido(Imaginacion);
        
        //2.2 Karel ejecuta el recorrido obtenido:
        
        l.recorrerLaberinto(Recorrido, Karel);
    }
    
}
