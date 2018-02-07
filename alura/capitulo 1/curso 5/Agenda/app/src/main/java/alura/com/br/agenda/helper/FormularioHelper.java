package alura.com.br.agenda.helper;

import android.widget.EditText;
import android.widget.RatingBar;

import alura.com.br.agenda.R;
import alura.com.br.agenda.activity.FormularioActivity;
import alura.com.br.agenda.model.Aluno;

public class FormularioHelper {

    private Aluno aluno;
    private final EditText nome;
    private final EditText endereco;
    private final EditText telefone;
    private final EditText site;
    private final RatingBar nota;

    public FormularioHelper(FormularioActivity activity) {
        aluno = new Aluno();

        nome = activity.findViewById(R.id.formulario_nome);
        endereco = activity.findViewById(R.id.formulario_endereco);
        telefone = activity.findViewById(R.id.formulario_telefone);
        site = activity.findViewById(R.id.formulario_site);
        nota = activity.findViewById(R.id.formulario_nota);
    }

    public Aluno getAluno() {
        aluno.setNome(this.nome.getText().toString());
        aluno.setEndereco(this.endereco.getText().toString());
        aluno.setTelefone(this.telefone.getText().toString());
        aluno.setSite(this.site.getText().toString());
        aluno.setNota((double) this.nota.getProgress());

        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;

        nome.setText(aluno.getNome());
        endereco.setText(aluno.getEndereco());
        telefone.setText(aluno.getTelefone());
        site.setText(aluno.getSite());
        nota.setProgress(aluno.getNota().intValue());
    }

}
