package br.com.jonathan.casadocodigo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.jonathan.casadocodigo.R;
import br.com.jonathan.casadocodigo.adapter.LivroAdapter;
import br.com.jonathan.casadocodigo.model.Autor;
import br.com.jonathan.casadocodigo.model.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaLivrosFragment extends Fragment {

    @BindView(R.id.lista_livros)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_livros, container, false);

        List<Livro> livros = new ArrayList<>();
        for (int i=0; i<6; i++) {
            Autor autor = new Autor();
            autor.setNome("Autor " + i);
            Livro livro = new Livro("Livro " + i, "Descrição " + i, Arrays.asList(autor));

            livros.add(livro);
        }

        ButterKnife.bind(this, view);

        recyclerView.setAdapter(new LivroAdapter(livros));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        return view;
    }
}
