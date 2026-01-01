// pipeline {
//     agent any
//
//     tools {
//         maven 'Maven_3.9.6'   // configure Maven in Jenkins global tools
//         jdk 'JDK_21'          // configure JDK in Jenkins global tools
//     }
//
//     stages {
//         stage('Checkout') {
//             steps {
//                 git branch: 'main', url: 'https://github.com/pratiksawant1496/cicd.git'
//             }
//         }
//
//         stage('Build') {
//             steps {
//                 sh 'mvn clean package -DskipTests'
//             }
//         }
//
//         stage('Run Tests on Selenium Grid') {
//             steps {
//                 sh 'mvn clean test'
//             }
//             post {
//                 // Always publish reports, even if tests fail
//                 always {
//                     junit 'target/surefire-reports/*.xml'
//                     allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
//                 }
//             }
//         }
//     }
// }


pipeline {
    agent any

    tools {
        maven 'Maven_3.9.6'
        jdk 'JDK_21'
    }

    parameters {
        choice(name: 'BROWSER', choices: ['Chrome', 'Firefox', 'Safari'], description: 'Choose browser to run tests')
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/pratiksawant1496/cicd.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Run Tests on Selenium Grid') {
            steps {
                // Only browser is passed, grid.url comes from config.properties
                sh "mvn clean test -Dbrowser.name=${params.BROWSER}"
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                    allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
                }
            }
        }
    }
}
