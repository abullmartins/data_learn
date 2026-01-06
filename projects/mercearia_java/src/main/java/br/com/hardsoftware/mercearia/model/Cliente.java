package br.com.hardsoftware.mercearia.model;

import br.com.hardsoftware.mercearia.util.Validator;

public class Cliente {

    private int codCliente;
    private String situacaoCliente;
    private String nmCliente;
    private String cpfCliente;
    private String dtNascCliente;
    private String endereco;
    private String emailCliente;
    private char sexoCliente;
    private String rg;
    private Cidade cidadeCliente;
    private String telefoneCliente;
    private String celularCliente;
    private String cepCliente;

    @Override
    public String toString() {
        return nmCliente;
    }

    public String getCepCliente() {
        return cepCliente;
    }

    public void setCepCliente(String cepCliente) {
        this.cepCliente = cepCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public String getCelularCliente() {
        return celularCliente;
    }

    public void setCelularCliente(String celularCliente) {
        this.celularCliente = celularCliente;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public Cidade getCidadeCliente() {
        return cidadeCliente;
    }

    public void setCidadeCliente(Cidade cidadeCliente) {
        this.cidadeCliente = cidadeCliente;
    }

    public String getNmCliente() {
        return nmCliente;
    }

    public void setNmCliente(String nmCliente) {
        this.nmCliente = nmCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public boolean setCpfCliente(String cpfCliente) {
        if (Validator.validaCpf(cpfCliente)) {
            this.cpfCliente = cpfCliente;
            return true;
        } else {
            return false;
        }
    }

    public String getDtNascCliente() {
        return dtNascCliente;
    }

    public void setDtNascCliente(String date) {
        this.dtNascCliente = date;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public char getSexoCliente() {
        return sexoCliente;
    }

    public void setSexoCliente(char sexoCliente) {
        this.sexoCliente = sexoCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getSituacaoCliente() {
        return situacaoCliente;
    }

    public void setSituacaoCliente(String situacaoCliente) {
        this.situacaoCliente = situacaoCliente;
    }
}
