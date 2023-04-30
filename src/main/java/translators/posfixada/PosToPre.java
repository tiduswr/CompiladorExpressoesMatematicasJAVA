package translators.posfixada;

import data_structures.SyntaxError;
import models.Digits;
import models.Operator;
import models.ReversedMathExpressionTranslator;

/**
 * Foi necessário reverter a entrada de dados para validar as expressões
 * para a leitura ser feita da direita para a esquerda
 */
public class PosToPre extends ReversedMathExpressionTranslator {

    protected void head() throws SyntaxError {
        String TOKEN = LOOK_AHEAD.atual();

        if(Operator.ehUmOperadorValido(TOKEN)){
            op(); term(); term(); pegarToken(TOKEN);
        }else{
            throw new SyntaxError("Erro no elemento " + TOKEN + " [index=" + LOOK_AHEAD.getIndexReverseFix() + "]");
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
