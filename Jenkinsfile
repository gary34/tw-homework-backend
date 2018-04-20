pipeline {
  agent none
  stages {
    agent {
      docker {
          image 'maven'
          args  '-v maven-repo:/root/.m2'
          reuseNode true
      }
  }
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
      when {
        branch 'develop'
      }
      steps {
        sh 'echo \'deploy to development\''
      }
    }
    stage('Deploy Production') {
      when {
        branch 'master'
      }
      steps {
        sh 'echo \'deploy to production\''
      }
    }
  }
}
