package br.com.jonathan.casadocodigo.dagger;

import com.google.firebase.auth.FirebaseAuth;

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

    @Provides
    @Singleton
    public FirebaseAuth getFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

}
