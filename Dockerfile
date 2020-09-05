FROM hseeberger/scala-sbt:8u252_1.3.13_2.13.3

ENV APP_ROOT=/workspace\
    PGSSLMODE=disable

RUN apt update && \
    apt upgrade -y && \
    rm -rf /var/lib/apt/lists/* &&\
    mkdir ${APP_ROOT}

COPY . ${APP_ROOT}
WORKDIR ${APP_ROOT}
CMD bash -c "sbt run -Dhttp.port=9000 -Dconfig.file=./conf/application.conf -Dlogger.file=./conf/logback.xml"
