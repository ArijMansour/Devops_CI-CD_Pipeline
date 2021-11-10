pipeline{
    
environment{
		registry = 'arijmansour/timesheet-devops'
		registryCredential= 'dockerHub'
		dockerImage = ''
	}
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
        
        stage('Building our image'){
			steps{ 
				script{ 
					dockerImage= docker.build registry + ":$BUILD_NUMBER" 
				}
			}
		}

		stage('Deploy our image'){
			steps{ 
				script{
					docker.withRegistry( '', registryCredential){
						dockerImage.push()
					} 
				} 
			}
		}

 }
 post{
		success{
			emailext body: 'Build success', subject: 'Jenkins', to:'arijbenmansour1@gmail.com'
		}
		failure{
			emailext body: 'Build failure', subject: 'Jenkins', to:'arijbenmansour1@gmail.com'
		}

	}
 
}
