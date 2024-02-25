FROM node:20-alpine3.19

COPY ./ /home/app
#RUN cd /home/app/src; \
#    sed -i 's/content="default-src 'unsafe-inline' 'self'; connect-src 'self'
#                      http://localhost:3000 https://localhost:443 https://192.168.0.185:443 https://127.0.0.1;"/
#                      content="default-src 'unsafe-inline' 'self'; connect-src 'self' https://192.168.0.185:443 https://127.0.0.1;"'


RUN cd /home/app; npm install; npm run build;

EXPOSE 3000
WORKDIR /home/app/
ENTRYPOINT ["node", "build"]
