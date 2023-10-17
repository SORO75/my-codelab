"""
BASIC
	Installation
			-Installtionsverzeichnis

			- Path Variable
============================			
Basic Syntax
============================

"""
#Operatoren

n1 = 1
n2 = 2
n3 = 3

l1 = 'a'
l2 = 'b'
l3 = 'c'

 
print ('Eins hoch Zwei ist:', n1 ** n2)
print ('Zwei hoch Drei ist:' ,n2 ** n3)
print ('Negative Eins:', -n1)
print ('Produkt von Zwei und Drei:', n2 * n3)
print ('Quotient von Zwei und Drei:', n2 / n3)
print ('Rest (Modulo) bei ganzzahliger Division Zwei durch Drei:', n2%n3)
print ('Ganzzahlige Division von Acht durch Drei ', 8//3)


#######################
# numerische Datentypen
########################

# Konvertierung
print (float (33))
print (bool (13))
print (int(13.55))

print (1_0_0)

#bit-Operationen
#UND
print (107 & 25)

#ODER
print (107 | 25)

#XOR
print (107 ^ 25)


#Boolische Werte
x = True
y = False

print (not x)
print (x and y)
print (x or y)

print (bool(0.0))
print (bool(False))
print (bool(''))
print (bool())
print (bool([]))
print (bool({}))
print (bool(set()))
print (not bool(set()))





# Kontrollstrukturen

	# if Anweisung

x=2

if x == 1: 
    print("x hat den Wert 1")
elif x == 2: 
    print("x hat den Wert 2")
else: 
    print("Fehler: Der Wert von x ist weder 1 noch 2")

#bedinte Ausdruck (conditional expression)
# A id Bedingung else B
var = (20 if x == 1 else 30)
print (var)
#

# Schleifen
    #while Schleife


#version1
i = 1
while i < 9:
    print(i)
    i += 1
  
#while Schleife mit else

i = 1
while i < 9:
    print(i)
    i += 1
else: print ('Die Schleife wurde beendet')    


# while Schleife mit breack und contienue

#with break
import random
secret = random.randint(1,10)
i=0
attempt = 42
print (secret)
while i<=3:
    attempt = int(input('Guess: '))
    i= i+1
    if attempt==secret:
        print ('Richtig, die Zahl ist :', attempt)
        break
    print("Versuchen Sie noch mal ")
print ("Spiel ist zu Ende")


#with break
#variante 2
import random
secret = random.randint(1,10)
i=0
attempt = 42
print (secret)
while i<=5:
    attempt =random.randint(1,10)
    i= i+1
    if attempt==secret:
        print ('Richtig, die Zahl ist :', attempt)
        break
    print("Die Zahl", attempt, "ist falsch")
print ("Spiel ist zu Ende")


# for schleife
for i in [1,2,3]:
    print (i)
    
#mit String
for i in "Hallo":
    print (i)
    
#with Dictionary	

kids={'old':'Christoper', 'jung':'Theodor'}
for pair in kids.items():
    print(pair)
    
kids={'old':'Christoper', 'jung':'Theodor'}
for k,v in kids.items():
    print('key=',k, ', value=', v)



#For Schleife als Zaelschleife
#range (stop)
for i in range (5):
    print (i)

#range (start, stop)
for i in range (1,6):
    print (i)
    
#range (start, stop, step)
for i in range (1,11,2):
    print (i)


#pass- Anweisung
wert=5
if wert == 5:
    pass
else:
    print ('hier ist der neächten Schritt')
    
# for schleife mit break
tiere = ['cat','dog','bird','cow']
for t in tiere:
	if t == 'bird':
        break
    print (t)
    

# for schleife mit continue

tiere = ['cat','dog','bird','cow']
for t in tiere:
	if t == 'bird':
        continue
    print (t)





##################################################
		Dateienoperationen
##################################################

#neue Datei erzeugen
f = open ("neueDatei.txt", "x")
f.write ('Erster Text') 
f.close()         
   
f = open ("neueDatei.txt", "r")
print (f.read())
f.close
         
   
#überschreibt komplett die Datei     
f = open ("neueDatei.txt", "w")
f.write ('Der erste Text wird überschrieben.') 
f.close()         
   
f = open ("neueDatei.txt", "r")
print (f.read())
f.close

# Text anhängen
f = open ("neueDatei.txt", "a")
f.write ('Hier ist noch ein Satz.') 
f.close()         
   
f = open ("neueDatei.txt", "r")
print (f.read())
f.close

#Datei löschen
import os
os.remove('neueDatei.txt')

# check, ob die Datei vorhanden ist und dann löschen
import os
if os.path.exists('neueDatei.txt'):
	os.remove('neueDatei.txt')
else:
	print ('Die gesuchte Datei ist nicht vorhanden')

#Datei lesen, nur erste Zeile ausgeben
import os
if os.path.exists('words.txt'):
	f = open ('words.txt', "r")
	print (f.readline())
	f.close
else:
	print ('Die gesuchte Datei ist nicht vorhanden')

#Datei öffnen, wenn vorhanden ist. Zeile für Zeile ausgeben
import os
if os.path.exists('words.txt'):
    f = open ('words.txt', "r")
    print ('Inhalt aus der Datei: ')
    for zeile in f:
        print (f.readline())
    f.close
else:
    print ('Die gesuchte Datei ist nicht vorhanden')
    

#Variante2
# import os
if os.path.exists('words.txt'):
    f = open ('words.txt', "r")
    print ('Inhalt aus der Datei: ')
    for zeile in f:
        print (zeile)
    f.close
else:
    print ('Die gesuchte Datei ist nicht vorhanden')

# ein komplexeres Programm mit tests und Exception
import os

def create_file(filename):
	try:
		with open(filename, 'w') as f:
			f.write('Hello, world!\n')
		print("File " + filename + " created successfully.")
	except IOError:
		print("Error: could not create file " + filename)

def read_file(filename):
	try:
		with open(filename, 'r') as f:
			contents = f.read()
			print(contents)
	except IOError:
		print("Error: could not read file " + filename)

def append_file(filename, text):
	try:
		with open(filename, 'a') as f:
			f.write(text)
		print("Text appended to file " + filename + " successfully.")
	except IOError:
		print("Error: could not append to file " + filename)

def rename_file(filename, new_filename):
	try:
		os.rename(filename, new_filename)
		print("File " + filename + " renamed to " + new_filename + " successfully.")
	except IOError:
		print("Error: could not rename file " + filename)

def delete_file(filename):
	try:
		os.remove(filename)
		print("File " + filename + " deleted successfully.")
	except IOError:
		print("Error: could not delete file " + filename)


if __name__ == '__main__':
	filename = "example.txt"
	new_filename = "new_example.txt"

	create_file(filename)
	read_file(filename)
	append_file(filename, "This is some additional text.\n")
	read_file(filename)
	rename_file(filename, new_filename)
	read_file(new_filename)
	delete_file(new_filename)


'''
Module
pickle
shelve

kompelexes Programm mit os, pickle, shelve etc Programm schreiben für datei beiabeitung 

Datei CRUD
Datei finden

Dateityp bestimmen
Prüfen, ob die DAtei leer ist
Dateityp ändern
datei öffenen
datei auslesen (komplett, schrittweise (line))
datei schliesen
Ihnalt ausgeben
Inhalt ändern
Dateiname ändern
DAteikopie anlegen
Datei löschen
Datei archivieren
split methode
strip methode
als dictionary auslesen
formatierter Text in die Datei schreiben



----


open(filename, [mode, buffering, encoding, errors, newline]) 

Daeimethoden
.close()
.fileno()
.flush()
.isatty()
.next()
read([size])
.readline([size])
.tell
truncate([size])
write(str)
writelines(iterable)


# String Methoden





.split([sep, maxsplit])
.rsplit
.splitlines
.partition
.rpartition


.find
rfind
.index
rindex
count


.replace
.lower
.upper.
swapcase
.capitalize
casefold
title


strip
lstrip
rstrip


format

'''
###################################################
# 			Sequenzille Datentypen
###################################################

#List	
# Tuple
# str
# bytes


lst =[32, 17, "Kind", 'Zwei',13.43, 0.04, 'a','b' ]
print (type(lst))	

print (3 not in lst)	
print (32 in lst)	
print (lst.count(3))
print (len(lst))
	
lst.append('append')
print (lst)	

lst.extend ('extend')
print (lst)	
	
lst.reverse()
print (lst)
	
lst.sort()
print(lst)		


strg ='das ist ein Testtext'
	
print ('e' in strg)
print (strg*5)



"""
help funktion



	Variable and Data Types (muttable und ummutable datentypen)
	Instanzen eines mutablen Typs sind dazu in der Lage, nach ihrer Erzeugung andere Werte anzunehmen,
	 während dies bei immutablen Datentypen nicht der Fall ist.

	Immutable:
		Numbers (Integer, Float, Complex, Decimal, Rational & Booleans)
		Tuples
		Strings
		Frozen Sets
	Muttable:
		Lists
		Sets
		Dictionaries

	Conditionals&Operation
	Looping
	Type Casting
	Exception Handling
	Funktions
	Builtin Functions

	'''
	#Lists



	# Methoden
	.append(x)
    .extend(t)
    .insert(x)
    .pop([i])
    .remove(x)
    .reverse()
    .sort([key, reverse])
	Tupels
	Sets
	Dictionaries
	Virtual environment

Datenstres and Algorithms
	Arrays
	Linked Lists
	Heaps
	Stacks
	Queues
	Hash Tables

	Binary Search Tree
	Recurision
	Sorting Algorithms

ADVANCED TOPICS
	Regular Expression
	Decorators
	Iterators
	Closures
	Generatoren
	Lamdas
	Threading
	map,reduce, filters
	Magic Methods
	List compehensions
	OOP
		Classes
		Dataclasses
		Inheritance
		Interfaces
		Properties
		Static & Class Methods
		Dunder

	Modules
		Builtin
		Custom
	
	GUI
	
	Automation
		File Manilupation
		Web Scrapping
		GUI AAutomations
		Network Automation

	Package vs Environment Managment
		Anaconde vs MiniConda
		PyPi
		pip
	
	Testing
		pytest
		unitest/PyUnit
		nose
		doctest
		End-to-End testing
		Load testing
		Seleni
	
	Logging
		Setting UP Logging
		Differnt Types of Handlers

WEB FRAMEWORKS
	Django
	Flask
	FastAPI

	gevent
	aiohttp
	Sanic
	Tornado

DATA SCIENCE
	NumPy
	Pandas
	Matplotlib
	Seaborn
	Scikit-learn
	TensorFlow
	Pytorch


























































Internetseiten:









Linux

installierte Version
upgrade auf neue Version
PATH erweitern
Wie sieht am besten mit Berechtigung?


Editoren:


Projektorlage einfach
-------------------------------------
README.rst
LICENSE
setup.py
requirements.txt
my_projekt/__init__.py
my_projekt/core.py
my_projekt/helpers.py
docs/conf.py
docs/index.rst
tests/test_basic.py
tests/test_advanced.py

----------------------------------------
https://docs.python-guide.org/


https://www.delftstack.com/de/howto/python/python-project-structure/
https://bodo-schoenfeld.de/python/




Python3 --version



—————
Flask
fastAPI
Pyramide



WebAPI
WEbsocket

REST
15.06.2023

Python Installtionsverzeichnis
PYPATH wo gespeichert und wie man korrigiert

Wie findet man Interpreter von Python (Mac, Windows, linux)


None 
Pass
Variable zurücksetzen 
Eigene Module ( registrieren bei pip, was erlaubt)
Main Funktion 
Zwei Unterlinien bei Variablennamen
Dateien Methoden 
Methoden vs Funktionen
Rückabewert

Local, nonlocal, global Variablen
Statische Variablen ( Klassenvariablen- eine Wert für mehrere Instanzen))
Statische Methoden
Statische Klassen


Konstruktiv (braucht man immer Methode __init__(self)?)

	Mehrere Konstruktionen bei einer Klasse
	getter, setter
	private, public, protected


Vererbung
	Variablen in patentklasse ändern
	Überschrieben von vererbten Methoden
	wie springt man von Klasse zur Klasse
	Mehrfachvererbung (von links nach rechts)
Diamanten Problem bei Mehrfachvererbung

Exception

Multitraiding
	Paket threading
	Threadmethoden
		start()
		join()


partial
lambda
map
filter



——————
logging
Logger
FileHandler
Unterschied zur Java (Ähnliches wie log4j)

Abstracte klasse
__metaclass__


———————
Enum

__sub__
__call__



Operatoren überladen
Liste mit ränge, random erstellen


regex
Interatoren, Generatoren
Yield
Argumente unpacking

Dekorator
Macros
Wrapping

Class object
@Classmethod
@staticmethod



___________________
Socket paket 
socket.AF_—
socket.SOCK_STREAM


____________________

Time module 
Knoten Synchronisierung

—————————————

JSON Module 
Daten Speicherung
Request packet über pip3


____________
Modul WebBrowser
Queue
Subprocess
_____________

hmac


Parameter bei Script ausführen
Systemargumente


Datentyp bei Methoden Argumenten eingaben und ausgaben

@dataclasses  (Dataentity)
(frozen=TRUe, False)




________________-
Asyncio packet
Await
Asynchrone Queues und Tanks
Batch Bearbeitung?
 Producer und Consumer 

Event handling

Assert Verwendung 



For I in range

Format String


Debugging mit PYcharm




Flask

Properties
Konstantes und Konfiguration

sqlalchemy

Session
Coockies

SSL HTTPS
Multitread

Pokemon Architecture

Authentification

Login Seite
Jsonify
Request
Response


Unittest

REST API
Fast restplus


Swagger
Blueprints ?

Namespaces

———————
Entity klassen
Business logic Klassen
APIs

JSON WebToken

Deployment
Gunicorn Server

===================================================
Datei typ bestimmen
Datei anzeigen
Datei in anderem Format speichern
Dateityp validieren
Daten Gültigkeit prüfen (Vor- Nachname, plz, email)
Duplikate suchen
Formulare generieren (PDF Formulare)
Daten aus Formulare in JSON, XML und Datenbank übertragen
Nach der Übertragung Datei markieren und archivieren

Beim Absturz übertragene Daten merken und beim Bedarf Rollback ausführen
Daten aus Datenbank löschen, ersetzen, korrigieren
Daten in mehrere DBs importieren.
Bei erneutem Versuch Daten zu importieren Fehler werfen
Datei mit einem Hash schützen - Datei mit Daten unmanipulierbar machen


Datei finden, text nach bestimmte Wörter durchsuchen
Datei umwandeln, text, per, image, excel
Csv lesen in json und xml umwandeln
Datei umbenennen, kopieren, löschen, updaten
Bestimmte Datei archivieren
Kopie von Datei erstellen mit neuemNamen_ Kopie
Dateityp bestimmen
Macrodaten auslesen


ISS tracking
GPS Tracing
GIS Daten
WebAPI



Absolute Pfade
QR Kode Generierung



Nomen und Anfang des Satzes groß schreiben
Exeption werfen, abfangen behandeln


Test eines Programms

Iterator vs Decorator (iter und next)

Dander method
String Bearbeitung

Decorator
Wrapping



Virtuelle Umgebung
Pass
Requerment Datei
config Datei
Propertydatei

Projektstruktur Python Projekt
	flask
	Django
	ML Projekt
	PyTorch
	PySpark
	tensorflow
	Numpy
	nameko





XML, JSON
REST


Verteilte Anwendung


.readline
.split



Objektfabrik
Observer
Singleton
Mehrfache Verarmung


Recurison aufbauen (wann ist sinnvoll)



Algoritmen:
Sortieralgorimen

OOP
Klassen
	Vererbung
	Interface
	Abstractklassen
	Statische Methoden
	überladen
	überschreiben
	getter, setter
	constructor, destructor
	statische Klasse

	
	
Funktionale Programming


GUI:
Tkinter
PyQT5


DBs
Standard DB


WEB
	eigebaute Webserver

IDE
PIP
Logging module
Sys Module




Python varianten…












Fragen:
Statische Klassen und Methoden
Interfaces
Kopie oder Referenz? Wie erkennen?
Werden Objekte automatisch in einer Liste angeordnet?
Liste von Objekten durchgehen… Duplikate zählen

Listen Methoden mit Index und mit Werten… kann man .remove(wert) mehrere Werten entfernen?
.extend mit einem wert?
Mehrfaches Vererbung
Versteht Shell for-Schleife listen auch so wie slice Python


Globale und lokale Variablen

Funktionen
Paramterstandrtwerte, 

Python Klassen

Keinen Statische Funktionsvariablen
Attributdictionary __dict__ 

x.__dict__  die Attribute werden als eine Liste ausgegeben…

Methode vs Funktion — keine Rückgabe bei der Methode?

__Methodename__  doppelte unterstrich == private methode

Methodenheader @—

@staticmethod

@classmethod

@property

Fabrikmethoden


Interface?
Statische Klassen?  
 Man kann direkt auf einer Methode in der Klassendefinition zugreifen bzw. eine Instanz erzeugen und als Instanzmethode nutzen


Getter setter? Man kann generieren, oder selber schreiben…
!!!! Ausprobieren
IDE testen…

Datenkapselung
Keinen expliziten Constructor, Destrucktor 
Konstruktur __init__ man kann als Konstruktion sehen

__del__ als Destruktor


	

	 

"""