package view;

import core.Game;
import core.Notificable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public class Tablero implements Notificable {

    private Game         model;
    private JFrame       frame;
    private JPanel       panelCentro;
    private JPanel       panelSur;
    private JMenuBar     barra;
    private JMenuItem    btnIniciar;
    private JMenuItem    btnSalir;
    private JLabel       lblIteracion;
    private JLabel       lblPoblacion;
    private Celula[][]   celulas;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public Tablero(Game model) {
        log.info("Creando tablero.");
        this.model = model;
        frame      = new JFrame("Juego de la vida");
        frame.setBounds(250,250,300,300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        celulas      = new Celula[30][30];
        barra        = new JMenuBar();
        btnIniciar   = new JMenuItem("Iniciar");
        btnSalir     = new JMenuItem("Salir");
        panelCentro  = new JPanel(null);
        panelSur     = new JPanel();
        lblIteracion = new JLabel();
        lblPoblacion = new JLabel();
        log.info("Tamaño: " + celulas.length + "x" + celulas[0].length);

        panelSur.add(lblIteracion);
        panelSur.add(lblPoblacion);

        barra.add(btnIniciar);
        barra.add(btnSalir);

        frame.setJMenuBar(barra);

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                Celula celula = new Celula(i * 10, j * 10, 10, 10);
                celulas[i][j] = celula;
                panelCentro.add(celula);
            }
        }
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panelCentro);
        frame.getContentPane().add(panelSur, BorderLayout.SOUTH);
    }

    public void mostrar() {
        frame.setVisible(true);
    }

    public void cerrar() {
        frame.dispose();
    }

    public JMenuBar getBarra() {
        return barra;
    }

    public JMenuItem getBtnIniciar() { return btnIniciar; };

    public JMenuItem getBtnSalir() {
        return btnSalir;
    }

    public JPanel getPanelSur() { return panelSur; };

    public JLabel getLblIteracion() { return lblIteracion; };

    public JLabel getLblPoblacion() { return lblPoblacion; };

    public int getWidth() { return celulas.length; };

    public int getHeight() { return  celulas[0].length; };

    @Override
    public void actualizar() {
        log.info("Actualizando vista.");
        boolean[][] matriz = this.model.getCelulas();
        // Actualizar panelCentro
        for (int i = 0; i < matriz.length; i++)
            for (int j = 0; j < matriz[0].length; j++)
                if ( matriz[i][j] )
                    this.celulas[i][j].nacer();
                else
                    this.celulas[i][j].morir();
        // Actualizar panelSur
        lblIteracion.setText("Iteración: " + this.model.getIteraion());
        lblPoblacion.setText("Población: " + this.model.getPoblacion());
    }

}
