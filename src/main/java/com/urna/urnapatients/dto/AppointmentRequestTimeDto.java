package com.urna.urnapatients.dto;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

public class AppointmentRequestTimeDto implements Serializable {
    @NotNull
    @FutureOrPresent
    private LocalDateTime requestTime;

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }
}
