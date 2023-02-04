pipeline {
    agent any
    tools {
        maven 'MAVEN'
    }
    stages {
        stage('Publish') {
          steps {
             bat'dir'
             bat 'mvn package -Dmaven.test.skip'
             bat'dir'
          }
        }
    }
}
