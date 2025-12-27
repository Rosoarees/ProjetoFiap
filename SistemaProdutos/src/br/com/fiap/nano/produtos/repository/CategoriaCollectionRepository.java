package br.com.fiap.nano.produtos.repository;

import br.com.fiap.nano.produtos.model.Categoria;
import java.util.ArrayList; 
import java.util.Arrays;
import java.util.List;

public class CategoriaCollectionRepository {

   
    private static final List<Categoria> categorias;

    static {
       
        categorias = new ArrayList<>(); 
        String[] nomes = {"Eletrônicos", "Celulares", "Livros", "Games"};
        Arrays.asList(nomes).forEach(CategoriaCollectionRepository::save);
    }

    public static Categoria save(String s) {
        Categoria c = new Categoria(s);
        return save(c);
    }

    public static Categoria save(Categoria categoria) {
        if (!categorias.contains(categoria)) {
            categoria.setId((long) categorias.size() + 1);
            categorias.add(categoria);
            return categoria;
        } else {
            return null;
        }
    }

    public static List<Categoria> findAll() {
        return categorias;
    }

    public static Categoria findById(Long id) {
        return categorias.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}