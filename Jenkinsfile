// pipeline {
//     agent any
//
//     tools {
//         maven 'Maven_3.9.6'
//         jdk 'JDK_21'
//     }
//
//     parameters {
//         choice(name: 'BROWSER', choices: ['Chrome', 'Firefox', 'Safari'], description: 'Choose browser to run tests')
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
//                 // Only browser is passed, grid.url comes from config.properties
//                 sh "mvn clean test -Dbrowser.name=${params.BROWSER}"
//             }
//             post {
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
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Run Tests on Selenium Grid') {
            steps {
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

    post {
        always {
            emailext (
                to: 'pratiksawant1496@gmail.com',
                subject: "Build Result: ${env.JOB_NAME} #${env.BUILD_NUMBER} - ${currentBuild.currentResult}",
                body: """<h3>Build Notification</h3>
                         <p>Project: ${env.JOB_NAME}</p>
                         <p>Build Number: ${env.BUILD_NUMBER}</p>
                         <p>Status: <b>${currentBuild.currentResult}</b></p>
                         <p>Browser: ${params.BROWSER}</p>
                         <p>👉 <a href="${env.BUILD_URL}allure">View Allure Report</a></p>""",
                mimeType: 'text/html'
            )
        }
    }

}
