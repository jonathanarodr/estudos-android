package br.com.jonathan.casadocodigo.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import br.com.jonathan.casadocodigo.CasaDoCodigoApplication;
import br.com.jonathan.casadocodigo.R;
import br.com.jonathan.casadocodigo.adapter.LivroAdapter;
import br.com.jonathan.casadocodigo.event.EndlessListListener;
import br.com.jonathan.casadocodigo.model.Autor;
import br.com.jonathan.casadocodigo.model.Livro;
import br.com.jonathan.casadocodigo.services.WebClient;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaLivrosFragment extends Fragment {

    private List<Livro> livros = new ArrayList<>();

    @Inject
    FirebaseRemoteConfig firebaseRemoteConfig;

    @BindView(R.id.lista_livros)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_livros, container, false);
        ButterKnife.bind(this, view);

        CasaDoCodigoApplication app = ((CasaDoCodigoApplication) getActivity().getApplication());
        app.getComponent().inject(this);

        firebaseRemoteConfig.fetch(30).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    firebaseRemoteConfig.activateFetched();
                    boolean isSimpleList = firebaseRemoteConfig.getBoolean("list_simple");
                    recyclerView.setAdapter(new LivroAdapter(livros, isSimpleList));
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                }
            }
        });

        return view;
    }

    public void populaLista(final List<Livro> livros) {
        this.livros.clear();
        this.livros.addAll(livros);
        recyclerView.getAdapter().notifyDataSetChanged();

        recyclerView.addOnScrollListener(new EndlessListListener() {
            @Override
            public void loadItensList() {
                new WebClient().getLivros(livros.size(), 10);
            }
        });
    }
}
