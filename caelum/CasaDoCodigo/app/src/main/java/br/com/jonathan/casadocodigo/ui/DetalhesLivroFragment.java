package br.com.jonathan.casadocodigo.ui;

import android.content.Intent;
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

import javax.inject.Inject;

import br.com.jonathan.casadocodigo.CasaDoCodigoApplication;
import br.com.jonathan.casadocodigo.R;
import br.com.jonathan.casadocodigo.model.Autor;
import br.com.jonathan.casadocodigo.model.Carrinho;
import br.com.jonathan.casadocodigo.model.Item;
import br.com.jonathan.casadocodigo.model.Livro;
import br.com.jonathan.casadocodigo.model.TipoDeCompra;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetalhesLivroFragment extends Fragment {

    @Inject
    Carrinho carrinho;

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

        CasaDoCodigoApplication app = ((CasaDoCodigoApplication) getActivity().getApplication());
        app.getComponent().inject(this);

        Bundle bundle = getArguments();
        livro = (Livro) bundle.getSerializable("livro");

        this.setView(livro);

        return view;
    }

    @OnClick(R.id.detalhes_livro_comprar_fisico)
    public void onClickCompraFisico(View view) {
        carrinho.add(new Item(livro, TipoDeCompra.FISICO));
        viewCarrinho();
    }

    @OnClick(R.id.detalhes_livro_comprar_ebook)
    public void onClickCompraeBook(View view) {
        carrinho.add(new Item(livro, TipoDeCompra.VIRTUAL));
        viewCarrinho();
    }

    @OnClick(R.id.detalhes_livro_comprar_ambos)
    public void onClickCompraAmbos(View view) {
        carrinho.add(new Item(livro, TipoDeCompra.JUNTOS));
        viewCarrinho();
    }

    private void viewCarrinho() {
        Intent intent = new Intent(getActivity(), CarrinhoActivity.class);
        startActivity(intent);
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
