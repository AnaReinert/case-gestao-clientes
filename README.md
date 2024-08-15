Esse é um projeto desenvolvido inteiramente em Java para resolução do Case de Gestão de Clientes da Inova Talentos e Senior.

## Frameworks e Tecnologias Utilizadas

- Eclipse IDE for Enterprise Java and Web Developers (Estou usando a [Versão 2024-06](https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2024-06/R/eclipse-inst-jre-win64.exe))
- [PostgreSQL 16](https://www.postgresql.org/download)
- WildFly 25.0.0.Final ([Jakarta EE 8 fULL & Web Distribuction](https://www.wildfly.org/downloads/))
- JSF(Mojarra) 2.2
- [Primefaces 11](https://primefaces.github.io/primefaces/11_0_0/#/)
- [JPA 2.2](https://www.oracle.com/java/technologies/persistence-jsp.html)
- [Hibernate](https://hibernate.org/)
- [Maven](https://maven.apache.org/)

## Passo a passo para Executar o Projeto

- Clone o projeto e coloque dentro da pasta da workspace do eclipse (Caminho da workspace do eclipse fica especificado ao abrir o eclipse, na escolha da workspace). 
- Importe no o projeto no eclipse, através do File > Import... > Existing Projects into Workspace
- (Opcional) Para melhor visualização do projeto, eu prefiro utilizar o Package Explorer no lugar do Project Explorer
  - Window > Show View > Other... > Java > Package Explorer > Open
  - Nos três pontos da direita superior, escolha a opção Package Presentation > Hierarchical. Ela ajudará na visualização hierarquica do projeto
- As vezes pode ser necessário dar um clean no projeto, principalmente se as classes não tiverem encontrando os imports informados. Para isso, em Project > Clean > Clean

### Criação e Execução do Banco de Dados

- Para que a persistencia ocorra sem erros. Crie uma database com o nome gestaoclientes no seu **PgAdmin**
- No documento src > main > java > META-INF > persistence.xml
    - edite a property `javax.persistence.jdbc.user` e `javax.persistence.jdbc.password` com as credenciais de acesso do seu PgAdmin.
      ->  Usuário e senha atuais estão como potgres
    - Depois de subir o servidor pela primeira vez, comente a property `hibernate.hbm2ddl.auto`. Isso evita que ela sobrescreva a datatabe todas as vezes que o servidor subir

### Criação e Execução do Servidor
- Para criar um servidor, acesse a aba de servers e escolha a opção `Red Had JBoss Middleware > JBoss AS, WildFly & EAP Server Tools`.
- Espere o Download das dependencias necessárias do eclipse.
- Aceite os termos e clique em Finish. Eclipse vai installar as ferramentas necessárias para utilização do WildFly.
- Na dialog de Trust Authorities que aparecer durante o Download, dê um Select All e Trust Selected.
- Faça a mesma coisa no Trust Artifacts.
- Com o fim da instalação, o eclipse pedirá para reiniciar. Clique em Restart Now.
- Na dialog JBoss Tools Usage - Escolha a opção que preferir.
- Novamente no Eclipse, clique com o botão direito na aba servers > New > Server
- Escolha a opção do WildFly 24+ > Next
- Mantenha no Local e FileSystem and Shell Operations > Next
- No Home Directory, informe o local de instalação do Wildfly baixado.
    - >Certifique-se de que o diretório informado chega até a pasta standalone. Utilizando o Extrair tudo do Windows, ele joga o item extraido em uma pasta com o nome do zip
- No Runtime JRE opte pelo Execution Environment com **JavaSE 17**
- Se o wildfly não encontrar a pasta standalone no server base directory, vá até wildfly-25.0.0.Final > standalone
- Certifique-se de que o Configuration file seja o standalone.xml da pasta standalone e de um Finish
- Com o botão direito em cima do servidor criado, clique em Add and Remove... e adicione o nosso projeto.
- No diretório do WildFly, vá até standalone > deployments e cole o jar disponível na raíz do projeto

### Projeto já está pronto para executar
Acesse-o pela url **localhost:8080/gestao-clientes**

  


