package alura.com.br.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import alura.com.br.agenda.model.Aluno;

public class AlunoDAO extends SQLiteOpenHelper
{

    private static final String DB_NAME = "Agenda";
    private static final int DB_VERSION = 1;

    public AlunoDAO(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Alunos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, site TEXT, nota REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Alunos";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insert(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nome", aluno.getNome());
        values.put("endereco", aluno.getEndereco());
        values.put("telefone", aluno.getTelefone());
        values.put("site", aluno.getSite());
        values.put("nota", aluno.getNota());

        db.insert("Alunos", null, values);
    }

    public List<Aluno> list() {
        String sql = "SELECT * FROM Alunos";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        List<Aluno> alunos = new ArrayList<>();

        while (cursor.moveToNext()) {
            Aluno aluno = new Aluno();

            aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("site")));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));

            alunos.add(aluno);
        }

        cursor.close();

        return alunos;
    }

}
