package br.com.hardsoftware.mercearia.model;

public class Funcionario {

    private int codFuncionario;
    private String cargoFuncionario;
    private String loginFunconario;
    private String senhaFuncionario;
    private double salarioFuncionario;
    private String situacaoFuncionario;
    private String nmFuncionario;
    private String cpfFuncionario;
    private String dtNascFuncionario;
    private char sexoFuncionario;
    private String emailFuncionario;
    private String rgFuncionario;
    private Cidade cidadeFuncionario;
    private String enderecoFuncionario;
    private String telefoneFuncionario;
    private String celularFuncionario;
    private String cepFuncionario;

    public String toString(){
        return nmFuncionario;
    }
    
    public String getCepFuncionario() {
        return cepFuncionario;
    }

    public void setCepFuncionario(String cepFuncionario) {
        this.cepFuncionario = cepFuncionario;
    }

    public Cidade getCidadeFuncionario() {
        return cidadeFuncionario;
    }

    public void setCidadeFuncionario(Cidade cidadeFuncionario) {
        this.cidadeFuncionario = cidadeFuncionario;
    }

    public String getTelefoneFuncionario() {
        return telefoneFuncionario;
    }

    public void setTelefoneFuncionario(String telefoneFuncionario) {
        this.telefoneFuncionario = telefoneFuncionario;
    }

    public String getCelularFuncionario() {
        return celularFuncionario;
    }

    public void setCelularFuncionario(String celularFuncionario) {
        this.celularFuncionario = celularFuncionario;
    }

    public int getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(int codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public String getCargoFuncionario() {
        return cargoFuncionario;
    }

    public void setCargoFuncionario(String cargoFuncionario) {
        this.cargoFuncionario = cargoFuncionario;
    }

    public String getLoginFunconario() {
        return loginFunconario;
    }

    public void setLoginFunconario(String loginFunconario) {
        this.loginFunconario = loginFunconario;
    }

    public String getSenhaFuncionario() {
        return senhaFuncionario;
    }

    public void setSenhaFuncionario(String senhaFuncionario) {
        this.senhaFuncionario = senhaFuncionario;
    }

    public double getSalarioFuncionario() {
        return salarioFuncionario;
    }

    public void setSalarioFuncionario(double salarioFuncionario) {
        this.salarioFuncionario = salarioFuncionario;
    }

    public String getSituacaoFuncionario() {
        return situacaoFuncionario;
    }

    public void setSituacaoFuncionario(String situacaoFuncionario) {
        this.situacaoFuncionario = situacaoFuncionario;
    }

    public String getNmFuncionario() {
        return nmFuncionario;
    }

    public void setNmFuncionario(String nmFuncionario) {
        this.nmFuncionario = nmFuncionario;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    public String getDtNascFuncionario() {
        return dtNascFuncionario;
    }

    public void setDtNascFuncionario(String dtNascFuncionario) {
        this.dtNascFuncionario = dtNascFuncionario;
    }

    public char getSexoFuncionario() {
        return sexoFuncionario;
    }

    public void setSexoFuncionario(char sexoFuncionario) {
        this.sexoFuncionario = sexoFuncionario;
    }

    public String getEmailFuncionario() {
        return emailFuncionario;
    }

    public void setEmailFuncionario(String emailFuncionario) {
        this.emailFuncionario = emailFuncionario;
    }

    public String getRgFuncionario() {
        return rgFuncionario;
    }

    public void setRgFuncionario(String rgFuncionario) {
        this.rgFuncionario = rgFuncionario;
    }

    public String getEnderecoFuncionario() {
        return enderecoFuncionario;
    }

    public void setEnderecoFuncionario(String enderecoFuncionario) {
        this.enderecoFuncionario = enderecoFuncionario;
    }
    
    public static  boolean validaCpf (String cpf ){
        
        try{
            String strCpf ="";
            for (int i = 0; i < 14 ; i++) {

               if(i == 3 || i ==7 || i == 11){

               }else {
                   strCpf += cpf.charAt(i);
               }
           }

          int     d1, d2;
          int     digito1, digito2, resto;
          int     digitoCPF;
          String  nDigResult;

          d1 = d2 = 0;
          digito1 = digito2 = resto = 0;

          for (int nCount = 1; nCount < strCpf.length() -1; nCount++)
          {
             digitoCPF = Integer.valueOf (strCpf.substring(nCount -1, nCount)).intValue();

             //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
             d1 = d1 + ( 11 - nCount ) * digitoCPF;

             //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
             d2 = d2 + ( 12 - nCount ) * digitoCPF;
          };

          //Primeiro resto da divisão por 11.
          resto = (d1 % 11);

          //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
          if (resto < 2)
             digito1 = 0;
          else
             digito1 = 11 - resto;

          d2 += 2 * digito1;

          //Segundo resto da divisão por 11.
          resto = (d2 % 11);

          //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
          if (resto < 2)
             digito2 = 0;
          else
             digito2 = 11 - resto;

          //Digito verificador do CPF que está sendo validado.
          String nDigVerific = strCpf.substring (strCpf.length()-2, strCpf.length());

          //Concatenando o primeiro resto com o segundo.
          nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

          //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
          return nDigVerific.equals(nDigResult);
        }
        catch(Exception e){
            return false;
        }
   }

}
