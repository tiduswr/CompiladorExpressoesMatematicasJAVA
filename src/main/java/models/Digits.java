package models;

import java.util.Arrays;

@SuppressWarnings("unused")
public enum Digits {
    ZERO(0), UM(1), DOIS(2), TRES(3),
    QUATRO(4), CINCO(5), SEIS(6), SETE(7),
    OITO(8), NOVE(9);

    private final int digit;

    Digits(int digit) {
        this.digit = digit;
    }

    public int getDigit() {
        return digit;
    }

    public static boolean ehUmDigitoValido(int digit) {
        return Arrays.stream(Digits.values())
                .anyMatch(validDigit -> digit == validDigit.getDigit());
    }

    public static boolean ehUmDigitoValido(String digit) {
        if(digit == null) return false;
        try{
            int convertedInt = Integer.parseInt(digit);
            return Arrays.stream(Digits.values())
                    .anyMatch(validDigit -> convertedInt == validDigit.getDigit());
        }catch(Exception e){
            return false;
        }
    }

}