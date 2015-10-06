package edu.fatecjh.si.pdm.tarefas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import edu.fatecjh.si.pdm.tarefas.persistence.TarefaDAO;

public class ListaTarefasActivity extends AppCompatActivity {

    BaseAdapter adpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Define o layout
        setContentView(R.layout.activity_listar_tarefas);

        // Obtem os dados
        adpt = new TarefasAdapter(this);

        // Pega lista para colocar o adapter
        ListView lista = (ListView) findViewById(R.id.listaTarefas);
        lista.setAdapter(adpt);

        lista.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent detalhes = new Intent(ListaTarefasActivity.this, DetalhesTarefaActivity.class);
                        detalhes.putExtra(DetalhesTarefaActivity.PARAM_COD_TAREFA, id);
                        startActivity(detalhes);
                    }
                }
        );

        lista.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        // Pega a Tarefa no Repositorio
                        Tarefa tar = TarefaDAO.getInstance(ListaTarefasActivity.this).pegar(id);
                        tar.status = StatusTarefa.COMPLETA;
                        TarefaDAO.getInstance(ListaTarefasActivity.this).atualizar(tar);
                        adpt.notifyDataSetChanged();
                        return false;
                    }
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla menu de opcoes; adiciona items aa action bar
        getMenuInflater().inflate(R.menu.menu_lista_tarefas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Trata Acao de Criar uma nova Tarefa
        if (id == R.id.action_criar_tarefa) {
            // Inicia a tela para preenchimento da nova Tarefa
            Intent criarTarefa = new Intent(this, CriarTarefaActivity.class);
            int codigoRequisicao = 0;
            startActivityForResult(criarTarefa, codigoRequisicao);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK){
            // CriarTarefaActivity terminou com sucesso, notifica o adapter para se atualizar
            // e pedir para o ListView re redesenhar
            adpt.notifyDataSetChanged();

        }
    }
}
