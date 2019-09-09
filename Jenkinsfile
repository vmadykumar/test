pipeline {
        agent any
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
                              echo 'hello, maven'
                              bat 'mvn package'
                             } 
                        }                      
                }
                stage('sonar analysis') {
                        steps {
                                dir('Code') {
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
                           always {
                               archiveArtifacts: 'target/*.war',
                               onlyIfSuccessful: true
                        }
                                echo 'Artifact created'
                        }
                      }
                }
             }       
}
