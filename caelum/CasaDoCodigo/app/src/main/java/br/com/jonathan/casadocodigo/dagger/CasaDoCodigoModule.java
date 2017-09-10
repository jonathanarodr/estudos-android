package br.com.jonathan.casadocodigo.dagger;

import javax.inject.Singleton;

import br.com.jonathan.casadocodigo.model.Carrinho;
import dagger.Module;
import dagger.Provides;

@Module
public class CasaDoCodigoModule {

    @Provides
    @Singleton
    public Carrinho getCarrinho() {
        return new Carrinho();
    }

}
