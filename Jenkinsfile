pipeline {
    agent any

    tools{
        maven 'MAVEN'
        jdk 'java.home'
    }

    stages {
        /*stage('Clone Private Repo') {
            steps {
                script {
                    // Define the Git URL of your private repository
                    def gitUrl = 'https://github.com/Malcomer123/Mini-Bank-Backend.git'

                    // Use the Jenkins "credentials" function to access the Git credentials configured in Jenkins
                    def credentials = credentials('PAT_Jenskin')

                    // Clone the repository using the provided credentials
                    git branch: 'main', credentialsId: credentials, url: gitUrl
                }
            }
        */
        stage('Build and Test') {
            steps {
                // Change the working directory to your project directory (where the pom.xml is located)
                bat 'mvn clean test'
            }
        }
        stage('Package .JAR'){
            steps{
                bat 'mvn clean package'
            }
        }
        stage('Build Docker Image'){
            steps{
                bat 'docker-compose up --build'
            }
        }
    }
}
