package org.example;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({AuthenticationException.class})
    public String handleAuthenticationException(Model model) {
        model.addAttribute("error", "Ошибка аутентификации");
        return "error";
    }

    @ExceptionHandler({AccessDeniedException.class})
    public String handleAccessDeniedException(Model model) {
        model.addAttribute("error", "Доступ запрещен");
        return "error";
    }
}
