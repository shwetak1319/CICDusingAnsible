pipeline {
    agent any
    // Maven install
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
             
                git branch: 'master', url: 'https://github.com/shwetak1319/CICDusingAnsible.git'
             
          }
        }
         stage('Build') {
           steps {
             
                sh 'mvn package'             
          }
        }
        stage('Sonar Scan') {
           steps {
                withSonarQubeEnv(credentialsId: '44afa76cb36f3a396435c0378ed295faed9fa218', installationName: 'shwetak-lti') {
                sh 'mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=CICDusingAnsible'
             }
           }
        }
        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                    // true = set pipeline to UNSTABLE, false = don't
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        /*
        stage('Upload to Artifactory') {
           steps {
             
                sh 'mvn deploy'             
          }
        }
        stage('Ansible Deploy') {
             
            steps {
               sh "ansible-playbook main.yml -i inventories/dev/hosts --user ubuntu --key-file ~/.ssh/id_rsa"
            }
        }
        */
    }
}
