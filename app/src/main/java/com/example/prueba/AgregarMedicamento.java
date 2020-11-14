package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarMedicamento extends AppCompatActivity {

    private EditText etId, etMed, etMg, etFor, etVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_agregar_medicamento);
        etId = (EditText) findViewById(R.id.etId);
        etMed = (EditText) findViewById(R.id.etMedicamento);
        etMg = (EditText) findViewById(R.id.etMg);
        etFor = (EditText) findViewById(R.id.etFormato);
        etVal = (EditText) findViewById(R.id.etValor);

    }

    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menuopciones, m);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        switch (i) {
            case R.id.menuAjustes:
                Toast.makeText(this, "Abriendo Ajustes", Toast.LENGTH_SHORT).show();
                Intent int1 = new Intent(this, MenuAjustes.class);
                startActivity(int1);
                break;

            case R.id.menuMiCuenta:
                Toast.makeText(this, "Abriendo Mi Cuenta", Toast.LENGTH_SHORT).show();
                Intent int2 = new Intent(this, MenuMiCuenta.class);
                startActivity(int2);
                break;
            case R.id.menuAgregarM:
                Toast.makeText(this, "Agregar Medicamento", Toast.LENGTH_SHORT).show();
                Intent int3 = new Intent(this, AgregarMedicamento.class);
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

    public void agregar(View view) { //Método que agrega medicamento a la bd
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administrador", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();

        registro.put("id", etId.getText().toString());
        registro.put("nombre", etMed.getText().toString());
        registro.put("mg", etMg.getText().toString());
        registro.put("formato", etFor.getText().toString());
        registro.put("valor", etVal.getText().toString());
        //Log.i("Nombre",  registro.get("nombre").toString());
        db.insert("medicamentos", null, registro);
        limpiar();

        db.close();
        Toast.makeText(this, "Agregado exitosamente", Toast.LENGTH_LONG).show();
    }


    public void buscar(View view) { //Método que busca medicamento por id en la bd, lo lleva a la primera posición y si lo encuentra lo trae.
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administrador", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor fila = db.rawQuery("Select * from medicamentos where id =" + etId.getText().toString(), null);
        if (fila.moveToFirst()) {
            Toast.makeText(this, "Medicamento encontrado", Toast.LENGTH_LONG).show();
            etMed.setText(fila.getString(1));
            etMg.setText(fila.getString(2));
            etFor.setText(fila.getString(3));
            etVal.setText(fila.getString(4));


        } else {
            Toast.makeText(this, "Medicamento no encontrado", Toast.LENGTH_LONG).show();
            limpiar();
        }
    }

    public void modificar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administrador", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues cambio = new ContentValues();

        cambio.put("id", etId.getText().toString());
        cambio.put("nombre", etMed.getText().toString());
        cambio.put("mg", etMg.getText().toString());
        cambio.put("formato", etFor.getText().toString());
        cambio.put("valor", etVal.getText().toString());

        int mod = db.update("medicamentos", cambio, "id=" + etId.getText().toString(), null);
        db.close();
        if (mod < 1) {
            Toast.makeText(this, "No hay modificaciones", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Modificaciones realizadas", Toast.LENGTH_SHORT).show();
        }
        limpiar();

    }

    public void eliminar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administrador", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        int del = db.delete("medicamentos", "id=" + etId.getText().toString(), null);

        if (del < 1) {
            Toast.makeText(this, "No se eliminó", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
        }
        limpiar();
    }
    public void BusquedaMed(){
        Intent i = new Intent(this, BuscarMedicamento.class);
    }


    public void limpiar() {
        etId.setText("");
        etMed.setText("");
        etMg.setText("");
        etFor.setText("");
        etVal.setText("");
    }
}