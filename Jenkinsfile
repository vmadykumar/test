pipeline {
        agent any
        stages {
          stage('checkout'){
             steps {
                    checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/vmadykumar/Spring-Boot.git']]])
                   }
                }
                stage('build') {
                        tools {
                          jdk 'JAVA_HOME'
                          maven 'maven'      
                        }
                        steps{
                          dir('Code') {
                              echo 'hello, maven'
                               sh 'mvn clean verify'
                             } 
                        }
                }     
             }       
}
