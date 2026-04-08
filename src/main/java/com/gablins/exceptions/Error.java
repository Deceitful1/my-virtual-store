package com.gablins.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

public record Error(LocalDateTime timestamp, HttpStatus status, String error)
{
}
