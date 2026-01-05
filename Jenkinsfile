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
        success {
            emailext (
                to: 'pratiksawant1496@gmail.com',
                subject: "SUCCESS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """Hello Team,

                        The Jenkins build for ${env.JOB_NAME} #${env.BUILD_NUMBER} succeeded.

                        Browser: ${params.BROWSER}
                        Build details: ${env.BUILD_URL}

                        👉 Allure Report: ${env.BUILD_URL}allure

                        Regards,
                        Jenkins
                        """
            )
        }
        failure {
            emailext (
                to: 'pratiksawant1496@gmail.com',
                subject: "FAILURE: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """Hello Team,

                        The Jenkins build for ${env.JOB_NAME} #${env.BUILD_NUMBER} failed.

                        Browser: ${params.BROWSER}
                        Logs: ${env.BUILD_URL}

                        👉 Allure Report: ${env.BUILD_URL}allure

                        Regards,
                        Jenkins
                        """
            )
        }
        unstable {
            emailext (
                to: 'pratiksawant1496@gmail.com',
                subject: "UNSTABLE: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """Hello Team,

                    The Jenkins build for ${env.JOB_NAME} #${env.BUILD_NUMBER} is unstable.

                    Browser: ${params.BROWSER}
                    Build details: ${env.BUILD_URL}

                    👉 Allure Report: ${env.BUILD_URL}allure

                    Regards,
                    Jenkins
                    """
            )
        }
    }
}
