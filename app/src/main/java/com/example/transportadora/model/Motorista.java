package com.example.transportadora.model;

import com.example.transportadora.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

public class Motorista {

    private String id;
    private String nome;
    private String cpf;
    private String endereco;

    public Motorista() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
