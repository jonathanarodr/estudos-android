package br.com.jonathan.casadocodigo.services;

import java.util.List;

import br.com.jonathan.casadocodigo.converter.LivroServiceConverterFactory;
import br.com.jonathan.casadocodigo.delegate.LivroDelegate;
import br.com.jonathan.casadocodigo.model.Livro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WebClient {

    private static final String SERVER_URL = "http://cdcmob.herokuapp.com/";
    private LivroDelegate delegate;

    public WebClient(LivroDelegate delegate) {
        this.delegate = delegate;
    }

    public void getLivros() {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(new LivroServiceConverterFactory())
                .build();

        LivroService service = client.create(LivroService.class);

        Call<List<Livro>> call = service.listarLivros();
        call.enqueue(new Callback<List<Livro>>() {
            @Override
            public void onResponse(Call<List<Livro>> call, Response<List<Livro>> response) {
                delegate.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Livro>> call, Throwable t) {
                delegate.onFailure(t);
            }
        });
    }

}
