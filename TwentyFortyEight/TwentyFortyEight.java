package TwentyFortyEight;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Created by james on 7/16/17.
 */
public class TwentyFortyEight extends JFrame {
    private final int BOARD_SIZE;
    private final JPanel gamePanel;

    public TwentyFortyEight(int n){
        this.BOARD_SIZE = n;
        this.gamePanel = new JPanel();
        initUI(gamePanel);
    }

    public void initUI(JPanel gamePanel){
        gamePanel.setSize(1500, 1500);
        setSize(1500, 1500);
        setTitle("2048");
        State state = new State(BOARD_SIZE);
        //add(new Board(BOARD_SIZE));
        gamePanel.add(new Board(BOARD_SIZE));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(gamePanel);
    }

    public static void main(String[] args){
        final int n;
        final boolean log;
        if ((args.length == 1)){
            n = (int) Integer.parseInt(args[0]);
            log = true;
        } else if (args.length == 2){
            n = (int) Integer.parseInt(args[0]);
            log = (boolean) Boolean.parseBoolean(args[1]);
        } else {
            n =4;
            log = true;

        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                TwentyFortyEight game = new TwentyFortyEight(n);
                game.setVisible(true);
            }
        });
    }

}
