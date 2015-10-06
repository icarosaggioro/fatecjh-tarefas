package edu.fatecjh.si.pdm.tarefas.persistence;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import edu.fatecjh.si.pdm.tarefas.StatusTarefa;
import edu.fatecjh.si.pdm.tarefas.Tarefa;
import edu.fatecjh.si.pdm.tarefas.TipoTarefa;

/**
 * Created by Icaro on 11/09/2015.
 */
public class TarefaDAOSQLiteImplTest extends AndroidTestCase {

    private TarefaDAO db;

    public void setUp() throws  Exception {
        super.setUp();

        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        db = new TarefaDAOSQLiteImpl(context);

    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCriarTarefa(){
        Tarefa tar = new Tarefa();
        tar.titulo = "Titulo 1";
        tar.descricao = "Descrica 1";
        tar.dataCriacao = 1000000;
        tar.dataCumprimento = 10000001;
        tar.status = StatusTarefa.NOVA;
        tar.tipo = TipoTarefa.PRESENTE;

        long id = db.criar(tar);

        assertTrue(id > 0);

    }

    public void testPegarTarefa(){
        Tarefa tar = new Tarefa();
        tar.titulo = "Titulo 1";
        tar.descricao = "Descrica 1";
        tar.dataCriacao = 1000000;
        tar.dataCumprimento = 10000001;
        tar.status = StatusTarefa.NOVA;
        tar.tipo = TipoTarefa.PRESENTE;
        long id = db.criar(tar);

        Tarefa tar2 = db.pegar(id);

        assertNotNull(tar2);
        assertTrue(tar.titulo.equals(tar2.titulo));

    }



}
