FROM alpine:3.4

RUN apk add --update nginx vim
RUN rm -rf /var/cache/apk/* && rm -rf /tmp/*

RUN mkdir /etc/nginx/available-enabled
RUN mkdir /etc/nginx/sites-enabled

ADD nginx.conf /etc/nginx/

CMD ["nginx", "-g", "daemon off;"]

EXPOSE 443 80
