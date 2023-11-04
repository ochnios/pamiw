package pl.ochnios.todobackend.controllers.utils;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ApiError {
    private final String timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date());
    private final int status;
    private final String message;
}
