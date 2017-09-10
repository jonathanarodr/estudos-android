package br.com.jonathan.casadocodigo.dagger;

import javax.inject.Singleton;

import br.com.jonathan.casadocodigo.ui.CarrinhoActivity;
import br.com.jonathan.casadocodigo.ui.DetalhesLivroFragment;
import br.com.jonathan.casadocodigo.ui.LoginActivity;
import br.com.jonathan.casadocodigo.ui.MainActivity;
import dagger.Component;

@Singleton
@Component(modules = CasaDoCodigoModule.class)
public interface CasaDoCodigoComponent {

    void inject(DetalhesLivroFragment fragment);
    void inject(CarrinhoActivity activity);
    void inject(LoginActivity activity);
    void inject(MainActivity activity);

}
