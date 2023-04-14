import java.util.List;

public class Tradutor {

    private LookAhead<String> LOOK_AHEAD;
    private final List<String> VALID_DIGITS = List.of("1","2","3","4","5","6","7","8","9","0");

    public Tradutor(){
       LOOK_AHEAD = new LookAhead<>();
    }

    public void checkExpressao(String expressao) throws SintaxError {
        receberExpressao(expressao);
        expr();
    }

    private void receberExpressao(String expressao){
        LOOK_AHEAD.clear();
        for(char TOKEN : expressao.toCharArray()){
            LOOK_AHEAD.add(String.valueOf(TOKEN));
        }
    }

    private void match(String terminal) throws SintaxError {
        String TOKEN = LOOK_AHEAD.current();
        if(TOKEN != null && TOKEN.equals(terminal)){
            LOOK_AHEAD.next();
        }else{
            throw new SintaxError(TOKEN + " -> Não reconhecido!");
        }
    }

    private void term() throws SintaxError {
        String TOKEN = LOOK_AHEAD.current();
        if(VALID_DIGITS.contains(TOKEN)){
            LOOK_AHEAD.next();
        }else{
            throw new SintaxError(TOKEN + " -> Não reconhecido!");
        }
    }

    private void expr() throws SintaxError {
        String TOKEN = LOOK_AHEAD.current();
        if(TOKEN != null){
            if(TOKEN.equals("+")){
                match("+"); term(); expr();
            }else if(TOKEN.equals("-")){
                match("-"); term(); expr();
            }else if(!LOOK_AHEAD.ehPrimeiraPosicao()){
                term();
            }else{
                throw new SintaxError(TOKEN + " -> Não reconhecido!");
            }
        }else if(LOOK_AHEAD.ehPrimeiraPosicao()){
            throw new SintaxError("VAZIO");
        }
    }

}
