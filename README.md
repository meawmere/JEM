# JEM (JDA Event Manager)

This is an open source library designed to simplify working with JDA. It provides the possibility of preprocessing events, splitting into groups, filters, as well as convenient interaction with slash commands.

## 📖 Overview

The core concepts of JEM have been developed to make building scalable apps easy:

1. Preprocessing  
    Event handling before executing the main code.
1. Filters  
    Filter out events by custom id to simplify the readability of the code.
1. Slash Commands  
    Create slash commands and interact with them seamlessly.

You can learn more by visiting our discord server.

## 🔬 Installation

To install this library, you need to go to [GitHub Releases](https://github.com/meawmere/JEM/releases) and download the version you are interested in. After that, you need to add the dependency to [pom.xml](https://www.jetbrains.com/help/idea/maven-support.html#maven_multi_module) and specify the path to the jar file.

The version used is **JAVA SE 22**.
JEM was developed on **JDA 5.6.1** but will work on many older and newer versions.

### Gradle

```gradle
repositories {
    flatDir {
        dirs 'libs'
    }
    mavenCentral()
}
 
dependencies {
    implementation("net.dv8tion:JDA:$jdaversion")
    implementation files("path_to_floder/JEM-$version.jar")
}
```

### Maven

```xml
<dependencies>
    <dependency>
        <groupId>net.dv8tion</groupId>
        <artifactId>JDA</artifactId>
        <version>$jdaversion</version>
    </dependency>
    <dependency>
        <groupId>net.meawmere.jem</groupId>
        <artifactId>JEM</artifactId>
        <version>$version</version>
        <scope>system</scope>
        <systemPath>path_to_floder/JEM-$version.jar</systemPath>
    </dependency>
</dependencies>
```

## 🏃‍♂️ Getting Started

As an example, you can look at my [example](https://github.com/meawmere/JEM/blob/main/src/examples/java/ReadyEventExample.java) of working with the library.

Every bot using JEM must add a JEM Handler to the list of event listeners.

### Example: Ready Event

> [!NOTE]
> In this example, the @async annotation is used. We have added the possibility of asynchronous execution of events to achieve maximum performance.

Just send messages to the console when the bot is ready. Use the **@EventListener** anotation to indicate that this class is an event listener and mark the methods with the event anotation that you want to handle.

Launch your bot and upload the classes to **JEM**:

```java
public static void main(String[] args) {
  JEM jem = new JEM();
  JDABuilder.createDefault("token")
      .addEventListeners(jem.handler())
      .build();

  jem.saveClass(Listener.class, Listener2.class);
  jem.assign();
}
```

Your event listener could look like this:

```java
@EventListener
public class Listener {

  @Ready
  public static void ready(@EventData ReadyEvent event) {
    System.out.println("Ready");
  }

  @Ready
  public static @async void asyncready(@EventData ReadyEvent event) {
    System.out.println("Async Ready");
  }
}
```
```java
@EventListener
public class Listener2 {

  @Constructor
  public Listener2() {}

  @Ready
  public void ready(@EventData ReadyEvent event) {
    System.out.println("Ready 2");
  }

  @Ready
  public @async void asyncready(@EventData ReadyEvent event) {
    System.out.println("Async Ready 2");
  }
}
```

> [!IMPORTANT]
> In Listener2, we use non-static methods, so we must declare an empty constructor and mark it with the **@Constructor** annotation.

## 🛠️ Contributing to JEM

If you want to contribute to JEM, make sure to base your branch off of our **master** branch (or a feature-branch) and create your PR into that **same** branch.

Please follow our [Contributing Guidelines](https://github.com/discord-jda/JDA/blob/master/.github/CONTRIBUTING.md).

Do not expect your pull request to get immediate attention, sometimes it will take a long time to get a response.
You can join our [discord server][https://discord.gg/CEkAjm762C] and ask in [#lib-dev](https://discord.com/channels/125227483518861312/869965829024915466) before starting to work on a new PR, to get more immediate feedback from our community members.

## 🚨 Breaking Changes

Due to the nature of the Discord API, the library will regularly introduce breaking changes to allow for a quick adoption of newer features. We try to keep these breaking changes minimal, but cannot avoid them entirely.

Most breaking changes will result in a **minor** version bump (`x.1.2` → `x.2.0`).
