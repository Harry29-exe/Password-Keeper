print y | docker-compose rm;
prinf y | docker image prune;

docker-compose build;
docker-compose up;