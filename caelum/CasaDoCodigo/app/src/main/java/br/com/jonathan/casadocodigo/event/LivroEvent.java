package br.com.jonathan.casadocodigo.event;

import java.util.List;

import br.com.jonathan.casadocodigo.model.Livro;

public class LivroEvent {

    private final List<Livro> livros;

    public LivroEvent(List<Livro> livros) {
        this.livros = livros;
    }

    public List<Livro> getLivros() {
        return livros;
    }

}


