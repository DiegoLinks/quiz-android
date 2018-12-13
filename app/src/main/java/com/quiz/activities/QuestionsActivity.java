package com.quiz.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.quiz.models.Quiz;
import com.quiz.R;

public class QuestionsActivity extends AppCompatActivity {

    Button btOk;
    Button btResposta1;
    Button btResposta2;
    Button btResposta3;
    Button btResposta4;

    int positionSelected=0;
    int respostaCerta=0;

    String resposta_1 = "";
    String resposta_2 = "";
    String resposta_3 = "";
    String resposta_4 = "";

    int pergunta=1;
    int erro=0;

    TextView tvTituloPergunta;
    TextView tvPergunta;

    int pontos=100;

    Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        //Exibe o ícone na toolBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        btOk = findViewById(R.id.btOk);
        btResposta1 = findViewById(R.id.btResposta1);
        btResposta2 = findViewById(R.id.btResposta2);
        btResposta3 = findViewById(R.id.btResposta3);
        btResposta4 = findViewById(R.id.btResposta4);
        tvTituloPergunta = findViewById(R.id.tvTituloPergunta);
        tvPergunta = findViewById(R.id.tvPergunta);

        next();

        btResposta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected(1);
            }
        });

        btResposta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected(2);
            }
        });

        btResposta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected(3);
            }
        });

        btResposta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected(4);
            }
        });

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(positionSelected>0){
                    validaResposta();
                } else {
                   Toast.makeText(QuestionsActivity.this, "Selecione uma resposta.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Muda resposta selecionada
    public void setSelected(int position){
        if(position==1){
            btResposta1.setBackgroundColor(getResources().getColor(R.color.selected_color));
            btResposta2.setBackgroundColor(getResources().getColor(R.color.white));
            btResposta3.setBackgroundColor(getResources().getColor(R.color.white));
            btResposta4.setBackgroundColor(getResources().getColor(R.color.white));
        } else  if(position==2){
            btResposta1.setBackgroundColor(getResources().getColor(R.color.white));
            btResposta2.setBackgroundColor(getResources().getColor(R.color.selected_color));
            btResposta3.setBackgroundColor(getResources().getColor(R.color.white));
            btResposta4.setBackgroundColor(getResources().getColor(R.color.white));
        } else  if(position==3){
            btResposta1.setBackgroundColor(getResources().getColor(R.color.white));
            btResposta2.setBackgroundColor(getResources().getColor(R.color.white));
            btResposta3.setBackgroundColor(getResources().getColor(R.color.selected_color));
            btResposta4.setBackgroundColor(getResources().getColor(R.color.white));
        } else  if(position==4){
            btResposta1.setBackgroundColor(getResources().getColor(R.color.white));
            btResposta2.setBackgroundColor(getResources().getColor(R.color.white));
            btResposta3.setBackgroundColor(getResources().getColor(R.color.white));
            btResposta4.setBackgroundColor(getResources().getColor(R.color.selected_color));
        }
        positionSelected=position;
    }

    public void validaResposta(){
        if(respostaCerta==positionSelected){
            next();
            erro=0;
        } else {
            erro++;
        }

        if(erro==1){
            erro1();
            pontos=pontos-5;
        } else if(erro==2){
            erro2();
            pontos=pontos-5;
        }
    }

    public void next(){

        //Reseta seleções
        btResposta1.setBackgroundColor(getResources().getColor(R.color.white));
        btResposta2.setBackgroundColor(getResources().getColor(R.color.white));
        btResposta3.setBackgroundColor(getResources().getColor(R.color.white));
        btResposta4.setBackgroundColor(getResources().getColor(R.color.white));

        getQuesions();

        btResposta1.setText(resposta_1);
        btResposta2.setText(resposta_2);
        btResposta3.setText(resposta_3);
        btResposta4.setText(resposta_4);
    }

    public void erro1(){

        if(positionSelected==1){
            btResposta1.setBackgroundColor(getResources().getColor(R.color.wrong_color));
        } else  if(positionSelected==2){
            btResposta2.setBackgroundColor(getResources().getColor(R.color.wrong_color));
        } else  if(positionSelected==3){
            btResposta3.setBackgroundColor(getResources().getColor(R.color.wrong_color));
        } else  if(positionSelected==4){
            btResposta4.setBackgroundColor(getResources().getColor(R.color.wrong_color));
        }

        positionSelected=0;
    }

    public void erro2(){

        if(respostaCerta==1){
            btResposta1.setBackgroundColor(getResources().getColor(R.color.correct_color));
        } else  if(respostaCerta==2){
            btResposta2.setBackgroundColor(getResources().getColor(R.color.correct_color));
        } else  if(respostaCerta==3){
            btResposta3.setBackgroundColor(getResources().getColor(R.color.correct_color));
        } else  if(respostaCerta==4){
            btResposta4.setBackgroundColor(getResources().getColor(R.color.correct_color));
        }

        erro1();

        btOk.setEnabled(false);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                next();
                btOk.setEnabled(true);
            }
        },1500);

        erro=0;
        positionSelected=0;
    }

    //Solução via API
