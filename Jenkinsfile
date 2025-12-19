pipeline {
    agent any

    environment {
        // Biến dùng cho Docker build frontend
        FRONTEND_API_URL = 'http://backend:8080'
    }

    stages {
        stage('Checkout Source') {
            steps {
                echo '🔄 Checkout source code'
                git branch: 'main', url: 'https://github.com/chanhtran235/project-ci-cd.git'
            }
        }

        stage('Build Backend') {
            steps {
                echo '🏗️ Build Spring Boot backend'
                dir('demo') {
                    bat 'gradlew.bat clean bootJar'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                echo '🏗️ Build React frontend'
                dir('frontend') {
                    bat 'npm install'
                    bat "npm run build"
                }
            }
        }

        stage('Docker Compose Build & Deploy') {
            steps {
                echo '🐳 Build and deploy Docker containers'
                // Build với ARG cho frontend
                bat "docker-compose -f docker/docker-compose.yml build --build-arg VITE_API_URL=${env.FRONTEND_API_URL}"
                // Restart services
                bat "docker-compose -f docker/docker-compose.yml down"
                bat "docker-compose -f docker/docker-compose.yml up -d"
            }
        }
    }

    post {
        success {
            echo '✅ CI/CD pipeline SUCCESS 🎉'
        }
        failure {
            echo '❌ CI/CD pipeline FAILED'
        }
    }
}
