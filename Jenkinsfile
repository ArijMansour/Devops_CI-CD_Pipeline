pipeline{

    agent any
    
    stages {
        stage('Checkout GIT') {
            steps {
                echo 'Pulling...';
                git branch: 'master', 
                url:'https://github.com/ArijMansour/Devops_Timesheet_CI-CD.git';
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
                bat "mvn deploy"
            }
        }

    }
}
