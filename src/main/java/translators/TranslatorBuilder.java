package translators;

import models.MathExpressionTranslator;
import translators.infixada.*;
import translators.posfixada.*;
import translators.prefixada.*;

public class TranslatorBuilder {
    public static MathExpressionTranslator buildTranslator(String from, String to) {
        if (from.equalsIgnoreCase("infixa") && to.equalsIgnoreCase("infixa")) {
            return new InfToInf();
        } else if (from.equalsIgnoreCase("infixa") && to.equalsIgnoreCase("posfixa")) {
            return new InfToPos();
        } else if (from.equalsIgnoreCase("infixa") && to.equalsIgnoreCase("prefixa")) {
            return new InfToPre();
        } else if (from.equalsIgnoreCase("prefixa") && to.equalsIgnoreCase("infixa")) {
            return new PreToInf();
        } else if (from.equalsIgnoreCase("prefixa") && to.equalsIgnoreCase("posfixa")) {
            return new PreToPos();
        } else if (from.equalsIgnoreCase("prefixa") && to.equalsIgnoreCase("prefixa")) {
            return new PreToPre();
        } else if (from.equalsIgnoreCase("posfixa") && to.equalsIgnoreCase("posfixa")) {
            return new PosToPos();
        } else if (from.equalsIgnoreCase("posfixa") && to.equalsIgnoreCase("prefixa")) {
            return new PosToPre();
        } else if (from.equalsIgnoreCase("posfixa") && to.equalsIgnoreCase("infixa")) {
            return new PosToInf();
        }
        return null;
    }
}
