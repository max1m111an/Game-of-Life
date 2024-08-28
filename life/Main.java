package Life;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class Main {
    private final static String Name = "Game of Life";

    JFrame jf = new JFrame(Name);
    JPanel text_stuff = new JPanel();
    JPanel panel = new JPanel();

    JLabel text = new JLabel();
    JButton Restart = new JButton("Restart retard");
    JButton Start = new JButton("Start fart");

    Cell[][] cc = new Cell[Const.Rows][Const.Cols];
    static ArrayList<Cell> liveCells = new ArrayList();

    Main() {
        //Инициализация текстовых окон и игрового поля
        jf.setSize(Const.Width, Const.Height + 200);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);
        jf.setLayout(new BorderLayout());
        jf.setVisible(true);

        text.setFont(new Font("Impact", Font.PLAIN, 25));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setText("\\__1984__/");
        text.setOpaque(true);

        text_stuff.setLayout(new GridLayout(1, 3));

        Restart.setBackground(Color.BLACK);
        Restart.setForeground(Color.WHITE);
        Restart.setFont(new Font("Impact", Font.PLAIN, 25));

        Start.setBackground(Color.BLACK);
        Start.setForeground(Color.WHITE);
        Start.setFont(new Font("Impact", Font.PLAIN, 25));

        text_stuff.add(text);
        text_stuff.add(Restart);
        text_stuff.add(Start);
        jf.add(text_stuff, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(Const.Rows, Const.Cols));
        jf.add(panel);
        panel.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Кнопка перезапуска
        Restart.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    //очистка панели клеток
                    panel.removeAll();
                    panel.revalidate();
                    panel.repaint();
                    if (!Start.isEnabled()) {
                        Start.setEnabled(true);
                    }
                }
            }
        });

        //Кнопка запуска
        Start.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    start();
                    Start.setEnabled(false);
                }
            }
        });

        DrawLayout();
        jf.setVisible(true);
        /*while(!Game_Over()){
            //...
        }*/
    }

    //Проверка на "жизнь" клетки
    public int checkLife(int x, int y) {
        if (x < 0 || x >= Const.Rows || y < 0 || y >= Const.Cols) {
            return 0;
        }
        if (liveCells.contains(cc[x][y])) {
            return 1;
        }
        return 0;
    }

    //проверка жизни клетки
    public void check(int x, int y) {
        if (x < 0 || x >= Const.Rows || y < 0 || y >= Const.Cols) return;

        Cell c = cc[x][y];
        int count_of_cells = 0;

        count_of_cells += checkLife(x - 1, y - 1);
        count_of_cells += checkLife(x - 1, y + 1);
        count_of_cells += checkLife(x - 1, y);
        count_of_cells += checkLife(x + 1, y - 1);
        count_of_cells += checkLife(x + 1, y + 1);
        count_of_cells += checkLife(x + 1, y);
        count_of_cells += checkLife(x, y - 1);
        count_of_cells += checkLife(x, y + 1);

        if ((count_of_cells != 2 || count_of_cells != 3) && liveCells.contains(c)){
            c.Die();
        } else {
            if (count_of_cells >= 3 && !liveCells.contains(c)) {
                c.Born();
            }
        }


    }

    //Пользователь рисует изначальную схему, по которой прогоняется
    //алогритм жизни
    public void DrawLayout() {

    }

    //Прекращение игры
    //1. На поле нет живых клеток
    //2. Периодическая конфигурация (сделать потом)
    public boolean Game_Over() {
        return liveCells.size() == 0 ? false:true;
    }

    //Основа
    public void start() {
        if (Start.isEnabled()) {
            for (int i = 0; i < Const.Height; i += Const.Cell_Size) {
                for (int j = 0; j < Const.Width; j += Const.Cell_Size) {
                    Cell c = new Cell(j, i);
                    /*JPanel ok = new JPanel() {
                        @Override
                        protected void paintComponent(Graphics g) {
                            //super.paintComponent(g);
                            g.setColor(Color.RED);
                            for (int k = 0; k < Const.Height; k += Const.Cell_Size) {
                                //g.drawLine(0, k, Const.Width, k);
                                g.drawLine(k, 0, k, Const.Height);
                            }
                            g.setColor(Color.BLACK);
                            //c.paint(g);
                        }
                    };*/
                    c.setBackground(Color.WHITE);
                    c.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    c.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            Cell c = (Cell) e.getSource();
                            if(e.getButton() == MouseEvent.BUTTON1) {
                                if (c.isEnabled()) {
                                    c.setBackground(Color.BLACK);
                                    c.setEnabled(false);
                                }else{
                                    c.setBackground(Color.WHITE);
                                    c.setEnabled(true);
                                }
                            }
                        }
                    });
                    //c.setPanel(ok);
                    panel.add(c);
                    //panel.repaint();
                    panel.revalidate();
                }
            }
        } else System.out.println("FUCK YOU");
    }
}