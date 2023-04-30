package data_structures;

public class SyntaxError extends Exception {

    private final int INDEX;

    public SyntaxError(String token, int index) {
        super("Erro no elemento " + token + " [index=" + index + "]");
        INDEX = index;
    }

    public SyntaxError(String message){
        super(message);
        INDEX = -1;
    }

}
