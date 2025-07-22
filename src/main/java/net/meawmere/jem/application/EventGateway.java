package net.meawmere.jem.application;

import net.meawmere.jem.infrastructure.services.ClassAssignService;

public class EventGateway {
    private final ClassAssignService classAssignService;

    public EventGateway(ClassAssignService classAssignService) {
        this.classAssignService = classAssignService;
    }

    public ClassAssignService getClassAssignService() {
        return classAssignService;
    }
}
