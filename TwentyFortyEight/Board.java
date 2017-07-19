package TwentyFortyEight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Created by james on 7/16/17.
 */

public class Board extends JPanel {
    private final StateLogger stateLogger;
    public State state;
    public Long score;
    public final int BOARD_SIZE;



    public Board(final int BOARD_SIZE) {
        this.stateLogger = new StateLogger();
        this.BOARD_SIZE = BOARD_SIZE;
        this.score = 0L;
        State state = new State(BOARD_SIZE);
        state.addNewSquare();
        setLayout(new GridLayout(BOARD_SIZE + 1, BOARD_SIZE));
        evolve(state);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {

                state.keyTyped(keyEvent);
                state.update();
                removeAll();
                evolve(state);
                revalidate();
            }
        });
        setFocusable(true);

        }


    public void evolve(State state){
        state.prettyPrint();
        try {
            stateLogger.writeState(state);
        }catch (IOException e){
            System.out.println("Could not write state log");
        }
        if (!hasNext(state)) {
            System.out.println("Game Over");
        }
        for (Tile t : state.getStates()) {
            //System.out.println("t = " + t);

            JLabel jLabel = new JLabel(t.toString(), SwingConstants.CENTER);
            jLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            jLabel.setSize(200, 200);
            jLabel.setFont(new Font("Arial", Font.BOLD, 144));
            Color color = getColor(t);
            jLabel.setForeground(color);
            add(jLabel);

        }
    }

    public boolean hasNext(State state){
        System.out.println("clone");

        boolean result = false;

        State test = state.clone();

        result |= test.moveDown();

        test = state.clone();
        result |= test.moveUp();

        test = state.clone();
        result |= test.moveLeft();

        test = state.clone();
        result |= test.moveRight();
        return result;
    }



    public static Color getColor(Tile tile){
        switch (tile.getValue() % 10){
            case 0:
                return Color.BLACK;

            case 1:
                return Color.DARK_GRAY;

            case 2:
                return Color.GRAY;

            case 3:
                return Color.BLUE;

            case 4:
                return Color.GREEN;

            case 5:
                return Color.RED;
            case 6:
                return Color.MAGENTA;
            case 7:
                return Color.ORANGE;
            case 8:
                return Color.YELLOW;
            case 9:
                return Color.CYAN;
            case 10:
                return Color.PINK;
            default:
                return Color.BLACK;
        }

    }

}


