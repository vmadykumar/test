pipelineJob ('Happytrip-DSL') {
     def gitUrl = 'https://github.com/vmadykumar/Spring-Boot.git'
     definition {
                    cps {
                         script(readFileFromWorkspace('Jenkinsfile'))
                         sandbox()     
                    }
     }
}
