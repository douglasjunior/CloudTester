# CloudTester

Biblioteca de teste e comparação entre APIs de acesso a nuvem.

## APIs disponíveis
- [jclouds](https://jclouds.apache.org/) 

## Plataformas suportadas
- [Microsoft Azure](https://azure.microsoft.com/en-us/)
- [Amazon Web Service (AWS)](http://aws.amazon.com/)
 
## Funções implementadas
- Armazenamento de arquivos (Blob)
 - Download
 - Upload
 - Listagem

## Instruções de uso
1. Crie um arquivo chamado `credentials.properties` na raiz do projeto para armazenar os atributos de autenticação.

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
 ```
 
2. Para testar Download e Upload é preciso criar os 10 arquivos a serem enviados, também na raiz do projeto.

 Exemplo:
 
 ```
 file0.txt
 file1.txt
 ...
 file9.txt
 ```

3. Execute os testes de unidade
