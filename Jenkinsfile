pipeline {
    agent any
    tools {
        maven 'MAVEN'
    }
    stages {
        stage('Publish') {
          steps {
              sh './mvn package'
          }
          post {
            success {
              archiveArtifacts 'target/*.jar'
            }
          }
        }
    }
}
