package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class BuscarMedicamento extends AppCompatActivity {

    private TextView et1, et2, et3, et4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_buscar_medicamento);
        et1 = (TextView) findViewById(R.id.txtNombre);
        et2 = (TextView) findViewById(R.id.txtMg);
        et3 = (TextView) findViewById(R.id.txtFormato);
        et4 = (TextView) findViewById(R.id.txtValor);


    }

    public void buscar(View view) { //Método que busca medicamento por id en la bd, lo lleva a la primera posición y si lo encuentra lo trae.
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administrador", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor fila = db.rawQuery("Select * from medicamentos where nombre =" + et1.getText(), null);
        if (fila.moveToFirst()) {
            Toast.makeText(this, "Medicamento encontrado", Toast.LENGTH_LONG).show();
            et1.setText(fila.getString(1));
            et2.setText(fila.getString(2));
            et3.setText(fila.getString(3));
            et4.setText(fila.getString(4));


        } else {
            Toast.makeText(this, "Medicamento no encontrado", Toast.LENGTH_LONG).show();
            limpiar();
        }
    }

        public boolean onCreateOptionsMenu (Menu m){
            getMenuInflater().inflate(R.menu.menuopciones, m);
            return true;
        }

        public boolean onOptionsItemSelected (MenuItem item){
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
                    Intent int4 = new Intent(this, MainActivity.class);
                    startActivity(int4);
                    break;
            }
            return super.onOptionsItemSelected(item);
        }
        public void limpiar(){
            et1.setText("");
            et2.setText("");
            et3.setText("");
            et4.setText("");
        }
    }
