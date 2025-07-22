package net.meawmere.jem.infrastructure.repositories;

import java.util.ArrayList;
import java.util.List;

public class ClassRepository {
    private final List<Class<?>> classes = new ArrayList<Class<?>>();
    
    public void save(final Class<?>... classes) {
        for (Class<?> c : classes) 
            this.classes.add(c);
    }
    
    public List<Class<?>> getClasses() {
        return new ArrayList<>(classes);
    }
    
    public void delete(final Class<?>... classes) {
        for (Class<?> c : classes)
            this.classes.remove(c);
    }
}
