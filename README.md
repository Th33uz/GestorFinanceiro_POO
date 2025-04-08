**0.Informações Gerais**

O Projeto foi desenvolvido por:

- David Camara - 841925
- Gabriel Barbosa dos Santos - 841923
- Matheus Abílio da Silva Camargo - 841929 

O video onde o projeto é descrito pode ser encontrado [aqui.](https://youtu.be/gVkKKjRpjh8)

**1. Estrutura do projeto**

Tentamos usar um padrão MVC em nosso projeto, com as devidas adaptações é claro.

-	**Model (Estrutura\_ classes, Armazenamento\_Memoria):**
-	`Estrutura_Usuario`, `Estrutura_Categoria`, `Estrutura_Transacao` definem quais dados são salvos (informações de login, detalhes das transações e categorias).
-	`Armazenamento_Memoria` é usado para armazenar as listas dos usuários durante a execução da aplicação.
-	**View (Interface\_ classes):** As interfaces da aplicação, todas `JFrame`.
-	**Controller (Controle\_ classes):** Serve para conectar as estruturas para a interface, nos permitiu desenvolver de forma simultânea sem criar grandes problemas de comunicação.

**2. Estruturas de Dados**

-	**Estrutura_Usuario:**

-	É um único usuário.
-	Que tem: id (int), nome (String), login (String), senha (String).
-	Também tem uma `List<Estrutura_Transacao>` e uma `List<Estrutura_Categoria>`. Dessa forma cada usuário tem suas próprias transações e categorias.
-	Tem get e set para os atributos e métodos `adicionarTransacao` e `adicionarCategoria` para adicionar itens às listas da classe.
 
-	**Estrutura_Categoria:**

-	É apenas um nome dado a transações para fácil filtragem.
-	Tem: id (int), nome (String), dataCriacao (LocalDate).
-	Get e set.
 
-	**Estrutura_Transacao:**

-	É uma despesa ou Receita.
-	Tem: descricao (String), valor (double), data (LocalDate), tipo (String – Apenas aceita "Receita" ou "Despesa"), idCategoria (int - que conecta a uma `Estrutura_Categoria`).
-	Get e set.

**3. Armazenamento na memoria**

-	**Armazenamento_Memoria:**

-	Tem a `List<Estrutura_Usuario>` que é a `ListaUsuarios`.
-	Tem métodos de `adicionarUsuario` e `getListaUsuarios`.
-	Seguindo as rubricas não utilizamos banco de dados, portanto após o programa ser fechado a `ListaUsuarios` é apagada.

**4. Controles**

-	**Controle_Usuario:**

-	Cria o usuário e o login.
-	`adicionarUsuario`: adiciona `Estrutura_Usuario` à lista de usuários.
-	`validarLogin`: Procura pela lista de usuários para achar um login existente. Retorna o objeto se achar ou `NULL`.
-	`getUsuarios`: Retorna a lista de usuários e é usada para gerar os IDs na `Interface_Cadastro`.
-	**Controle_Categoria:**

-	Gerencia operações de categoria para um usuário específico. Os métodos exigem um objeto `Estrutura_Usuario` como entrada.
-	`adicionarCategoria`: Cria uma nova `Estrutura_Categoria` e a adiciona à lista `categorias` dentro do objeto `Estrutura_Usuario` passado.
-	`editarCategoria`: Encontra uma categoria pelo ID na lista do usuário e atualiza seu nome.
-	`excluirCategoria`: Encontra uma categoria pelo ID na lista do usuário e a remove.
-	**Controle_Transacao:**

-	Gerencia operações de transação para um usuário específico. Os métodos exigem um objeto `Estrutura_Usuario` como entrada.
- `adicionarTransacao`, `editarTransacao`, `excluirTransacao`: São similares ao métodos de categoria, porem utiliza descrição em vez de ID.

**5. Interface do Usuário (Pacote Interface_)**

-	**Estrutura Geral:**
-   Cada classe de interface (`Interface_Login`, `Interface_Cadastro`, `Interface_Principal`, `Interface_Categoria`, `Interface_Transacao`, `Interface_Historico`, `Interface_Resumo`) estende `javax.swing.JFrame`, criando uma janela separada para cada funcionalidade principal.
-   A navegação entre as janelas é gerenciada pela criação de novas instâncias de `JFrame` e pelo fechamento (`dispose()`) da janela anterior.
-   O `Controle_Usuario` é passado entre as interfaces para manter o estado da aplicação (lista de usuários) e o objeto `Estrutura_Usuario` do usuário logado é passado para as interfaces que precisam de seus dados específicos (categorias, transações).
-   `JOptionPane` é utilizado extensivamente para exibir mensagens de feedback (sucesso, erro) e para coletar dados simples (como nome de categoria ou confirmações).

Fluxo Principal e Interfaces Específicas:

-   **Main**: Ponto de entrada da aplicação. Instancia `Controle_Usuario` e abre a `Interface_Login`.
-   **Interface_Login**:
-   Apresenta campos para "Login" (`JTextField`) e "Senha" (`JPasswordField`).
-   Possui botões "Entrar" e "Cadastrar".
-   O botão "Entrar" chama `controleUsuario.validarLogin`. Se o login for válido, exibe uma mensagem de boas-vindas e abre a `Interface_Principal`, passando o objeto `Estrutura_Usuario` correspondente. Caso contrário, exibe uma mensagem de erro (`JOptionPane`).
-   O botão "Cadastrar" abre a `Interface_Cadastro`.
-   **Interface_Cadastro**:
-   Permite o registro de novos usuários com campos para "Nome", "Login" (`JTextField`) e "Senha" (`JPasswordField`).
-   Possui botões "Salvar" e "Voltar".
-   O botão "Salvar" valida se todos os campos foram preenchidos. Se sim, cria uma nova `Estrutura_Usuario` (gerando um ID sequencial baseado no tamanho da lista de usuários atual) e a adiciona usando `controleUsuario.adicionarUsuario`. Exibe uma mensagem de sucesso e retorna para a `Interface_Login`.
-   O botão "Voltar" retorna para a `Interface_Login` sem salvar.
-   **Interface_Principal**:
-   Tela principal após o login bem-sucedido. Exibe uma mensagem de boas-vindas com o nome do usuário.
-   Contém botões para navegar para as diferentes seções: "Categorias" (`Interface_Categoria`), "Transações" (`Interface_Transacao`), "Histórico" (`Interface_Historico`), "Resumo" (`Interface_Resumo`).
-   Possui um botão "Voltar" que retorna para a `Interface_Login`.
-   **Interface_Categoria**:
-   Gerencia as categorias do usuário logado.
-   Exibe as categorias existentes em uma `JTable` (colunas: ID, Nome, Data de Criação).
-   Botões:
-   "Adicionar": Usa `JOptionPane.showInputDialog` para obter o nome da nova categoria e chama `controleCategoria.adicionarCategoria`.
-   "Editar": Requer a seleção de uma linha na tabela. Usa `JOptionPane.showInputDialog` para obter o novo nome e chama `controleCategoria.editarCategoria`.
-   "Excluir": Requer a seleção de uma linha na tabela e chama `controleCategoria.excluirCategoria`.
-   "Voltar": Retorna para a `Interface_Principal`.
-   A tabela é atualizada (`atualizarTabela()`) após cada operação de adição, edição ou exclusão.
-   **Interface_Transacao**:
-   Gerencia as transações financeiras (receitas e despesas) do usuário logado.
-   Exibe as transações em uma `JTable` (colunas: Descrição, Valor (R$), Data, Tipo, Categoria). O nome da categoria é obtido buscando na lista de categorias do usuário pelo `idCategoria` da transação.
-   Botões:
-   "Adicionar": Abre um `JOptionPane.showConfirmDialog` com múltiplos campos (`JTextField` para Descrição, Valor, Data; `JComboBox` para Tipo e Categoria) para coletar os dados da nova transação. Chama `controleTransacao.adicionarTransacao`. Inclui tratamento básico de exceções para parsing de dados.
-   "Editar": Requer a seleção de uma linha. Abre um diálogo similar ao de adicionar, pré-preenchido com os dados da transação selecionada. Chama `controleTransacao.editarTransacao`.
-   "Excluir": Requer a seleção de uma linha e chama `controleTransacao.excluirTransacao` usando a descrição como identificador.
-   "Voltar": Retorna para a `Interface_Principal`.
-   A tabela é atualizada (`atualizarTabela()`) após cada operação.
-   **Interface_Historico**:
-   Apresenta um resumo financeiro agregado por categoria.
-   Exibe em uma `JTable` (colunas: Categoria, Receitas (QTD), Despesas (QTD), Total Receita (R$), Total Despesa (R$)). Os dados são calculados iterando sobre as categorias do usuário e usando streams (`.stream().filter().mapToDouble().sum()` e `.stream().filter().count()`) nas transações do usuário para obter os totais e contagens por categoria e tipo.
-   Exibe o "Saldo TOTAL" (`JLabel`) calculado como a soma (Total Receita - Total Despesa) de todas as categorias.
-   Possui um botão "Voltar" para retornar à `Interface_Principal`.
-   A tabela e o saldo são atualizados na inicialização (`atualizarTabela()`).
-   **Interface_Resumo**:
-   Exibe uma lista detalhada de transações com opções de filtragem.
-   A `JTable` é a mesma da `Interface_Transacao`.
-   Controles de Filtro: `JComboBox` para Categoria ("Todas" + nomes das categorias do usuário), `JComboBox` para Tipo ("Todos", "Receita", "Despesa"), `JTextField` para Data Início e Data Fim (formato dd/MM/yyyy).
-   Botão "Filtrar": Aplica os filtros selecionados à lista de transações do usuário usando streams (`.stream().filter(...)`) e atualiza a tabela (`atualizarTabela()`) com os resultados. Inclui tratamento para datas vazias e parsing das datas.
-   Exibe totais (`JLabel`) de despesas e receitas *apenas para as transações exibidas na tabela* (atualizados a cada chamada de `atualizarTabela`).
-   Possui um botão "Voltar" para retornar à `Interface_Principal`.
