Nice to see you are one of the few people in the world who read Readme files :-)  
This is a markdown file: https://en.wikipedia.org/wiki/Markdown  

#About  
This is an Open Source Java program you can use to hash (some people mistakenly call it 'encrypt') chosen columns of CSV files.
The program uses a Java Crypto Message Digest, which is a secure one-way hash function that takes arbitrary-sized data and outputs a fixed-length hash value.
This particular program uses the SHA-256 algorithm to perform the hashing.
Many projects across the world need data in CSV files to be hashed before handing it over to a client.

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
* Take inputs about input file, output file and column numbers from the commandline
* Add a commandline help mode where if the user types something wrong, the program gives examples on how the commands could be used correctly.
* Display a progress bar for how many lines of the file got completed.
* Use batch processing to process multiple CSV files.
  
#Known issues/bugs  
* None


