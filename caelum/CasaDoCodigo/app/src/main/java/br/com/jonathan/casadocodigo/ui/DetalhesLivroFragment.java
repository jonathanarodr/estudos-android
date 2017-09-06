package br.com.jonathan.casadocodigo.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.jonathan.casadocodigo.R;
import br.com.jonathan.casadocodigo.model.Autor;
import br.com.jonathan.casadocodigo.model.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalhesLivroFragment extends Fragment {

    private Livro livro;

    @BindView(R.id.detalhes_livro_nome)
    TextView nome;
    @BindView(R.id.detalhes_livro_foto)
    ImageView foto;
    @BindView(R.id.detalhes_livro_autores)
    TextView autores;
    @BindView(R.id.detalhes_livro_comprar_fisico)
    Button val_fisico;
    @BindView(R.id.detalhes_livro_comprar_ebook)
    Button val_ebook;
    @BindView(R.id.detalhes_livro_comprar_ambos)
    Button val_ambos;
    @BindView(R.id.detalhes_livro_descricao)
    TextView descricao;
    @BindView(R.id.detalhes_livro_num_paginas)
    TextView num_paginas;
    @BindView(R.id.detalhes_livro_data_publicacao)
    TextView dt_publicacao;
    @BindView(R.id.detalhes_livro_isbn)
    TextView isbn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_livro, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        livro = (Livro) bundle.getSerializable("livro");
        this.setView(livro);

        return view;
    }

    private void setView(Livro livro) {
        nome.setText(livro.getNome());

        String listaAutores = "";
        for (Autor autor : livro.getAutores()) {
            if (!listaAutores.isEmpty()) {
                listaAutores += ", ";
            }
            listaAutores += autor.getNome();
        }
        autores.setText(listaAutores);
        descricao.setText(livro.getDescricao());
        num_paginas.setText(String.valueOf(livro.getNumPaginas()));
        isbn.setText(livro.getISBN());
        dt_publicacao.setText(livro.getDataPublicacao());
        val_fisico.setText(String.format("Comprar livro - R$ %.2f", livro.getValorFisico()));
        val_ebook.setText(String.format("Comprar eBook - R$ %.2f", livro.getValorVirtual()));
        val_ambos.setText(String.format("Comprar ambos - R$ %.2f", livro.getValorDoisJuntos()));

        //carrega imagem com lib picasso
        Picasso.with(getActivity())
                .load(livro.getUrlFoto())
                .placeholder(R.drawable.livro)
                .into(foto);
    }
}
