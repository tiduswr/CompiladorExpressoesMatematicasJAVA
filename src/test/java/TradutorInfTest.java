import data_structures.SyntaxError;
import models.MathExpressionTranslator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import translators.infixada.InfToInf;
import translators.infixada.InfToPos;
import translators.infixada.InfToPre;
import translators.prefixada.PreToInfix;
import translators.prefixada.PreToPos;
import translators.prefixada.PreToPre;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;


public class TradutorInfTest {

    @ParameterizedTest(name = "{2}")
    @MethodSource("parametrosValidos")
    public void deveValidarExpressao(String expression, MathExpressionTranslator translatorImplementation, String testName){
        assertDoesNotThrow(() -> translatorImplementation.verificarExpressao(expression));
    }
    @ParameterizedTest(name = "{2}")
    @MethodSource("parametrosInvalidos")
    public void deveLancarSintaxError(String expression, MathExpressionTranslator translatorImplementation, String testName){
        assertThrows(SyntaxError.class, () -> translatorImplementation.verificarExpressao(expression));
    }

    @ParameterizedTest(name = "{3}")
    @MethodSource("conversaoValida")
    public void deveTraduzirCorretamente(String expressao, String traducaoEsperada,
                MathExpressionTranslator translatorImplementation, String testName) throws SyntaxError {
        //Teste
        String traducaoRecebida = translatorImplementation
                .verificarExpressao(expressao)
                .pegarUltimaTraducao();

        assertEquals(traducaoEsperada, traducaoRecebida);
    }

    private static Collection<Object[]> parametrosValidos(){
        MathExpressionTranslator infToPos = new InfToPos();

        return Arrays.asList(new Object[][]{
                {"2+8", infToPos, "Expressao Prefixa Valida[0]"},
                {"7+1-6", infToPos, "Expressao Prefixa Valida[1]"},
                {"2*5/(1+3)", infToPos, "Expressao Prefixa Valida[2]"},
                {"2*(8+9)/3", infToPos, "Expressao Prefixa Valida[3]"}
        });
    }

    private static Collection<Object[]> parametrosInvalidos(){
        MathExpressionTranslator preToPos = new PreToPos();

        return Arrays.asList(new Object[][]{
                {"+238", preToPos, "Expressao Prefixa Invalida[0]"},
                {"+7--16", preToPos, "Expressao Prefixa Invalida[1]"},
                {"*2/5+1-3", preToPos, "Expressao Prefixa Invalida[2]"},
                {"/3*2", preToPos, "Expressao Prefixa Invalida[3]"}
        });
    }

    private static Collection<Object[]> conversaoValida(){
        MathExpressionTranslator infToPos = new InfToPos();
        MathExpressionTranslator infToPre = new InfToPre();
        MathExpressionTranslator infToInf = new InfToInf();

        return Arrays.asList(new Object[][]{
                {"2+8", "28+", infToPos, "Traducao Prefixa para PosFixa[0]"},
                {"3-5+(4-3)", "3543-+-", infToPos, "Traducao Prefixa para PosFixa[1]"},
                {"2*5-(1+3)", "25*13+-", infToPos, "Traducao Prefixa para PosFixa[2]"},
                {"2+8", "+28", infToPre, "Traducao Prefixa para Prefixa[0]"},
                {"3-5+(4-3)", "-3+5-43", infToPre, "Traducao Prefixa para Prefixa[1]"},
                {"2*5-(1+3)", "-*25+13", infToPre, "Traducao Prefixa para Prefixa[2]"},
                {"2+8", "2+8", infToInf, "Traducao Prefixa para Infixa[0]"},
                {"3-5+(4-3)", "3-5+(4-3)", infToInf, "Traducao Prefixa para Infixa[1]"},
                {"2*5-(1+3)", "2*5-(1+3)", infToInf, "Traducao Prefixa para Infixa[2]"}
        });
    }

}
