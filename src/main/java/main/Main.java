package main;

import controller.Controller;
import core.Game;
import view.Tablero;

public class Main {

    public static void main(String[] args) {

        Game    juego          = new Game();
        Tablero tablero        = new Tablero(juego);
        Controller controlador = new Controller(juego, tablero);
        controlador.mostrarVista();
    }
}
