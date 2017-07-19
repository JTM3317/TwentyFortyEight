package TwentyFortyEight;

/**
 * Created by james on 7/15/17.
 */
public class Tile {

    private int value;


    public Tile(){
        this.value = 0;

    }

    public Tile(int i){
        this.value = i;
    }

    public void setValue(int v){
        this.value = v;
    }

    public int getValue(){
        return this.value;
    }

    public int getDisplayValue(){
        if (!this.isEmpty()) {
            return (int) Math.pow(2, (double) this.value);
        }else{
            return (int) 0;
        }
    }
    public void increment(){
        setValue(getValue()+1);
    }

    public void resetValue(){
        setValue(0);
    }

    public boolean tryMerge(Tile otherTile) {
        //System.out.println("merging " + this + "and " + otherTile);
        if (this.equals(otherTile) && !isEmpty()) {
            otherTile.increment();
            resetValue();
            return true;
        }
        return false;
    }


    public boolean tryMove(Tile otherTile){
        //System.out.println("moving " + this + "and " + otherTile);
        if (otherTile.isEmpty() &&!isEmpty()){
            otherTile.setValue(getValue());
            resetValue();
            return true;
        }
        return false;

    }




    public boolean isEmpty(){
        return (this.value == 0) ? true : false;
    }
    @Override
    public String toString(){
        return String.valueOf(this.getDisplayValue());
    }

    @Override
    public boolean equals(Object that){
        if (!(that instanceof Tile)) { return false;}
        Tile thatTile = (Tile) that;
        return (this.getValue() == thatTile.getValue());
    }


    @Override
    public int hashCode(){
        return (int) getValue();
    }

    @Override
    public Tile clone(){
        return new Tile(this.getValue());
    }


}
