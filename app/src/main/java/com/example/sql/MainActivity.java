package com.example.sql;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sql.classes.Etudiant;
import com.example.sql.service.EtudiantService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etNom, etPrenom, etPhone, etId;
    private Button btnAjouter, btnChercher, btnSupprimer;
    private LinearLayout listContainer;
    private EtudiantService etudiantService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des vues
        etNom = findViewById(R.id.et_nom);
        etPrenom = findViewById(R.id.et_prenom);
        etPhone = findViewById(R.id.et_phone);
        etId = findViewById(R.id.et_id);
        btnAjouter = findViewById(R.id.btn_ajouter);
        btnChercher = findViewById(R.id.btn_chercher);
        btnSupprimer = findViewById(R.id.btn_supprimer);
        listContainer = findViewById(R.id.list_container);

        // Initialisation du service
        etudiantService = new EtudiantService(this);

        // Action Ajouter
        btnAjouter.setOnClickListener(v -> {
            String nom = etNom.getText().toString().trim();
            String prenom = etPrenom.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();

            if (!nom.isEmpty() && !prenom.isEmpty() && !phone.isEmpty()) {
                Etudiant e = new Etudiant(nom, prenom, phone);
                etudiantService.addEtudiant(e);
                Toast.makeText(this, "Étudiant ajouté !", Toast.LENGTH_SHORT).show();
                clearFields();
                refreshList();
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            }
        });

        // Action Chercher
        btnChercher.setOnClickListener(v -> {
            String idStr = etId.getText().toString().trim();
            if (!idStr.isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    Etudiant e = etudiantService.getEtudiant(id);
                    listContainer.removeAllViews();
                    if (e != null) {
                        addEtudiantToView(e);
                    } else {
                        Toast.makeText(this, "Étudiant non trouvé", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "ID invalide", Toast.LENGTH_SHORT).show();
                }
            } else {
                refreshList(); // Si vide, on réaffiche tout
            }
        });

        // Action Supprimer
        btnSupprimer.setOnClickListener(v -> {
            String idStr = etId.getText().toString().trim();
            if (!idStr.isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    etudiantService.deleteEtudiant(id);
                    Toast.makeText(this, "Étudiant supprimé", Toast.LENGTH_SHORT).show();
                    etId.setText("");
                    refreshList();
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "ID invalide", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Afficher la liste au démarrage
        refreshList();
    }

    private void refreshList() {
        listContainer.removeAllViews();
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        for (Etudiant e : etudiants) {
            addEtudiantToView(e);
        }
    }

    private void addEtudiantToView(Etudiant e) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_etudiant, listContainer, false);
        
        TextView tvName = view.findViewById(R.id.item_name);
        TextView tvPhone = view.findViewById(R.id.item_phone);
        TextView tvId = view.findViewById(R.id.item_id);

        tvName.setText(e.getNom().toUpperCase() + " " + e.getPrenom());
        tvPhone.setText("Tel: " + e.getPhone());
        tvId.setText("ID: " + e.getId());

        listContainer.addView(view);
    }

    private void clearFields() {
        etNom.setText("");
        etPrenom.setText("");
        etPhone.setText("");
        etId.setText("");
    }
}
