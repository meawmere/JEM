import os

events = []

with open("buffer.txt", "r") as f:
    lines = f.readlines()
    for line in lines:
        text = line
        result = text[:text.find(' -')] if ' -' in text else text
        events.append(result)
        # result = result + "(net.meawmere.jem.infrastructure.annotations.events." + result + ".class, " + result + "Event.class),"


for event in events:
    with open (f"./src/main/java/net/meawmere/jem/infrastructure/annotations/events/{event}.java", "w") as f:
        f.write("""package net.meawmere.jem.infrastructure.annotations.events;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.METHOD)
public @interface """ + event + """ {
}
""")