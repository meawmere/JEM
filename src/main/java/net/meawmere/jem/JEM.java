package net.meawmere.jem;

import net.meawmere.jem.domain.Event;
import net.meawmere.jem.infrastructure.InvalidSettingsException;
import net.meawmere.jem.infrastructure.repositories.ClassRepository;
import net.meawmere.jem.infrastructure.repositories.EventRepository;
import net.meawmere.jem.infrastructure.services.ClassAssignService;
import net.meawmere.jem.infrastructure.services.Handler;

import java.util.List;

public class JEM {
    private final ClassRepository classRepository = new ClassRepository();
    private final EventRepository eventRepository = new EventRepository();
    private final ClassAssignService classAssignService = new ClassAssignService();
    private final Handler handler = new Handler(eventRepository);
    
    public ClassRepository getClassRepository() {
        return classRepository;
    }

    public ClassAssignService getClassAssignService() {
        return classAssignService;
    }

    public EventRepository getEventRepository() {
        return eventRepository;
    }
    
    public Handler handler() {
        return handler;
    }
    
    public void assign() throws InvalidSettingsException, InstantiationException, IllegalAccessException {
        List<Event> eventsEntity = classAssignService.assignClasses(classRepository.getClasses());
        for (Event event : eventsEntity) {
            eventRepository.save(event);
        }
    }
    
    public void saveClass(final Class<?>... classes) {
        classRepository.save(classes);
    }
}
