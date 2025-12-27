package br.com.fiap.nano.produtos.repository;

import br.com.fiap.nano.produtos.model.Categoria;
import br.com.fiap.nano.produtos.model.Produto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList; 
import java.util.Arrays;
import java.util.List;

public class ProdutoCollectionRepository {

   
    private static final List<Produto> produtos;

    static {
        produtos = new ArrayList<>();

       
        Produto kindle = new Produto();
        kindle.setCategoria(CategoriaCollectionRepository.findById(1L))
                .setNome("Kindle")
                .setDescricao("e-reader da Amazon")
                .setDataCadastro(LocalDateTime.now())
                .setPreco(BigDecimal.valueOf(899.99));

        Produto iphone = new Produto();
        iphone.setCategoria(CategoriaCollectionRepository.findById(2L))
                .setNome("Iphone 14 PRO MAX")
                .setDescricao("Aparelho celular de última geração da Apple")
                .setDataCadastro(LocalDateTime.now())
                .setPreco(BigDecimal.valueOf(12999.99));

        Arrays.asList(kindle, iphone).forEach(ProdutoCollectionRepository::save);
    }

    public static Produto save(Produto produto) {
        if (!produtos.contains(produto)) {
            produto.setId((long) produtos.size() + 1);
            produtos.add(produto);
            return produto;
        } else {
            return null;
        }
    }

    public static Produto findById(Long id) {
        return produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static List<Produto> findByCategoria(Categoria categoria) {
        return produtos.stream()
                .filter(p -> p.getCategoria().equals(categoria))
                .toList();
    }

    public static List<Produto> findAll() {
        return produtos;
    }
}