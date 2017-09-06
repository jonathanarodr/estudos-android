package br.com.jonathan.casadocodigo.event;

public class LivroEventError {

    private Throwable throwable;

    public LivroEventError(Throwable throwable) {
        this.throwable = throwable;
    }

}
