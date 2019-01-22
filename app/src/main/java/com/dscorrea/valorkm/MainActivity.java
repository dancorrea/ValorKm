package com.dscorrea.valorkm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Double.NaN;

public class MainActivity extends AppCompatActivity {

    private EditText txtKmInicial;
    private EditText txtKmFinal;
    private EditText txtConsumo;
    private EditText txtValorCombustivel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtKmInicial = findViewById(R.id.txt_km_inicial);
        txtKmFinal = findViewById(R.id.txt_km_final);
        txtConsumo = findViewById(R.id.txt_consumo);
        txtValorCombustivel = findViewById(R.id.txt_valor_combustivel);
        Button btnCalcular = findViewById(R.id.btn_calcular);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtKmInicial.getText().toString().length() == 0 || txtKmFinal.getText().toString().length() == 0 || txtConsumo.getText().toString().length() == 0 || txtValorCombustivel.getText().toString().length() == 0) {
                    Toast.makeText(MainActivity.this,"Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Integer.parseInt(txtKmFinal.getText().toString()) < Integer.parseInt(txtKmInicial.getText().toString())) {
                    Toast.makeText(MainActivity.this,"A KM final deve ser maior que a inicial", Toast.LENGTH_SHORT).show();
                    return;
                }

                int kmTotal = calculaKmTotal();
                double consumoLitros = calculaConsumoLitros();
                double valorKm = calculaValorKm();
                double valorTotal = calculaValorTotal();

                Intent intent = new Intent(MainActivity.this, RelatorioActivity.class);
                intent.putExtra("kmTotal", kmTotal);
                intent.putExtra("consumoLitros", consumoLitros);
                intent.putExtra("valorKm", valorKm);
                intent.putExtra("valorTotal", valorTotal);
                startActivity(intent);
            }
        });
    }

    private int calculaKmTotal() {
        return   Integer.parseInt(txtKmFinal.getText().toString()) - Integer.parseInt(txtKmInicial.getText().toString());
    }

    private double calculaConsumoLitros() {
        int kmTotal = calculaKmTotal();
        return kmTotal / Double.parseDouble(txtConsumo.getText().toString());
    }

    private double calculaValorKm() {
        return Double.parseDouble(txtValorCombustivel.getText().toString()) / Double.parseDouble(txtConsumo.getText().toString());
    }

    private double calculaValorTotal() {
        return calculaValorKm() * calculaKmTotal();
    }
}
