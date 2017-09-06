package br.com.jonathan.casadocodigo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.jonathan.casadocodigo.R;
import br.com.jonathan.casadocodigo.model.Livro;

public class LivroAdapter extends RecyclerView.Adapter {

    private List<Livro> livros;

    public LivroAdapter(List<Livro> livros) {
        this.livros = livros;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nome;
        ImageView foto;

        public ViewHolder(View view) {
            super(view);

            nome = (TextView) view.findViewById(R.id.item_livro_nome);
            foto = (ImageView) view.findViewById(R.id.item_livro_foto);
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
