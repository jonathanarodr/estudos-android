package br.com.jonathan.casadocodigo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.jonathan.casadocodigo.R;
import br.com.jonathan.casadocodigo.model.Item;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CarrinhoAdapter extends RecyclerView.Adapter {

    private List<Item> itens;

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nome_item_comprado)
        TextView nome;
        @BindView(R.id.imagem_item_comprado)
        ImageView foto;
        @BindView(R.id.valor_pago_item_comprado)
        TextView valor;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public CarrinhoAdapter(List<Item> itens) {
        this.itens = itens;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = R.layout.activity_item_carrinho;
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CarrinhoAdapter.ViewHolder viewHolder = (CarrinhoAdapter.ViewHolder) holder;
        Item item = itens.get(position);
        viewHolder.nome.setText(item.getLivro().getNome());
        viewHolder.valor.setText(String.valueOf(item.getValor()));

        //carrega imagem com lib picasso
        Picasso.with(viewHolder.foto.getContext())
                .load(item.getLivro().getUrlFoto())
                .placeholder(R.drawable.livro)
                .into(viewHolder.foto);
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}
