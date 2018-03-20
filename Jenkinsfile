pipeline {
   agent any
   tools { 
        maven 'Maven 3.3.9' 
        jdk 'jdk8' 
    }
   stages {
	     stage('Build') {
            steps {
              sh 'mvn install'
            }
        }
     stage('Push image') {
        /* Finally, we'll push the image with two tags:
         * First, the incremental build number from Jenkins
         * Second, the 'latest' tag.
         * Pushing multiple tags is cheap, as all the layers are reused. */
       steps {
         script {
        docker.withRegistry('https://registry.hub.docker.com') {
            def authImage = docker.image("registry.hub.docker.com/abhisheknn/auth:latest")
             authImage.push()
        }
        }
                 
       }
    }
  }
}
