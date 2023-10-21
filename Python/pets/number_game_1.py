import random

secret = random.randint(1,11)
i=0
attempt = 42
print (secret)
while i<4:
    attempt = int(input('Guess: '))
    i= i+1
    if attempt==secret:
        print ('Richtig, die Zahl ist :', attempt)
        break
    print("Versuchen Sie noch mal ")
    
print ("Spiel ist zu Ende")