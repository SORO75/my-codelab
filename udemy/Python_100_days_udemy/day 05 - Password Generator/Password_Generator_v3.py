import random
import string


def generate_password():
    # Benutzereingaben für Passwortlänge und Zeichenanzahl
    length = int(input("Wie lang soll das Passwort sein? "))
    num_letters = int(input("Wie viele Buchstaben soll das Passwort enthalten? "))
    num_digits = int(input("Wie viele Zahlen soll das Passwort enthalten? "))
    num_special = int(input("Wie viele Sonderzeichen soll das Passwort enthalten? "))

    # Überprüfen, ob die Gesamtlänge ausreicht
    if num_letters + num_digits + num_special > length:
        print("Die Summe der gewünschten Zeichenarten überschreitet die Passwortlänge.")
        return None

    # Generieren der verschiedenen Zeichengruppen
    letters = ''.join(random.choice(string.ascii_letters) for _ in range(num_letters))
    digits = ''.join(random.choice(string.digits) for _ in range(num_digits))
    specials = ''.join(random.choice(string.punctuation) for _ in range(num_special))

    # Erstellen des Grundpassworts aus den angegebenen Teilen
    password = letters + digits + specials

    # Füge zufällige Zeichen hinzu, falls die gewünschte Länge noch nicht erreicht ist
    if len(password) < length:
        remaining_chars = ''.join(random.choice(string.ascii_letters + string.digits + string.punctuation)
                                  for _ in range(length - len(password)))
        password += remaining_chars

    # Mische das Passwort, um die Reihenfolge zufällig zu gestalten
    password = ''.join(random.sample(password, len(password)))

    return password


# Beispielaufruf
print("Dein generiertes Passwort:", generate_password())
