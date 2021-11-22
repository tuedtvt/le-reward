pipeline {
    //options { timeout(time: "${BUILD_TIMEOUT}", unit: 'MINUTES') }
    agent {
        docker {
            image 'pycodocker/gradle-jdk11-build:v1'
            args '-v $PWD:/var/maven -v /var/run/docker.sock:/var/run/docker.sock --network="host" -u root --privileged'
            reuseNode true
        }
    }
    stages {
        stage('Build') {
            steps {
                sh '
                echo $PWD
                ls
                gradlew clean build'
            }
        }
        stage('SonarQube analysis') {
            when {
                branch 'develop'
            }
            steps {
                withSonarQubeEnv('PYCO SonarQube Server') {
                    sh 'gradlew sonarqube'
                }
            }
        }
    }
}
