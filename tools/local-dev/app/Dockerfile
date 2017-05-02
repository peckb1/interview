FROM ubuntu:16.04

# update our apt-get
RUN apt-get update

# install common things like python
RUN apt-get install -y software-properties-common

# add the Java 8 repository which is not default for Ubuntu
RUN apt-add-repository -y ppa:webupd8team/java
# auto select that we accept the Oracle licence
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections
# update our apt-get again with the new repository
RUN apt-get update

# install Oracle Java 8
RUN apt-get install -y oracle-java8-installer oracle-java8-set-default

# create a directory for our logs
RUN mkdir -p /opt/interview/api
COPY run-app.py /opt/interview

# expose our ports
# 8080 - base dropwizard
# 8081 - base dropwizard:admin
# 8082 - our Java debug port
EXPOSE 8080 8081 8082

CMD ["/opt/interview/run-app.py", "/opt/interview/conf/local.env"]