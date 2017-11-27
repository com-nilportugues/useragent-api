# production-api-user-agent

## Responses:

- Response is in HAL+JSON.
- Error Response is in VND+ERROR

## Endpoints

[GET] /me (to get the current data from the User-Agent header)
[GET] /user-agent/?value= string
[POST] /user-agent/
{data: <User-Agent> string}