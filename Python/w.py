import os
if os.path.exists('words.txt'):
    f = open ('words.txt', "r")
    print ('Inhalt aus der Datei: ')
    for zeile in f:
        print (zeile)
    f.close
else:
    print ('Die gesuchte Datei ist nicht vorhanden')
