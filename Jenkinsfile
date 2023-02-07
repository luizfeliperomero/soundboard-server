pipeline {
    agent any
    tools {
        maven 'MAVEN'
    }
    stages {
        stage('Publish') {
           steps {
             bat 'mvn package -Dmaven.test.skip'            
          }
          stage("Copy"){
             steps {
                 fileOperations([fileCopyOperation(
                    excludes: '',
                    flattenFiles: false,
                     includes: 'target/*.jar',
                    targetLocation: "C:\\Desktop"
             )])
      }
}
           
            }
        }
    }
}
