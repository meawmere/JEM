package net.meawmere.jem.infrastructure.repositories;

import net.meawmere.jem.domain.Event;
import net.meawmere.jem.domain.EventType;

import java.util.ArrayList;
import java.util.List;

public class EventRepository {
    private final List<Event> events = new ArrayList<Event>();
    
    public void save(final Event event) {
        events.add(event);
    }
    
    public List<Event> getEvents() {
        return new ArrayList<>(events);
    }
    
    public void clear() {
        events.clear();
    }
    
    public void delete(final Event event) {
        events.remove(event);
    }
    
    public List<Event> findEventsByGroup(final String name) {
        List<Event> list = new ArrayList<>();
        
        for (Event e : events) 
            if (e.group().equals(name)) 
                list.add(e);
        
        return list;
    }
    
    public List<Event> findEventsByType(final EventType type) {
        List<Event> list = new ArrayList<>();

        for (Event e : events)
            if (e.eventType() == type)
                list.add(e);

        return list;
    }
    
    public void update(final Event oldEvent, final Event newEvent) {
        events.set(events.indexOf(oldEvent), newEvent);
    }
}
