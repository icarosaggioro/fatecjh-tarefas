package edu.fatecjh.si.pdm.tarefas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.fatecjh.si.pdm.tarefas.persistence.TarefaDAO;

public class AberturaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_abertura);

        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(5 * 1000);

                    // After 5 seconds redirect to another intent
                    Intent i = new Intent(getBaseContext(), ListaTarefasActivity.class);
                    startActivity(i);

                    //Remove activity
                    finish();

                } catch (Exception e) {

                }
            }
        };

        // start thread
        background.start();

        popularTabelaTarefa(this);

    }

    private void popularTabelaTarefa(Context ctx) {
        TarefaDAO dao = TarefaDAO.getInstance(ctx);
        Tarefa tar;

        tar = new Tarefa();
        tar.titulo = "Tarefa Teste 1";
        tar.descricao = "Descricao da Tarefa Teste 1";
        tar.tipo = TipoTarefa.FACULDADE;
        tar.status = StatusTarefa.NOVA;
        dao.criar(tar);

        tar = new Tarefa();
        tar.titulo = "Tarefa Teste 2";
        tar.descricao = "Descricao da Tarefa Teste 2";
        tar.tipo = TipoTarefa.SONHOS_FUTUROS;
        tar.status = StatusTarefa.NOVA;
        dao.criar(tar);

    }

}
