package com.example.transportadora.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.transportadora.R;
import com.example.transportadora.model.Fornecedor;
import com.example.transportadora.model.Veiculo;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class VeiculoActivity extends AppCompatActivity {

    private TextInputEditText campoModelo, campoPlacaCarreta, campoPlacaCavalo, campoProprietario, campoNome;
    private FirebaseAuth autenticacao;
    private Veiculo cadastrarVeiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiculo);

        campoNome = findViewById(R.id.editNome);
        campoModelo = findViewById(R.id.editModelo);
        campoPlacaCarreta = findViewById(R.id.editPlacaCarreta);
        campoPlacaCavalo = findViewById(R.id.editPlacaCavalo);
        campoProprietario = findViewById(R.id.editProprietario);


    }

    public void cadastrarVeiculo(View view){

        //recupera textos dos campos

        String modelo = campoModelo.getText().toString();
        String placaCarreta = campoPlacaCarreta.getText().toString();
        String placaCavalo = campoPlacaCavalo.getText().toString();
        String proprietario = campoProprietario.getText().toString();
        String nome = campoNome.getText().toString();

        if (!modelo.isEmpty()) {//verifica Modelo
            if (!placaCarreta.isEmpty()) {//verifica placa carreta
                if (!placaCavalo.isEmpty()) {//verifica placa cavalo
                    if (!proprietario.isEmpty()) {//verifica proprietario
                        if (!nome.isEmpty()) {//verifica nome

                            Veiculo veiculo = new Veiculo();
                            veiculo.setModelo(modelo);
                            veiculo.setNome(nome);
                            veiculo.setPlacaCarreta(placaCarreta);
                            veiculo.setPlacaCavalo(placaCavalo);
                            veiculo.setProprietario(proprietario);

                            cadastrarVeiculo(veiculo);

                        }else{
                            Toast.makeText(VeiculoActivity.this,
                                    "Preencha o campo nome",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(VeiculoActivity.this,
                                "Preencha o campo Proprietário",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(VeiculoActivity.this,
                            "Preencha o Placa Cavalo",
                            Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(VeiculoActivity.this,
                        "Preencha o campo Placa Carreta",
                        Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(VeiculoActivity.this,
                    "Preencha o campo Modelo",
                    Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(VeiculoActivity.this,
                "Sucesso ao cadastrar Veículo",
                Toast.LENGTH_SHORT).show();

    }

    public void cadastrarVeiculo (Veiculo veiculo){

        cadastrarVeiculo = new Veiculo();
        cadastrarVeiculo.setProprietario(campoProprietario.getText().toString());
        cadastrarVeiculo.setPlacaCavalo(campoPlacaCavalo.getText().toString());
        cadastrarVeiculo.setPlacaCarreta(campoPlacaCarreta.getText().toString());
        cadastrarVeiculo.setNome(campoNome.getText().toString());
        cadastrarVeiculo.setModelo(campoModelo.getText().toString());

        cadastrarVeiculo.salvar();

    }
}