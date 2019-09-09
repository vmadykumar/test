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
                                       withSonarQubeEnv('SonarQube6.4') 
                                       bat '$SONAR_MAVEN_GOAL'
                                }
                        }
                }
             }       
}
