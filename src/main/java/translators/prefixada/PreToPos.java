package translators.prefixada;

import data_structures.SyntaxError;
import models.Digits;
import models.MathExpressionTranslator;
import models.Operator;

public class PreToPos extends MathExpressionTranslator {

    protected void head() throws SyntaxError {
        String TOKEN = LOOK_AHEAD.atual();

        if(Operator.ehUmOperadorValido(TOKEN)){
            op();dig(); expr(); pegarToken(TOKEN);
        }else if(TOKEN != null){
            throw new SyntaxError("Operador Invalido[" + TOKEN + "]");
        }
    }

    protected void expr() throws SyntaxError{
        String TOKEN = LOOK_AHEAD.atual();

        if(Digits.ehUmDigitoValido(TOKEN)){
            dig(); head();
        }else if(Operator.ehUmOperadorValido(TOKEN)){
            head();
        }else{
            throw new SyntaxError("Operador Invalido[" + TOKEN + "]");
        }
    }

}
