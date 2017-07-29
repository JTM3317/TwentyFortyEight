package SolverTools;

import TwentyFortyEight.State;

import java.util.Stack;

/**
 * Created by james on 7/29/17.
 */
public class DisplayBoard{

    private final Stack<State> moves_stack;
    private final Stack<State> redo_stack;

        public DisplayBoard(Stack<State> moves_stack) {
            this.moves_stack = moves_stack;
            this.redo_stack = new Stack<>();
        }


}
