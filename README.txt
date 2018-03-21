======================================== HOW IS THAT ===============================================

    This project is created like a template to start another projets. It`s based in MVP pattern and
    Onion arquitecture, besides use Dagger2 to injection dependencies, RX2 to reactive programing
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
        com.omvp.app -> com.companyname.proyectname

    2. Register application in Firebase and download|replace google-services.json contained in
    app module. Remember that proyect has n(flavours) diferences configurations, dev and prod.
    Therefore you must register n(flavours) applications instead of one.
        {androidApplicationId}
        {androidApplicationId}.dev
        {androidApplicationId}.mock

    3. Install lombok plugin in your AndroidStudio.

    4. Create|Replace release.jks keystore
        buildSystem/release.jks

    5. Copy and rename local.properties.sample to local.properties, any property defined in this
    file is available through BuildConfig.KEY_TO_USE in DEBUG configuration. Use this file to
    custom properties like user and password credentials used only in development mode.

    6. To use Crashlytics, configure app/fabric.properties. To personalize configuration like
    distribution or release notes, modify preferences in build.gradle.

    7. Define what languages will be use the application in
    com.omvp.app.injector.module.LocaleModule

    8. Defines path to store pictures in device in: (Replace package com.omvp.app by yours)
        + dev/res/xml/provider_paths.xml
        + prod/res/xml/provider_paths.xml

    9. Put analytics code in:
        + dev/res/xml/app_tracker.xml
        + prod/res/xml/app_tracker.xml

============================================= TIPS =================================================

    1. All project configuration is located in build.gradle
    2. Library dependency configuration is located in buildSystem/dependencies.gradle.
    3. keystore's is located in buildSystem/*


