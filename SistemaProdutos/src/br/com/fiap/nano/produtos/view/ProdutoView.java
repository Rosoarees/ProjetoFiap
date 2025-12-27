package br.com.fiap.nano.produtos.view;

import br.com.fiap.nano.produtos.model.Categoria;
import br.com.fiap.nano.produtos.model.Produto;
import br.com.fiap.nano.produtos.repository.ProdutoCollectionRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.swing.*;

public class ProdutoView {

    public static Produto select() {
        return (Produto) JOptionPane.showInputDialog(
                null, 
                "Selecione um produto",
                "Menu",
                JOptionPane.QUESTION_MESSAGE,
                null, 
                ProdutoCollectionRepository.findAll().toArray(), 
                1);
    }

    public static Produto form() {
        Categoria categoria;
        do {
            categoria = CategoriaView.select(null);
          
            if (categoria == null && JOptionPane.showConfirmDialog(null, "Deseja cancelar o cadastro?", "Cancelar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                return null;
            }
        } while (categoria == null);

        String nome;
        do {
            nome = JOptionPane.showInputDialog("Nome do Produto");
            if (nome == null) return null; // Cancelou
            if (nome.length() < 3)
                JOptionPane.showMessageDialog(null, "O nome do produto precisa ter no mínimo 3 digitos");
        } while (nome.length() < 3);

        String descricao;
        do {
            descricao = JOptionPane.showInputDialog("Descrição do Produto");
            if (descricao == null) return null; // Cancelou
            if (descricao.length() < 5)
                JOptionPane.showMessageDialog(null, "A descrição do produto precisa ter no mínimo 5 digitos");
        } while (descricao.length() < 5);

        double p = 0;
        do {
            try {
                String input = JOptionPane.showInputDialog("Preço do Produto");
                if (input == null) return null; // Cancelou
                p = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                p = 0;
            }
            if (p <= 0) JOptionPane.showMessageDialog(null, "Valor inválido");
        } while (p <= 0);

        BigDecimal preco = BigDecimal.valueOf(p);

        Produto ret = new Produto();
        ret.setNome(nome)
            .setCategoria(categoria)
            .setDescricao(descricao)
            .setPreco(preco)
            .setDataCadastro(LocalDateTime.now());

        return ret;
    }

    public static void update(Produto produto) {
        if(produto == null) return;

        Categoria categoria = CategoriaView.select(produto.getCategoria());
        if (categoria != null) produto.setCategoria(categoria);

       
        String nome = JOptionPane.showInputDialog("Nome do Produto", produto.getNome());
        if (nome != null && nome.length() >= 3) produto.setNome(nome);

        String descricao = JOptionPane.showInputDialog("Descrição do Produto", produto.getDescricao());
        if (descricao != null && descricao.length() >= 5) produto.setDescricao(descricao);

        try {
            String input = JOptionPane.showInputDialog("Preço do Produto", produto.getPreco());
            if(input != null) {
                double p = Double.parseDouble(input);
                if (p > 0) produto.setPreco(BigDecimal.valueOf(p));
            }
        } catch (NumberFormatException e) {
           
        }

        sucesso(produto);
        show(produto);
    }

    public static void sucesso(Produto produto) {
        System.out.println(produto);
        JOptionPane.showMessageDialog(null, "Produto " + produto.getNome().toUpperCase() + " salvo com sucesso!");
    }

    public static void show(Produto p) {
        System.out.println(p);
        String produtoString = String.format("PRODUTO: %s\nDESCRIÇÃO: %s\nCATEGORIA: %s\nPREÇO: R$ %,.2f", 
            p.getNome(), p.getDescricao(), p.getCategoria().toString(), p.getPreco());
        JOptionPane.showMessageDialog(null, produtoString);
    }
}