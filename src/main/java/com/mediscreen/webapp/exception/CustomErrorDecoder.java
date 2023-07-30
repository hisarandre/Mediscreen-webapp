package com.mediscreen.webapp.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/**
 * Custom Feign Error Decoder to handle specific error responses from Feign clients.
 */
@Component
public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Decodes the Feign client response and handles specific error cases based on the HTTP status code.
     *
     * @param methodKey The Feign client method key.
     * @param response The Feign Response object containing the HTTP response details.
     * @return An Exception representing the decoded error, or null if the error is not handled.
     */
    @Override
    public Exception decode(String methodKey, Response response) {

        // Handle specific error cases based on the HTTP status code.
        switch (response.status()){
            case 400:
                // Log the error for status code 400 (Bad Request).
                logger.error("Status code " + response.status() + ", methodKey = " + methodKey);
            case 404: {
                // Log the error for status code 404 (Not Found) and create a ResponseStatusException with the error details.
                logger.error("Error took place when using Feign client to send HTTP Request. Status code " + response.status() + ", methodKey = " + methodKey);
                return new ResponseStatusException(HttpStatus.valueOf(response.status()), "<You can add error message description here>");
            }
            default:
                // For all other error codes, return a generic Exception with the response reason.
                return new Exception(response.reason());
        }
    }
}