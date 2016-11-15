Nice to see you are one of the few people in the world who read Readme files :-)  
This is a markdown file: https://en.wikipedia.org/wiki/Markdown  

#About  
This is an Open Source Java program you can use to hash (some people mistakenly call it 'encrypt') chosen columns of CSV files.
The program uses a Java Crypto Message digest, which is a secure one-way hash function that takes arbitrary-sized data and outputs a fixed-length hash value.
This particular program uses the SHA-256 algorithm to perform the hashing.
Many projects across the world need data to be hashed before 
IMPORTANT: The program starts reading from line number 2 (line numbers start from zero)

The master branch is used for active collaborative development.   
The release branch is used only when a version of the project is ready for release. The release branch is ideally operated only by the repository owner.  
  
#Running this project  
* Because this project uses Gradle, you may find it tough to configure and run it. Please ask Navin for help. Otherwise, you can also simply create a new java project, use the java files of this project and use the dependency jar files manually. Gradle just helps in downloading those dependency libraries automatically and building.
* To run this project, you need Gradle.   
* Install Gradle and navigate to this project's folder in the commandline and simply type "gradle". The project will build and a jar will be created in ./build/libs.  
* Now you can modify the json file with names and ratings of your choice.  
* From then on, you can simply run the program with "java -jar hashCSV.jar".  
* You can also install the Gradle plugin in Netbeans and directly run it from the IDE. It may take a long time to load the project for the first time if dependencies have to be loaded. The gradle build file contains support for creating an Eclipse project too.   
* Using Netbeans instead of Eclipse is recommended. Netbeans is just much cooler, has better features and is easier to use than Eclipse.  
  
#TODO list  
* Use a hash algorithm that gives a more readable (alphanumeric) hash instead of unknown UTF characters
* Perhaps use Apache Commons to handle commandline parameters
  
#Known issues/bugs  
* None


