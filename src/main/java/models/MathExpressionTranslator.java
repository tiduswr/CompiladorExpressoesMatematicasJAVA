package models;

import data_structures.LookAhead;
import data_structures.SyntaxError;

@SuppressWarnings("unused")
public abstract class MathExpressionTranslator {

    protected final LookAhead<String> LOOK_AHEAD;
    protected String traducao;

    public MathExpressionTranslator(){
       LOOK_AHEAD = new LookAhead<>();
    }

    public MathExpressionTranslator verificarExpressao(String expressao) throws SyntaxError {
        traducao = "";
        receberExpressao(expressao);
        head();
        return this;
    }

    public String pegarUltimaTraducao(){
        return traducao;
    }

    protected abstract void head() throws SyntaxError ;

    protected void receberExpressao(String expressao){
        LOOK_AHEAD.resetarLookAhead();
        for(char TOKEN : expressao.toCharArray()){
            LOOK_AHEAD.add(String.valueOf(TOKEN));
        }
    }

    protected void match(String terminal) throws SyntaxError {
        String TOKEN = LOOK_AHEAD.atual();
        if(TOKEN != null && TOKEN.equals(terminal)){
            LOOK_AHEAD.proximo();
        }else{
            throw new SyntaxError(TOKEN + " -> Não reconhecido!");
        }
    }

    protected void op() throws SyntaxError {
        String TOKEN = LOOK_AHEAD.atual();

        if(Operator.ehUmOperadorValido(TOKEN)){
            match(TOKEN);
        }else{
            throw new SyntaxError("Operador Invalido[" + TOKEN + "]");
        }
    }

    protected void dig() throws SyntaxError {
        String TOKEN = LOOK_AHEAD.atual();

        if(Digits.ehUmDigitoValido(TOKEN)){
            match(TOKEN); pegarToken(TOKEN);
        }else{
            throw new SyntaxError("Digito Invalido[" + TOKEN + "]");
        }
    }

    protected void pegarToken(String TOKEN){
        traducao = traducao + TOKEN;
    }

}
