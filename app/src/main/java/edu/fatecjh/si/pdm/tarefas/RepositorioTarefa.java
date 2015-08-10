package edu.fatecjh.si.pdm.tarefas;

import java.util.ArrayList;

/**
 * Created by Icaro on 08/06/2015.
 */
public class RepositorioTarefa {

    private static final ArrayList<Tarefa> repositorio = new ArrayList<>();

    public static void criar(Tarefa tar) {
        repositorio.add(tar);
    }

    public static Tarefa[] pegarTodos() {
        return repositorio.toArray(new Tarefa[repositorio.size()]);
    }

    public static Tarefa pegar(long id) {
        int localId = (int) id;
        return repositorio.get(localId);
    }

    public static void atualizar(Tarefa tar) {
        // nao faz nada nesse momento
    }

}
