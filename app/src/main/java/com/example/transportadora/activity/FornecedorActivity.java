package com.example.transportadora.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.transportadora.R;
import com.example.transportadora.model.CadastroMotorista;
import com.example.transportadora.model.Fornecedor;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class FornecedorActivity extends AppCompatActivity {

    private TextInputEditText campoCnpj, campoTelefone, campoEndereco, campoTipo, campoNome;
    private FirebaseAuth autenticacao;
    private Fornecedor cadastrarFornecedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornecedor);

        campoNome = findViewById(R.id.editNome);
        campoCnpj = findViewById(R.id.editCnpj);
        campoTelefone = findViewById(R.id.editTelefone);
        campoEndereco = findViewById(R.id.editEndereco);
        campoTipo = findViewById(R.id.edittipo);


        //Mascara Telefone
        SimpleMaskFormatter smf = new SimpleMaskFormatter("(NN)NNNNN - NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(campoTelefone, smf);
        campoTelefone.addTextChangedListener(mtw);

        //Mascara Cnpj
        smf = new SimpleMaskFormatter("NN.NNN.NNN/NNNN-NN");
        mtw = new MaskTextWatcher(campoCnpj, smf);
        campoCnpj.addTextChangedListener(mtw);

    }
    public void cadastrarFornecedor(View view){

        //recupera textos dos campos

        String cnpj = campoCnpj.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String endereco = campoEndereco.getText().toString();
        String tipo = campoTipo.getText().toString();
        String nome = campoNome.getText().toString();

        if (!cnpj.isEmpty()) {//verifica Cnpj
            if (!telefone.isEmpty()) {//verifica telefone
                if (!endereco.isEmpty()) {//verifica endereco
                    if (!tipo.isEmpty()) {//verifica tipo
                        if (!nome.isEmpty()) {//verifica nome

                            Fornecedor fornecedor = new Fornecedor();
                            fornecedor.setCnpj(cnpj);
                            fornecedor.setTelefone(telefone);
                            fornecedor.setEndereco(endereco);
                            fornecedor.setTipo(tipo);
                            fornecedor.setNome(nome);

                            cadastrarFornecedor(fornecedor);

                        }else{
                            Toast.makeText(FornecedorActivity.this,
                                    "Preencha o campo nome",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(FornecedorActivity.this,
                                "Preencha o campo tipo",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(FornecedorActivity.this,
                            "Preencha o campo endereço",
                            Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(FornecedorActivity.this,
                        "Preencha o campo telefone",
                        Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(FornecedorActivity.this,
                    "Preencha o campo cnpj",
                    Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(FornecedorActivity.this,
                "Sucesso ao cadastrar Fornecedor",
                Toast.LENGTH_SHORT).show();

    }

    public void cadastrarFornecedor (Fornecedor fornecedor){

        cadastrarFornecedor = new Fornecedor();
        cadastrarFornecedor.setCnpj(campoCnpj.getText().toString());
        cadastrarFornecedor.setEndereco(campoEndereco.getText().toString());
        cadastrarFornecedor.setTipo(campoTipo.getText().toString());
        cadastrarFornecedor.setNome(campoNome.getText().toString());
        cadastrarFornecedor.setTelefone(campoTelefone.getText().toString());

        cadastrarFornecedor.salvar();

    }


}