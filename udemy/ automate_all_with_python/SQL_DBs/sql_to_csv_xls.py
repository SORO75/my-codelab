import sqlite3

#Establish a connection and create a cursor
con = sqlite3.connect('database.db')
cur = con.cursor()

cur.execute("SELECT * FROM 'ips' ORDER BY asn")
print(cur.fetchall())
