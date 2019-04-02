package com.example.yemece.helpers;

import com.example.yemece.enums.GravidadeIndiceImc;

public class CalculoImcHelper {

    private final double limiteMinimoIdeal = 18.5;
    private static double limiteMaximoIdeal = 24.9;
    private static double limiteLevementeAcimaPeso = 29.9;
    private static double limiteObedisadeGrauI = 34.9;
    private static double limiteObedisadeGrauII = 39.9;


    private double indice;
    private double peso;
    private double altura;

    public CalculoImcHelper(double peso, double altura) {
        this.indice = peso/(altura*altura);
        this.peso = peso;
        this.altura = altura;
    }

    public double getIndice () {
        return indice;
    }

    public String getSituacao() {

        if (indice < limiteMinimoIdeal) {
            return "Abaixo do peso";
        } else if (indice <= limiteMaximoIdeal) {
            return "Peso Ideal";
        } else if (indice <= limiteLevementeAcimaPeso) {
            return "Acima do peso";
        } else if (indice <= limiteObedisadeGrauI) {
            return "Obesidade grau I";
        } else if (indice <= limiteObedisadeGrauII) {
            return "Obesidade grau II";
        } else {
            return "Obesidade grau III";
        }
    }

    public GravidadeIndiceImc getGravidadeIndice() {

        if (indice < limiteMinimoIdeal) {
            return GravidadeIndiceImc.BAIXA;
        } else if (indice <= limiteMaximoIdeal) {
            return GravidadeIndiceImc.NENHUMA;
        } else if (indice <= limiteLevementeAcimaPeso) {
            return GravidadeIndiceImc.BAIXA;
        } else {
            return GravidadeIndiceImc.ALTA;
        }
    }

    public double getDiferencaPesoParaSituacaoIdeal() {
        return indice - (altura*altura);
    }
}
