package br.com.jonathan.casadocodigo.delegate;

import java.util.List;

import br.com.jonathan.casadocodigo.model.Livro;

public interface LivroDelegate {

    public void clickItem(Livro livro);

    public void onSuccess(List<Livro> livros);

    public void onFailure(Throwable throwable);
}
