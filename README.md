

#  Sistema de Gerenciamento de Produtos - Nano

Este projeto é uma aplicação Desktop em Java desenvolvida para atender à demanda de uma microempresa de comércio eletrônico. O sistema permite o gerenciamento completo (cadastro, consulta e alteração) de produtos e suas respectivas categorias.

## Funcionalidades

O sistema conta com um menu interativo gráfico que oferece as seguintes operações:

*   **Cadastrar Categoria:** Cria novas categorias para organizar os produtos.
*   **Cadastrar Produto:** Registra produtos vinculando-os a uma categoria existente, com validação de dados (preço, nome, descrição).
*   **Alterar Produto:** Permite editar os dados de um produto já cadastrado.
*   **Consultar por ID:** Busca rápida de um produto específico pelo seu identificador único.
*   **Consultar por Categoria:** Lista todos os produtos pertencentes a uma determinada categoria.
*   **Persistência em Memória:** Utiliza o padrão *Repository* com `ArrayList` para armazenar os dados durante a execução.

##  Tecnologias Utilizadas

*   **Linguagem:** Java (JDK 17+)
*   **Interface Gráfica (GUI):** Java Swing (`JOptionPane`)
*   **Arquitetura:** MVC (Model-View-Controller)
*   **IDE:** Visual Studio Code

##  Estrutura do Projeto (Arquitetura MVC)

O projeto foi organizado seguindo boas práticas de separação de responsabilidades:

```
src/br/com/fiap/nano/produtos/
│
├──  App.java                  # Classe Principal (Controller/Entry Point)
│
├──  model/                    # Modelos de Dados (Entidades)
│   ├── Categoria.java
│   └── Produto.java
│
├──  repository/               # Acesso a Dados (Simulação de Banco de Dados)
│   ├── CategoriaCollectionRepository.java
│   └── ProdutoCollectionRepository.java
│
└── view/                     # Camada de Visualização (Telas)
    ├── CategoriaView.java
    ├── Opcao.java              # Enum para controle do Menu
    ├── OpcaoView.java
    └── ProdutoView.java




Aprendizado

Este projeto foi desenvolvido com foco no aprendizado de conceitos fundamentais da linguagem Java e Orientação a Objetos:
Encapsulamento: Uso de modificadores de acesso (private) e métodos Getters/Setters.
Polimorfismo e Sobrescrita: Uso de @Override nos métodos toString, equals e hashCode.
Coleções: Manipulação de List e ArrayList.
Streams API: Uso de filtros e buscas otimizadas nas listas.
Tratamento de Exceções: Blocos try-catch para evitar erros de entrada do usuário.
Organização das opções do menu.

Desenvolvido por Ronaldo Soares Pinto durante estudos de Java e Orientação a Objetos.
>>>>>>> 0a7375d (Projeto criado para o curso Java da FIAP, Sistema de cadastro de produtos)
