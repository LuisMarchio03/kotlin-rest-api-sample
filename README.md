# kotlin-rest-api-sample

# Aplicativo Semelhante ao Trello - Documentação

## Requisitos Funcionais

1. **Autenticação e Autorização:**
    - RF1: Os usuários devem se registrar e autenticar no sistema.
    - RF2: Apenas usuários autenticados devem ter permissão para criar quadros, listas, cartões e comentários.
    - RF3: Os usuários devem poder definir permissões de acesso em quadros específicos.

2. **Gerenciamento de Quadros:**
    - RF4: Os usuários devem poder criar, visualizar, editar e excluir quadros.
    - RF5: Cada quadro deve ter um nome e uma descrição.

3. **Gerenciamento de Listas e Cartões:**
    - RF6: Os usuários devem poder criar, visualizar, editar e excluir listas em um quadro.
    - RF7: Os usuários devem poder criar, visualizar, editar e excluir cartões em uma lista.
    - RF8: Os cartões devem ter um título, descrição e podem ser movidos entre listas.

4. **Comentários:**
    - RF9: Os usuários devem poder adicionar comentários a cartões.
    - RF10: Os usuários devem poder editar e excluir seus próprios comentários.

5. **Notificações e Atividades:**
    - RF11: Os usuários devem receber notificações sobre atividades relevantes.
    - RF12: Uma área de atividades recentes deve ser exibida.

## Requisitos Não Funcionais

1. **Desempenho:**
    - RNF1: O sistema deve fornecer tempos de resposta rápidos, mesmo em grandes quantidades de dados.
    - RNF2: As consultas de banco de dados devem ser otimizadas para eficiência.

2. **Segurança:**
    - RNF3: Os dados do usuário devem ser armazenados de forma segura.
    - RNF4: A autenticação deve ser segura, preferencialmente usando OAuth 2.0.

3. **Escalabilidade:**
    - RNF5: O sistema deve ser escalável para lidar com um aumento no número de usuários e dados.

4. **Disponibilidade:**
    - RNF6: O sistema deve ter uma alta disponibilidade para minimizar períodos de inatividade.

5. **Compatibilidade e Responsividade:**
    - RNF7: A aplicação web deve ser compatível com vários navegadores.
    - RNF8: A interface do usuário deve ser responsiva.

6. **Backup e Recuperação:**
    - RNF9: Deve haver um sistema de backup regular para evitar a perda de dados críticos.

7. **Conformidade com Padrões:**
    - RNF10: O aplicativo deve seguir as melhores práticas de desenvolvimento e conformar-se aos padrões de segurança da indústria.

## Regras de Negócio

1. **Cadastro de Usuários:**
    - RN1: Cada usuário deve ter um endereço de e-mail único.
    - RN2: A senha do usuário deve atender a requisitos mínimos de segurança.

2. **Quadros:**
    - RN3: Um usuário pode criar vários quadros.
    - RN4: Apenas o criador de um quadro pode editá-lo ou excluí-lo, a menos que permissões adicionais sejam concedidas.

3. **Listas e Cartões:**
    - RN5: Cada lista deve pertencer a um único quadro.
    - RN6: Os cartões devem pertencer a uma única lista.
    - RN7: A ordem das listas e dos cartões deve ser personalizável pelo usuário.

4. **Comentários:**
    - RN8: Um usuário só pode editar ou excluir seus próprios comentários.
    - RN9: Os comentários devem ser associados a um cartão específico.

5. **Colaboração e Permissões:**
    - RN10: Um usuário pode convidar outros para colaborar em seus quadros.
    - RN11: O criador do quadro pode definir permissões para os colaboradores.

6. **Notificações:**
    - RN12: Os usuários devem receber notificações apenas para atividades relevantes aos quadros em que estão envolvidos.

7. **Segurança e Privacidade:**
    - RN13: As informações do usuário devem ser armazenadas de forma segura e privada.
    - RN14: A troca de dados entre o cliente e o servidor deve ser criptografada.

8. **Exclusão de Dados:**
    - RN15: A exclusão de um usuário deve ser cuidadosamente tratada para evitar a perda de dados críticos.

9. **Atividades Recentes:**
    - RN16: A seção de atividades recentes deve ser atualizada em tempo real para refletir as alterações nos quadros.

10. **Limites do Sistema:**
    - RN17: Estabelecer limites para o número de quadros, listas, cartões e colaboradores que um usuário pode ter, dependendo do plano de assinatura.


# Modelagem do Banco de Dados

## Entidades

1. **Usuario (User):**
    - `id` (PK): Identificador único do usuário.
    - `nome`: Nome do usuário.
    - `email`: E-mail do usuário.

2. **Quadro (Board):**
    - `id` (PK): Identificador único do quadro.
    - `nome`: Nome do quadro.
    - `descricao`: Descrição do quadro.
    - `id_usuario` (FK): Chave estrangeira referenciando o usuário que criou o quadro.

3. **Lista (List):**
    - `id` (PK): Identificador único da lista.
    - `nome`: Nome da lista.
    - `posicao`: Posição da lista no quadro.
    - `id_quadro` (FK): Chave estrangeira referenciando o quadro ao qual a lista pertence.

4. **Cartao (Card):**
    - `id` (PK): Identificador único do cartão.
    - `titulo`: Título do cartão.
    - `descricao`: Descrição do cartão.
    - `posicao`: Posição do cartão na lista.
    - `id_lista` (FK): Chave estrangeira referenciando a lista à qual o cartão pertence.
    - `id_usuario` (FK): Chave estrangeira referenciando o usuário responsável pelo cartão.

5. **Comentario (Comment):**
    - `id` (PK): Identificador único do comentário.
    - `conteudo`: Conteúdo do comentário.
    - `id_cartao` (FK): Chave estrangeira referenciando o cartão ao qual o comentário pertence.
    - `id_usuario` (FK): Chave estrangeira referenciando o usuário que fez o comentário.

## Relacionamentos

- Um usuário pode criar vários quadros (relação um-para-muitos).
- Um quadro pode ter várias listas (relação um-para-muitos).
- Uma lista pode ter vários cartões (relação um-para-muitos).
- Um cartão pode ter vários comentários (relação um-para-muitos).
- Cada entidade que se refere a um usuário (quadro, cartão, comentário) possui uma chave estrangeira para o usuário correspondente.

