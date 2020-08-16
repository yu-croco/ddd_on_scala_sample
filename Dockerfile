FROM hseeberger/scala-sbt:8u252_1.3.10_2.12.11

ENV APP_ROOT=/workspace\
    PGSSLMODE=disable


RUN apt update && apt upgrade -y && apt install wget ca-certificates software-properties-common -y &&\
    bash -c 'echo "deb http://apt.postgresql.org/pub/repos/apt/ buster-pgdg main" > /etc/apt/sources.list.d/pgdg.list' &&\
    wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | apt-key add - &&\
    apt update 2> /tmp/keymissing; for key in $(grep "NO_PUBKEY" /tmp/keymissing | sed "s/.*NO_PUBKEY //"); do echo -e "\nProcessing key: $key"; gpg --keyserver keyserver.ubuntu.com --recv $key && gpg -a --export $key | apt-key add -; done &&\
    apt update && apt upgrade -y --allow-unauthenticated &&\
    apt install postgresql-10 build-essential git -y --allow-unauthenticated &&\
    rm -rf /var/lib/apt/lists/* &&\
    mkdir ${APP_ROOT}

COPY . ${APP_ROOT}
WORKDIR ${APP_ROOT}
CMD bash -c "sbt ~run -Dhttp.port=9000 -Dconfig.file=./conf/application.conf -Dlogger.file=./conf/logback.xml"
