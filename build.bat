@echo off
echo Building EMI Calculator App...
echo.

REM Clean previous builds
call gradlew.bat clean

REM Build the debug APK
call gradlew.bat assembleDebug

echo.
echo Build complete!
echo APK location: app\build\outputs\apk\debug\app-debug.apk
echo.

pause

# Note: This script assumes you have Gradle Wrapper (gradlew.bat) in your project root.