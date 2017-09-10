package br.com.jonathan.casadocodigo.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import br.com.jonathan.casadocodigo.adapter.LivroAdapter;

public class ColorUpdater {

    private AppCompatActivity activity;
    private FirebaseRemoteConfig firebaseRemoteConfig;

    public ColorUpdater(AppCompatActivity activity, FirebaseRemoteConfig firebaseRemoteConfig) {
        this.activity = activity;
        this.firebaseRemoteConfig = firebaseRemoteConfig;

        this.updateConfig();
    }

    private void updateConfig() {
        firebaseRemoteConfig.fetch(30).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    firebaseRemoteConfig.activateFetched();
                    String toolbarColorEx = firebaseRemoteConfig.getString("toolbar_color");
                    String statusColorEx = firebaseRemoteConfig.getString("statusbar_color");
                    setBarsColor(toolbarColorEx, statusColorEx);
                }
            }
        });
    }

    private void setBarsColor(String toolbarColorEx, String statusColorEx) {
        int toolbarColor = Color.parseColor(toolbarColorEx);
        int statusColor = Color.parseColor(statusColorEx);

        activity.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(toolbarColor));

        //verifica suporte ao statusbar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(statusColor);
        }
    }

}
