package edu.fatecjh.si.pdm.tarefas;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;


public class CriarTarefaActivity extends ActionBarActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_tarefa);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_criar_tarefa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_salvar) {
            // Trata criacao da Tarefa
            tratarCriarTarefa();

            // Registra que o resultado desta Activity cumpriu o objetivo
            setResult(Activity.RESULT_OK, null);

            // Finaliza a Activity, controle volta para a Activity anterior
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tratarCriarTarefa() {
        // Cria nova instancia de Tarefa em memoria
        Tarefa tar = new Tarefa();

        // Preenche com dados default
        tar.status = StatusTarefa.NOVA;

        tar.dataCriacao = Util.getCurrentTimestamp();

        // Preenche com os valores entrados pelo Usuario
        EditText edTxtTitulo = (EditText) findViewById(R.id.edTitulo);
        tar.titulo = edTxtTitulo.getText().toString();

        EditText edTxtDescricao = (EditText) findViewById(R.id.edDesc);
        tar.descricao = edTxtDescricao.getText().toString();

        Spinner spTipo = (Spinner) findViewById(R.id.spTipoTarefa);
        tar.tipo = TipoTarefa.values()[spTipo.getSelectedItemPosition()];

        DatePicker dtPicker = (DatePicker) findViewById(R.id.datePicker);
        int day = dtPicker.getDayOfMonth();
        int month = dtPicker.getMonth();
        int year = dtPicker.getYear();
        tar.dataCumprimento = Util.getTimestampFromDate(day, month, year);


        // Persistencia
        RepositorioTarefa.criar(tar);

    }

}
