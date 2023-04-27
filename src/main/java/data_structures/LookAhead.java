package data_structures;

import java.util.LinkedList;

@SuppressWarnings("unused")
public class LookAhead<E> extends LinkedList<E> {

    private int index = 0;

    public LookAhead(){}

    public E atual(){
        if(index == size())
            return null;
        return index >= 0 ? this.get(index) : null;
    }

    public E proximo(){
        if(index >= 0){
            index++;
            if(index > size())
                index--;
            if(index == size())
                return null;
            return this.get(index);
        }
        return atual();
    }

    public E anterior(){
        if(index > 0){
            index--;
            return this.get(index);
        }
        return atual();
    }

    public void resetarLookAhead(){
        index = 0;
        super.clear();
    }

}
