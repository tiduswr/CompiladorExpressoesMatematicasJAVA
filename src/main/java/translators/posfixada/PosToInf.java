package translators.posfixada;

import data_structures.SyntaxError;
import models.Digits;
import models.Operator;
import models.ReversedMathExpressionTranslator;

import static models.Operator.PARENTESES_DIREITO;
import static models.Operator.PARENTESES_ESQUERDO;

/**
 * Foi necessário reverter a entrada de dados para validar as expressões
 * para a leitura ser feita da direita para a esquerda
 */
public class PosToInf extends ReversedMathExpressionTranslator {

    protected void head() throws SyntaxError {
        String TOKEN = LOOK_AHEAD.atual();

        if(Operator.ehUmOperadorValido(TOKEN)){
            op(); term(); pegarToken(TOKEN); term();
        }else{
            throw new SyntaxError("Erro no elemento " + TOKEN + " [index=" + LOOK_AHEAD.getIndexReverseFix() + "]");
        }
    }

    protected void term() throws SyntaxError{
        String TOKEN = LOOK_AHEAD.atual();

        if(Digits.ehUmDigitoValido(TOKEN)){
            dig();
        }else{
            pegarToken(PARENTESES_DIREITO); head(); pegarToken(PARENTESES_ESQUERDO);
        }
    }

}
