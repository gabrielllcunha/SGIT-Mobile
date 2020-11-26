package com.example.transportadora.model;

import com.example.transportadora.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

public class CadastroMotorista {

    private String cpf;
    private String nome;
    private String telefone;
    private String idade;
    private String endereco;

    public CadastroMotorista() {

    }

    public void salvar(){

        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("Cadastro_Motorista")
        .push()
        .setValue(this);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}