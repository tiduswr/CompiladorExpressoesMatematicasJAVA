import java.util.LinkedList;

public class LookAhead<E> extends LinkedList<E> {

    private int index = 0;

    public LookAhead(){}

    public E current(){
        return index >= 0 ? this.get(index) : null;
    }

    public E next(){
        if(index >= 0){
            index++;
            if(index >= size()){
                index--;
                return null;
            }
            return this.get(index);
        }
        return current();
    }

    public E prev(){
        if(index > 0){
            index--;
            return this.get(index);
        }
        return current();
    }

    public boolean ehPrimeiraPosicao() {
        return index == 0;
    }

    @Override
    public void clear(){
        index = 0;
        super.clear();
    }

}
