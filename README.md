# Sistema de Gestão de Vendas - README

Este repositório contém a implementação de um Sistema de Gestão de Vendas desenvolvido em Java, atendendo os requisitos descritos. O sistema permite o cadastro de produtos, clientes e vendas, vinculação de produtos a vendas, controle de estoque, efetivação e estorno de vendas, consulta detalhada ou consolidada das vendas.

## Requisitos

- JDK: Versão 8 ou superior
- Interface: Swing
- Banco de dados: PostgreSQL
- Frameworks: Nenhum (Java puro)
- Versionamento

## Funcionalidades Implementadas

1. **Cadastro de Produto**
   - Atributos: id, descrição, preço, quantidade.

2. **Cadastro de Cliente**
   - Atributos: id, nome.

3. **Cadastro de Venda**
   - Atributos: id, data, cliente, valor_total, status.

4. **Vinculação de Produtos a Vendas**
   - Uma venda pode conter múltiplos produtos.
   - Um produto pode estar em várias vendas.

5. **Itens referentes à Venda**
   - Atributos: id_produto, quantidade, preço, valor_total.
   - O valor total é calculado como preço * quantidade.

6. **Atualização de Quantidade e Valor Total na Venda**
   - Para inserir um produto, digite o nome e aperte enter.
   - Não é possível inserir o mesmo produto mais de uma vez na mesma venda.
   - Se o mesmo produto for adicionado novamente, apenas atualiza quantidade e valor total.

7. **Efetivação da Venda**
   - Ao finalizar o cadastro da venda, o usuário pode efetivá-la.
   - Atualiza a quantidade do produto no estoque e o status da venda para efetivada.

8. **Estorno de Venda Efetivada**
   - Permite estornar uma venda efetivada.
   - A quantidade do produto volta ao estoque e o status da venda é alterado para digitando.

9. **Consulta de Vendas**
   - Permite consultar dados das vendas.

10. **Filtragem e Exibição Detalhada ou Consolidada**
   - O usuário pode filtrar as vendas por data.
   - Pode visualizar as vendas de forma consolidada ou detalhada.

## Estrutura do Projeto

O projeto está estruturado de forma organizada, com separação de responsabilidades e aplicação de boas práticas de programação. As funcionalidades estão implementadas conforme os requisitos, utilizando coleções Java, relacionamentos entre produto, venda e cliente, tratamento adequado de exceções, e testes unitários com jUnit e Mockito.

## Inicialização da Aplicação

- Certifique-se de ter o PostgreSQL instalado e configurado corretamente.
- Importe o projeto no ambiente de desenvolvimento.
- Execute a aplicação a partir da classe principal.

## Desenvolvimento

A aplicação foi desenvolvida em Java puro (sem frameworks). Utilizou-se a versão 20 do JDK e a interface gráfica foi construída utilizando o pacote Swing.

## Persistência de Dados

Os dados são persistidos em um banco de dados PostgreSQL. A conexão é realizada através de JDBC e as queries são feitas utilizando SQL.

## Testes

Foram implementados testes unitários para as classes de serviço, atingindo uma cobertura de testes de 80%.


