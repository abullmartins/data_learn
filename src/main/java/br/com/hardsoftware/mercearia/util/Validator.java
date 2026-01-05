package br.com.hardsoftware.mercearia.util;

public class Validator {

    public static boolean validaCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return false;
        }
        
        try {
            String strCpf = "";
            for (int i = 0; i < 14 && i < cpf.length(); i++) {
                if (i == 3 || i == 7 || i == 11) {
                    continue;
                } else {
                    strCpf += cpf.charAt(i);
                }
            }
            // Note: The original logic seemed to expect formatting, but the check checks length 11. 
            // If input is "123.456.789-00", length is 14. 
            // If input is "12345678900", length is 11.
            // I'll stick to a standard algorithm or just adapt the old one.
            // The old one was a bit messy. Let's implement a standard CPF validation.
            
            // Standard CPF validation (simplified for brevity but correct)
            // Remove non-digits
            String num = cpf.replaceAll("\\D", "");
            if (num.length() != 11 || num.matches("(\\d)\\1{10}")) return false;

            int soma = 0;
            int peso = 10;
            for (int i = 0; i < 9; i++) {
                soma += (num.charAt(i) - '0') * peso--;
            }

            int r = 11 - (soma % 11);
            char dig10 = (r == 10 || r == 11) ? '0' : (char) (r + '0');

            soma = 0;
            peso = 11;
            for (int i = 0; i < 10; i++) {
                soma += (num.charAt(i) - '0') * peso--;
            }

            r = 11 - (soma % 11);
            char dig11 = (r == 10 || r == 11) ? '0' : (char) (r + '0');

            return (dig10 == num.charAt(9)) && (dig11 == num.charAt(10));
        } catch (Exception e) {
            return false;
        }
    }
}
