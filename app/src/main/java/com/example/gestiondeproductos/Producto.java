package com.example.gestiondeproductos;

public class Producto {
    private String nombre;
    private String codigo;
    private int stock;
    private double precioAdquirido; // Costo del producto
    private double precioVenta;

    public Producto(String nombre, String codigo, int stock, double precioAdquirido, double precioVenta) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.stock = stock;
        this.precioAdquirido = precioAdquirido;
        this.precioVenta = precioVenta;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecioAdquirido() {
        return precioAdquirido;
    }

    public void setPrecioAdquirido(double precioAdquirido) {
        this.precioAdquirido = precioAdquirido;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    // Método adicional: getCosto()
    public double getCosto() {
        return precioAdquirido; // Asumimos que el costo es el precio adquirido
    }
}
