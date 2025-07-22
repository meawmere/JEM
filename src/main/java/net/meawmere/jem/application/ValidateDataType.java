package net.meawmere.jem.application;

import net.meawmere.jem.domain.EventType;
import net.meawmere.jem.infrastructure.InvalidSettingsException;
import net.meawmere.jem.infrastructure.annotations.EventData;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ValidateDataType {
    private final Method method;
    private final EventType type;
    
    public ValidateDataType(Method method, EventType type) {
        this.method = method;
        this.type = type;
    }
    
    public void validate() throws InvalidSettingsException {
        List<Parameter> parameters = List.of(method.getParameters());
        List<Class<?>> parametersType = new ArrayList<>();

        for (Parameter parameter : parameters) {
            EventData annotation = parameter.getAnnotation(EventData.class);
            if (annotation != null) 
                parametersType.add(parameter.getType());
        }
        
        validate(parametersType, type);
    }
    
    private void validate(List<Class<?>> parametrsType, EventType type) throws InvalidSettingsException {
        InvalidSettingsException exception = new InvalidSettingsException(method + " The data type of the received parameter differs from the desired data type");

        if (!new HashSet<>(parametrsType).containsAll(type.getEventClasses())) throw exception;
    }
}
