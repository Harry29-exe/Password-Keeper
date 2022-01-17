FROM node:17-alpine3.14

COPY ./ /home/app
RUN cd /home/app; npm install; npm run build;

EXPOSE 3000
ENTRYPOINT ["node", "/home/app/build"]
