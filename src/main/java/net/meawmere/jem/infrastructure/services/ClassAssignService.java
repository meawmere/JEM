package net.meawmere.jem.infrastructure.services;

import net.meawmere.jem.domain.Event;
import net.meawmere.jem.domain.EventType;
import net.meawmere.jem.domain.InvokeEvent;
import net.meawmere.jem.application.ValidateDataType;
import net.meawmere.jem.infrastructure.InvalidSettingsException;
import net.meawmere.jem.infrastructure.annotations.EventListener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ClassAssignService {

    public List<Event> assignClasses(List<Class<?>> classes) throws InvalidSettingsException, InstantiationException, IllegalAccessException {
        return assign(classes);
    }
    
    private List<Event> assign(List<Class<?>> classes) throws InvalidSettingsException, InstantiationException, IllegalAccessException {
        List<Event> events = new ArrayList<>();
        for (Class<?> clazz : classes) {
            EventListener annotation = clazz.getAnnotation(EventListener.class);
            List<Event> entities = new ArrayList<>();
            if (annotation != null) 
                for (Event event : assign(clazz, annotation)) 
                    entities.add(event);
            
            for (Event event : entities) {
                events.add(event);
            }
        }
        return events;
    }
    
    private List<Event> assign(Class<?> clazz, EventListener annotation) throws InvalidSettingsException, InstantiationException, IllegalAccessException {
        List<Event> events = new ArrayList<>();
        
        Object instance = null;
        for (Method method : clazz.getMethods()) {
            boolean isStatic = Modifier.isStatic(method.getModifiers());
            
            if (!isStatic) {
                validateConstructor(clazz);
                if (instance == null)
                    instance = clazz.newInstance();
            };
            
            EventType type = findEventType(method);
            if (type != null) {
                validateMethod(method, type);
                Event invokeEvent = new Event(type, annotation.group(), new InvokeEvent(clazz, method, (isStatic ? null : instance), isStatic));
                
                if (invokeEvent.eventType() == null || invokeEvent.group() == null || invokeEvent.invoke() == null ||
                    invokeEvent.invoke().eventClass() == null || invokeEvent.invoke().method() == null ||
                    (!isStatic && invokeEvent.invoke().instance() == null)) {
                    throw new InvalidSettingsException("I cant compile " + method + " to event entity!");
                }
                
                events.add(invokeEvent);
            }
            
        }
        
        return events;
    }

    private EventType findEventType(Method method) throws InvalidSettingsException {
        EventType type = null;
        
        for (Annotation annotation : method.getAnnotations()) {
            EventType eventType = EventType.findEventType(annotation);
            if (eventType != null && type != null)
                throw new InvalidSettingsException("You cannot use multiple event types on the same method.");
            
            if (type == null)
                type = eventType;
        }
        
        return type;
    }
    
    private void validateMethod(Method method, EventType type) throws InvalidSettingsException {
        ValidateDataType validateDataType = new ValidateDataType(method, type);
        validateDataType.validate();
    }

    private void validateConstructor(Class<?> clazz) throws InvalidSettingsException {
        boolean has_con = false;
        for (Constructor<?> constructor : clazz.getConstructors()) {
            if (constructor.getParameterCount() == 0 && constructor.getModifiers() == Modifier.PUBLIC && constructor.getAnnotation(net.meawmere.jem.infrastructure.annotations.Constructor.class) != null) {
                has_con = true;
                break;
            }
        }

        if (!has_con)
            throw new InvalidSettingsException("For using non-static method in listener you must have public empty constructor marked with @Constructor");
    }
}
