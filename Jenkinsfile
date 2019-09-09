Pipeline{
        agent {any}
        stages{
          stage('checkout'){
             steps{
                    checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, 
                    extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '70daea05-ee60-49d1-bfdd-b0670cf42f90', 
                    url: 'https://github.com/vmadykumar/Spring-Boot.git']]])                      
                  }
                }
             }
        
}
