import data_structures.SyntaxError;
import models.MathExpressionTranslator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import translators.infixada.InfToInf;
import translators.infixada.InfToPos;
import translators.infixada.InfToPre;
import translators.posfixada.PosToInf;
import translators.posfixada.PosToPos;
import translators.posfixada.PosToPre;
import translators.prefixada.PreToPos;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;


public class TradutorPosTest {

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
        MathExpressionTranslator posToPos = new PosToPos();

        return Arrays.asList(new Object[][]{
                {"54+", posToPos, "Expressao Posfixa Valida[0]"},
                {"57+67+*", posToPos, "Expressao Posfixa Valida[1]"},
                {"452*+5+", posToPos, "Expressao Posfixa Valida[2]"},
                {"456*+", posToPos, "Expressao Posfixa Valida[3]"}
        });
    }

    private static Collection<Object[]> parametrosInvalidos(){
        MathExpressionTranslator preToPos = new PreToPos();

        return Arrays.asList(new Object[][]{
                {"+238", preToPos, "Expressao Posfixa Invalida[0]"},
                {"+7--16", preToPos, "Expressao Posfixa Invalida[1]"},
                {"*2/5+1-3", preToPos, "Expressao Posfixa Invalida[2]"},
                {"/3*2", preToPos, "Expressao Posfixa Invalida[3]"}
        });
    }

    private static Collection<Object[]> conversaoValida(){
        MathExpressionTranslator posToPos = new PosToPos();
        MathExpressionTranslator posToPre = new PosToPre();
        MathExpressionTranslator posToInf = new PosToInf();

        return Arrays.asList(new Object[][]{
                {"28+", "28+", posToPos, "Traducao Posfixa para PosFixa[0]"},
                {"3543-+-", "3543-+-", posToPos, "Traducao Posfixa para PosFixa[1]"},
                {"25*13+-", "25*13+-", posToPos, "Traducao Posfixa para PosFixa[2]"},
                {"28+", "+28", posToPre, "Traducao Posfixa para Prefixa[0]"},
                {"3543-+-", "-3+5-43", posToPre, "Traducao Posfixa para Prefixa[1]"},
                {"25*13+-", "-*25+13", posToPre, "Traducao Posfixa para Prefixa[2]"},
                {"28+", "2+8", posToInf, "Traducao Posfixa para Infixa[0]"},
                {"3543-+-", "3-(5+(4-3))", posToInf, "Traducao Posfixa para Infixa[1]"},
                {"25*13+-", "(2*5)-(1+3)", posToInf, "Traducao Posfixa para Infixa[2]"}
        });
    }

}
