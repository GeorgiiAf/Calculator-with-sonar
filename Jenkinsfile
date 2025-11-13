pipeline {

    agent any


    environment {
        PATH = "/usr/local/bin:${env.PATH}"

        SONARQUBE_SERVER = 'SonarQubeServer'
        SONAR_TOKEN = 'sqa_3fbbf528df6ded7a6f9f6bdb15c7716e23cc1366' // Make sure a right token is selected
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
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Code Coverage') {
            steps {
                sh 'mvn jacoco:report'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv("${env.SONARQUBE_SERVER}") {
                    sh """
                        /usr/local/sonarscanner/bin/sonar-scanner \
                        -Dsonar.projectKey=LectureDemo_SonarQube \
                        -Dsonar.sources=src \
                        -Dsonar.projectName=LectureDemo_SonarQube \
                        -Dsonar.host.url=http://localhost:9000 \
                        -Dsonar.token=${env.SONAR_TOKEN} \
                        -Dsonar.java.binaries=target/classes \
                    """
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                    // Or specify Dockerfile path explicitly if needed
                    // docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}", "-f ./Dockerfile .")
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: "${DOCKERHUB_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh '''
                        docker login -u $DOCKER_USER -p $DOCKER_PASS
                        docker push $DOCKERHUB_REPO:$DOCKER_IMAGE_TAG
                    '''
                }
            }
        }
    }
}