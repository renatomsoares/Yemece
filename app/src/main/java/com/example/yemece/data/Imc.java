package com.example.yemece.data;

import java.io.Serializable;

public class Imc implements Serializable {
    private int id;
    private String situacao;
    private double peso;
    private double altura;
    private String dataRegistro;


    public Imc(int id, String situacao, double peso, double altura, String dataRegistro) {
        this.id = id;
        this.situacao = situacao;
        this.peso = peso;
        this.altura = altura;
        this.dataRegistro = dataRegistro;

    }

    public Imc(String situacao, double peso, double altura, String dataRegistro) {
        this.situacao = situacao;
        this.peso = peso;
        this.altura = altura;
        this.dataRegistro = dataRegistro;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
