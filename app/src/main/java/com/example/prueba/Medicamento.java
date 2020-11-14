package com.example.prueba;


public class Medicamento {

    private String nombre;
    private int mg;
    private String formato;
    private float valor;

    public Medicamento() {
    }

    public Medicamento(String nombre, int mg, String formato, float valor) {
        this.nombre = nombre;
        this.mg = mg;
        this.formato = formato;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMg() {
        return mg;
    }

    public void setMg(int mg) {
        this.mg = mg;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
