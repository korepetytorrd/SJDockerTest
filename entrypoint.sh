#!/bin/sh
# Uruchom H2 w tle (przekierowując logi do /dev/null)
nohup java -cp app.jar org.h2.tools.Server -tcp -web -ifNotExists >/dev/null 2>&1 &

# Uruchom aplikację Swing z obsługą GUI
exec java -Dsun.java2d.opengl=true -jar app.jar