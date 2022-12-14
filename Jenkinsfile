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
                withSonarQubeEnv(credentialsId: 'SONAR_TOKEN', installationName: 'shwetak-lti') {
                sh 'mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=CICDusingAnsible'
             }
           }
        }
        stage("Quality Gate") {
            steps {
                waitForQualityGate abortPipeline: true
            }
        }
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
    }
}
