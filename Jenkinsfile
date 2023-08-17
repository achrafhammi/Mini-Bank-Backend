pipeline {
    agent any

    tools{
        maven 'MAVEN'
        jdk 'java.home'
    }

    stages {

        stage('Build and Test') {
            steps {
                // Change the working directory to your project directory (where the pom.xml is located)
                sh 'mvn clean test'
            }
        }
        stage('Compiling Package .JAR'){
            steps{
                sh 'mvn clean compile package'
            }
        }
        stage('Sonar Scan'){
            steps{
                withSonarQubeEnv(installationName:'sq1'){
                    sh 'mvn clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar -Dsonar.java.binaries=/var/lib/jenkins/workspace/CI-CD-Mini-Bank-Pipeline/target/classes'
                }
            }
        }
        stage('Build Docker Image'){
            steps{
                sh 'sudo docker build -t mini-bank-backend:0.0.1 .'
            }
        }
        stage('Push to Docker Repo'){
            steps{
                sh 'sudo docker tag mini-bank-backend:0.0.1 malcomer/mini-bank-app:0.0.1'
                sh 'sudo docker login -u malcomer -p dockeritoo123'
                sh 'sudo docker push malcomer/mini-bank-app:0.0.1'
            }
        }
        stage('Run App on Server'){
            steps{
                sh 'mvn spring-boot:run'
            }
        }
    }
}
