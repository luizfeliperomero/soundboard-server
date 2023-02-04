pipeline {
    agent any
    tools {
        maven 'MAVEN'
    }
    stages {
        stage('Publish') {
          steps {
              sh 'cd /src/main/java/com/luizf/soundboard/'
             sh './mvn package'
             sh 'dir'
          }
        }
    }
}
