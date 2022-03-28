# Aplikacja do bezpiecznego przechowywania haseł

### Wykonał Kamil Wójcik

- [Aplikacja do bezpiecznego przechowywania haseł](#aplikacja-do-bezpiecznego-przechowywania-hase)
- [Uruchomienie](#uruchomienie)
- [Używanie aplikacji](#uywanie-aplikacji)
- [Uwagi](#uwagi)
- [Struktura aplikacji](#struktura-aplikacji)
  - [Backend](#backend)
  - [Frontend](#frontend)

## Uruchomienie

Aby uruchomić aplikacje należy mieć na komputerze zainstalowanego docker'a oraz docker-compose'a. Aplikacje włączamy
będąc w głównym folderze aplikacji, za pomocą komendy

```shell
docker-compose build; docker-compose up;
```

## Używanie aplikacji

Z punktu użytkownika ważny jest jedynie adres https://localhost:4430 ponieważ na nim znajduje się pplickacja z
interfejsem graficznym. API aplickacji znajduje się pod adresem https://localhost:443

## Uwagi

Aplikacja jest skonfigurowana pod moją sieć domową z tąd w wielu miejscach adresy 192.168.0.185 w wersji stricte
produkcyjne powinny zostać usunięte, a adresy localhost powinny zostać zmienione na domenę na której aplikacja się
znajduje.

W plikach konfiguracyjnych znajdują się hasła do bazy danych, klucze algorytmów szyfrujących czy klucze RSA. W wersji
stricte-produkcyjnej powinny zostać one rzecz jasna podmienione na nowe tajne które nigdy nie wyjdą poza maszyny na
których są uruchomione.

## Struktura aplikacji

Aplikacja jest podzielona na cztery sekcje

* Backend aplikacji (./Password-Keeper-API)
* Frontend aplikacji (./Password-Keeper-WebApp)
* Bazę danych (./postgres)
* odrócone proxy (./nginx)

### Backend

Backend został napisany przy użyciu java + spring.

Został podzielony na sekcje ogólnego przeztnacenia takie jak:

* config - tutaj znajduje się ogólna konfiguracja aplikacji taka jak
  * konfiguracja spring security
  * konfiguracja cors (annotacja DefaultCors)
  * podstawowe funkcje sprawdzające czy użytkownik jest uprawniony do dostępu do serwisu/endpointu
* validation - tutaj znajdują się validatory wykorzystywane w aplikacji
* exceptions - niestandardowe wyjątki oraz ich handler'y
* utils - zawierające wszystko co jest potrzebne całej aplikacji a nie pasje do wyżej wymienionych
* moduły - moduły aplikacji
  * auth - kontener na moduły związane z authentication i authorization
  * user - obsługa konta użytkonika (tworzenie, usuwanie, modyfikowanie)
  * password_storage - moduł odpowiedzialny za przechowywanie zapisanych haseł użytkowników

### Frontend

Frontend został napisany przy użyciu typescript + sveltekit

Został podzielony na

* routes - strony aplikacji związane z technologią sveltekit
* components - komponenty używane przez strony aplikacji
* logic - klienci api oraz dto
* stores - store'y związane z technologią svelte
