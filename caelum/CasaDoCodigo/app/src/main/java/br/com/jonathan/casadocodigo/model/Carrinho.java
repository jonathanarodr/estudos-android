package br.com.jonathan.casadocodigo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Carrinho implements Serializable {

    private List<Item> itens = new ArrayList<>();

    public void add(Item item) {
        itens.add(item);
    }

    public void remove(Item item) {
        itens.remove(item);
    }

    public void clear() {
        itens.clear();
    }

    public List<Item> getItens() {
        return Collections.unmodifiableList(itens);
    }

}
