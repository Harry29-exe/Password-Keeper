FROM maven:3.8.4-openjdk-17 as build
COPY pom.xml /home/app/
RUN mvn -f /home/app/pom.xml verify clean --fail-never
COPY src /home/app/src
RUN mvn -f /home/app/pom.xml clean package


FROM openjdk:17
COPY --from=build /home/app/target/Password-Keeper-0.4.jar /usr/local/lib/mjm.jar



EXPOSE 8080
#todo
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-Djwt.secrets.auth=fkgpour34820htyp3498y532gh798pgbasdbgfv9320gfdjgnbkieds4gh534h31oib;","-Djwt.secrets.refresh=gnreo34i8g3p4o9hbtpo9f34gn3u48592ht4398nq03pr9vun92oytnov438ntomiwqvygot492qhvmnra79tg4yo9vmyntr42q980", "-jar", "/usr/local/lib/mjm.jar"]