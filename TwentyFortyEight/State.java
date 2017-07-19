package TwentyFortyEight;



import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by james on 7/15/17.
 */
public class State {
    private final Tile[] stateArray;
    private final int BOARD_SIZE;
    private Move moveCommand;


    public State(final int boardSize) {
        this.BOARD_SIZE = boardSize;
        stateArray = new Tile[boardSize * boardSize];
        for (int i = 0; i < stateArray.length; i++) {
            stateArray[i] = new Tile();
        }
    }

    public State(State otherState){
        this.BOARD_SIZE = otherState.get_BoardSize();
        stateArray =  new Tile[this.get_BoardSize() * this.get_BoardSize()];
        for (int i = 0; i < stateArray.length; i++) {
            stateArray[i] = otherState.getTile(i).clone();
        }
    }

    public State() {
        this(4);
    }

    public Tile[] getStates() {
        return stateArray;
    }

    private void setTile(int index, int Value){
        stateArray[index].setValue(Value);
    }

    private Tile getTile(int index){
        return getStates()[index];
    }

    public int get_BoardSize() {
        return this.BOARD_SIZE;
    }

    public void update() {
        switch (moveCommand) {
            case UP:
                moveUp();
                break;

            case DOWN:
                moveDown();
                break;

            case LEFT:
                moveLeft();
                break;

            case RIGHT:
                moveRight();
                break;
            default:
                break;
        }

    }

    public boolean moveLeft() {
        boolean has_moved = false;
        System.out.println("Moving left");
        for (int i = 0; i < get_BoardSize(); i++) {
            List<Tile> column = new ArrayList<>();
            List<Integer> indexes = new ArrayList<>();
            for (int j = 0; j < get_BoardSize(); j++) {
                column.add(getStates()[(get_BoardSize() * i) + j]);
                indexes.add((get_BoardSize() * i) + j);
//                System.out.println("i = " + i);
//                System.out.println("j = " + j);
//                System.out.println((get_BoardSize() * i) + j);
            }
            has_moved |=moveTileArray(column, false);
            has_moved |=mergeTileArray(column);
            has_moved |=moveTileArray(column, false);
        }
        if (has_moved) { addNewSquare();}
        return has_moved;
    }
    public boolean moveRight() {
        boolean has_moved =false;
        System.out.println("Moving Right");
        for (int i = 0; i < get_BoardSize(); i++) {
            List<Tile> column = new ArrayList<>();
            List<Integer> indexes = new ArrayList<>();
            for (int j = 0; j < get_BoardSize(); j++) {
                column.add(getStates()[(get_BoardSize() * i) + j]);
                indexes.add((get_BoardSize() * i) + j);

            }
            //System.out.println("Indices " + indexes);
            Collections.reverse(column);
            has_moved |=moveTileArray(column, false);
            has_moved |=mergeTileArray(column);
            has_moved |=moveTileArray(column, false);
        }
        if (has_moved) { addNewSquare();}

        return has_moved;
    }


    public boolean moveDown() {
        boolean has_moved = false;
        System.out.println("Moving Down");
        for (int i =0; i< get_BoardSize(); i++){
            List<Tile> column = new ArrayList<>();
            List<Integer> indexes = new ArrayList<>();
            for (int j= 0; j < get_BoardSize(); j++) {
                column.add(getStates()[(get_BoardSize() * j) + i]);
                indexes.add((get_BoardSize() * j) + i);
//                System.out.println("i = " + i);
//                System.out.println("j = " + j);
//                System.out.println((get_BoardSize() * i) + j);
            }
            //System.out.println("Indices " +indexes);
            Collections.reverse(column);
            has_moved |=moveTileArray(column, false);
            has_moved |=mergeTileArray(column);
            has_moved |=moveTileArray(column, false);
        }

        if (has_moved){ addNewSquare();}
        return has_moved;
    }

