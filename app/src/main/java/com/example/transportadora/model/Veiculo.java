package com.example.transportadora.model;

import com.example.transportadora.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

public class Veiculo {

    private String placaCarreta;
    private String placaCavalo;
    private String nome;
    private String modelo;
    private String proprietario;

    public Veiculo() {
    }

    public void salvar(){

        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("Cadastro_Veiculo")
                .push()
                .setValue(this);
    }

    public String getPlacaCarreta() {
        return placaCarreta;
    }

    public void setPlacaCarreta(String placaCarreta) {
        this.placaCarreta = placaCarreta;
    }

    public String getPlacaCavalo() {
        return placaCavalo;
    }

    public void setPlacaCavalo(String placaCavalo) {
        this.placaCavalo = placaCavalo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }
}
