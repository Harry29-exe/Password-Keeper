sudo podman container kill odwsi-db;
sudo podman container rm odwsi-db;
sudo podman image rm odwsi-db;

sudo podman build ./ --tag odwsi-db;
sudo podman run -p 5432:5432 --name odwsi-db odwsi-db