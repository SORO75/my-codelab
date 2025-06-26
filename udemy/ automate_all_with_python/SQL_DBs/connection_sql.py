import sqlite3

#Establish a connection and create a cursor
con = sqlite3.connect('database.db')
cur = con.cursor()

cur.execute("SELECT * FROM 'ips' ORDER BY asn")
print(cur.fetchall())


cur.execute("SELECT address, asn  FROM 'ips' ORDER BY asn")
print(cur.fetchall())

cur.execute("SELECT * FROM 'ips' WHERE asn <300")
print(cur.fetchall())


########################
