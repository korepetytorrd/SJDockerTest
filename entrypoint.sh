#!/bin/sh

# Uruchom H2 w tle i loguj do pliku (można potem podglądać)
nohup java -cp app.jar org.h2.tools.Server -tcp -web -ifNotExists > h2.log 2>&1 &

# Upewnij się, że DISPLAY jest ustawione
if [ -z "$DISPLAY" ]; then
  echo "Brak DISPLAY – GUI nie uruchomione"
  exit 1
fi

# Uruchom aplikację Swing
exec java -Dsun.java2d.opengl=true -jar app.jar
