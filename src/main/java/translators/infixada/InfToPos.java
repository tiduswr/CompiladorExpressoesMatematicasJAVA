package translators.infixada;

import data_structures.SyntaxError;
import models.Digits;
import models.MathExpressionTranslator;
import models.Operator;

import java.util.Stack;

import static models.Operator.PARENTESES_DIREITO;
import static models.Operator.PARENTESES_ESQUERDO;

public class InfToPos extends MathExpressionTranslator {

    Stack<String> stack = new Stack<>();

    protected void head() throws SyntaxError {
        String TOKEN = LOOK_AHEAD.atual();

        if(Digits.ehUmDigitoValido(TOKEN) || PARENTESES_ESQUERDO.ehIgual(TOKEN)){
            term(); headTail();
        }else{
            throw new SyntaxError(TOKEN, LOOK_AHEAD.getIndex());
        }
    }

    protected void headTail() throws SyntaxError{
        String TOKEN = LOOK_AHEAD.atual();

        if(Operator.ehUmaAdicaoOuSubtracao(TOKEN)){
            stack.push(TOKEN); op(); term(); headTail(); pegarToken(stack.pop());
        }else if(TOKEN != null && !PARENTESES_DIREITO.ehIgual(TOKEN)){
            throw new SyntaxError(TOKEN, LOOK_AHEAD.getIndex());
        }
    }

    protected void term() throws SyntaxError{
        String TOKEN = LOOK_AHEAD.atual();

        if(Digits.ehUmDigitoValido(TOKEN) || PARENTESES_ESQUERDO.ehIgual(TOKEN)){
            factor(); termTail();
        }else{
            throw new SyntaxError(TOKEN, LOOK_AHEAD.getIndex());
        }
    }

    protected void termTail() throws SyntaxError{
        String TOKEN = LOOK_AHEAD.atual();

        if(Operator.ehUmaMultiplicacaoOuDivisao(TOKEN)){
            stack.push(TOKEN); op(); factor(); termTail(); pegarToken(stack.pop());
        }
    }

    protected void factor() throws SyntaxError{
        String TOKEN = LOOK_AHEAD.atual();

        if(Digits.ehUmDigitoValido(TOKEN)){
            dig();
        }else if(PARENTESES_ESQUERDO.ehIgual(TOKEN)){
            parenEsq(); head(); parenDir();
        } else {
            throw new SyntaxError(TOKEN, LOOK_AHEAD.getIndex());
        }
    }

    protected void parenEsq() throws SyntaxError{
        String TOKEN = LOOK_AHEAD.atual();

        if(PARENTESES_ESQUERDO.ehIgual(TOKEN)){
            match(TOKEN);
        } else {
            throw new SyntaxError(TOKEN, LOOK_AHEAD.getIndex());
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
