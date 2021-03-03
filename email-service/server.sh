#!/bin/zsh

buildApplication(){
  echo "
  *****************************************************
    build java application
  *******************************************************";
  ## Build maven application
  export JAVA_HOME=$JAVA_11_HOME
  mvn clean package -DskipTests
}


buildDockerImage(){
  echo "
  *****************************************************
    build docker image
  *******************************************************";
  # Change version
  currentVersion=$(sed -n -e 's/^.*email-service:build//p' deployment/docker-compose.yml)
  nextVersion=$((currentVersion + 1))


  # Change image
  sed -i '' "s/email-service:build$currentVersion.*/email-service:build$nextVersion/g" deployment/docker-compose.yml
  image=$(grep -o 'eu.gcr.io/marufh/email-service.*' deployment/docker-compose.yml)

  # Build and deploy docker
  docker build -t $image .
  docker push $image
}


run(){
  echo "
  *****************************************************
    Update image on the server
  *******************************************************";
  # Get server ip
  ip=$(sed -n -e 's/^.*ansible_host=//p' ./deployment/hosts | cut -d' ' -f 1)

  # Copy docker-compose
  rsync -av --progress  ./deployment/docker-compose.yml  ./deployment/marufh-storage-permission.json root@${ip}:/opt/email-service/

  # Run docker compose
  ssh root@$ip "
    cd /opt/email-service
    docker-compose pull
    docker-compose stop
    docker-compose up -d
  "
}



commitFile(){
  git add .
  git commit -m "update application"
  git push origin master
}


deploy(){
  commitFile
  buildApplication
  buildDockerImage
  run
}



loginProd(){
    ip=$(sed -n -e 's/^.*ansible_host=//p' ./deployment/hosts | cut -d' ' -f 1)
    ssh root@$ip
}


option="${1}"
case ${option} in

  # Build application
  "build") buildApplication ;;

  # deploy means build application and run on the server
  "deploy") deploy ;;

  # restart means only restart the docker server
  "restart") run ;;

  # login into the server
  "login") loginProd ;;
esac

