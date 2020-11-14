package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MenuMiCuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_mi_cuenta);
    }
    public boolean onCreateOptionsMenu (Menu m){
        getMenuInflater().inflate(R.menu.menuopciones, m);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item) {
        int i = item.getItemId();
        switch (i) {
            case R.id.menuAjustes:
                Toast.makeText(this, "Abriendo Ajustes", Toast.LENGTH_SHORT).show();
                Intent int1 = new Intent(  this, MenuAjustes.class);
                startActivity(int1);
                break;

            case R.id.menuMiCuenta:
                Toast.makeText(this, "Abriendo Mi Cuenta", Toast.LENGTH_SHORT).show();
                Intent int2 = new Intent(  this, MenuMiCuenta.class);
                startActivity(int2);
                break;
            case R.id.menuAgregarM:
                Toast.makeText(this, "Agregar Medicamento", Toast.LENGTH_SHORT).show();
                Intent int3 = new Intent(  this, AgregarMedicamento.class);
                startActivity(int3);
                break;
            case R.id.menuHome:
                Toast.makeText(this, "Redireccionando al Home", Toast.LENGTH_SHORT).show();
                Intent int4 = new Intent(  this, MainActivity.class);
                startActivity(int4);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    public void salir(){
        finish();
    }
}