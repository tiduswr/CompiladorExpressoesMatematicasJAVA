import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TradutorTest {

    @Test
    public void deveValidarExpressao(){
        //Cenario
        String expressao1 = "+28";
        String expressao2 = "+7-16";
        String expressao3 = "-3+5-43";
        Tradutor tradutor = new Tradutor();

        //Teste
        assertAll(
                () -> assertDoesNotThrow(() -> tradutor.checkExpressao(expressao1)),
                () -> assertDoesNotThrow(() -> tradutor.checkExpressao(expressao2)),
                () -> assertDoesNotThrow(() -> tradutor.checkExpressao(expressao3))
        );
    }

    @Test
    public void deveLancarSintaxError(){
        //Cenario
        String expressao1 = "2+8";
        String expressao2 = "28+";
        String expressao3 = "+234";
        Tradutor tradutor = new Tradutor();

        //Teste
        assertAll(
                () -> assertThrows(SintaxError.class, () -> tradutor.checkExpressao(expressao1)),
                () -> assertThrows(SintaxError.class, () -> tradutor.checkExpressao(expressao2)),
                () -> assertThrows(SintaxError.class, () -> tradutor.checkExpressao(expressao3))
        );
    }

}
