package edu.fatecjh.si.pdm.tarefas;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class DetalhesTarefaActivity extends ActionBarActivity {

    public static final String PARAM_COD_TAREFA = "PARAM_COD_TAREFA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_tarefa);
    }

}
