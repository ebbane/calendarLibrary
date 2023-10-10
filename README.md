# CalendarLibrary

**CalendarLibrary** is a Java library that allows you to generate calendars in HTML and object format
for a given month.

## Usage

### In a Maven Project

To use CalendarLibrary in a Maven project, follow these steps:

1. Add the CalendarLibrary dependency to your `pom.xml` file:

   ```xml
   <dependencies>
       		<dependency>
			<groupId>org.ebbane</groupId>
			<artifactId>CalendarLibrary</artifactId>
			<version>1.0-SNAPSHOT</version>
			<scope>system</scope>
			<systemPath>${project.basedir}/src/main/resources/lib/CalendarLibrary.jar</systemPath>
		</dependency>
   </dependencies>

2. Update Maven dependencies:

   ```shell
    mvn clean install
3. Use CalendarLibrary in your Java code

   Import CalendarLibrary classes into your Java code:
    ``` java
   import org.ebbane.CalendarLibrary;
   import org.ebbane.model.MonthlyCalendar;
   import org.ebbane.model.Day;
    
4. Then use CalendarLibrary's methods in your code:
    ```java 
    // Generate HTML calendar
    String htmlCalendar = CalendarLibrary.generateHTMLCalendar(2023, 10);
    // Generate MonthlyCalendar object
    MonthlyCalendar monthlyCalendar = CalendarLibrary.generateCalendar(2023, 10);

### In a Gradle Project

To use CalendarLibrary in a Gradle project, follow these steps:

1. Add the CalendarLibrary dependency in your `build.gradle` file :

   ```gradle
   dependencies {
        implementation group: 'org.ebbane', name: 'CalendarLibrary', version: '1.0-SNAPSHOT'
    }

2. Update Gradle dependencies:

   ```shell
    ./gradlew clean build

## Genrate new JAR

``` shell
   javac -d out src/main/java/org/ebbane/model/Day.java src/main/java/org/ebbane/model/MonthlyCalendar.java src/main/java/org/ebbane/CalendarLibrary.java
```

```shell
   jar cvf CalendarLibrary.jar -C out/ .
```

