package com.example.transportadora.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.transportadora.R;
import com.example.transportadora.model.CadastroMotorista;

public class EmpresaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa);
    }

    public void abrirTelaCadastro (View view){
        startActivity(new Intent(this, CadastroMotoristaActivity.class));
    }

    public void abrirRequisicao (View view) {
        startActivity(new Intent(this, RequisicoesActivity.class));
    }

    public void abrirTelaFornecedor (View view) {
        startActivity(new Intent(this, FornecedorActivity.class));
    }

    public void abrirTelaVeiuclo (View view) {
        startActivity(new Intent(this, VeiculoActivity.class));
    }


    public void abrirMotorista (View view) {
        startActivity(new Intent(this, CadastroMotoristaActivity.class));
    }

    public void abrirLocalizar (View view) {
        startActivity(new Intent(this, CadastroMotoristaActivity.class));
    }


}