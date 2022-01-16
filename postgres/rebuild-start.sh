sudo docker container kill odwsi-db;
sudo docker container rm odwsi-db;
sudo docker image rm odwsi-db;

sudo docker build ./ --tag odwsi-db;
sudo docker run -p 5432:5432 --name odwsi-db odwsi-db