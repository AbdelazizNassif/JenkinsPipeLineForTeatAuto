pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/AbdelazizNassif/JenkinsPipeLineForTestAuto.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                echo 'testing'
                bat 'mvn test -Ppartest'
            }
        }
        
        
    }
}
