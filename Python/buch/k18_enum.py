import enum
class Wochentag (enum.Enum):
    Montag = 1
    Dienstag =2
    Mittwoch = 3
    Donnerstag = 4
    Freitag = 5
    Samstag = 6
    Sonntag = 7


print('Wert vom Attribut EnumKlasse Wochentag (Samstag):',Wochentag.Samstag) 
print(Wochentag(6))  
print(Wochentag.Samstag.name) 


print ('hier werden alle Attribute von enum Klasse Wochentag aufgelistet:')
for tag in Wochentag: 
    print(tag)