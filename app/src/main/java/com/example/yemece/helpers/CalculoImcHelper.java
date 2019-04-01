package com.example.yemece.helpers;

import com.example.yemece.enums.GravidadeIndiceImc;

public class CalculoImcHelper {

    private final double limiteAbaixoPeso = 18.5;
    private static double limiteIdeal = 24.9;
    private static double limiteLevementeAcimaPeso = 29.9;
    private static double limiteObedisadeGrauI = 34.9;
    private static double limiteObedisadeGrauII = 39.9;


    private double indice;

    public CalculoImcHelper(double peso, double altura) {
        this.indice = peso/(altura * 2);
    }

    public String getSituacao() {

        if (indice < limiteAbaixoPeso) {
            return "Abaixo do peso";
        } else if (indice < limiteIdeal) {
            return "Peso Ideal";
        } else if (indice < limiteLevementeAcimaPeso) {
            return "Acima do peso";
        } else if (indice < limiteObedisadeGrauI) {
            return "Obesidade grau I";
        } else if (indice < limiteObedisadeGrauII) {
            return "Obesidade grau II";
        } else {
            return "Obesidade grau III";
        }
    }

    public GravidadeIndiceImc getGravidadeIndice() {

        if (indice < limiteAbaixoPeso) {
            return GravidadeIndiceImc.BAIXA;
        } else if (indice < limiteIdeal) {
            return GravidadeIndiceImc.NENHUMA;
        } else if (indice < limiteLevementeAcimaPeso) {
            return GravidadeIndiceImc.BAIXA;
        } else {
            return GravidadeIndiceImc.ALTA;
        }
    }
}
