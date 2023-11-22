stage('Deploy to Artifactory') {
            steps {
                script {
                    def server = Artifactory.server 'Artifactory'
                    def buildInfo = Artifactory.newBuildInfo()

                    // Deploy artifacts to Artifactory
                    server.upload spec: {
                        "files": [
                            {
                                "pattern": "target/*.jar",
                                "target": "java-application-libs-snapshot-local/${BUILD_NUMBER}/",
                                "props": "build.name=${JOB_NAME};build.number=${BUILD_NUMBER}"
                            }
                        ]
                    }, buildInfo: buildInfo

                    server.publishBuildInfo buildInfo
                }
            }
        }
