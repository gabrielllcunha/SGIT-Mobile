package com.example.transportadora.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.example.transportadora.config.ConfiguracaoFirebase;
import com.example.transportadora.model.Requisicao;
import com.example.transportadora.model.Usuario;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.example.transportadora.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ViagemActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private LatLng localMotorista;
    private Usuario motorista;
    private String idRequisicao;
    private Requisicao requisicao;
    private DatabaseReference firebaseRef;
    private Button aceitarViagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagem);

        inicializarComponentes();

        //Recupera Dados do Usuário
        if(getIntent().getExtras().containsKey("idRequisicao")
            && getIntent().getExtras().containsKey("motorista")){
                Bundle extras = getIntent().getExtras();
                motorista = (Usuario) extras.getSerializable("motorista");
                idRequisicao = extras.getString("idRequisicao");
                verificaStatusRequisicao();
        }

    }

    private void verificaStatusRequisicao(){

        final DatabaseReference requisicoes = firebaseRef.child("requisicoes")
                .child(idRequisicao);
        requisicoes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Recupera Requisição
                requisicao = dataSnapshot.getValue(Requisicao.class);
                switch ( requisicao.getStatus() ){
                    case Requisicao.STATUS_AGUARDANDO :
                        requisicaoAguardando();
                        break;
                    case  Requisicao.STATUS_A_CAMINHO :
                        requisicaoACaminho();
                        break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void requisicaoACaminho(){

        aceitarViagem.setText("A caminho");

    }

    private void requisicaoAguardando(){

        aceitarViagem.setText("Aceitar Viagem");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Recuperar localização do Motorista
        recuperarLocalizacaoMotorista();

    }

    private void recuperarLocalizacaoMotorista() {

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                //Recuperar latitude e longitude
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                localMotorista = new LatLng(latitude, longitude);

                mMap.clear();
                mMap.addMarker(
                        new MarkerOptions()
                                .position(localMotorista)
                                .title("Meu Local")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.carro))
                );
                mMap.moveCamera(
                        CameraUpdateFactory.newLatLngZoom(localMotorista, 20)
                );


            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        //Solicitar atualizações de Localização
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0,
                    0,
                    locationListener
            );
        }

    }

    public void aceitarViagem (View view){

        //Configura Requisicao

        requisicao = new Requisicao();
        requisicao.setId(idRequisicao);
        requisicao.setStatus(Requisicao.STATUS_A_CAMINHO);

        requisicao.atualizar();
    }

    private void inicializarComponentes () {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Configurações iniciais
        firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
        aceitarViagem = findViewById(R.id.ButtonAceitarViagem);

        // Config Maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

}