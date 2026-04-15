package com.example.sql.classes;

public class Etudiant {
    private int id;
    private String nom;
    private String prenom;
    private String phone;

    public Etudiant() {
    }

    public Etudiant(int id, String nom, String prenom, String phone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
    }

    public Etudiant(String nom, String prenom, String phone) {
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
