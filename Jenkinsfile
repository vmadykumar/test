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
                                withSonarQubeEnv('sonar server') {
                                       bat 'mvn sonar:sonar'  
                                }
                        }
                }
             }       
}
