package br.com.jonathan.casadocodigo.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.jonathan.casadocodigo.R;
import br.com.jonathan.casadocodigo.delegate.LivroDelegate;
import br.com.jonathan.casadocodigo.model.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LivroAdapter extends RecyclerView.Adapter {

    private List<Livro> livros;

    public LivroAdapter(List<Livro> livros) {
        this.livros = livros;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_livro_nome)
        TextView nome;
        @BindView(R.id.item_livro_foto)
        ImageView foto;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_livro)
        public void onClickItem() {
            Livro livro = livros.get(getAdapterPosition());
            LivroDelegate delegate = (LivroDelegate) itemView.getContext();
            delegate.clickItem(livro);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = R.layout.activity_item_livro_par;

        if (viewType % 2 != 0) {
            layout = R.layout.activity_item_livro_impar;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Livro livro = livros.get(position);
        viewHolder.nome.setText(livro.getNome());

        //carrega imagem com lib picasso
        Picasso.with(viewHolder.foto.getContext())
                .load(livro.getUrlFoto())
                .placeholder(R.drawable.livro)
                .into(viewHolder.foto);
    }

    @Override
    public int getItemCount() {
        return this.livros.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

}
