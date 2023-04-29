import data_structures.SyntaxError;
import models.MathExpressionTranslator;
import translators.infixada.InfToInf;
import translators.prefixada.PreToInfix;
import translators.prefixada.PreToPos;
import translators.prefixada.PreToPre;

public class Main {

    public static void main(String[] args) throws SyntaxError {
        String expressao1 = "-*31/24";
        MathExpressionTranslator tradutor = new PreToInfix();
        System.out.println(tradutor.verificarExpressao(expressao1).pegarUltimaTraducao());
    }

}
