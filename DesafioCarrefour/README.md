# DesafioCarrefour
O desafio consiste na implementação de uma aplicação Androidque deverá consumir a [API pública do Github](https://developer.github.com/v3/), que disponibiliza informações sobre os usuários e seus repositórios, onde o aplicativo deverá permitir a listagem de usuários, busca de usuário por nome de usuário e visualização das informações de um usuário específico, bem como a listagem de seus repositórios.

# Autenticação
A Api do Github possui um limite de requisições para request não autenticados,
acesse a [doc](https://docs.github.com/en/rest/guides/getting-started-with-the-rest-api?apiVersion=2022-11-28#authenticating)
para gerar sua própria chave e altere o valor API_KEY="YOUR_KEY" no arquivo  DesafioCarrefour/apikey.properties.

# Execução
O projeto pode ser executado normalmente pelo android studio utilizando Run ou por linha de comando  para gerar um apk em build/outputs/apk/debug/app-debug.apk
```
./gradlew build
```

# Teste
Testes podem ser executados normalmente pelo android studio ou utilizando linha de comando
```
./gradlew testDebugUnitTest
```