import yagmail
import os
import time
from datetime import datetime as dt

sender = 'jflsjsljsk@gmail.com'
receiver = '2lslfdm10@gmail.com'

subject = "This is the subject!"


contents = """
Here is the content of the email! 
Hi!
"""
while True:
    now = dt.now()
    if dt.now().hour == 13 and now.minute == 15:
        yag = yagmail.SMTP(user=sender, password=os.getenv('PASSWORD'))
        yag.send(to=receiver, subject=subject, contents=contents)
        print("Email Sent!")
        time.sleep(60)
