import data_structures.SyntaxError;
import models.MathExpressionTranslator;
import translators.prefixada.PreToPos;

public class Main {

    public static void main(String[] args) throws SyntaxError {
        String expressao1 = "+2-4-32";
        MathExpressionTranslator tradutor = new PreToPos();
        System.out.println(tradutor.verificarExpressao(expressao1).pegarUltimaTraducao());
    }

}
