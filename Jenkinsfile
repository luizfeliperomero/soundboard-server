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
            post {
                success {
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
