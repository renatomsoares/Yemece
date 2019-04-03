package com.example.yemece.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yemece.R;

public class ExibeDicaFragment extends Fragment {

    private TextView colorShow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exibe_dica_fragment,
                container,
                false);
        colorShow = view.findViewById(R.id.colorShow);
        return view;
    }


    public void setColorShow(int buttonName){

        switch(buttonName) {
            case R.id.btnDica1:
                colorShow.setText("A obesidade é determinada pelo Índice de Massa Corporal (IMC) que é calculado dividindo-se o peso (em kg) pelo quadrado da altura (em metros). O resultado revela se o peso está dentro da faixa ideal, abaixo ou acima do desejado.");
                break;
            case R.id.btnDica2:
                colorShow.setText("Como a obesidade é provocada por uma ingestão de energia que supera o gasto do organismo, a forma mais simples de tratamento é a adoção de um estilo de vida mais saudável, com menor ingestão de calorias e aumento das atividades físicas. Essa mudança não só provoca redução de peso como facilita sua manutenção.");
                break;
            case R.id.btnDica3:
                colorShow.setText("A utilização de medicamentos contribui de forma modesta e temporária para a redução de peso e nunca devem ser usados como única forma de tratamento. Boa parte das substâncias usadas atuam no cérebro e podem provocar reações adversas graves, como: nervosismo, insônia, aumento da pressão sanguínea, batimentos cardíacos acelerados, boca seca, intestino preso. Um dos riscos mais preocupantes é o de se tornar dependente do medicamento, por isso, o tratamento deve ser acompanhado com rigor e restrito a alguns tipos de pacientes.");
                break;
            case R.id.btnDica4:
                colorShow.setText("- Não deposite as esperanças do tratamento apenas no medicamento, pois o resultado depende principalmente das mudanças nos hábitos de vida (dieta e atividade física);\n" +
                        "\n" +
                        "- Com o tempo o medicamento pode passar a perder o efeito. Se isso ocorrer, consulte seu médico e nunca aumente a dose por conta própria;\n" +
                        "\n" +
                        "- Existem muitas propagandas irregulares de medicamentos para emagrecer nos meios de comunicação, por isso não acredite em promessas de emagrecimento rápido e fácil;\n" +
                        "\n" +
                        "- Fórmulas de emagrecimento com várias substâncias misturadas são proibidas pelo Ministério da Saúde e já provocaram mortes.");
                break;
            case R.id.btnDica5:
                colorShow.setText("Não existe mágica! Para manter o peso dentro dos valores desejáveis a melhor opção é ter uma alimentação balanceada e praticar atividades físicas regularmente.");
                break;
        }
    }
}
