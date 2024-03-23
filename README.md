
# Hola, Edgar Retana les saluda! 👋

A continuacion encontraran el detalle de todo el proyecto Marvel


## Marvel Android APP

La app desarrollada les permitira a los usuarios autenticados ver la lista completa de personajes del universo de Marvel, podran realizar busquedas por nombre de los personajes y podras guardar tus personajes favoritos a una lista personal. Ademas podras ver la descripcion de cada personaje y la lista de comics en los que este ha aparecido, podras ver el precio del comic y abrir un enlace web que te llevara a ese comic.


## Pantallas

- Splash Screen
- Login
- Main
- (Fragment) Lista de favoritos 
- (Fragment) Details


## Caracteristicas Implementadas

- Patron de diseño MVVM
- Splash Screen (4s Delay)
- Lottie Files Animation
- Firebase Auth Login
- Infinite Scroll
- Libreria ViewPager2
- Libreria Material Desing 3
- Libreria Retrofit para peticiones HTTP
- Consumo de API [Marvel](https://gateway.marvel.com/v1)
- Busquedor de personajes por nombre
- Lista de favoritos
- Base de datos local con libreria Room
- Nested Scroll View Anidacion de ConstraintLayout con RecyclerView
- **Modo Dark** / Light
- Soporte de lenguaje Español (SV) / Ingles
- Redireccionamiento a navegador Web
- Libreria Glide para mostrar Images
- JetPack Navigation para navegacion entre fragmentos del Main Activity
- App icon
- Plurals Strings

## ScreenShots
#### Splash
![Splash](https://raw.githubusercontent.com/eretana97/Marvel/main/app/src/main/res/raw/ss_splash.jpg)
#### Login
![Login](https://raw.githubusercontent.com/eretana97/Marvel/main/app/src/main/res/raw/ss_login.jpg)
#### Info
![Info](https://raw.githubusercontent.com/eretana97/Marvel/main/app/src/main/res/raw/ss_info.jpg)
#### Favoritos
![Favoritos](https://raw.githubusercontent.com/eretana97/Marvel/main/app/src/main/res/raw/ss_favorites.jpg)
#### Dark/Light Theme
![Dark Light](https://raw.githubusercontent.com/eretana97/Marvel/main/app/src/main/res/raw/ss_darklight)



## Peticiones HTTPS
Lista de peticiones realizadas a la API de Marvel

#### Obtener lista de personajes

```http
  GET /characters
```

| Parametro        | Tipo     | Descripción                  |
|:-----------------|:---------|:-----------------------------|
| `ts`             | `long`   | Current Timestamp            |
| `apikey`         | `String` | Public Api Key               |
| `hash`           | `String` | MD5 Hash                     |
| `nameStartsWith` | `String` | Nombre de Personaje          |
| `limit`          | `int`    | Limite de resultados         |
| `offset`         | `int`    | Desplazamiento de resultados |

#### Filtrar personaje por id

```http
  GET characters/{id}
```

| Parametro | Tipo     | Descripción       |
|:----------|:---------|:------------------|
| `id`      | `string` | Id del personaje  |
| `ts`      | `long`   | Current Timestamp |
| `apikey`  | `String` | Public Api Key    |
| `hash`    | `String` | MD5 Hash          |

#### Filtrar comics por personaje
```http
  GET characters/{id}/comics
```
| Parametro    | Tipo     | Descripción               |
|:-------------|:---------|:--------------------------|
| `id`         | `string` | Id del personaje          |
| `format`     | `string` | Formato del comic         |
| `formatType` | `string` | Tipo de formato del comic |
| `ts`         | `long`   | Current Timestamp         |
| `apikey`     | `String` | Public Api Key            |
| `hash`       | `String` | MD5 Hash                  |


## Autor

Edgar Retana Github: [@eretana97](https://www.github.com/eretana97)


