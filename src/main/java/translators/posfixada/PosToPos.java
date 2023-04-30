package translators.posfixada;

import data_structures.SyntaxError;
import models.Digits;
import models.Operator;
import models.ReversedMathExpressionTranslator;

/**
 * Foi necessário reverter a entrada de dados para validar as expressões
 * para a leitura ser feita da direita para a esquerda
 */
public class PosToPos extends ReversedMathExpressionTranslator {

    protected void head() throws SyntaxError {
        String TOKEN = LOOK_AHEAD.atual();

        if(Operator.ehUmOperadorValido(TOKEN)){
            op(); pegarToken(TOKEN); term(); term();
        }else{
            throw new SyntaxError(TOKEN, LOOK_AHEAD.getIndexReverseFix());
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
