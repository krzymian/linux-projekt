# Hotel-Finder

Aplikacja pozwalająca wyszukać najlepszy hotel w danym mieście. Wyniki wyszukiwania można sortować po cenie lub ilości gwiazdek. Aby skorzystać z wyszukiwarki należy zalogować się na utworzone wcześniej konto użytkowania. 

Aplikacja korzysta z bazy danych: https://github.com/lucasmonteiro001/free-world-hotel-database

#### Wykorzystane technologie:
* Java 8
* Spring Framework
* Spring boot
* Spring Security
* H2 Database
* JPA - Hibernate
* Thymeleaf
* HTML5 + CSS3
* JS + JQuery

#### Aplikacja:
* Strona główna:
![Home Page](https://i.imgur.com/OOF2UBq.png)

* Formularz logowania:
![Login Form](https://i.imgur.com/dEgJaJz.png)

* Strona rejestracji:
![Login Form](https://i.imgur.com/vfiyfUA.png)

* Strona wyszukiwania:
![Login Form](https://imgur.com/3RLy8l7.png)

* Podział wyników wyszukiwania na strony:
![Login Form](https://imgur.com/8Cf0e6Q.png)

#### Struktura projektu:
* src/main/
    * java/com/krzymianowski/hotelfinder/
        * config/
            * component/
                * CustomAuthFailureHandler.java
                * CustomAuthSuccessHandler.java
            * SecurityConfig.java
        * controller/
            * HomePageController.java
            * SearchController.java
            * UserController
        * model/
            * repository/
                * CountryRepository.java
                * HotelsRepository.java
                * UserRepository.java
            * Country.java
            * Hotel.java
            * Role.java
            * User.java
        * service/
            * CountryService.java
            * HotelService.java
            * UserService.java
        * HotelFinderApplication.java
    * java/resources/
        * database/
            * hotels.csv
            * small_hotels.ccv
            * super_small_hotels.csv
        * static/
            * css/
                * master.css
                * home-page.css
                * register-page.css
                * search-page.css
            * js/
                * jquery-3.3.1.min.js
                * master.js
                * register-page.js
                * search-page.js
        * templates/
            * fragments/
                * footer.html
                * head.html
                * header.html
                * login.html
                * nav.html
            * home-page.html
            * register-page.html
            * search-page.html
            * szablon.html
        * application.properties
