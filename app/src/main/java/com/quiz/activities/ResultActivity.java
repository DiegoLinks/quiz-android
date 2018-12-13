package com.quiz.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.quiz.R;

public class ResultActivity extends AppCompatActivity {

    int pontos=0;
    TextView tvResultado;
    Button btClose;
    Button btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Exibe o ícone na toolBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        pontos = getIntent().getIntExtra("pontos",0);

        tvResultado = findViewById(R.id.tvResultado);
        btClose = findViewById(R.id.btClose);
        btVoltar = findViewById(R.id.btVoltar);

        if (pontos>60){
            tvResultado.setText(
                    "Parabéns!\n" +
                    "Você acertou\n" +
                    pontos+"% das\n" +
                    "perguntas."
            );
        } else {
            tvResultado.setText(
                    "Que pena,\n" +
                    "você acertou\n" +
                    pontos+"% das\n" +
                    "perguntas."
            );
        }

        //Resultados via API
//        int totalQuestions = 0;
//        int score = pontos;
//        int finalScore = ((totalQuestions*10) * pontos )/ totalQuestions;
//        if (finalScore>60){
//            tvResultado.setText(
//                    "Parabéns!\n" +
//                            "Você acertou\n" +
//                            pontos+"% das\n" +
//                            "perguntas."
//            );
//        } else {
//            tvResultado.setText(
//                    "Que pena,\n" +
//                            "você acertou\n" +
//                            pontos+"% das\n" +
//                            "perguntas."
//            );
//        }

        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fechar();
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltar();
            }
        });
    }

    public void fechar(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void voltar(){
        Intent intent = new Intent(this, QuestionsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
