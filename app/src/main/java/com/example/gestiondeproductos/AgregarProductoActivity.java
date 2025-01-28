package com.example.gestiondeproductos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AgregarProductoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Vincular con el diseño correcto
        setContentView(R.layout.activity_agregar_producto);

        // Referencias a los elementos del diseño
        EditText edtNombre = findViewById(R.id.edtNombre);
        EditText edtCodigo = findViewById(R.id.edtCodigo);
        EditText edtStock = findViewById(R.id.edtStock);
        EditText edtPrecioAdquirido = findViewById(R.id.edtPrecioAdquirido);
        EditText edtPrecioVenta = findViewById(R.id.edtPrecioVenta);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        // Acción al presionar el botón
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recuperar datos
                String nombre = edtNombre.getText().toString();
                String codigo = edtCodigo.getText().toString();
                String stockStr = edtStock.getText().toString();
                String precioAdquiridoStr = edtPrecioAdquirido.getText().toString();
                String precioVentaStr = edtPrecioVenta.getText().toString();

                // Validar campos
                if (nombre.isEmpty() || codigo.isEmpty() || stockStr.isEmpty() ||
                        precioAdquiridoStr.isEmpty() || precioVentaStr.isEmpty()) {
                    Toast.makeText(AgregarProductoActivity.this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int stock = Integer.parseInt(stockStr);
                    double precioAdquirido = Double.parseDouble(precioAdquiridoStr);
                    double precioVenta = Double.parseDouble(precioVentaStr);

                    // Crear el objeto del producto
                    Producto producto = new Producto(nombre, codigo, stock, precioAdquirido, precioVenta);

                    // Aquí podrías guardar el producto en una base de datos o lista
                    Toast.makeText(AgregarProductoActivity.this, "Producto guardado correctamente.", Toast.LENGTH_SHORT).show();

                    // Limpiar campos
                    edtNombre.setText("");
                    edtCodigo.setText("");
                    edtStock.setText("");
                    edtPrecioAdquirido.setText("");
                    edtPrecioVenta.setText("");
                } catch (NumberFormatException e) {
                    Toast.makeText(AgregarProductoActivity.this, "Por favor, ingresa valores válidos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
