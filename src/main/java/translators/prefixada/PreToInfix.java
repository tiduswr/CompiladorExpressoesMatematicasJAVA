package translators.prefixada;

import data_structures.SyntaxError;
import models.Digits;
import models.MathExpressionTranslator;
import models.Operator;

public class PreToInfix extends MathExpressionTranslator {

    protected void head() throws SyntaxError {
        String TOKEN = LOOK_AHEAD.atual();

        if(Operator.ehUmOperadorValido(TOKEN)){
            op(); term(); pegarToken(TOKEN); term();
        }else{
            throw new SyntaxError("Erro no elemento " + TOKEN + " [index=" + LOOK_AHEAD.getIndex() + "]");
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
