package com.ntconsult.application.domain;

import lombok.Data;

@Data
public class Notification {
    private Long reservationId;
    private String status;
    private String message;
}
