package net.meawmere.jem.infrastructure.services;

import net.meawmere.jem.domain.Event;

public class InvokeService {
    private final Event event;

    public InvokeService(Event event) {
        this.event = event;
    }
    
    public void invoke() {}
}
