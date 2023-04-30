package translators.infixada;

import data_structures.SyntaxError;
import models.Digits;
import models.MathExpressionTranslator;
import models.Operator;
import models.ReversedMathExpressionTranslator;

import java.util.Stack;

import static models.Operator.PARENTESES_DIREITO;
import static models.Operator.PARENTESES_ESQUERDO;

/**
 * Foi necessário reverter a entrada de dados para validar expressões e converter
 * para prefixadas, pois nessa tradução a leitura deve ser feita da direita para
 * a esquerda
 * @see <a href="https://www.calcont.in/Conversion/infix_to_prefix">Conversion from Infix to Prefix expressions | CalcCont</a>
 */
public class InfToPre extends ReversedMathExpressionTranslator {

    Stack<String> stack = new Stack<>();

    protected void head() throws SyntaxError {
        String TOKEN = LOOK_AHEAD.atual();

        if(Digits.ehUmDigitoValido(TOKEN) || PARENTESES_ESQUERDO.ehIgual(TOKEN)){
             term(); headTail();
        }else{
            throw new SyntaxError(TOKEN, LOOK_AHEAD.getIndexReverseFix());
        }
    }

    protected void headTail() throws SyntaxError{
        String TOKEN = LOOK_AHEAD.atual();

        if(Operator.ehUmaAdicaoOuSubtracao(TOKEN)){
            stack.push(TOKEN); op(); term(); pegarToken(stack.pop()); headTail();
        }else if(TOKEN != null && !PARENTESES_DIREITO.ehIgual(TOKEN)){
            throw new SyntaxError(TOKEN, LOOK_AHEAD.getIndexReverseFix());
        }
    }

    protected void term() throws SyntaxError{
        String TOKEN = LOOK_AHEAD.atual();

        if(Digits.ehUmDigitoValido(TOKEN) || PARENTESES_ESQUERDO.ehIgual(TOKEN)){
            factor(); termTail();
        }else{
            throw new SyntaxError(TOKEN, LOOK_AHEAD.getIndexReverseFix());
        }
    }

    protected void termTail() throws SyntaxError{
        String TOKEN = LOOK_AHEAD.atual();

        if(Operator.ehUmaMultiplicacaoOuDivisao(TOKEN)){
            stack.push(TOKEN); op(); factor(); pegarToken(stack.pop()); termTail();
        }
    }

    protected void factor() throws SyntaxError{
        String TOKEN = LOOK_AHEAD.atual();

        if(Digits.ehUmDigitoValido(TOKEN)){
            dig();
        }else if(PARENTESES_ESQUERDO.ehIgual(TOKEN)){
            parenEsq(); head(); parenDir();
        }
    }

    protected void parenEsq() throws SyntaxError{
        String TOKEN = LOOK_AHEAD.atual();

        if(PARENTESES_ESQUERDO.ehIgual(TOKEN)){
            match(TOKEN);
        } else {
            throw new SyntaxError(TOKEN, LOOK_AHEAD.getIndexReverseFix());
        }
    }

    protected void parenDir() throws SyntaxError{
        String TOKEN = LOOK_AHEAD.atual();

        if(PARENTESES_DIREITO.ehIgual(TOKEN)){
            match(TOKEN);
        } else {
            throw new SyntaxError("Parenteses nao fechado");
        }
    }

}
