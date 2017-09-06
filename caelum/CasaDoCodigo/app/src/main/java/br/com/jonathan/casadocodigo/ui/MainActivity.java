package br.com.jonathan.casadocodigo.ui;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import br.com.jonathan.casadocodigo.R;
import br.com.jonathan.casadocodigo.delegate.LivroDelegate;
import br.com.jonathan.casadocodigo.event.LivroEvent;
import br.com.jonathan.casadocodigo.model.Livro;
import br.com.jonathan.casadocodigo.services.WebClient;

public class MainActivity extends AppCompatActivity implements LivroDelegate, FragmentManager.OnBackStackChangedListener {

    private ListaLivrosFragment livrosFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        this.livrosFragment = new ListaLivrosFragment();
        transaction.replace(R.id.frame_principal, this.livrosFragment);
        transaction.commit();

        new WebClient().getLivros();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void clickItem(Livro livro) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DetalhesLivroFragment fragment = criarDetalhesCom(livro);
        transaction.replace(R.id.frame_principal, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        getSupportFragmentManager().addOnBackStackChangedListener(this);
        setDisplayUpToolBar();
    }

    @Subscribe
    public void onSuccess(LivroEvent livroEvent) {
        livrosFragment.populaLista(livroEvent.getLivros());
    }

    @Subscribe
    public void onFailure(Throwable t) {
        Snackbar.make(findViewById(R.id.frame_principal), t.getMessage(), Snackbar.LENGTH_INDEFINITE)
                .setAction("try", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Carregando livros...", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private DetalhesLivroFragment criarDetalhesCom(Livro livro) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("livro", livro);

        DetalhesLivroFragment fragment = new DetalhesLivroFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    //implementando botao voltar usando fragments
    public void setDisplayUpToolBar() {
        boolean back = getSupportFragmentManager().getBackStackEntryCount() > 0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(back);
    }

    @Override
    public void onBackStackChanged() {
        this.setDisplayUpToolBar();
    }

    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStack();
        return true;
    }
}
