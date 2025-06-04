pipeline 
{
    agent any
    
    tools{
        maven 'maven'
        }

    stages 
    {
        stage('Build') 
        {
            steps
            {
                               echo("build step")
               
            }
            post 
            {
                success
                {
                                  echo("build success")
                  
                }
            }
        }
        
        
        
        
        
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa")
            }
        }
        
                
        stage('Regression UI Automation Tests') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/ajaybhave07/OpenCartSeleniumFramework'
                    sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/recources/testrunner/testng_sanity.xml"
                    
                }
            }
        }
                
     
        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
        
        
        stage('Publish Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: true, 
                                  reportDir: 'reports', 
                                  reportFiles: 'TestExecutionReport.html', 
                                  reportName: 'HTML Regression Extent Report', 
                                  reportTitles: ''])
            }
        }
          
        stage("Deploy to PROD"){
            steps{
                echo("deploy to PROD")
            }
        }
        
        
    }
}