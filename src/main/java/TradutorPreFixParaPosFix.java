import java.util.List;

public class TradutorPreFixParaPosFix {

    private LookAhead<String> LOOK_AHEAD;
    private final List<String> VALID_DIGITS = List.of("1","2","3","4","5","6","7","8","9","0");
    private String traducao;

    public TradutorPreFixParaPosFix(){
       LOOK_AHEAD = new LookAhead<>();
    }

    public String verificarExpressao(String expressao) throws SintaxError {
        traducao = "";
        receberExpressao(expressao);
        head();
        return traducao;
    }

    private void receberExpressao(String expressao){
        LOOK_AHEAD.resetarLookAhead();
        for(char TOKEN : expressao.toCharArray()){
            LOOK_AHEAD.add(String.valueOf(TOKEN));
        }
    }

    private void match(String terminal) throws SintaxError {
        String TOKEN = LOOK_AHEAD.atual();
        if(TOKEN != null && TOKEN.equals(terminal)){
            LOOK_AHEAD.proximo();
        }else{
            throw new SintaxError(TOKEN + " -> Não reconhecido!");
        }
    }

    private void head() throws SintaxError {
        String TOKEN = LOOK_AHEAD.atual();
        if(TOKEN != null){
            if(TOKEN.equals("+")){
                match("+"); term(); expr(); pegarToken(TOKEN);
            }else if(TOKEN.equals("-")){
                match("-"); term(); expr(); pegarToken(TOKEN);
            }else{
                throw new SintaxError(TOKEN + " -> Não reconhecido!");
            }
        }
    }

    private void expr() throws SintaxError {
        String TOKEN = LOOK_AHEAD.atual();
        if(TOKEN != null){
            if(VALID_DIGITS.contains(TOKEN)){
                term(); head();
            }else{
                head();
            }
        }
    }

    private void term() throws SintaxError {
        String TOKEN = LOOK_AHEAD.atual();
        if(VALID_DIGITS.contains(TOKEN)){
            match(TOKEN); pegarToken(TOKEN);
        }else{
            throw new SintaxError(TOKEN + " -> Não reconhecido!");
        }
    }

    private void pegarToken(String TOKEN){
        traducao = traducao + TOKEN;
    }

}
