package edu.fatecjh.si.pdm.tarefas.persistence;

import java.util.ArrayList;

import edu.fatecjh.si.pdm.tarefas.Tarefa;

/**
 * Created by Icaro on 08/06/2015.
 */
class TarefaDAOArrayImpl extends TarefaDAO {

    private static final ArrayList<Tarefa> repositorio = new ArrayList<>();


    @Override
    public long criar(Tarefa tar) {

        repositorio.add(tar);

        return -1;
    }

    @Override
    public Tarefa[] pegarTodos() {
        return repositorio.toArray(new Tarefa[repositorio.size()]);
    }

    @Override
    public Tarefa pegar(long id) {
        int localId = (int) id;
        return repositorio.get(localId);
    }

    @Override
    public void atualizar(Tarefa tar) {
        // nao faz nada nesse momento
    }

    @Override
    public void deletar(Tarefa tar) {
        // nao faz nada nesse momento
    }

}
