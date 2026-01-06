SISTEMA BANCÁRIO ------------------

(v 1.0)
O sistema foi desenvolvido bom base em POO, SPRING BOOT E SPRING DATA JPA utilizando Banco de Dados PostGreSQL.
Sistema construido pensado para o desenvolvimento back - end utilizando meus conhecimetos em programção orientada a objetos (instanciar metódos e variáveis, utlizar polimorfismo e interfaces ajustaveis).
Utilizando a API do ViaCep WebService : https://viacep.com.br/ .Foi feita integração do endereco a partir do HTTP request e reponse, para modelar nossa reposta foi utilizada uma classe Record chamada Endereco.
Apos a modelagem o endereço é automaticamente atribuído a conta que esta sendo criada no sistema interno do banco, e salvo no banco de dados (POSTGRESQL 18).

(v 1.1)
Utilizando SpringData foi adicionado ao projeto os devidos tratamentos em relação as nossas tabelas, sendo elas, Dados_Conta onde está o coração do sistema, reponsável pela criação de contas, e operções como sque e depósito.
Dentro dos metódos de depósito e saque foram implementados um novo metódo para adicionar um determinado tipo de transação no histórico, ou seja, sempre que uma operação for feita ela será adicionada ao histórico com tipo, valor e tempo real.
As transações assim como a linha de crédito estão conectadas ao nosso banco de dados.

(v 1.2)
O projeto foi integrado a uma API REST, utilizando comandos de CRUD para realizar alterações na renda, sacar ou depositar um valor x a uma conta pelo id, CRUDS de get foram usados para exibir uma lista de endereços e transações relacionadas a cada conta, o projeto foi testado manualmente no POSTMAN, em uma futura atualização será feito testes unitários.

OBSERVAÇÕES ----------------------

Projeto ainda sofrera melhorias e novas implementações assim que meus conhecimentos em JAVA evoluirem, poderia usar JPQL para metódos mais avançados, até o momento deixarei como está.
