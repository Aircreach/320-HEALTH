package com.air.health.common.event;

import org.springframework.context.ApplicationEvent;

public class AdvisoryEvent extends ApplicationEvent {

    private final String message;

    public AdvisoryEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
