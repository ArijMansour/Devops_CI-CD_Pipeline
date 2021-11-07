pipeline{

    agent any
    
    stages {
        stage('Checkout GIT') {
            steps {
                echo 'Pulling...';
                git branch: 'roua', 
                url:'https://github.com/elposs10/Timesheet-Project.git';
            }
        }
        stage("Test, Build"){
            steps{
                bat "mvn clean install"
            }
        }
	stage("package"){
               steps{

                   bat "mvn package"
                    }

                  }
         stage("Sonar"){
            steps{
                bat "mvn sonar:sonar"
            }
        }
         stage("Nexus"){
            steps{
                bat "mvn clean package deploy:deploy-file -DgroupId=tn.esprit.spring -Dmaven.test.skip=true -DartifactId=Timesheet-spring-boot-core-data-jpa-mvc-REST-1 -Dversion=0.0.1-SNAPSHOT -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo1 -Durl=http://localhost:8081/repository/maven-snapshots/ -Dfile=target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.1-SNAPSHOT.war"
            }
        }
        
        stage('Cloning our Git') {
            steps{
checkout([$class: 'GitSCM', branches: [[name: '*/roua']], extensions: [], userRemoteConfigs: [[credentialsId: 'dockerhub_id', url: 'https://github.com/elposs10/Timesheet-Project.git']]])
                
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                  bat 'docker build -t roua5/docker-spring-boot:1.0.0 .'
                }
            }
        }
         stage('Deploy Docker Image') {
            steps {
                script {
withCredentials([string(credentialsId: 'docker-pwd', variable: 'dockerHubPwd')]) {
                    bat "docker login -u roua5 -p ${dockerHubPwd}"
                 }  
                 bat 'docker push roua5/docker-spring-boot:1.0.0'
                }
            }
         }
         
        
    }
	post{
        always{
            emailext body: 'build success' , subject: 'Jenkins' , to: 'rouambarki19@gmail.com'
        }
        
    }
    
}
