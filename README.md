# Avaliação técnica back-end Sicredi
Gerência de sessões para votação de Pautas das Assembleias do Sicredi
<!--ts-->
## Índice
   * [Sobre](##Sobre)
   * [Instalação](#instalação)
   * [Especificações de gerenciamento](#especificações-de-gerenciamento)
      * [Pauta](#pauta)
      * [Sessão](#sessão)
      * [Associado](#associado)
      * [Voto](#voto)
   * [Tecnologias](#tecnologias)
   * [Recursos pendentes](#recursos-pendentes)
<!--te-->
# Sobre
- O sistema consiste em gerenciar sessões para votação de pautas internas no Sicredi, podendo gerar uma sessão com um tempo limite 
que deve ser especificado.

## Instalação
- Necessário JDK 11 e Maven
- Banco de dados
  - O projeto possui um banco de dados H2 em arquivo, com schema de tabelas e todas inicialmente populadas. Está configurado para manter os dados.    
  
## Especificações de gerenciamento
### Pauta
    - tema é obrigatório
    - temas de pautas são unicos
### Sessão
    - tempo da sessão é obrigatório
    - pauta deve estar registrada
    - pauta não pode possuir outra sessão
    - pauta deve estar com status "em aberto" para votos
    
### Associado  
    - cpf é obrigatório
    
### Voto 
    - sessão é obrigatório
    - sessão deve estar registrada
    - sessão deve estar com "statusAberto" true
    - associado é obrigatório
    - associado deve estar registrado
    - associado não pode votar mais de uma vez na sessão
  
 ## Tecnologias
  - Java 11
  - Spring Boot
  - H2 Database
  - OpenAPI/Swagger UI
  
## Recursos pendentes
  - Integração com api de verificação de CPF
  - Mensageria 
  - Colocar em um servidor web
  - melhorar performance do endpoint Votar para cenários com maior número de requisições 
