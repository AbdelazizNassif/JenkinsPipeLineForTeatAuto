pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test -Pregression'
            }
            
        }
        
    }
}
