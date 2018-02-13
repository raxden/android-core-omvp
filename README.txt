================================== STEPS TO CONFIGURE PROJECT ======================================

    1. Refactor all packages that contains "raxdenstudios.sample" by your company.project name.
        com.raxdenstudios.sample -> com.companyname.proyectname

    2. Register application in Firebase and download|replace google-services.json. Remember that
    proyect has two diferences configurations, dev and prod. Therefore you must register two
    applications instead of one.
        {application}
        {application}.dev

    3. Create|Replace release.jks keystore
        buildSystem/release.jks

    4. Copy local.properties.sample and rename to local.properties, use this file to

    4. Register application in Crashlytics.
        build.gradle

    5. Register application in Sonarqube. To launch sonarqube, execute "gradlew sonarqube"
    in console.
        build.gradle

