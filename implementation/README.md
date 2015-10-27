### Running

In order to run the application from the command line, execute ```./gradlew run```

### Changing Main Class

In the build.gradle file, there is a line that says
```groovy
mainClassName = 'com.teampc.example.ExampleMain'
```
Simple change the class value to the desired class, then execute ```./gradlew run``` again

### Folder structure

The project is organized into two main "source" folders. All the java code is
located in `src/main/java`, and all the fxml layout files are located in
```src/main/layout```. We customized our build to include
```src/main/layout``` in the resources; placing layout files in
```src/main/resources``` will also work, but leads to some ambiguity about
resource files and layout files, so please don't.
