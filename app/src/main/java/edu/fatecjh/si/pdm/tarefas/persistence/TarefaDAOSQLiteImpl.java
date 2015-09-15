package edu.fatecjh.si.pdm.tarefas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.fatecjh.si.pdm.tarefas.StatusTarefa;
import edu.fatecjh.si.pdm.tarefas.Tarefa;
import edu.fatecjh.si.pdm.tarefas.TipoTarefa;

class TarefaDAOSQLiteImpl extends TarefaDAO {

    private static final String NOME_TABELA = "TAREFA";

    private static final String COLUNA_CODIGO           = "CODIGO";            // INTEGER
    private static final String COLUNA_TITULO           = "TITULO";            // TEXT
    private static final String COLUNA_DESCRICAO        = "DESCRICAO";         // TEXT
    private static final String COLUNA_DATA_CRIACAO     = "DATA_CRIACAO";      // INTEGER
    private static final String COLUNA_DATA_CUMPRIMENTO = "DATA_CUMPRIMENTO";  // INTEGER
    private static final String COLUNA_STATUS           = "STATUS";            // INTEGER
    private static final String COLUNA_TIPO             = "TIPO";              // INTEGER

    static final java.lang.String SCRIPT_CRIACAO_TABELA = "CREATE TABLE " + NOME_TABELA
            + "("
            + COLUNA_CODIGO + " INTEGER PRIMARY KEY, "
            + COLUNA_TITULO + " TEXT, "
            + COLUNA_DESCRICAO + " TEXT, "
            + COLUNA_DATA_CRIACAO + " INTEGER, "
            + COLUNA_DATA_CUMPRIMENTO + " INTEGER, "
            + COLUNA_STATUS + " INTEGER, "
            + COLUNA_TIPO + " INTEGER"
            + ")";

    static final java.lang.String SCRIPT_DELECAO_TABELA = "DROP TABLE IF EXISTS " + NOME_TABELA;

    private SQLiteOpenHelper helper;

    public TarefaDAOSQLiteImpl(Context ctx) {
        this.helper = new SQLiteHelper(ctx);
    }

    @Override
    public long criar(Tarefa tar) {

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUNA_TITULO, tar.titulo);
        values.put(COLUNA_DESCRICAO, tar.descricao);
        values.put(COLUNA_DATA_CRIACAO, tar.dataCriacao);
        values.put(COLUNA_DATA_CUMPRIMENTO, tar.dataCumprimento);
        values.put(COLUNA_STATUS, tar.status.ordinal());
        values.put(COLUNA_TIPO, tar.tipo.ordinal());

        long id = db.insert(NOME_TABELA, null, values);

        return id;
        
    }

    @Override
    public Tarefa[] pegarTodos() {

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor res = db.rawQuery("select * from " + NOME_TABELA, null);
        Tarefa[] tarefas = construirTarefaPorCursor(res);
        res.close();

        return tarefas;
    }

    @Override
    public Tarefa pegar(long id) {

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor res = db.rawQuery("select * from " + NOME_TABELA + " where " + COLUNA_CODIGO + " = " + id, null);

        return construirTarefaPorCursor(res)[0];
    }

    @Override
    public void atualizar(Tarefa tar) {

        SQLiteDatabase db = helper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUNA_TITULO, tar.titulo);
        values.put(COLUNA_DESCRICAO, tar.descricao);
        values.put(COLUNA_DATA_CRIACAO, tar.dataCriacao);
        values.put(COLUNA_DATA_CUMPRIMENTO, tar.dataCumprimento);
        values.put(COLUNA_STATUS, tar.status.ordinal());
        values.put(COLUNA_TIPO, tar.tipo.ordinal());

        db.update(NOME_TABELA,
                values,
                COLUNA_CODIGO + " = ? ",
                new String[] { Integer.toString(tar.codigo) }
        );

    }

    @Override
    public void deletar(Tarefa tar) {

        String[] selectionArgs = { Integer.toString(tar.codigo) };

        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(NOME_TABELA, " id = ? ", selectionArgs);

    }

    private Tarefa[] construirTarefaPorCursor(Cursor cursor) {

        if (cursor == null) {
            return new Tarefa[0];
        }

        Tarefa[] tarefas = new Tarefa[cursor.getCount()];
        int tarIndex = 0;

        try {
            if (cursor.moveToFirst()) {
                do {

                    int indexCodigo = cursor.getColumnIndex(COLUNA_CODIGO);
                    int indexTitulo = cursor.getColumnIndex(COLUNA_TITULO);
                    int indexDescricao = cursor.getColumnIndex(COLUNA_DESCRICAO);
                    int indexDTCriacao = cursor.getColumnIndex(COLUNA_DATA_CRIACAO);
                    int indexDTCumprimento = cursor.getColumnIndex(COLUNA_DATA_CUMPRIMENTO);
                    int indexStatus = cursor.getColumnIndex(COLUNA_STATUS);
                    int indexTipo = cursor.getColumnIndex(COLUNA_TIPO);

                    Tarefa tar = new Tarefa();
                    tar.codigo = cursor.getInt(indexCodigo);
                    tar.titulo = cursor.getString(indexTitulo);
                    tar.descricao = cursor.getString(indexDescricao);
                    tar.dataCriacao = cursor.getLong(indexDTCriacao);
                    tar.dataCumprimento = cursor.getLong(indexDTCumprimento);
                    tar.status = StatusTarefa.values()[cursor.getInt(indexStatus)];
                    tar.tipo = TipoTarefa.values()[cursor.getInt(indexTipo)];

                    tarefas[tarIndex] = tar;
                    tarIndex++;

                } while (cursor.moveToNext());
            }

        } finally {
            cursor.close();
        }
        return tarefas;
    }

}
