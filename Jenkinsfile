pipeline {
    agent any
    environment {
		dockerImage = ''
    }
    stages {
    	stage('CheckOut Code'){
         	steps{
            	checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/andyYsz/sba.git']]])
            }
        }
        stage('Maven Build'){
        	steps{
        	    bat 'mvn clean package -DskipTests'
        	}
        }
        stage('Image Build'){
        	steps{
        	    bat 'mvn dockerfile:build'
        	}
        }
        stage('Image Push'){
        	steps{
        	    bat 'docker push tesla2020/eureka-server:latest'
        	    bat 'docker push tesla2020/gateway:latest'
        	    bat 'docker push tesla2020/payment:latest'
        	    bat 'docker push tesla2020/search:latest'
        	    bat 'docker push tesla2020/technology:latest'
        	    bat 'docker push tesla2020/training:latest'
        	    bat 'docker push tesla2020/user:latest'
        	}
        }
        stage('Remove Image'){
        	steps{
        	    bat 'docker rmi tesla2020/eureka-server'
        	    bat 'docker rmi tesla2020/gateway'
        	    bat 'docker rmi tesla2020/payment'
        	    bat 'docker rmi tesla2020/search'
        	    bat 'docker rmi tesla2020/technology'
        	    bat 'docker rmi tesla2020/training'
        	    bat 'docker rmi tesla2020/user'
        	    bat 'docker image prune -f'
        	}
        }
    }
}