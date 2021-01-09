def agentLabel
if (BRANCH_NAME == 'main') {
    agentLabel = 'master'
} else {
    agentLabel = ''
}

pipeline {
    agent { label agentLabel }

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
        stage('Sonarqube') {
            environment {
                scannerHome = tool 'SonarQubeScanner'
            }
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh './gradlew sonarqube'
                }
                timeout(time: 10, unit: 'MINUTES') {
                    // Needs to be changed to true in the real project
                    waitForQualityGate abortPipeline: false
                }
            }
        }
        stage('Deploy') {
            when {
                branch 'main'
            }
            steps {
                echo 'Deploying....'
                sh 'rm -rf /srv/Backend/gateway/*'
                sh 'cd ./build && mv jib-image.tar /srv/Backend/cd/gateway/gateway.tar'
                sh 'touch /srv/Backend/cd/gateway/deploy'
            }
        }
        stage('Release') {
            when { allOf { branch 'dev'; triggeredBy 'UserIdCause' } }
            steps {
                sshagent (credentials: ['jenkins']) {
                    echo 'Pushing dev to main'
                    sh 'git push git@github.com:IT-REX-Platform/Gateway.git dev:main'
                }
            }
        }
    }
}
