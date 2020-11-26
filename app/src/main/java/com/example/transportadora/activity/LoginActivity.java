package com.example.transportadora.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.transportadora.R;
import com.example.transportadora.config.ConfiguracaoFirebase;
import com.example.transportadora.helper.UsuarioFirebase;
import com.example.transportadora.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText campoEmail, campoSenha;
    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = findViewById(R.id.editLoginEmail);
        campoSenha = findViewById(R.id.editLoginSenha);

    }

    public void validarLoginUsuario( View view ) {

        String textoEmail = campoEmail.getText().toString();
        String textoSenha = campoSenha.getText().toString();

        if ( !textoEmail.isEmpty() ) {
            if ( !textoSenha.isEmpty() ) {
                Usuario usuario = new Usuario();
                usuario.setEmail( textoEmail );
                usuario.setSenha ( textoSenha );

                logarUsuario( usuario );

            }else{
                Toast.makeText(LoginActivity.this,
                        "Preencha a senha!",
                        Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(LoginActivity.this,
                    "Preencha o email!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void logarUsuario (Usuario usuario){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
               usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                //verificar o tipo de usuario Logado
                    // Motorista / Empresa

                    UsuarioFirebase.redirecionaUsuarioLogado( LoginActivity.this );


                } else {

                    String excecao = "";
                    try {
                        throw task.getException();
                    }catch ( FirebaseAuthInvalidUserException e){
                        excecao = "Usuario não está cadastrado!";
                    }catch ( FirebaseAuthInvalidCredentialsException e){
                        excecao= "E-mail e senha não correspondem a um cadastro ";
                    }catch (Exception e){
                        excecao = "Erro ao fazer login do usuário: "  + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(LoginActivity.this,
                            excecao, Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

}