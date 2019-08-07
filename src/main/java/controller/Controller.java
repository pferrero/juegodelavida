package controller;

import core.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import view.Tablero;

public class Controller {


    private Game    game;
    private Tablero tablero;
    private Thread  hilo;

    private final Logger log = LoggerFactory.getLogger(getClass());

    public Controller(Game game, Tablero tablero) {
        this.game    = game;
        this.tablero = tablero;
        this.hilo    = new Thread(game);

        this.game.addObserver(this.tablero);

        this.tablero.getBtnSalir().addActionListener( a -> this.tablero.cerrar() );
        this.tablero.getBtnIniciar().addActionListener( a -> iniciar() );
    }

    private void iniciar() {
        if( !hilo.isAlive() )
            hilo.start();
        else
            hilo.interrupt();
    }

    public void mostrarVista() {
        this.tablero.actualizar();
        this.tablero.mostrar();
    }

}
