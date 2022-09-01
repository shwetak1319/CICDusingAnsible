def call(String repoUrl) {
    pipeline {
        agent any
        tools
        {
           maven "Maven"
        }
        environment {
            SONAR_TOKEN = '44afa76cb36f3a396435c0378ed295faed9fa218'
        } 
        stages {
          stage('Checkout') {
               steps {
                    git branch: 'master', url: "${repoUrl}"     
                  }
              }  
          stage("Cleaning workspace") {
                   steps {
                       sh "mvn clean"
                    }
               }
           stage('Build') {
               steps {
                    sh 'mvn package'             
                  }
              }
        stage('Upload to Artifactory') {
           steps {
                sh 'mvn deploy'    
              }
            }
        }
    }
}
