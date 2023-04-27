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

    @Override
    public String toString() {
        return operator;
    }
}
