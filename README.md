# CloudTester

Biblioteca de teste e comparação entre APIs de acesso a nuvem.

## APIs disponíveis
- [jclouds](https://jclouds.apache.org/) 1.9.2
- [Azure Storage](https://azure.microsoft.com/pt-br/develop/java/) 4.2.0
- [Amazon AWS](https://aws.amazon.com/pt/sdk-for-java/) 1.11.3

## Plataformas suportadas
- [Microsoft Azure](https://azure.microsoft.com/pt-br/)
- [Amazon Web Service (AWS)](http://aws.amazon.com/)
 
## Funções implementadas
- Armazenamento de arquivos (Blob)
  - Download
  - Upload
  - Listagem (JClouds apresentou problema [JCLOUDS-548](https://issues.apache.org/jira/browse/JCLOUDS-548))
  - Remoção
- Fila de Mensagens (SQS) *(JClouds ainda não implementa Azure)*
  - Fila
    - Criar
    - Recuperar
    - Listar
    - Limpar (JClouds não implementa)
    - Apagar (JClouds apresentou problema [JCLOUDS-1116](https://issues.apache.org/jira/browse/JCLOUDS-1116))
  - Mensagem
    - Enviar
    - Recuperar
    - Inspecionar
    - Listar
    - Apagar

## Instruções de uso
1. Crie um arquivo chamado `credentials.properties` no diretório `src/main/resources/` para armazenar os atributos de autenticação.

 Exemplo:
 
 ```properties
 ### AZURE
 IDENTITY_AZURE = cloudtester #[nome do Serviço Blob]
 CREDENTIAL_AZURE = T8pUMVXjE+QRC8c5tdwn/ffpsdsPOUlJBjLg98Jyl2qOxWmBxThewERoDENBLgYahjkX9fYlfzLywSO/aps== #[ID da Assinatura do Serviço Blob]
 CONTAINER_NAME_AZURE = cloudtester #[nome do container criado]
 ### AMAZON
 IDENTITY_AWS = ETOEJY4S8VB2AUG5L84B #[Access Key ID criada na seção de Security Credentials]
 CREDENTIAL_AWS = 8+NeuyABHDop7WuIjP1Xs6+7mRZOhauIJgom2vWz #[Secret Access Key criada na seção de Security Credentials]
 CONTAINER_NAME_AWS = cloudtester-utfpr #[nome do Bucket criado no S3]
 REGION_AWS = sa-east-1 #[especifica a região a trabalhar, requerido para SQS]
 ```
 *Obs: Substitua os valores de exemplo pelos valores de suas credenciais. Não compartilhe suas credenciais com terceiros.*<br>
 *Obs²: Azure não precisa especificar região, pois a mesma já está amarrada ao Container.*
2. Para testar Download e Upload é preciso criar os 10 arquivos a serem enviados na raiz do projeto. O conteúdo do arquivo fica a cargo do usuário.

 Exemplo:
 
 ```
 file0.txt
 file1.txt
 ...
 file9.txt
 ```

3. Execute os testes de unidade
