Pipeline{
        agent any
        stages{
          stage('checkout'){
             steps{
                    checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/vmadykumar/Spring-Boot.git']]])                   
                  }
                }
                stage('Compile'){
                        tools {
                                jdk 'JAVA_HOME'
                                maven 'maven'
                        }
                }
             }
        
}
