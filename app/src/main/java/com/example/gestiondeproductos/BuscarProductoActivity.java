package com.example.gestiondeproductos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.Locale;

public class BuscarProductoActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private LinearLayout productInfoLayout;
    private TextView tvProductoNombre, tvProductoCosto, tvTotalCarrito;
    private LinearLayout carritoItemsContainer;
    private double totalCarrito = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_producto);

        db = FirebaseFirestore.getInstance();

        EditText edtBuscarNombre = findViewById(R.id.edtBuscarNombre);
        Button btnBuscarProducto = findViewById(R.id.btnBuscarProducto);
        Button btnAñadirCarrito = findViewById(R.id.btnAñadirCarrito);

        productInfoLayout = findViewById(R.id.productInfoLayout);
        tvProductoNombre = findViewById(R.id.tvProductoNombre);
        tvProductoCosto = findViewById(R.id.tvProductoCosto);
        carritoItemsContainer = findViewById(R.id.carritoItemsContainer);
        tvTotalCarrito = findViewById(R.id.tvTotalCarrito);

        btnBuscarProducto.setOnClickListener(v -> {
            String nombre = edtBuscarNombre.getText().toString().trim();
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa un nombre.", Toast.LENGTH_SHORT).show();
                return;
            }

            db.collection("productos")
                    .whereEqualTo("nombre", nombre)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                                Producto producto = document.toObject(Producto.class);
                                mostrarInformacionProducto(producto);
                            }
                        } else {
                            Toast.makeText(this, "Producto no encontrado.", Toast.LENGTH_SHORT).show();
                            ocultarInformacionProducto();
                        }
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Error al buscar.", Toast.LENGTH_SHORT).show());
        });

        btnAñadirCarrito.setOnClickListener(v -> {
            String productoNombre = tvProductoNombre.getText().toString().replace("Nombre del Producto: ", "").trim();
            String costoProductoStr = tvProductoCosto.getText().toString().replace("Costo del Producto: $", "").trim();

            if (!productoNombre.isEmpty() && !costoProductoStr.isEmpty()) {
                try {
                    double costoProducto = Double.parseDouble(costoProductoStr);
                    añadirProductoAlCarrito(productoNombre, costoProducto);
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Error al procesar el costo del producto.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "No hay producto seleccionado para añadir al carrito.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mostrarInformacionProducto(Producto producto) {
        tvProductoNombre.setText(String.format(Locale.getDefault(), "Nombre del Producto: %s", producto.getNombre()));
        tvProductoCosto.setText(String.format(Locale.getDefault(), "Costo del Producto: $%.2f", producto.getCosto()));
        productInfoLayout.setVisibility(View.VISIBLE);
    }

    private void ocultarInformacionProducto() {
        productInfoLayout.setVisibility(View.GONE);
    }

    private void añadirProductoAlCarrito(String nombre, double costo) {
        // Crear una nueva vista para el producto
        TextView productoEnCarrito = new TextView(this);
        productoEnCarrito.setText(String.format(Locale.getDefault(), "%s - $%.2f", nombre, costo));
        productoEnCarrito.setTextSize(16);
        carritoItemsContainer.addView(productoEnCarrito);

        // Actualizar el total del carrito
        totalCarrito += costo;
        tvTotalCarrito.setText(String.format(Locale.getDefault(), "Total: $%.2f", totalCarrito));
        Toast.makeText(this, "Producto añadido al carrito.", Toast.LENGTH_SHORT).show();

        // Limpiar la información del producto para buscar otro
        ocultarInformacionProducto();
    }
}
