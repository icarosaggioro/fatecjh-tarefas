package edu.fatecjh.si.pdm.tarefas.persistence;

import android.content.Context;

import edu.fatecjh.si.pdm.tarefas.Tarefa;

/**
 * Created by Icaro on 04/09/2015.
 */
public abstract class TarefaDAO {

    private static TarefaDAO instance;

    public static TarefaDAO getInstance(Context ctx) {
        if(instance == null) {
            instance = new TarefaDAOSQLiteImpl(ctx);
        }
        return instance;
    }

    public abstract long criar(Tarefa tar);

    public abstract Tarefa[] pegarTodos();

    public abstract Tarefa pegar(long id);

    public abstract void atualizar(Tarefa tar);

    public abstract void deletar(Tarefa tar);

}
