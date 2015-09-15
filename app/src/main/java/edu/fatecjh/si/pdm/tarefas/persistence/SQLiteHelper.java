package edu.fatecjh.si.pdm.tarefas.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Icaro on 04/09/2015.
 */
class SQLiteHelper extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "TAREFASDB";

    public static final int VERSAO = 3;

    public SQLiteHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TarefaDAOSQLiteImpl.SCRIPT_CRIACAO_TABELA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TarefaDAOSQLiteImpl.SCRIPT_DELECAO_TABELA);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
