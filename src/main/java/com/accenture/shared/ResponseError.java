package com.accenture.shared;

import java.time.LocalDateTime;

public record ResponseError(LocalDateTime dateTime, String type, String message) {
}
