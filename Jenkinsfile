pipeline {
    agent any

    tools{
        maven 'MAVEN'
        jdk 'java.home'
    }

    stages {

        stage('Test and Compile') {
            steps {
                // Change the working directory to your project directory (where the pom.xml is located)
                sh 'mvn clean test compile'
            }
        }
        stage('Sonar Scan'){
            steps{
                withSonarQubeEnv(installationName:'sq1'){
                    sh 'mvn clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar -Dsonar.java.binaries=./target'
                }
            }
        }
        stage('Package .JAR'){
            steps{
                sh 'mvn clean package'
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
                sh 'sudo docker push malcomer/mini-bank-app:0.0.1'
            }
        }
    }
}
