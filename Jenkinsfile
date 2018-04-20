pipeline {
  agent {
    docker {
      image 'maven'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean package'
      }
    }
    stage('Test') {
      steps {
        echo 'Ok'
      }
    }
    stage('Deploy Development') {
      steps {
        sh 'echo \'deploy to development\''
      }
    }
  }
}