package com.fsm.tp4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Button ajouter,supprimerTous,pereferences,loginBtn;
EditText _prenom,_nom,_login,_password;
ListView lvEtudiant;
SQLiteDatabase sqlDb;
ArrayList listEtudiants;
ArrayAdapter arrayAdapter;
SharedPreferences preferences;

    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         preferences = getSharedPreferences("com.fsm.tp4", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);

            ajouter = findViewById(R.id.btnAjouter);
            supprimerTous = findViewById(R.id.btnSupprimer);
            pereferences = findViewById(R.id.btnPerefences);
            _nom = findViewById(R.id.eNom);
            _prenom = findViewById(R.id.ePrenom);
            lvEtudiant = findViewById(R.id.lvEtufdiants);
            listEtudiants = new ArrayList();

            sqlDb = openOrCreateDatabase("mydatabase.db", SQLiteDatabase.OPEN_READWRITE, null);
            sqlDb.execSQL("CREATE TABLE IF NOT EXISTS etudiant (id INTEGER "
                    + " PRIMARY KEY AUTOINCREMENT, nom VARCHAR, prenom VARCHAR);");
            readData();



                arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listEtudiants);
                lvEtudiant.setAdapter(arrayAdapter);

            ajouter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ajouterEtudiant();
                }
            });

            supprimerTous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SupprimerEtudiant();
                }
            });











    }


    private  void ajouterEtudiant(){
        ContentValues values = new ContentValues();
        values.put("nom", _nom.getText().toString());
        values.put("prenom", _prenom.getText().toString());
        long id = sqlDb.insert("etudiant", null, values);
        if (id == -1) {
            Toast.makeText(getApplicationContext(), "Erreur d'insertion dans la base", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "etudiant ajouter avec succes", Toast.LENGTH_SHORT).show();

        listEtudiants.clear();
        arrayAdapter.notifyDataSetChanged();
        readData();
        }
    }



    private void SupprimerEtudiant(){
        sqlDb.delete("etudiant","1",null);
        sqlDb.execSQL("UPDATE `sqlite_sequence` SET `seq` = 0 WHERE `name` = 'etudiant'");
        listEtudiants.clear();
        arrayAdapter.notifyDataSetChanged();
    }


    private void readData(){
        Cursor resultSet = sqlDb.rawQuery("SELECT * FROM etudiant", null);
        if (resultSet.moveToFirst()) {
            do {
                int id = resultSet.getInt(0);
                String nom = resultSet.getString(1);
                String prenom = resultSet.getString(2);
                String text = id + " - " + nom + " " + prenom;

                listEtudiants.add(text);
            } while (resultSet.moveToNext());
        }
        resultSet.close();
    }

    public void startPreferenceActivity(View view) {
        Intent intent = new Intent(getApplicationContext(),MyPreferenceActivity.class);

        intent.putExtra("appName",MainActivity.this.getTitle());
        startActivityForResult(intent,0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode==0){
                if (resultCode== Activity.RESULT_OK){

                }
            }
    }






}