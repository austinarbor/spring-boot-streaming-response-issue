package dev.aga.controller;

import dev.aga.model.ErrorDetails;
import dev.aga.service.SomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SomeController {
    private static final MediaType MEDIA_TYPE_CSV;

    static {
        MEDIA_TYPE_CSV = MediaType.asMediaType(MediaType.parseMediaType("text/csv"));
    }

    private final SomeService service;

    @GetMapping("/download1")
    public ResponseEntity<StreamingResponseBody> download1() {
        StreamingResponseBody body = outputStream -> service.throwIllegalArgumentException();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;\"download1.csv\"")
                .contentType(MEDIA_TYPE_CSV)
                .body(body);
    }

    @GetMapping("download2")
    public ResponseEntity<StreamingResponseBody> download2() {
        StreamingResponseBody body = outputStream -> service.throwIllegalStateException();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;\"download2.csv\"")
                .contentType(MEDIA_TYPE_CSV)
                .body(body);
    }

    @GetMapping("download3")
    public StreamingResponseBody download3() {
        StreamingResponseBody body = outputStream -> service.throwIllegalArgumentException();
        return body;
    }

    @GetMapping("download4")
    public StreamingResponseBody download4() {
        StreamingResponseBody body = outputStream -> service.throwIllegalStateException();
        return body;
    }


    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<ErrorDetails> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request, HttpServletResponse response) {
        log.info("Inside handleIllegalArgumentException");
        response.setContentType("application/json");
        var errorDetails = new ErrorDetails(request.getRequestURI(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(errorDetails);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Illegal state!")
    @ExceptionHandler(IllegalStateException.class)
    void handleIllegalStateException() {
        log.info("Inside handleIllegalStateException");
    }
}
