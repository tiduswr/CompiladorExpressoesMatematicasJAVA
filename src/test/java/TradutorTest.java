import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TradutorTest {

    @Test
    public void deveValidarExpressao(){
        //Cenario
        String expressao1 = "+28";
        String expressao2 = "+7-16";
        String expressao3 = "-3+5-43";
        TradutorPreFixParaPosFix tradutor = new TradutorPreFixParaPosFix();

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
        TradutorPreFixParaPosFix tradutor = new TradutorPreFixParaPosFix();

        //Teste
        assertAll(
                () -> assertThrows(SintaxError.class, () -> tradutor.verificarExpressao(expressao1)),
                () -> assertThrows(SintaxError.class, () -> tradutor.verificarExpressao(expressao2)),
                () -> assertThrows(SintaxError.class, () -> tradutor.verificarExpressao(expressao3)),
                () -> assertThrows(SintaxError.class, () -> tradutor.verificarExpressao(expressao4))
        );
    }

    @Test
    public void deveTraduzirCorretamente() throws SintaxError {
        //Cenario
        String expressao1 = "+28";
        String traducao1 = "28+";
        String expressao2 = "-3+5-43";
        String traducao2 = "3543-+-";
        TradutorPreFixParaPosFix tradutor = new TradutorPreFixParaPosFix();

        //Teste
        String saida = tradutor.verificarExpressao(expressao1);
        assertEquals(traducao1, saida);
        String saida2 = tradutor.verificarExpressao(expressao2);
        assertEquals(traducao2, saida2);
    }

}