    public boolean moveUp() {
        boolean has_moved = false;
        System.out.println("Moving Up");
        for (int i =0; i< get_BoardSize(); i++){
            List<Tile> column = new ArrayList<>();
            List<Integer> indexes = new ArrayList<>();
            for (int j= 0; j < get_BoardSize(); j++) {
                column.add(getStates()[(get_BoardSize() * j) + i]);
                indexes.add((get_BoardSize() * j) + i);
//                System.out.println("i = " + i);
//                System.out.println("j = " + j);
//                System.out.println((get_BoardSize() * i) + j);
          }
                //System.out.println("Indices " +indexes);

                has_moved |= moveTileArray(column, false);
                has_moved |=mergeTileArray(column);
                has_moved |=moveTileArray(column, false);
        }
    if (has_moved) {addNewSquare();}

    return has_moved;
    }

    private boolean mergeTileArray(List<Tile> column){
        boolean has_moved = false;
        //System.out.println("MERGING column_before = " + column);
        for (int i = 0; i< column.size()-1; i++) {
            //System.out.println("i = " + i);
            has_moved |= column.get(i + 1).tryMerge(column.get(i));
        }
        //System.out.println("column_after = " + column);
        //System.out.println("has_merged = " + has_moved);
        return has_moved;

        }
    private boolean moveTileArray(List<Tile> column, boolean last){
        boolean flag = false;
        boolean has_moved = last;
        //System.out.println("MOVING column_before = " + column);
        for (int i = 0; i< column.size()-1; i++) {
            //System.out.println("i = " + i);
            //System.out.println("tile = " + column.get(i));
            flag |= column.get(i + 1).tryMove(column.get(i));
            has_moved |= flag;

        }
        //System.out.println("column_after = " + column);

        if (flag){
            //System.out.println("RECURSING");
            moveTileArray(column, has_moved);
        }

        return has_moved;
    }





    public void addNewSquare(){


        Random random = new Random();
        int valueNewSquare = random.nextInt(2)+1;
        int newSquareIndex = random.nextInt((getStates().length));
        if (!getStates()[newSquareIndex].isEmpty()){
            //System.out.println("Trying again, this number does not work "  + newSquareIndex);
            addNewSquare();
        }else {
            setTile(newSquareIndex, valueNewSquare);
        }
    }

    public void keyTyped(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                moveCommand = Move.UP;
                break;
            case KeyEvent.VK_DOWN:
                moveCommand = Move.DOWN;
                break;
            case KeyEvent.VK_RIGHT:
                moveCommand = Move.RIGHT;
                break;
            case KeyEvent.VK_LEFT:
                moveCommand = Move.LEFT;
                break;
            default:
                break;
        }

    }

    public Integer[][] toGrid() {
        Integer[][] result = new Integer[BOARD_SIZE][BOARD_SIZE];
        for (int col = 0; col < BOARD_SIZE; col++) {
            for (int row = 0; row < BOARD_SIZE; row++) {
                result[row][col] = (Integer) stateArray[row + col].getDisplayValue();


            }
        }
        return result;
    }

    public static String str(int i) {
        return i < 0 ? "" : str((i / 26) - 1) + (char)(65 + i % 26);
    }

    public String[] getColNames(){
        String[] result = new String[BOARD_SIZE];
        for (int i =0; i<BOARD_SIZE; ++i) {
            result[i] = str(i);
        }
        return result;
    }

    public void prettyPrint(){
        for (int i=0; i<get_BoardSize(); i++){
            List<Integer> temp= new LinkedList<>();
            for (int j=0; j<get_BoardSize(); j++){
                temp.add(getStates()[i*get_BoardSize()+j].getDisplayValue());
            }
            System.out.println(temp);
        }
    }

    public String toCsvString(){
        StringBuilder sb = new StringBuilder();
        for (Tile t: getStates()){
            sb.append(t.getValue() + ", ");
            }
        sb = sb.deleteCharAt(sb.length()-1);
            sb = sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
    @Override
    public State clone(){
        State result = new State(this);

        return result;
    }



}
