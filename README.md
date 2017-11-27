# production-api-user-agent

**Uses:**

- https://github.com/ua-parser/uap-java
- https://github.com/pieroxy/java-user-agent-detection

**Responses:**

- Response is in HAL+JSON.
- Error Response is in VND+ERROR

**Endpoints**

- [GET] /me (to get the current data from the User-Agent header)
- [GET] /user-agent/?value=<User-Agent> string
- [POST] /user-agent/ 
- `{data: <User-Agent> string}`
