package alura.com.br.agenda.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import alura.com.br.agenda.R;
import alura.com.br.agenda.dao.AlunoDAO;
import alura.com.br.agenda.model.Aluno;

public class MainActivity extends AppCompatActivity {

    private ListView listaAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_novo = findViewById(R.id.novo_aluno);
        listaAluno = findViewById(R.id.lista_aluno);

        listaAluno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int position, long id) {
                Aluno aluno = (Aluno) listaAluno.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });

        btn_novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(listaAluno);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaAlunos();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDeletar = menu.add("Excluir");

        menuDeletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Aluno aluno = (Aluno) listaAluno.getItemAtPosition(((AdapterView.AdapterContextMenuInfo) menuInfo).position);

                AlunoDAO alunoDAO = new AlunoDAO(MainActivity.this);
                alunoDAO.delete(aluno);

                carregaAlunos();

                return false;
            }
        });
    }



    private void carregaAlunos() {AlunoDAO alunoDAO = new AlunoDAO(this);
        List<Aluno> alunos = alunoDAO.list();
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);

        listaAluno.setAdapter(adapter);
    }
}
