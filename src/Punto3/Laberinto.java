package Punto3;

import becker.robots.*;

/**
 * Esta clase define un laberinto creado con la libreria becker
 * @author Felipe López, Nicolás Delgado, Paula Sánchez
 * @version 1.0.0 
 */
public class Laberinto {

    private final City Ciudad;

    /**
     * Constructor de la clase. Inicializa el laberinto
     */
    public Laberinto() {
        Ciudad = new City(11, 12);
        Ciudad.setFrameTitle("Laberinto");
        Wall[] Paredes;
        Paredes = new Wall[76];
        int x = 0;
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i == 2 && (j < 4 && j > 0)) || (i == 1 && (j > 3 && j < 9)) 
                   || (i == 9 - j && (j > 3 && j < 7)) || (i == 4 && j > 5) ||
                   (i == 7 && (j > 1 && j < 5))) {
                    
                    Paredes[x] = new Wall(Ciudad, i, j, Direction.NORTH);
                    x++;
                }
                if ((i == 8 && j != 0 && j != 6 && j != 7 && j != 8 && j != 3) || 
                   (i == 7 && (j > 5 && j < 9)) || (i == 3 && (j < 3 && j > 0)) 
                   || (i == 9 && j > 0)) {
                    
                    Paredes[x] = new Wall(Ciudad, i, j, Direction.SOUTH);
                    x++;
                }
                if ((j == 8 && (i == 1 || i == 2 || i == 6 || i == 7)) || 
                   (j == 7 && (i == 4 || i == 5)) || (i == 9 - j && (j > 3 && j < 7))
                   || (j == 4 && (i == 6 || i == 1)) || (j == 2 && (i > 4 && i < 8))
                   || (j == 1 && i > 1)) {
                    
                    Paredes[x] = new Wall(Ciudad, i, j, Direction.WEST);
                    x++;
                }
                if ((j == 2 && (i == 2 || i == 3)) || (j == 4 && (i == 7 || i == 8))
                   || (j == 5 && (i > 5 && i < 9)) || j == 9) {
                    
                    Paredes[x] = new Wall(Ciudad, i, j, Direction.EAST);
                    x++;
                }
            }
        }
        Thing t = new Thing(Ciudad, 1, 10);
    }

    /**
     * Obtiene el recorrido necesario para salir del laberinto en función de un 
     * String, dicho recorrido se obtiene al dirigir al robot siempre hacia la 
     * pared derecha hasta encontrar un objeto que indica el punto de llegada.
     * @param im Robot imaginación
     * @return Recorrido para salir del laberinto en un String como un conjunto
     * de instrucciones:<br>
     * - 0 = Avanzar <br>
     * - 1 = Girar hacia la derecha <br>
     * - 2 = Girar hacia la izquierda
     */
    public String getRecorrido(Robot im) {
        String x = "";
        int y = 0;
        while (!im.canPickThing()) {
            for (int i = 0; i < 3; i++) {
                im.turnLeft();
            }
            for (int i = 0; i < 4; i++) {
                if (im.frontIsClear()) {
                    y = i;
                    im.move();
                    break;
                }
                im.turnLeft();
            }
            switch (y) {
                case 0:
                    x += "10";
                    break;
                case 1:
                    x += "0";
                    break;
                case 2:
                    x += "20";
                    break;
                case 3:
                    x += "220";
                    break;
            }
        }
        return x;
    }

    /**
     * Recibe una cadena y la decodifica en un conjunto de instrucciones, que son
     * las que el robot debe realizar para salir del laberinto
     * @param x Cadena de recorrido en funcion de 0's 1's y 2's
     * @param r Robot que realizará los movimientos establecidos en el String
     */
    public void recorrerLaberinto(String x, Robot r) {
        for (int i = 0; i < x.length(); i++) {
            switch (x.charAt(i)) {
                case '0':
                    r.move();
                    break;
                case '1':
                    for (int j = 0; j < 3; j++) {
                        r.turnLeft();
                    }
                    break;
                case '2':
                    r.turnLeft();
                    break;
            }
        }
    }

    /**
     * Retorna la ciudad en la que es creada el laberinto
     * @return Ciudad laberinto
     */
    public City getCiudad() {
        return Ciudad;
    }
}
