import data_structures.SyntaxError;
import models.MathExpressionTranslator;
import org.junit.jupiter.api.Test;
import translators.prefixada.PreToPos;

import static org.junit.jupiter.api.Assertions.*;

public class TradutorPreToPosTest {

    @Test
    public void deveValidarExpressao(){
        //Cenario
        String expressao1 = "-2+8-32";
        String expressao2 = "+7-16";
        String expressao3 = "-3+5-43";
        MathExpressionTranslator tradutor = new PreToPos();

        //Teste
        assertAll(
                () -> assertDoesNotThrow(() -> tradutor.verificarExpressao(expressao1)),
                () -> assertDoesNotThrow(() -> tradutor.verificarExpressao(expressao2)),
                () -> assertDoesNotThrow(() -> tradutor.verificarExpressao(expressao3))
        );
    }

    @Test
    public void deveLancarSintaxError(){
        //Cenario
        String expressao1 = "2+8";
        String expressao2 = "28+";
        String expressao3 = "+234";
        String expressao4 = "+ab";
        MathExpressionTranslator tradutor = new PreToPos();

        //Teste
        assertAll(
                () -> assertThrows(SyntaxError.class, () -> tradutor.verificarExpressao(expressao1)),
                () -> assertThrows(SyntaxError.class, () -> tradutor.verificarExpressao(expressao2)),
                () -> assertThrows(SyntaxError.class, () -> tradutor.verificarExpressao(expressao3)),
                () -> assertThrows(SyntaxError.class, () -> tradutor.verificarExpressao(expressao4))
        );
    }

    @Test
    public void deveTraduzirCorretamente() throws SyntaxError {
        //Cenario
        String expressao1 = "+28";
        String traducao1 = "28+";
        String expressao2 = "-3+5-43";
        String traducao2 = "3543-+-";
        MathExpressionTranslator tradutor = new PreToPos();

        //Teste
        String saida = tradutor
                .verificarExpressao(expressao1)
                .pegarUltimaTraducao();
        String saida2 = tradutor
                .verificarExpressao(expressao2)
                .pegarUltimaTraducao();

        assertAll(
                () -> assertEquals(traducao1, saida),
                () -> assertEquals(traducao2, saida2)
        );

    }

}
