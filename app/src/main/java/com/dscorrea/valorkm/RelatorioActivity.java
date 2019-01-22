package com.dscorrea.valorkm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RelatorioActivity extends AppCompatActivity {

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        TextView lblKmTotal = findViewById(R.id.lbl_km_total);
        TextView lblConsumoLitros = findViewById(R.id.lbl_consumo_litros);
        TextView lblValorKm = findViewById(R.id.lbl_valor_km);
        TextView lblValorTotal = findViewById(R.id.lbl_valor_total);
        Button btnVoltar = findViewById(R.id.btn_voltar);

        Intent intent = getIntent();
        int kmTotal = intent.getIntExtra("kmTotal", 0);
        Double consumoLitros = intent.getDoubleExtra("consumoLitros", 0);
        Double valorKm = intent.getDoubleExtra("valorKm", 0);
        Double valorTotal = intent.getDoubleExtra("valorTotal", 0);

        lblKmTotal.setText("Distância percorrida: " + kmTotal + " km");
        lblConsumoLitros.setText("Combustível consumido: " + String.format("%.2f", consumoLitros) + " litros");
        lblValorKm.setText("Valor por KM rodado: R$ " + String.format("%.2f", valorKm));
        lblValorTotal.setText("Valor total gasto: R$ " + String.format("%.2f", valorTotal));

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltar = new Intent(RelatorioActivity.this, MainActivity.class);
                startActivity(intentVoltar);
            }
        });

    }
}
