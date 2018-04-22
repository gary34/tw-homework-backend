pipeline {
  agent any
  environment {
      CODE_VERSION = sh(returnStdout: true, script: 'git rev-parse --short HEAD').trim()
  }
  stages {
    stage('Build') {
      agent {
        docker {
            image 'maven'
            args  '-v maven-repo:/root/.m2'
            reuseNode true
        }
      }
      steps {
        sh 'mvn clean packages'
        sh 'mkdir -p packages'
        sh 'cp target/backend-1.0-SNAPSHOT packages/backend-${CODE_VERSION}.jar'
      }
    }
    stage('Test') {
      steps {
        echo 'Ok'
      }
    }
    stage('Prepare Deploy') {
        steps {
            sh 'rm -rf tw-homework-ansible-master && wget https://github.com/gary34/tw-homework-ansible/archive/master.zip && unzip master.zip'
            sh 'cp packages/backend-${CODE_VERSION}.jar  tw-homework-ansible-master/roles/backend/files/backend.jar'
        }
    }
    stage('Deploy Development') {
      when {
        branch 'develop'
      }
      steps {
         sh 'cd tw-homework-ansible-master && chmod 600 id_rsa-ansible && ansible-playbook --tags "setup,backend" -i hosts/development site.yml'
      }
    }
    stage('Deploy Production') {
      when {
        branch 'master'
      }
      steps {
          sh 'cd tw-homework-ansible-master && chmod 600 id_rsa-ansible && ansible-playbook --tags "setup,backend" -i hosts/production site.yml'
      }
    }
  }
  post {

      success {
          sh "echo 'Please visite to http://b.iyomi.me:9000/geo'"
      }

      cleanup {
          sh 'rm -rf tw-homework-ansible-master master.zip'
      }
  }
}
