pipeline {
    agent any

    stages {
        stage('Pre-build') {
            steps {
                echo "NODE_NAME = ${env.NODE_NAME}"
                echo 'Pre-build..'
                sh './gradlew npmInstall'
            }
        }
        stage('Build') {
            steps {
                echo 'Building Gateway..'
                sh './gradlew -Pprod bootJar jibBuildTar'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            when {
                branch 'main'
            }
            steps {
                echo 'Deploying....'
                sh 'rm -rf /srv/Backend/gateway/*'
                sh 'cd ./build && mv jib-image.tar /srv/Backend/gateway/gateway.tar'
                sh 'touch /srv/Backend/gateway/deploy'
            }
        }
        stage('Release') {
            when { allOf { branch 'dev'; triggeredBy 'UserIdCause' } }
            steps {
                sshagent (credentials: ['Github']) {
                    echo 'Pushing dev to main'
                    sh 'git push git@github.com:IT-REX-Platform/Gateway.git dev:main'
                }
            }
        }
    }
}