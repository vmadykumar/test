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
                                expression { params.Run_SonarAnalysis == true}
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
                stage('Approval Step'){
                                   when {
                                        expression { params.Release == true}
                                   }
                                   steps{
                                           script {  
                                                input message: 'Approve Deploy?', ok: 'Yes', submitter: 'PME'
                                               // echo "${env.approved}"
                                                // echo ("User Input is: "+env.approved['env'])
                                                }
                                   }
                }
                stage('Release'){
                                agent any
                                when {
                                        expression { params.Release == true }
                                }
                        steps {
                                echo 'Starting Release'
                                deploy adapters: [tomcat7(credentialsId: '51d79b50-5fd8-4444-91eb-c6ead7dc4151', path: '', url: 'http://172.28.99.129:8081')], contextPath: '/HappyTrip', war: 'Code/target/*.war'
                        }
                }
                stage('Notification') {
                        steps {
                                echo 'Sending Email'
                                emailext body: 'Build Suceeded', subject: 'Jenkins Job Status', to: 'vikash.bcet@gmail.com'
                        }
                }
        }
        post {
                        always {
                                echo 'One way or another, I have finished'
                                deleteDir() /* clean up our workspace */
                        }
                        success {
                                echo 'Yeppie.. I succeeeded!'
                                mail to: 'vikash.bcet@gmail.com',
                                        subject: "Succeeded Pipeline: ${currentBuild.fullDisplayName}",
                                        body: "Everything went well :) ${env.BUILD_URL}"
                        }
                        unstable {
                                echo 'Oh! No I am unstable :/'
                        }
                        failure {
                                echo 'Shit.. I failed :('
                                mail to: 'vikash.bcet@gmail.com',
                                        subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
                                        body: "Something is wrong with ${env.BUILD_URL}"
                        }
                        changed {
                                echo 'Things were different before...'
                        }
        }
}
