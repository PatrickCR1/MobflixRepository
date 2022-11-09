# <p align="center"> Mobflix App </p>

<p align="center"><img src="https://img.shields.io/badge/finished-november2022-green"/></p>
<p align="center"><img src="https://img.shields.io/badge/tested-november2022-green"/></p>

# Mobflix
### Aplicação que recebe o cadastro de vídeos do Youtube, para separar-los em categorias e exibi-los em uma lista. Feito para o Challenge Mobile de Android da Alura, utilizando a linguagem Kotlin.


## Cadastro de Vídeo |  Demonstração de Uso 
<img src="https://github.com/PatrickCR1/MobflixRepository/blob/Main/Images/Cadastro%20de%20V%C3%ADdeo.gif" width="240" height="450" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="https://github.com/PatrickCR1/MobflixRepository/blob/Main/Images/Filtra%20por%20Categoria%20e%20Redireciona%20para%20o%20Youtube.gif" width="240" height="450" /> 

## Alterando Vídeo | Deletando Vídeo
<img src="https://github.com/PatrickCR1/MobflixRepository/blob/Main/Images/Alterando%20V%C3%ADdeo.gif" width="240" height="450" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="https://github.com/PatrickCR1/MobflixRepository/blob/Main/Images/Removendo%20V%C3%ADdeo.gif" width="240" height="450" />

# :hammer: Funcionalidades do projeto
### <center> O Aplicativo lista os vídeos, podendo ser separados por categorias, mostrando a thumbnail e a categoria do vídeo. Ao clicar em um vídeo, a pessoa é redirecionada para o Youtube para assisti-lo. Há opções de cadastro, alteração e remocão de vídeos. Todo armazenamento é mantido com persistência interna, portanto, ao finalizar o aplicativo as informações não são perdidas. <center>

# ✔️ Técnicas e tecnologias utilizadas

## Tecnologias utilizadas:

### `Linguagem Kotlin`
### `Android Studio`
### `Paradigma de orientação à objetvos`
### `Arquitetura MVVM`

## As técnicas e tecnologias utilizadas pra isso são:

### `Jetpack Compose`: Para desenvolver o layout e aprensentar as telas do aplicativo, com todos os ícones, listas, botões, etc. 
### `Jetpack Navigation`: Para navegação entre telas.
### `Safe Args`: Para transportar as informações entre telas.
### `Coil`: Carregar imagens via requisição HTTP
### `Room:`: Para armazenamento interno dos dados salvos, para que haja persistência interna e os dados não se percam ao finalizar o aplicativo.
### `Retrofit:`: Para realizar chamadas à API, obtendo os dados necessários para rodar a aplicação.
### `Youtube DATA API`: Para verificar os links do Youtube e retornar um thumbnail para ser exibida na listagem de vídeos.
### `Koin`: Injeção de dependências
### `Mockk`: Para realizar simulações (mocks) e testes 
### `Espresso`: Testes Instrumentados
### `Jetpack Compose Test`: Testes de Interface com o Compose

## Dependências

### Você pode [acessar as dependências aqui](https://github.com/PatrickCR1/MobflixRepository/blob/Main/Dependencies.txt).
  
## 📁 Acesso ao projeto
### Você pode [acessar o código fonte do projeto inicial](https://github.com/PatrickCR1/MobflixRepository/tree/Main/Mobflix) ou [baixá-lo](https://github.com/PatrickCR1/MobflixRepository/archive/refs/heads/Main.zip).

## 🛠️ Abrir e rodar o projeto

Após baixar o projeto, você pode abrir com o Android Studio. Para isso, na tela de launcher clique em:

- **Open an Existing Project** (ou alguma opção similar)
- Procure o local onde o projeto está e o selecione (Caso o projeto seja baixado via zip, é necessário extraí-lo antes de procurá-lo)
- Por fim clique em OK

O Android Studio deve executar algumas tasks do Gradle para configurar o projeto, aguarde até finalizar. Ao finalizar as tasks, você pode executar o App 🏆 

# Autor
[<img src="https://avatars.githubusercontent.com/u/86331226?v=4" width=115><br><sub>Patrick Contarini Richard</sub>](https://github.com/PatrickCR1) 
