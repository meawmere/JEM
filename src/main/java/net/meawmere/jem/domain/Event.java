package net.meawmere.jem.domain;

public record Event(EventType eventType, String group, InvokeEvent invoke) {
}
