package models;

import data_structures.SyntaxError;

import static models.Operator.PARENTESES_ESQUERDO;

public abstract class ReversedMathExpressionTranslator extends MathExpressionTranslator{
    /** Revertendo entrada para ler da Direita para a esquerda.*/
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
}
