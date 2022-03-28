# PasswordKeeper

### Web application for secure password storage

---

## Table of content

- [About](#about)
- [How it works](#how-it-works)
  - [Registration](#register)
  - [Saving passwords](#savingcreating-password)
  - [Reading passwords](#reading-password)
  - [Deployment diagram](#deployment)
- [Default Configuration](#default-configuration)
- [Application deployment](#application-deployment)
- [Features](#features)
- [App Tour](#app-tour)
  - [Main page](#application-main-page)
  - [Registration](#user-registration)
  - [Login page](#login-page)
  - [Device authorization](#device-authorization-with-email-messaging-mock)
  - [Welcome page](#welcome-page)
  - [Adding password](#adding-password)
  - [Generating password](#generating-secure-password)
  - [Viewing saved password](#viewing-saved-password)
  - [Authorized devices page](#authorized-devices-page)
  - [Login history](#login-history-page)

## About

PasswordKeeper is web application which allows users to store their password in secure way.

## How it works

User have 2 password just for this app, and they have to remember it. Both application password are stored only as their
hash (using BCrypt algorith). Application use first of passwords for logging users in, second one is used for encrypting
others password with aes algorith.

Thanks to this approach even administrator of application is not able to see user passwords.

### Register

![](readme/HowItWorks1_Register.png)

### Saving/Creating Password

![](readme/HowItWorks2_SavingCreatingPassw.png)

### Reading Password

![](readme/HowItWorks3_ReadingPassw.png)

### Deployment

![](readme/HowItWorks4_Deploymend.png)

## Default configuration

By default, application (nginx proxi) is configured in such a way that frontend lives on port: 4430 and communicates
with backend which can be found on port 443. Before using frontend user should visit any backend address in order to add
ssl certificate to trusted list.

## Application deployment

Requirements:

- installed docker
- installed docker-compose

To start and run application run command:

```shell
docker-compose build; docker-compose up;
```

## Features

Application offers:

- Authorization system based on 2 JWT, where one is sent as cookie and serves as refresh token and the other one is used
  for user authorization,
- Storing user's passwords in such way that only user can decrypt it,
- Encrypting stored password with AES in CBC mode,
- Input data validation on 2 layers (api, service layers),
- CSRF protection,
- Device authorization system based on User-Agent and Ip information,
- Device login history available for user,

## App Tour

### Application main page:

![](readme/Tour1_MainPage.png)

### User registration

![](readme/Tour2_UserRegiter.png)

### Login Page

![](readme/Tour3_Login.png)

### Device Authorization (with email messaging mock)

![](readme/Tour4_1_DeviceAuthorization.png)
![](readme/Tour4_2_DeviceAuthMsg.png)

### Welcome page

![](readme/Tour5_WellcomePage.png)

### Adding Password

![](readme/Tour6_1_AddingPassword.png)
![](readme/Tour6_2_AddedPassword.png)

### Generating Secure Password

![](readme/Tour7_1_GeneratingPassword.png)
![](readme/Tour7_2_GeneratedPassword.png)

### Viewing Saved Password

![](readme/Tour8_1_ViewingSavePassword.png)
![](readme/Tour8_2_SavedPasswordView.png)
![](readme/Tour8_3_GeneratedPasswordView.png)

### Authorized Devices Page

![](readme/Tour9_AuthorizedDevicesList.png)

### Login History Page

![](readme/Tour10_LoginHistory.png)






