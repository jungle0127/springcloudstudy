FROM centos:7

# set label
LABEL maitainer="PS <arbor@foxmail.com>"

# set environment
ENV FESCAR_USER="fescar" \
    JAVA_HOME="/usr/lib/jvm/java-1.8.0-openjdk" \
    JAVA="/usr/lib/java-1.8.0-openjdk/bin/java" \
    TIME_ZONE="Asia/Shanghai"

 ARG FESCAR_VERSION=0.2.2

 WORKDIR /$BASE_DIR

COPY ./fescar-server-${FESCAR_VERSION}.tar.gz /home

 RUN set -x \
    && yum update -y \
    && yum install -y java-1.8.0-openjdk java-1.8.0-devel wget inputils nc vim libcurl \
    && mkdir /opt/fescar \
    && tar -xzvf /home/fescar-server-${FESCAR_VERSION}.tar.gz -C /opt/fescar \
    && rm -rf /home/fescar-server-${FESCAR_VERSION}.tar.gz \
    && ln -snf /usr/share/zoneinfo/${TIME_ZONE} /etc/localtime && echo '$TIME_ZONE' > /etc/timezone \
    && yum clean all

 # set more param
 ENV EXTRA_JVM_ARGUMENTS="-XX:MaxDirectMemorySize=1024M"

 ENTRYPOINT ["sh", "/opt/fescar/bin/fescar-server.sh"]
 EXPOSE 8091