//    public void getQuesions() {
//
//        quiz = new Quiz();
//
//        tvTituloPergunta.setText(quiz.getTitle());
//        tvPergunta.setText(quiz.getQuestion());
//        resposta_1 = quiz.getAnswers().get(0).getContent();
//        resposta_2 = quiz.getAnswers().get(1).getContent();
//        resposta_3 = quiz.getAnswers().get(2).getContent();
//        resposta_4 = quiz.getAnswers().get(3).getContent();
//
//        respostaCerta = quiz.correctAnswer;
//
//        //Esse teste deve ser feito no método next() para que o teste seja finalizado após a última pergunta
//        //O teste tambe~m pode ser feito a partir ddo size ddo objeto
//        if (quiz.last){
//            Intent intent = new Intent(this, ResultActivity.class);
//            intent.putExtra("pontos",pontos);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
//            finish();
//        }
//        pergunta++;
//    }

    //Solução provisória para MVP
    public void getQuesions(){
        if(pergunta==1){
            tvTituloPergunta.setText("Pergunta 1");
            tvPergunta.setText("Primeira... is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s?");
            resposta_1 = "01. Primeira rodada de perguntas... pergunta número 1.";
            resposta_2 = "02. Primeira rodada de perguntas... pergunta número 2.";
            resposta_3 = "03. Primeira rodada de perguntas... pergunta número 3.";
            resposta_4 = "04. Primeira rodada de perguntas... pergunta número 4.";
            respostaCerta=1;

        //Modelo para configurar as perguntas e respostas pelo arquivo de strings
//            tvTituloPergunta.setText(R.string.titulo1);
//            tvPergunta.setText(R.string.pergunta1);
//            resposta_1 = getString(R.string.resposta1_rodada1);
//            resposta_2 = getString(R.string.resposta2_rodada1);
//            resposta_3 = getString(R.string.resposta3_rodada1);
//            resposta_4 = getString(R.string.resposta4_rodada1);
//            respostaCerta=1;

        } else if(pergunta==2){
            tvTituloPergunta.setText("Pergunta 2");
            tvPergunta.setText("Segunda... is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s?");
            resposta_1 = "01. Segunda rodada de perguntas... pergunta número 1.";
            resposta_2 = "02. Segunda rodada de perguntas... pergunta número 2.";
            resposta_3 = "03. Segunda rodada de perguntas... pergunta número 3.";
            resposta_4 = "04. Segunda rodada de perguntas... pergunta número 4.";
            respostaCerta=2;
        } else if(pergunta==3){
            tvTituloPergunta.setText("Pergunta 3");
            tvPergunta.setText("Terceira... is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s?");
            resposta_1 = "01. Terceira rodada de perguntas... pergunta número 1.";
            resposta_2 = "02. Terceira rodada de perguntas... pergunta número 2.";
            resposta_3 = "03. Terceira rodada de perguntas... pergunta número 3.";
            resposta_4 = "04. Terceira rodada de perguntas... pergunta número 4.";
            respostaCerta=3;
        } else if(pergunta==4){
            tvTituloPergunta.setText("Pergunta 4");
            tvPergunta.setText("Quarta... is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s?");
            resposta_1 = "01. Quarta rodada de perguntas... pergunta número 1.";
            resposta_2 = "02. Quarta rodada de perguntas... pergunta número 2.";
            resposta_3 = "03. Quarta rodada de perguntas... pergunta número 3.";
            resposta_4 = "04. Quarta rodada de perguntas... pergunta número 4.";
            respostaCerta=4;
        } else if(pergunta==5){
            tvTituloPergunta.setText("Pergunta 5");
            tvPergunta.setText("Quinta... is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s?");
            resposta_1 = "01. Quinta rodada de perguntas... pergunta número 1.";
            resposta_2 = "02. Quinta rodada de perguntas... pergunta número 2.";
            resposta_3 = "03. Quinta rodada de perguntas... pergunta número 3.";
            resposta_4 = "04. Quinta rodada de perguntas... pergunta número 4.";
            respostaCerta=1;
        } else if(pergunta==6){
            tvTituloPergunta.setText("Pergunta 6");
            tvPergunta.setText("Sexta... is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s?");
            resposta_1 = "01. Sexta rodada de perguntas... pergunta número 1.";
            resposta_2 = "02. Sexta rodada de perguntas... pergunta número 2.";
            resposta_3 = "03. Sexta rodada de perguntas... pergunta número 3.";
            resposta_4 = "04. Sexta rodada de perguntas... pergunta número 4.";
            respostaCerta=2;
        } else if(pergunta==7){
            tvTituloPergunta.setText("Pergunta 7");
            tvPergunta.setText("Sétima... is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s?");
            resposta_1 = "01. Sétima rodada de perguntas... pergunta número 1.";
            resposta_2 = "02. Sétima rodada de perguntas... pergunta número 2.";
            resposta_3 = "03. Sétima rodada de perguntas... pergunta número 3.";
            resposta_4 = "04. Sétima rodada de perguntas... pergunta número 4.";
            respostaCerta=3;
        } else if(pergunta==8){
            tvTituloPergunta.setText("Pergunta 8");
            tvPergunta.setText("Oitava... is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s?");
            resposta_1 = "01. Oitava rodada de perguntas... pergunta número 1.";
            resposta_2 = "02. Oitava rodada de perguntas... pergunta número 2.";
            resposta_3 = "03. Oitava rodada de perguntas... pergunta número 3.";
            resposta_4 = "04. Oitava rodada de perguntas... pergunta número 4.";
            respostaCerta=4;
        } else if(pergunta==9){
            tvTituloPergunta.setText("Pergunta 9");
            tvPergunta.setText("Nona... is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s?");
            resposta_1 = "01. Nona rodada de perguntas... pergunta número 1.";
            resposta_2 = "02. Nona rodada de perguntas... pergunta número 2.";
            resposta_3 = "03. Nona rodada de perguntas... pergunta número 3.";
            resposta_4 = "04. Nona rodada de perguntas... pergunta número 4.";
            respostaCerta=1;
        } else if(pergunta==10){
            tvTituloPergunta.setText("Pergunta 10");
            tvPergunta.setText("Décima... is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s?");
            resposta_1 = "01. Décima rodada de perguntas... pergunta número 1.";
            resposta_2 = "02. Décima rodada de perguntas... pergunta número 2.";
            resposta_3 = "03. Décima rodada de perguntas... pergunta número 3.";
            resposta_4 = "04. Décima rodada de perguntas... pergunta número 4.";
            respostaCerta=2;
        } else if (pergunta>10){
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("pontos",pontos);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        pergunta++;
    }
}
