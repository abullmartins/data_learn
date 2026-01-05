package br.com.hardsoftware.mercearia.model;

import java.util.InputMismatchException;

public class Fornecedor {

    private int codFornecedor;
    private boolean entregaFornecedor;
    private String situacaoFornecedor;
    private String observFornecedor;
    private String nmFornecedor;
    private String cnpjFornecedor;
    private String emailFornecedor;
    private String enderecoFornecedor;
    private Cidade cidadeFornecedor;
    private String telefoneFornecedor;
    private String celularFornecedor;
    private String cepFornecedor;

    public String toString() {
        return nmFornecedor;
    }

    public String getEnderecoFornecedor() {
        return enderecoFornecedor;
    }

    public void setEnderecoFornecedor(String enderecoFornecedor) {
        this.enderecoFornecedor = enderecoFornecedor;
    }

    public String getCepFornecedor() {
        return cepFornecedor;
    }

    public void setCepFornecedor(String cepFornecedor) {
        this.cepFornecedor = cepFornecedor;
    }

    public String getTelefoneFornecedor() {
        return telefoneFornecedor;
    }

    public void setTelefoneFornecedor(String telefoneFornecedor) {
        this.telefoneFornecedor = telefoneFornecedor;
    }

    public String getCelularFornecedor() {
        return celularFornecedor;
    }

    public void setCelularFornecedor(String celularFornecedor) {
        this.celularFornecedor = celularFornecedor;
    }

    public Cidade getCidadeFornecedor() {
        return cidadeFornecedor;
    }

    public void setCidadeFornecedor(Cidade cidadeFornecedor) {
        this.cidadeFornecedor = cidadeFornecedor;
    }

    public int getCodFornecedor() {
        return codFornecedor;
    }

    public void setCodFornecedor(int codFornecedor) {
        this.codFornecedor = codFornecedor;
    }

    public boolean isEntregaFornecedor() {
        return entregaFornecedor;
    }

    public void setEntregaFornecedor(boolean entregaFornecedor) {
        this.entregaFornecedor = entregaFornecedor;
    }

    public String getSituacaoFornecedor() {
        return situacaoFornecedor;
    }

    public void setSituacaoFornecedor(String situacaoFornecedor) {
        this.situacaoFornecedor = situacaoFornecedor;
    }

    public String getObservFornecedor() {
        return observFornecedor;
    }

    public void setObservFornecedor(String observFornecedor) {
        this.observFornecedor = observFornecedor;
    }

    public String getNmFornecedor() {
        return nmFornecedor;
    }

    public void setNmFornecedor(String nmFornecedor) {
        this.nmFornecedor = nmFornecedor;
    }

    public String getCnpjFornecedor() {
        return cnpjFornecedor;
    }

    public void setCnpjFornecedor(String cnpjFornecedor) {
        this.cnpjFornecedor = cnpjFornecedor;
    }

    public String getEmailFornecedor() {
        return emailFornecedor;
    }

    public void setEmailFornecedor(String emailFornecedor) {
        this.emailFornecedor = emailFornecedor;
    }

    public static boolean validaCnpj(String cnpj) {

        String remover = ".;/;-";
        String limite = ";";
        if (remover != null && remover.length() > 0 && cnpj != null) {

            String[] remove = remover.split(limite);

            for (int i = 0; i < remove.length; i++) {
                //System.out.println("i: " + i + " ["+ remover[i]+"]");
                if (cnpj.indexOf(remove[i]) != -1) {
                    cnpj = cnpj.replace(remove[i], "");
                }
            }
        }

// considera-se erro cnpj's formados por uma sequencia de numeros iguais
        if (cnpj.equals(
                "00000000000000") || cnpj.equals("11111111111111")
                || cnpj.equals("22222222222222") || cnpj.equals("33333333333333")
                || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
                || cnpj.equals("66666666666666") || cnpj.equals("77777777777777")
                || cnpj.equals("88888888888888") || cnpj.equals("99999999999999")
                || (cnpj.length() != 14)) {
            return (false);
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

// "try" - protege o código para eventuais erros de conversao de tipo (int)
        try {
// Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
// converte o i-ésimo caractere do cnpj em um número:
// por exemplo, transforma o caractere '0' no inteiro 0
// (48 eh a posição de '0' na tabela ASCII)
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char) ((11 - r) + 48);
            }

// Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char) ((11 - r) + 48);
            }

// Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static String imprimecnpj(String cnpj) {
// máscara do cnpj: 99.999.999/9999-99
        return (cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "."
                + cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12) + "-"
                + cnpj.substring(12, 14));
    }
}
