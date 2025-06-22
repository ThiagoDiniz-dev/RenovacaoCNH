package com.oficina.renovacaocnh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RelatorioActivity extends AppCompatActivity {
    private String nome, idade, opcao;
    private int cnhSelecionada;
    private TextView relatorio;
    private Button compartilhar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_relatorio);

        nome = getIntent().getStringExtra("nome");
        idade = getIntent().getStringExtra("idade");
        opcao = getIntent().getStringExtra("cnhSelecionadaTexto");
        relatorio = findViewById(R.id.txtRelatorio);
        compartilhar = findViewById(R.id.btCompartilhar);

        geraRelatorio();

        compartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compartilhar(v);
            }
        });
    }

    public String calcularValidade (){
        int anosVida = Integer.parseInt(idade);
        String validade;

        if(anosVida < 50){
            validade = "10 anos";
        }else if (anosVida >= 50 && anosVida < 70){
            validade = "5 anos";
        }else{
            validade = "3 anos";
        }
        return validade;
    }

    public String defineToxicologico (){
        String prazoToxicologico = " ";

        if(opcao.equals("C") || opcao.equals("D") || opcao.equals("E")){
            prazoToxicologico = " e terá que realizar novo teste toxicológico daqui a 2 anos e 6 meses!";
        }
        return prazoToxicologico;
    }

    public void geraRelatorio (){
        String texto = nome + ", com " + idade + " anos" + " e carteira do tipo " + opcao
                         + ", deverá renovar sua carteira daqui " + calcularValidade() + defineToxicologico();
        relatorio.setText(texto);
    }


    public void compartilhar (View view){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_SUBJECT, compartilhar.getText().toString());
        intent.putExtra((Intent.EXTRA_TEXT), relatorio.getText().toString());
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, "Compartilhar com .."));
    }
}
