**0. Membros do grupo**

O Projeto foi desenvolvido por:

- David Camara - 841925
- Gabriel Barbosa dos Santos - 841923
- Matheus Abilio - 000000

**1. Estrutura do projeto**

Tentamos usar um padrão MVC em nosso projeto, com as devidas adaptações é claro.

-	**Model (Estrutura\_ classes, Armazenamento\_Memoria):**
	o	`Estrutura_Usuario`, `Estrutura_Categoria`, `Estrutura_Transacao` definem quais dados são salvos (informações de login, detalhes das transações e categorias).
	o	`Armazenamento_Memoria` é usado para armazenar as listas dos usuários durante a execução da aplicação.
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

- Cada classe extends uma `JFrame`.
-	Vou terminar após a prova.
