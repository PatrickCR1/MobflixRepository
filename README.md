# <p align="center"> Mobflix App </p>

<p align="center"><img src="https://img.shields.io/badge/finished-november2022-green"/></p>
<p align="center"><img src="https://img.shields.io/badge/tested-november2022-green"/></p>

# Mobflix
## Aplicação que recebe o cadastro de vídeos do Youtube, para separar-los em categorias e exibi-los em uma lista. Feito para o Challenge Mobile de Android da Alura, utilizando a linguagem Kotlin.


## Cadastro de Vídeo |  Demonstração de Uso | Alterando Vídeo | Deletando Vídeo
<p float="left">
  <img src="https://github.com/PatrickCR1/MobflixRepository/blob/Main/Mobflix%20Gifs/Cadastro%20de%20V%C3%ADdeo.gif" width="240" height="450" />
  <img src="https://github.com/PatrickCR1/MobflixRepository/blob/Main/Mobflix%20Gifs/Filtra%20por%20Categoria%20e%20Redireciona%20para%20o%20Youtube.gif" width="240" height="450" /> 
  <img src="https://github.com/PatrickCR1/MobflixRepository/blob/Main/Mobflix%20Gifs/Alterando%20V%C3%ADdeo.gif" width="240" height="450" />
  <img src="https://github.com/PatrickCR1/MobflixRepository/blob/Main/Mobflix%20Gifs/Removendo%20V%C3%ADdeo.gif" width="240" height="450" />
</p>

# :hammer: Funcionalidades do projeto
## O App lista os vídeos, podendo ser separados por categorias, mostrando a thumbnail e a categoria do vídeo. Ao clicar em um vídeo, a pessoa é redirecionada para o Youtube para assisti-lo. Há opções de cadastro, alteração e remocão de vídeos. Todo armazenamento é mantido com persistência interna, portanto, ao finalizar o App as informações não são perdidas.

# ✔️ Técnicas e tecnologias utilizadas

# Tecnologias utilizas:

## `Linguagem Kotlin`
## `Android Studio`
## `Paradigma de orientação à objetvos`
## `Arquitetura MVVM`

# As técnicas e tecnologias utilizadas pra isso são:

## `Jetpack Compose`: Para desenvolver o layout e aprensentar as telas do aplicativo, com todos os ícones, listas, botões, etc. 
## `Jetpack Navigation`: Para navegação entre telas.
## `Safe Args`: Para transportar as informações entre telas.
## `Coil`: Carregar imagens via requisição HTTP
## `Room:`: Para armazenamento interno dos dados salvos, para que haja persistência interna e os dados não se percam ao finalizar o aplicativo.
## `Retrofit:`: Para realizar chamadas à API, obtendo os dados necessários para rodar a aplicação.
## `Youtube DATA API`: Para verificar os links do Youtube e retornar um thumbnail para ser exibida na listagem de vídeos.
## `Koin`: Injeção de dependências
## `Mockk`: Para realizar simulações (mocks) e testes 
## `Espresso`: Testes Instrumentados
## `Jetpack Compose Test`: Testes de Interface com o Compose
