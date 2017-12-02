server {
  listen       80;
  server_name  useragent.api.nilportugues.com;

  location / {
    proxy_pass                          http://useragent.internal:8080;
    proxy_pass_request_headers          on;
    proxy_set_header X-Real-IP          $remote_addr;
    proxy_set_header X-Forwarded-For    $proxy_add_x_forwarded_for;
    proxy_set_header Host               $http_host;
  }
}