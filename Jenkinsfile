pipeline {
    agent any

    environment {
        PATH = "C:\\Program Files\\Git\\bin;${env.PATH}" // путь к Git Bash, если нужно
        SONAR = 'Sonar'
        SONAR_TOKEN = 'sqa_3fbbf528df6ded7a6f9f6bdb15c7716e23cc1366'
        DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
        DOCKERHUB_REPO = 'georgiiafa/calculator-with-sonar'
        DOCKER_IMAGE_TAG = 'latest'
    }

    tools {
        maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/GeorgiiAf/Calculator-with-sonar.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Code Coverage') {
            steps {
                bat 'mvn jacoco:report'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv("${env.SONAR}") {

                    bat """
                        sonar-scanner ^
                        -Dsonar.projectKey=LectureDemo_SonarQube ^
                        -Dsonar.sources=src ^
                        -Dsonar.projectName=LectureDemo_SonarQube ^
                        -Dsonar.host.url=http://localhost:9000 ^
                        -Dsonar.token=${env.SONAR_TOKEN} ^
                        -Dsonar.java.binaries=target/classes
                    """
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: "${DOCKERHUB_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat """
                        docker login -u %DOCKER_USER% -p %DOCKER_PASS%
                        docker push ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}
                    """
                }
            }
        }
    }
}
