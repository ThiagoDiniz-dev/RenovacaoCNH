package com.oficina.renovacaocnh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView nome, idade;
    private Button consultar;
    private RadioGroup tipoCNH;
    private int cnhSelecionada;
    private RadioButton selecaoCNH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.edtNome);
        idade = findViewById(R.id.edtIdade);
        consultar = findViewById(R.id.btConsultar);
        tipoCNH = findViewById(R.id.rgpTipoCNH);

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultar(v);
            }
        });
    }

    public void consultar(View view) {
        cnhSelecionada = tipoCNH.getCheckedRadioButtonId();
        if (cnhSelecionada != -1) {
            selecaoCNH = findViewById(cnhSelecionada);
            String cnhSelecionadaTexto = selecaoCNH.getText().toString();

            Intent intent = new Intent(getApplicationContext(), RelatorioActivity.class);
            intent.putExtra("nome", nome.getText().toString());
            intent.putExtra("idade", idade.getText().toString());
            intent.putExtra("cnhSelecionadaTexto", cnhSelecionadaTexto);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Por favor, selecone um tipo de CNH", Toast.LENGTH_SHORT).show();
        }
    }
}
