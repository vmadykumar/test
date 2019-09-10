pipeline {
        agent any
        parameters {
                booleanParam(name: 'Run_SonarAnalysis', defaultValue: false, description: 'Will Run Sonar Code Analysis')
                booleanParam(name: 'Release', defaultValue: false, description: 'Will Push code to the Server')             
        }
        tools {
                   jdk 'JAVA_HOME'
                   maven 'maven'      
               }
        stages {
                stage('checkout'){
                        
                        steps {
                                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/vmadykumar/Spring-Boot.git']]])
                        }
                }
                stage('build') {
                      steps {
                          dir('Code') {
                                       echo 'Hello Maven, Executing build'
                                       bat 'mvn clean package'
                          } 
                       }                      
                }
                stage('sonar analysis') {
                        when {
                                expression { params.Run_SonarAnalysis == True}
                        }
                        steps { 
                                dir('Code') {
                                        echo 'Executing sonar Analysis'        
                                        withSonarQubeEnv('sonar server') {
                                                bat 'mvn sonar:sonar'  
                                        }
                                }
                        }
                }
                stage('Artifactory'){
                        steps { 
                                dir('Code') {
                                        echo 'creating Artifacts'         
                                        archiveArtifacts 'target/*.war'
                                        echo 'Artifact created'
                                }
                         }
                 }
                stage('Approval'){
                                   when {
                                        expression { params.Release == True}
                                   }
                                   steps{
                                           script {  
                                                approved = input message: 'Release to production?', ok: 'Yes', submitter: 'PME'
                                                if (approved) {
                                                         withCredentials([usernamePassword(credentialsId: 'privilegedCreds', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                                                                        echo "Approved"
                                                         }
                                                }
                                           }
                                  }
                }
                stage('Release'){
                                agent any
                                when {
                                        expression { params.Release == True }
                                }
                        steps {
                                echo 'Starting Release'
                                deploy adapters: [tomcat7(credentialsId: '1363756c-b4e2-495b-b925-fc8572d5962f', path: '', url: 'http://172.30.11.187:8081')], contextPath: '/HappyTrip', war: '**/*.war'
                        }
                }
        }       
}
