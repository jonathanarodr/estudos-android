package br.com.jonathan.casadocodigo;

import android.app.Application;

import br.com.jonathan.casadocodigo.dagger.CasaDoCodigoComponent;
import br.com.jonathan.casadocodigo.dagger.DaggerCasaDoCodigoComponent;

public class CasaDoCodigoApplication extends Application {

    private CasaDoCodigoComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerCasaDoCodigoComponent.builder().build();
    }

    public CasaDoCodigoComponent getComponent() {
        return component;
    }

}
