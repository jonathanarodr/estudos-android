package alura.com.br.agenda.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import alura.com.br.agenda.dao.AlunoDAO;
import alura.com.br.agenda.helper.FormularioHelper;
import alura.com.br.agenda.R;
import alura.com.br.agenda.model.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_formulario_ok) {
            Aluno aluno = helper.getAluno();
            AlunoDAO alunoDAO = new AlunoDAO(this);
            alunoDAO.insert(aluno);
            alunoDAO.close();

            Toast.makeText(FormularioActivity.this, aluno.getNome() + " salvo com sucesso", Toast.LENGTH_SHORT).show();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
