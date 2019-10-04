def gitUrl = 'https://github.com/vmadykumar/Spring-Boot.git'
job ('Happytrip-DSL') {
     scm {
          git (gitUrl)
     }
     steps {
            
     }
}
