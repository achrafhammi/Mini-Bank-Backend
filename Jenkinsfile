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
        stage('Package .JAR'){
            steps{
                sh 'mvn clean package'
            }
        }
        stage('Sonar Scan'){
            steps{
                sh 'sudo chmod a+x mvnw'
                withSonarQubeEnv(installationName:'sq1'){
                    sh 'sudo ./mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar'
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
    }
}
