package translators.infixada;

import data_structures.SyntaxError;
import models.Digits;
import models.MathExpressionTranslator;
import models.Operator;

import java.util.Stack;

import static models.Operator.PARENTESES_DIREITO;
import static models.Operator.PARENTESES_ESQUERDO;

public class InfToPre extends MathExpressionTranslator {

    Stack<String> stack = new Stack<>();

    @Override
    protected void receberExpressao(String expressao){
        LOOK_AHEAD.resetarLookAhead();
        char[] chars = expressao.toCharArray();
        for(int i = chars.length -1; i >= 0; i--){
            String TOKEN = String.valueOf(chars[i]);
            if(Operator.ehParenteses(TOKEN)){
                TOKEN = PARENTESES_ESQUERDO.ehIgual(TOKEN) ? ")" : "(";
            }
            LOOK_AHEAD.add(TOKEN);
        }
    }

    @Override
    public MathExpressionTranslator verificarExpressao(String expressao) throws SyntaxError {
        traducao = "";
        receberExpressao(expressao);
        head();
        if(LOOK_AHEAD.atual() != null){
            int index = LOOK_AHEAD.getIndex();
            throw new SyntaxError("Erro no elemento "
                    + (index >=0 && index <= (expressao.length()-1) ? expressao.charAt(index) : "")
                    + " [index=" + index + "]");
        }
        return this;
    }

    @Override
    public String pegarUltimaTraducao(){
        return (new StringBuilder(traducao))
                .reverse()
                .toString();
    }

    protected void head() throws SyntaxError {
        String TOKEN = LOOK_AHEAD.atual();

        if(Digits.ehUmDigitoValido(TOKEN) || PARENTESES_ESQUERDO.ehIgual(TOKEN)){
             term(); headTail();
        }else{
            throw new SyntaxError("HEAD -> Erro no Token " + TOKEN);
        }
    }

    protected void headTail() throws SyntaxError{
        String TOKEN = LOOK_AHEAD.atual();

        if(Operator.ehUmaAdicaoOuSubtracao(TOKEN)){
            stack.push(TOKEN); op(); term(); pegarToken(stack.pop()); headTail();
        }else if(TOKEN != null && !PARENTESES_DIREITO.ehIgual(TOKEN)){
            throw new SyntaxError("HEAD_TAIL -> Erro no Token" + TOKEN);
        }
    }

    protected void term() throws SyntaxError{
        String TOKEN = LOOK_AHEAD.atual();

        if(Digits.ehUmDigitoValido(TOKEN) || PARENTESES_ESQUERDO.ehIgual(TOKEN)){
            factor(); termTail();
        }else{
            throw new SyntaxError("TERM -> Erro no Token" + TOKEN);
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
        System.out.println("parenEsq() -> " + TOKEN);
        if(PARENTESES_ESQUERDO.ehIgual(TOKEN)){
            match(TOKEN);
        } else {
            throw new SyntaxError("Erro no elemento " + TOKEN + " [index=" + LOOK_AHEAD.getIndex() + "]");
        }
    }

    protected void parenDir() throws SyntaxError{
        String TOKEN = LOOK_AHEAD.atual();
        System.out.println("parenDir() -> " + TOKEN);
        if(PARENTESES_DIREITO.ehIgual(TOKEN)){
            match(TOKEN);
        } else {
            throw new SyntaxError("Parenteses nao fechado");
        }
    }

}
