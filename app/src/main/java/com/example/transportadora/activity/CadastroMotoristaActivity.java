package com.example.transportadora.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.transportadora.R;
import com.example.transportadora.config.ConfiguracaoFirebase;
import com.example.transportadora.model.CadastroMotorista;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroMotoristaActivity extends AppCompatActivity {

    private TextInputEditText campoCpf, campoTelefone, campoEndereco, campoIdade, campoNome;
    private FirebaseAuth autenticacao;
    private CadastroMotorista cadastrarMotorista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_motorista);

        campoNome = findViewById(R.id.editNome);
        campoCpf = findViewById(R.id.editCpf);
        campoTelefone = findViewById(R.id.editTelefone);
        campoEndereco = findViewById(R.id.editEndereco);
        campoIdade = findViewById(R.id.editIdade);


        //Mascara Telefone
        SimpleMaskFormatter smf = new SimpleMaskFormatter("(NN)NNNNN - NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(campoTelefone, smf);
        campoTelefone.addTextChangedListener(mtw);

        //Mascara Cpf
        smf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        mtw = new MaskTextWatcher(campoCpf, smf);
        campoCpf.addTextChangedListener(mtw);

    }

    public void cadastrarMotorista(View view){

        //recupera textos dos campos

        String cpf = campoCpf.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String endereco = campoEndereco.getText().toString();
        String idade = campoIdade.getText().toString();
        String nome = campoNome.getText().toString();

        if (!cpf.isEmpty()) {//verifica Cpf
            if (!telefone.isEmpty()) {//verifica telefone
                if (!endereco.isEmpty()) {//verifica endereco
                    if (!idade.isEmpty()) {//verifica idade
                        if (!nome.isEmpty()) {//verifica nome

                            CadastroMotorista cadastroMotorista = new CadastroMotorista();
                            cadastroMotorista.setCpf(cpf);
                            cadastroMotorista.setTelefone(telefone);
                            cadastroMotorista.setEndereco(endereco);
                            cadastroMotorista.setIdade(idade);
                            cadastroMotorista.setNome(nome);

                            cadastrarMotorista(cadastroMotorista);

                        }else{
                            Toast.makeText(CadastroMotoristaActivity.this,
                                    "Preencha o campo nome",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(CadastroMotoristaActivity.this,
                                "Preencha o campo idade",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CadastroMotoristaActivity.this,
                            "Preencha o campo endereço",
                            Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(CadastroMotoristaActivity.this,
                        "Preencha o campo telefone",
                        Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(CadastroMotoristaActivity.this,
                    "Preencha o campo cpf",
                    Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(CadastroMotoristaActivity.this,
                "Sucesso ao cadastrar Motorista",
                Toast.LENGTH_SHORT).show();

    }

    public void cadastrarMotorista (CadastroMotorista cadastroMotorista){

        cadastrarMotorista = new CadastroMotorista();
        cadastrarMotorista.setCpf(campoCpf.getText().toString());
        cadastrarMotorista.setEndereco(campoEndereco.getText().toString());
        cadastrarMotorista.setIdade(campoIdade.getText().toString());
        cadastrarMotorista.setNome(campoNome.getText().toString());
        cadastrarMotorista.setTelefone(campoTelefone.getText().toString());

        cadastrarMotorista.salvar();

    }


}