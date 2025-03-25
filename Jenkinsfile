pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                echo "🔹 Pulling latest code from GitHub..."
                git branch: 'main', url: 'https://github.com/amitnvgm/ToolshopV5.0.git'
            }
        }

        stage('Setup Environment') {
            steps {
                script {
                    echo "🔹 Installing dependencies..."
                    sh 'mvn clean install -DskipTests'
                }
            }
        }

        stage('Start Selenium Grid in Docker') {
            steps {
                script {
                    echo "🔹 Starting Selenium Grid..."
                    sh 'docker-compose up -d'
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    echo "🔹 Running tests..."
                    sh "mvn test"
                }
            }
        }

        stage('Publish Test Reports') {
            steps {
                publishHTML(target: [
                    reportDir: 'target/extent-reports',
                    reportFiles: 'index.html',
                    reportName: 'Extent Reports',
                    keepAll: true
                ])
            }
        }

        stage('Stop Selenium Grid') {
            steps {
                script {
                    echo "🔹 Stopping Selenium Grid..."
                    sh 'docker-compose down'
                }
            }
        }
    }

    post {
        always {
            echo "🔹 Cleaning up workspace..."
            cleanWs()
        }
        success {
            echo "✅ Build and Tests Successful!"
        }
        failure {
            echo "❌ Build or Tests Failed!"
        }
    }
}
