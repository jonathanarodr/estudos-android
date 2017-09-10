package br.com.jonathan.casadocodigo.dagger;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import javax.inject.Singleton;

import br.com.jonathan.casadocodigo.R;
import dagger.Module;
import dagger.Provides;

@Module
public class FirebaseModule {

    @Provides
    @Singleton
    public FirebaseAuth getFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    @Singleton
    public FirebaseRemoteConfig providerRemoteConfig(FirebaseRemoteConfigSettings settings) {
        FirebaseRemoteConfig config = FirebaseRemoteConfig.getInstance();
        config.setDefaults(R.xml.remote_config);
        config.setConfigSettings(settings);

        return config;
    }

    @Provides
    public FirebaseRemoteConfigSettings providerConfigSettings() {
        return new FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(true).build();
    }

}
