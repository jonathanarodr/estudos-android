package br.com.jonathan.casadocodigo.services;

import java.util.List;

import br.com.jonathan.casadocodigo.model.Livro;
import retrofit2.Call;
import retrofit2.http.GET;

public interface LivroService {

    @GET("listarLivros?indicePrimeiroLivro=0&qtdLivros=20")
    Call<List<Livro>> listarLivros();

}