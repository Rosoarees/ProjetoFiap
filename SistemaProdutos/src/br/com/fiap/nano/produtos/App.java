package br.com.fiap.nano.produtos;

import br.com.fiap.nano.produtos.model.Categoria;
import br.com.fiap.nano.produtos.model.Produto;
import br.com.fiap.nano.produtos.repository.CategoriaCollectionRepository;
import br.com.fiap.nano.produtos.repository.ProdutoCollectionRepository;
import br.com.fiap.nano.produtos.view.CategoriaView;
import br.com.fiap.nano.produtos.view.Opcao;
import br.com.fiap.nano.produtos.view.OpcaoView;
import br.com.fiap.nano.produtos.view.ProdutoView;
import java.util.List;
import javax.swing.*;

public class App {

    public static void main(String[] args) {

        Opcao opcao; 
        do {
            opcao = OpcaoView.select();
            switch (opcao) {
                
                case CADASTRAR_CATEGORIA -> cadastrarCategoria();
                case CADASTRAR_PRODUTO -> cadastrarproduto();
                case ALTERAR_PRODUTO -> alterarproduto();
                case CONSULTAR_PRODUTO_POR_ID -> consultarprodutoporid();
                case CONSULTAR_PRODUTO_POR_CATEGORIA -> consultarprodutoporcategoria();
                case ENCERRAR_SISTEMA -> System.exit(0);
            }
        } while (opcao != Opcao.ENCERRAR_SISTEMA);
    }

    public static void cadastrarCategoria() {
        Categoria categoria = CategoriaView.form(); 
        
        
        if (categoria != null) {
            CategoriaCollectionRepository.save(categoria);
            
            CategoriaView.sucesso(categoria);
        }
    }

    private static void cadastrarproduto() {
        Produto produto = ProdutoView.form();
        
        if (produto != null && produto.getNome() != null) {
            ProdutoCollectionRepository.save(produto);
            ProdutoView.sucesso(produto);
        }
    }

    private static void alterarproduto() {
        Produto produto = ProdutoView.select();
        if (produto != null) {
            ProdutoView.update(produto);
        }
    }

    private static void consultarprodutoporid() {
        long id = 0;
        do {
            try {
                String input = JOptionPane.showInputDialog("Informe o id do produto");
                if (input == null) return; 
                id = Long.parseLong(input); 
            } catch (NumberFormatException e) { 
                JOptionPane.showMessageDialog(null, "Id inválido! Digite apenas números.");
            }
        } while (id <= 0);

        Produto p = ProdutoCollectionRepository.findById(id);
        if (p != null) {
            ProdutoView.show(p);
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
        }
    }

    private static void consultarprodutoporcategoria() {
        Categoria categoria = CategoriaView.select(null);
        if (categoria == null) return;

        List<Produto> produtos = ProdutoCollectionRepository.findByCategoria(categoria);
        
        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não encontramos produtos para a categoria " + categoria.getNome());
        } else {
            produtos.forEach(ProdutoView::show);
        }
    }
}