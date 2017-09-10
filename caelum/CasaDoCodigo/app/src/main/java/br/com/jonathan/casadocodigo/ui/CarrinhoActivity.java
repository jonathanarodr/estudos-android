package br.com.jonathan.casadocodigo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import javax.inject.Inject;

import br.com.jonathan.casadocodigo.CasaDoCodigoApplication;
import br.com.jonathan.casadocodigo.R;
import br.com.jonathan.casadocodigo.adapter.CarrinhoAdapter;
import br.com.jonathan.casadocodigo.model.Carrinho;
import br.com.jonathan.casadocodigo.model.Item;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CarrinhoActivity extends AppCompatActivity {

    @Inject
    Carrinho carrinho;

    @BindView(R.id.lista_itens_livros)
    RecyclerView recyclerView;
    @BindView(R.id.carrinho_valtotal)
    TextView val_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        CasaDoCodigoApplication app = ((CasaDoCodigoApplication) getApplication());
        app.getComponent().inject(this);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void carregaLista() {
        recyclerView.setAdapter(new CarrinhoAdapter(carrinho.getItens()));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        double total = 0;
        for (Item item : carrinho.getItens()) {
            total += item.getValor();
        }

        val_total.setText("R$ " + total);
    }
}
