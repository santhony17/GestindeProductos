package com.example.gestiondeproductos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los botones del diseño
        Button btnAgregarProducto = findViewById(R.id.btnAgregarProducto);
        Button btnBuscarProducto = findViewById(R.id.btnBuscarProducto);

        // Acción para agregar un nuevo producto
        btnAgregarProducto.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AgregarProductoActivity.class);
            startActivity(intent);
        });

        // Acción para buscar productos existentes
        btnBuscarProducto.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BuscarProductoActivity.class);
            startActivity(intent);
        });
    }
}
