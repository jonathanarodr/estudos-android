package br.com.jonathan.casadocodigo.services;

import java.util.List;

import br.com.jonathan.casadocodigo.model.Livro;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LivroService {

    @GET("listarLivros")
    Call<List<Livro>> listarLivros(@Query("indicePrimeiroLivro") int indicePrimeiroLivro, @Query("qtdLivros") int qtdLivros);

}