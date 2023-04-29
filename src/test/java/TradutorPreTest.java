import data_structures.SyntaxError;
import models.MathExpressionTranslator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import translators.prefixada.PreToInfix;
import translators.prefixada.PreToPos;
import translators.prefixada.PreToPre;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;


public class TradutorPreTest {

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
        MathExpressionTranslator preToPos = new PreToPos();

        return Arrays.asList(new Object[][]{
                {"+28", preToPos, "Expressao Prefixa Valida[0]"},
                {"+7-16", preToPos, "Expressao Prefixa Valida[1]"},
                {"/*25+13", preToPos, "Expressao Prefixa Valida[2]"},
                {"/3*2+89", preToPos, "Expressao Prefixa Valida[3]"}
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
        MathExpressionTranslator preToPos = new PreToPos();
        MathExpressionTranslator preToPre = new PreToPre();
        MathExpressionTranslator preToInf = new PreToInfix();

        return Arrays.asList(new Object[][]{
                {"+28", "28+", preToPos, "Traducao Prefixa para PosFixa[0]"},
                {"-3+5-43", "3543-+-", preToPos, "Traducao Prefixa para PosFixa[1]"},
                {"-*25+13", "25*13+-", preToPos, "Traducao Prefixa para PosFixa[2]"},
                {"+28", "+28", preToPre, "Traducao Prefixa para Prefixa[0]"},
                {"-3+5-43", "-3+5-43", preToPre, "Traducao Prefixa para Prefixa[1]"},
                {"-*25+13", "-*25+13", preToPre, "Traducao Prefixa para Prefixa[2]"},
                {"+28", "2+8", preToInf, "Traducao Prefixa para Infixa[0]"},
                {"-3+5-43", "3-5+4-3", preToInf, "Traducao Prefixa para Infixa[1]"},
                {"-*25+13", "2*5-1+3", preToInf, "Traducao Prefixa para Infixa[2]"}
        });
    }

}
