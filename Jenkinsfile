pipeline {
    agent none
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
