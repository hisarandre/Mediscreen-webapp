package com.mediscreen.webapp.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to handle custom error pages for HTTP errors.
 */
@Controller
@ControllerAdvice
public class HandleErrorController implements ErrorController {

    /**
     * Handles the HTTP error and forwards to the corresponding error page.
     *
     * @param request The HttpServletRequest containing the error status code.
     * @return The view name of the corresponding error page.
     */
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            // If the status code corresponds to a NOT_FOUND (404) error, display the error-404 page.
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            }
        }
        // For any other error, display the default error page.
        return "error";
    }
}
