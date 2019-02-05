package com.dscorrea.valorkm;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


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
        ImageView imgDuvidas = findViewById(R.id.img_duvidas);
        Button btnCalcular = findViewById(R.id.btn_calcular);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtKmInicial.getText().toString().length() == 0 || txtKmFinal.getText().toString().length() == 0 || txtConsumo.getText().toString().length() == 0 || txtValorCombustivel.getText().toString().length() == 0) {
                    Toast.makeText(MainActivity.this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Integer.parseInt(txtKmFinal.getText().toString()) < Integer.parseInt(txtKmInicial.getText().toString())) {
                    Toast.makeText(MainActivity.this, "A KM final deve ser maior que a inicial", Toast.LENGTH_SHORT).show();
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

        imgDuvidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Modo de usar");
                alertDialog.setMessage("Preencha todos os campos. \n \n" +
                        "Caso tenha o valor total da viagem colocar em \"KM final\" e deixar \"KM inicial\" como 0.");

                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
    }

    private int calculaKmTotal() {
        return Integer.parseInt(txtKmFinal.getText().toString()) - Integer.parseInt(txtKmInicial.getText().toString());
    }

    private double calculaConsumoLitros() {
        int kmTotal = calculaKmTotal();
        double consumo = Double.parseDouble(txtConsumo.getText().toString().replace(',', '.'));
        return kmTotal / consumo;
    }

    private double calculaValorKm() {
        double valorCombustivel = Double.parseDouble(txtValorCombustivel.getText().toString().replace(',', '.'));
        double consumo = Double.parseDouble(txtConsumo.getText().toString().replace(',', '.'));
        return valorCombustivel / consumo;
    }

    private double calculaValorTotal() {
        return calculaValorKm() * calculaKmTotal();
    }
}
