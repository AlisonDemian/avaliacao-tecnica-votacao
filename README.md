# Avaliação técnica back-end Sicredi
API para gerenciamento de votações de pautas das assembleias do banco Sicredi
<!--ts-->
## Índice
   * [Sobre](#sobre)
   * [Instalação](#instalação)
   * [Especificações de gerenciamento](#especificações-de-gerenciamento)
      * [Pauta](#pauta)
      * [Sessão](#sessão)
      * [Associado](#associado)
      * [Voto](#voto)
   * [Tecnologias](#tecnologias)
   * [Recursos pendentes](#recursos-pendentes)
<!--te-->
## Sobre
- O sistema consiste em gerenciar sessões para votação de pautas internas no Sicredi, podendo gerar uma sessão com um tempo limite 
que deve ser especificado.

## Instalação
- Necessário JDK 11 e Maven
- Banco de dados
  - O projeto possui um banco de dados H2 em arquivo, com schema de tabelas e todas inicialmente populadas. Está configurado para manter os dados.  
  - Configuração no arquivo application.yml para desfazer dados e recriar tabelas ao reiniciar aplicação: 
  ~~~yml 
  "ddl-auto: create-drop"
  ~~~
## Especificações de gerenciamento
### Link Swagger UI 
   - http://localhost:8080/swagger-ui/index.html
### Pauta
   - tema é obrigatório
   - temas de pautas são unicos
### Sessão
   - tempo da sessão é obrigatório
   - pauta deve estar registrada
   - pauta não pode possuir outra sessão
   - pauta deve estar com status "em aberto" para votos
    
### Associado  
   - CPF será validado (ao criar, atualizar e votar)
    
### Voto 
   - sessão é obrigatório
   - sessão deve estar registrada
   - sessão deve estar com "statusAberto" = true
   - associado é obrigatório
   - associado deve estar registrado
   - associado não pode votar mais de uma vez na sessão
### Observação
**Para maior segurança dos dados, a modificação manual do status de uma pauta só é possível gerenciando diretamente o banco de dados**
   - Link para acesso ao console do banco: http://localhost:8080/h2-console/ <br />
   - Query para modificar status de uma pauta: 
  ~~~sql
  UPDATE pauta SET status_votacao = 'status' WHERE id = id
  ~~~
   **Padrões de status:**
   - "em aberto" 
   - "aprovada" 
   - "reprovada" 
   - "empate"
## Tecnologias
  - Java 11
  - Spring Boot
  - H2 Database
  - OpenAPI/Swagger UI
  
## Recursos pendentes
  - Introduzir Kafka no projeto
  - Implementar paginação no endpoint de listagem de pautas
