pipeline {
    agent any
    tools {
        maven 'MAVEN'
    }
    stages {
        stage('Publish') {
           steps {
               bat 'mvn package -Dmaven.test.skip'
               dir("eletroposto-api/target/") {
                     fileOperations([fileCopyOperation(
                        excludes: '',
                        flattenFiles: false,
                        includes: '*.jar',
                        targetLocation: "C:\\Desktop"
                    )])
               }
           }
        }    
    }           
}                
      
