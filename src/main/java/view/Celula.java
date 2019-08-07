package view;

import javax.swing.*;
import java.awt.*;

public class Celula extends JLabel {

    private boolean viva;

    public Celula(int x, int y, int ancho, int largo) {
        setBounds(x,y,ancho,largo);
        setOpaque(true);
        viva = false;
        setBackground(Color.BLACK);
    }

    public boolean isViva() {
        return viva;
    }

    public void nacer() {
        viva = true;
        setBackground(Color.BLUE);
    }

    public void morir() {
        viva = false;
        setBackground(Color.BLACK);
    }
}
