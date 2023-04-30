import data_structures.SyntaxError;
import models.MathExpressionTranslator;
import translators.posfixada.PosToInf;
import translators.posfixada.PosToPos;
import translators.posfixada.PosToPre;

public class Main {

    public static void main(String[] args) throws SyntaxError {
        String expressao1 = "573-+";
        MathExpressionTranslator tradutor = new PosToInf();
        System.out.println(tradutor.verificarExpressao(expressao1).pegarUltimaTraducao());
    }

}
