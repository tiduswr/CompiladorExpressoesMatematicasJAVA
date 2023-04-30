package translators.prefixada;

import data_structures.SyntaxError;
import models.Digits;
import models.MathExpressionTranslator;
import models.Operator;

public class PreToPos extends MathExpressionTranslator {

    protected void head() throws SyntaxError {
        String TOKEN = LOOK_AHEAD.atual();

        if(Operator.ehUmOperadorValido(TOKEN)){
            op(); term(); term(); pegarToken(TOKEN);
        }else{
            throw new SyntaxError(TOKEN, LOOK_AHEAD.getIndex());
        }
    }

    protected void term() throws SyntaxError{
        String TOKEN = LOOK_AHEAD.atual();

        if(Digits.ehUmDigitoValido(TOKEN)){
            dig();
        }else{
            head();
        }
    }

}
