package com.example.aboutwork.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class AfterCommitEvent extends ApplicationEvent {

    final String name;

    public AfterCommitEvent(final Object source, final String name) {
        super(source);
        this.name = name;
    }

}
