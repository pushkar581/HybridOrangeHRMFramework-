pipeline {
    agent any

    tools {
        maven 'Maven'   // 'Maven' must match the name you gave Maven in "Global Tool Configuration"
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }
        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
        }
        stage('Archive Reports') {
            steps {
                archiveArtifacts artifacts: 'test-output/**/*.html', allowEmptyArchive: true
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}
