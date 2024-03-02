FROM node:20-alpine3.19 as build

COPY ./Password-Keeper-WebApp2 /home/app
WORKDIR /home/app
RUN npm i;
RUN npm i @sveltejs/adapter-static;
RUN npm run build;

FROM nginx

COPY --from=build /home/app/build/ /home/build/

COPY nginx/password.keeper.crt /etc/ssl/certs/password.keeper.crt
COPY nginx/password.keeper.key /etc/ssl/private/password.keeper.key
COPY nginx/nginx.conf /etc/nginx/nginx.conf