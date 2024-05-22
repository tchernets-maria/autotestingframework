pipeline {
    agent any

    environment {
        GIT_REPOSITORY = 'https://github.com/DameerGamlet/automated_testion_of_artnow_website'
    }

    tools {
        gradle '8.6'
        allure 'allure 2.24.0'
    }

    stages {
        stage("Test Run Status") {
            steps {
                git '${env.GIT_REPOSITORY}'
                bat 'gradle test'
            }
        }

        stage("Run allure report") {
            steps {
                allure ([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: "app/allure-results"]]
                ])
            }
        }
    }
}