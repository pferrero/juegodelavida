package core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Game implements Runnable{

    private int          iteracion;
    private int          poblacion;
    private boolean[][]  matriz;

    private final Logger log = LoggerFactory.getLogger(getClass());
    private List<Notificable> observers = new ArrayList<Notificable>();

    public Game() {
        this.iteracion = 0;
        this.poblacion = 0;
        this.matriz    = new boolean[30][30];
        matriz[5][11] = true;
        matriz[6][11] = true;
        matriz[7][10] = true;
        matriz[7][12] = true;
        matriz[8][11] = true;
        matriz[9][11] = true;
        matriz[10][11] = true;
        matriz[11][11] = true;
        matriz[12][10] = true;
        matriz[12][12] = true;
        matriz[13][11] = true;
        matriz[14][11] = true;

        matriz[24][20] = true;
        matriz[25][20] = true;
        matriz[26][20] = true;
    }

    @Override
    public void run() {
        log.info("Iniciando juego.");
        while ( !Thread.interrupted() ) {
            boolean[][] newMatriz = new boolean[30][30];
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[0].length; j++) {
                    newMatriz[i][j] = matriz[i][j];
                    int cantVecinos = contarVecinos(i, j);
                    if ( matriz[i][j] ) {
                        if (cantVecinos < 2 || cantVecinos > 3)
                            newMatriz[i][j] = false;
                    } else {
                        if (cantVecinos == 3)
                            newMatriz[i][j] = true;
                    }
                    //log.info("Cantidad de vecinos de (" + i + "," + j + ") = " + cantVecinos);
                }
            }
            this.matriz = newMatriz;
            actualizarObservers();
        }

    }

    private int contarVecinos(int x, int y) {
        int vecinos = 0;
        for (int i = x - 1; i <= (x + 1); i++) {
            for (int j = y - 1; j <= (y + 1); j++) {
                try {
                    if( !(i == x && j == y) ) {
                        if ( matriz[i][j] ) vecinos += 1;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    //log.info("Indice fuera de rango: i = " + i + ", j = " + j);
                }
            }
        }
        return vecinos;
    }

    public void addObserver(Notificable observer) {
        observers.add(observer);
    }

    private void actualizarObservers() {
        for ( Notificable observer : observers )
            observer.actualizar();
    }

    public boolean[][] getCelulas() {
        return matriz.clone();
    }
}
