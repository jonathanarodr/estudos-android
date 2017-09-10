package br.com.jonathan.casadocodigo.event;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessListListener extends RecyclerView.OnScrollListener {

    private final int MAX_VISIBLE_ITENS = 5;

    private boolean loading;
    private int countVisibleItens;
    private int countItens;
    private int countItensBefore = 0;
    private int firstVisibleItem;

    @Override
    public void onScrolled(RecyclerView recyclerView, int scrollHorizonal, int scrollVertical) {
        super.onScrolled(recyclerView, scrollHorizonal, scrollVertical);
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        countVisibleItens = layoutManager.getChildCount();
        countItens = layoutManager.getItemCount();
        firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

        int indiceLimitLoad = countItens - countVisibleItens - MAX_VISIBLE_ITENS;

        if (loading) {
            if (countItens > countItensBefore) {
                countItensBefore = countItens;
                loading = false;
            }
        }

        if (!loading && firstVisibleItem >= indiceLimitLoad) {
            loadItensList();
            loading = true;
        }
    }

    public abstract void loadItensList();

}
