package br.com.jonathan.casadocodigo.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import br.com.jonathan.casadocodigo.CasaDoCodigoApplication;
import br.com.jonathan.casadocodigo.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private boolean auth = false;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Inject
    FirebaseAuth firebaseAuth;

    @BindView(R.id.login_email)
    TextView email;
    @BindView(R.id.login_senha)
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        CasaDoCodigoApplication app = ((CasaDoCodigoApplication) getApplication());
        app.getComponent().inject(this);

        ButterKnife.bind(this);

        onAuthStateChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (firebaseAuthListener != null) {
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }

    /**
     * Firebase: utilizado para notificacao ao trocar status de autenticacao
     */
    public void onAuthStateChanged() {
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null && !auth) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    auth = true;
                    vaiParaMain();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    auth = false;
                }
            }
        };

        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    private void vaiParaMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.login_novo)
    public void createAccount(final View view) {
        String email = this.email.getText().toString().trim();
        String password = this.password.getText().toString().trim();

        if ((email.isEmpty()) || (password.isEmpty())) {
            Snackbar.make(this.password, "Preencha todos os campos", Snackbar.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "Authentication failed.", task.getException());
                        }
                        // ...
                    }
                });
    }

    @OnClick(R.id.login_logar)
    public void signIn() {
        String email = this.email.getText().toString().trim();
        String password = this.password.getText().toString().trim();

        if ((email.isEmpty()) || (password.isEmpty())) {
            Snackbar.make(this.password, "Preencha todos os campos", Snackbar.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    @OnClick(R.id.login_logar)
    public void autenticar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("login", );
    }

}
