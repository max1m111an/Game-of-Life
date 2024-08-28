package Life;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton{
    private int x, y;
    //private JPanel panel = null;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Cell(){
        this.x = 0;
        this.y = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if(x >= 0) {
            this.x = x;
        }
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if(y >= 0) {
            this.y = y;
        }
    }

    //"Смерть" клетки, если соседей НЕ 2 или НЕ 3
     void Die() {
        Main.liveCells.remove(this);

    }

    //Клетка становится "живой", если рядом 3 "живых" клетки
     void Born() {
         Main.liveCells.add(this);

    }

    /*public void setPanel(JPanel panel) {
        this.panel = panel;
    }
      public void paint(Graphics g){
        g.fillRect(this.getX(), this.getY(), Const.Cell_Size, Const.Cell_Size);
    }*/
}
