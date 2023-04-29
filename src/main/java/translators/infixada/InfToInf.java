package translators.infixada;

import data_structures.SyntaxError;
import models.Digits;
import models.MathExpressionTranslator;
import models.Operator;

public class InfToInf extends MathExpressionTranslator {

    protected void head() throws SyntaxError {
        String TOKEN = LOOK_AHEAD.atual();

        if(Digits.ehUmDigitoValido(TOKEN)){
            term(); headTail();
        }else{
            throw new SyntaxError("HEAD -> Erro no Token " + TOKEN);
        }
    }

    protected void headTail() throws SyntaxError{
        String TOKEN = LOOK_AHEAD.atual();

        if(Operator.ehUmOperadorValido(TOKEN)){
            op(); pegarToken(TOKEN); term(); headTail();
        }else if(TOKEN != null){
            throw new SyntaxError("HEAD_TAIL -> Erro no Token" + TOKEN);
        }
    }

    protected void term() throws SyntaxError{
        String TOKEN = LOOK_AHEAD.atual();

        if(Digits.ehUmDigitoValido(TOKEN)){
            dig(); termTail();
        }else{
            throw new SyntaxError("TERM -> rro no Token" + TOKEN);
        }
    }

    protected void termTail() throws SyntaxError{
        String TOKEN = LOOK_AHEAD.atual();

        if(Operator.ehUmOperadorValido(TOKEN)){
            op(); pegarToken(TOKEN); dig(); termTail();
        }else if(TOKEN != null){
            throw new SyntaxError("TERM_TAIL -> Erro no Token" + TOKEN);
        }
    }

}
