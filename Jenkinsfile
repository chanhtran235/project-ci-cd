pipeline {
    agent any

    environment {
        // Biến dùng cho Docker build frontend (truyền vào Dockerfile)
        FRONTEND_API_URL = 'http://localhost:8080'
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

        /*
         ❌ BỎ HOÀN TOÀN stage Build Frontend
         ❌ Không npm install
         ❌ Không npm run build
         👉 Frontend sẽ build trong Dockerfile
        */

        stage('Docker Compose Build & Deploy') {
            steps {
                echo '🐳 Build and deploy Docker containers'

                // Build Docker images (frontend + backend)
                bat """
                    docker-compose -f docker/docker-compose.yml build ^
                    --build-arg VITE_API_URL=${env.FRONTEND_API_URL}
                """

                // Restart services
                bat 'docker-compose -f docker/docker-compose.yml down'
                bat 'docker-compose -f docker/docker-compose.yml up -d'
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
        always {
            // Xoá workspace Jenkins (C:)
            cleanWs()

            // Dọn Docker cache (E:)
            bat 'docker image prune -f'
            bat 'docker builder prune -f'
        }
    }
}
