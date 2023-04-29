import data_structures.SyntaxError;
import models.MathExpressionTranslator;
import translators.infixada.InfToInf;
import translators.infixada.InfToPos;
import translators.infixada.InfToPre;

public class Main {

    public static void main(String[] args) throws SyntaxError {
        String expressao1 = "(3*2)/2+8-3";
        MathExpressionTranslator tradutor = new InfToPre();
        System.out.println(tradutor.verificarExpressao(expressao1).pegarUltimaTraducao());
    }

}
