package edu.fatecjh.si.pdm.tarefas;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetalhesTarefaActivity extends AppCompatActivity {

    public static final String PARAM_COD_TAREFA = "PARAM_COD_TAREFA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_tarefa);
    }

}
