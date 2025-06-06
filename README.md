Projeto Gestor de Finanças

Realizado para disciplina de Laboratorio de Programação Orientada a Objetos ministrada professor Bruno 

Matheus Abilio da Silva Camargo(841929)

Gabriel Barbosa dos Santos(841923)

David Camara(841925)

Visão Geral

O Projeto Finanças é uma aplicação desktop desenvolvida em Java, utilizando a biblioteca
Swing para a construção da interface gráfica e o SQLite como sistema de banco de dados. Seu
objetivo é oferecer uma solução simples e funcional para o gerenciamento de finanças
pessoais, permitindo o controle de usuários, categorias de despesas e receitas, além do
registro de transações financeiras.

Arquitetura do Sistema

A aplicação está estruturada em camadas bem definidas, promovendo organização,
reutilização de código e separação de responsabilidades. As principais camadas são:

Estrutura (Modelos de Dados)

Controle (Lógica de Negócio)

DAO (Acesso a Dados)

Interface (Swing)

Armazenamento (Conexão com o Banco de Dados)

1. Estrutura (Modelos de Dados)
   
Esta camada contém as classes puras que representam as entidades do sistema. Essas classes
possuem apenas atributos privados, com seus respectivos getters e setters, mantendo o
modelo de dados desacoplado da lógica de negócio.

Estrutura_Usuario

Atributos:

• int ID_Usuario
• String Nome
• String Login
• String Senha
• double Historico_Valor_Total
Responsabilidades:
• Armazenar dados do usuário.
• Não contém lógica de validação ou regras de negócio.

Estrutura_Categoria

Atributos:

• int ID_Categoria
• String Nome_Categoria
• double Valor_Total_Despesa
• double Valor_Total_Receita
• String Data_Criacao_Categoria
• int ID_Usuario

Responsabilidades:

• Representar as categorias associadas a cada usuário.

Estrutura_Transacao

Atributos:

• int ID_Transacao
• String Descricao
• double Valor
• String Data
• String Tipo (valores: 'Receita' ou 'Despeza')
• int ID_Usuario
• int ID_Categoria

Responsabilidades:

• Representar as transações financeiras dos usuários.

2. Controle (Lógica de Negócio)
   
As classes de controle contêm regras de negócio, validações e a intermediação entre a
interface do usuário e o banco de dados (DAO).

Controle_Usuario

Métodos Principais:

• autenticarUsuario(String login, String senha): valida credenciais.
• verificarExistenciaUsuario(String login): verifica se o login já está em uso.
• cadastrarUsuario(Estrutura_Usuario usuario): registra um novo usuário.
• atualizarHistorico(int idUsuario, String tipo, double valor): atualiza o histórico total do
usuário com base no tipo da transação.

Controle_Categoria

Métodos Principais:

• listarCategoriasPorUsuario(int idUsuario): retorna categorias do usuário.
• alterarCategoria(Estrutura_Categoria categoria): edita categoria.
• excluirCategoria(int idCategoria): remove categoria (respeitando restrições).
• atualizarTotaisCategoria(int idCategoria): recalcula totais de receita e despesa.

Controle_Transacao

Métodos Principais:

• listarTransacoesPorUsuario(int idUsuario): retorna todas as transações do usuário.
• inserirTransacao(Estrutura_Transacao transacao): adiciona nova transação.
• alterarTransacao(Estrutura_Transacao transacao): modifica uma transação.
• excluirTransacao(int idTransacao): remove transação existente.
• atualizarValoresRelacionados(int idUsuario): atualiza totais em categorias e histórico
do usuário.

3. DAO (Data Access Object)
Responsáveis pela comunicação com o banco de dados, os DAOs executam comandos SQL e
convertem os dados entre o banco e os objetos da camada de estrutura.
Cada DAO realiza as operações CRUD (Create, Read, Update, Delete) de forma isolada e
reutilizável.

4. Interface Gráfica (Swing)
A interface do sistema é implementada com Swing, permitindo uma navegação intuitiva e
direta. A interface mantém o contexto do usuário logado através do parâmetro ID_Usuario,
garantindo que todas as operações sejam realizadas de forma personalizada.
Os dados são carregados em tabelas Swing, utilizando vetores ou listas das estruturas para
melhorar o desempenho e facilitar o uso local das informações.

5. Armazenamento (Conexão com SQLite)
A classe Conexao_SQLite gerencia a conexão com o banco de dados, permitindo sua abertura e
fechamento de forma segura e eficiente. É utilizada por todas as classes DAO para executar
comandos SQL.

RESUMO:

![{C407A459-D8A8-40D0-8298-D50877E37F8F}](https://github.com/user-attachments/assets/f2c94c17-1422-4239-a4d9-3e2a5c0a82c9)
