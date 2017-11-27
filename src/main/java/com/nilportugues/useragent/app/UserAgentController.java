package com.nilportugues.useragent.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nilportugues.useragent.app.modules.context.useragent.detection.UserAgentDetectionResult;
import com.nilportugues.useragent.app.modules.context.useragent.detection.UserAgentDetector;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserAgentController {

    @RequestMapping(value = "/me", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> getMe(@RequestHeader(value = "User-Agent") final String userAgentHeader) throws JsonProcessingException {
        final UserAgentDetectionResult userAgent = new UserAgentDetector().parseUserAgent(userAgentHeader);
        final ObjectMapper objectMapper = new ObjectMapper();

        return ResponseEntity.ok(objectMapper.writeValueAsString(userAgent));
    }
}
