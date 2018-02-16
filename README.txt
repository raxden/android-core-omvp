======================================== HOW IS THAT ===============================================

    This project is created like a template to start another projets. It`s based in MVP pattern and
    Onion arquitecture, besides use Dagger2 to injection dependencies, RX2 to reactive programiming
    and Retrofit2 to http layer. Another libraries used in proyect:

    - timber - Library to print logs
    - butterknife - Library to inject views.
    - glide - Library to load images.
    - lombok - Plugin that generate getters and setters for POJO classes.
    - parceler - Library to parcel any object between activities.
    - crashlytics - Library to register any crash in app.
    - calligraphyVersion - Library to use custom fonts.
    - threeTen - Library to use dates
    - autoFitTextView - Library to use TextViews with autoresize property.
    - modelmapper - Library to map any object to another.

================================== STEPS TO CONFIGURE PROJECT ======================================

    1. Rename androidApplicationId and testApplicationId in build.gradle.
        com.raxdenstudios.sample -> com.companyname.proyectname

    2. Install lombok plugin in your AndroidStudio.

    3. Register application in Firebase and download|replace google-services.json contained in
    app module. Remember that proyect has two diferences configurations, dev and prod.
    Therefore you must register two applications instead of one.
        {androidApplicationId}
        {androidApplicationId}.dev

    4. Create|Replace release.jks keystore
        buildSystem/release.jks

    5. Copy local.properties.sample and rename to local.properties, use this file to custom
    properties like user and password credentials used in development mode.

    6. To use Crashlytics, register application in Crashlytics via plugin. To personalize
    configuration like distribution or release notes, modify preferences in build.gradle.

    7. To use Sonarqube register application in Sonarqube. To launch sonarqube, execute
    "gradlew sonarqube" in console. Configuration is located in build.gradle.

============================================= TIPS =================================================

    1. All configuration is located in build.gradle
    2. Dependency configuration is located in buildSystem/dependencies.gradle.
    3. keystores is located in buildSystem/*


