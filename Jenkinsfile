pipeline {
    agent any
    tools {
        maven 'MAVEN'
    }
    stages {
        stage('Publish') {
          steps {
             bat 'cd /src/main/java/com/luizf/soundboard/'
             bat './mvn package'
             bat'dir'
          }
        }
    }
}
