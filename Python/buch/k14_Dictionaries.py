# Paar-wörter befinden sich in der Datei words.txt
#keys sollten immutabel sein, die Werte können beliebig sein


woerterbuch = {
              "Germany" : "Deutschland",
              "Spain" : "Spanien",
              "France" : "Frankreich"
              }


for key in woerterbuch:
    print (key)























'''import os

if os.path.exists('words.txt'):
    f = open ('words.txt', "r")
    print ('Inhalt aus der Datei: ')
    for zeile in f:
        print (zeile)
    f.close
else:
    print ('Die gesuchte Datei ist nicht vorhanden')

    ''''