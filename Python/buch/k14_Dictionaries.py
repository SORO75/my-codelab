# Paar-wörter befinden sich in der Datei words.txt
#keys sollten immutabel sein, die Werte können beliebig sein


woerterbuch = {
              "Germany" : "Deutschland",
              "Spain" : "Spanien",
              "France" : "Frankreich"
              }


print ('Die dictionary beinhaltet: ', len(woerterbuch), 'Elemente')
print (Auflistung aller keys in der Dictionary )

for key in woerterbuch:
    print (key)



print ('hier wird eine kopie von woerterbuch erzeugt')
woerterbuch2 = woerterbuch.copy()

print (woerterbuch2)

print ('hier wird Wert von Spain ausgegeben:', woerterbuch2['Spain'])

print (woerterbuch2.items())
for paar in woerterbuch2.items():
   print(paar)






print (woerterbuch2.keys())
print (woerterbuch2.values())




















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