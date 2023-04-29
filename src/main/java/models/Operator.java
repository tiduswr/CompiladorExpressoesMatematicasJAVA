package models;

import java.util.Arrays;

@SuppressWarnings("unused")
public enum Operator {
    MAIS("+"), MENOS("-"), VEZES("*"), DIVISAO("/");

    private final String operator;

    Operator(String op){
        this.operator = op;
    }

    public static boolean ehUmOperadorValido(String operatorChecking){
        if(operatorChecking == null) return false;
        return Arrays.stream(values())
                .anyMatch(operator -> operator.toString().equalsIgnoreCase(operatorChecking));
    }

    public static boolean ehUmaAdicaoOuSubtracao(String operatorChecking){
        if(operatorChecking == null) return false;
        return Arrays.stream(new Operator[]{MAIS, MENOS})
                .anyMatch(operator -> operator.toString().equalsIgnoreCase(operatorChecking));
    }

    public static boolean ehUmaMultiplicacaoOuDivisao(String operatorChecking){
        if(operatorChecking == null) return false;
        return Arrays.stream(new Operator[]{VEZES, DIVISAO})
                .anyMatch(operator -> operator.toString().equalsIgnoreCase(operatorChecking));
    }

    public boolean ehIgual(String operatorChecking){
        return this.toString().equals(operatorChecking);
    }

    @Override
    public String toString() {
        return operator;
    }
}
