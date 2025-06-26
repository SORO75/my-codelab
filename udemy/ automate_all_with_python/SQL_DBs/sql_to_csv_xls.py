import sqlite3
import pandas as pd

#Establish a connection and create a cursor
con = sqlite3.connect('database.db')
cur = con.cursor()

df =pd.read_sql_query("SELECT * FROM 'ips' ORDER BY asn", con)
print(df)

df.to_csv('database.csv', index=None)
df.to_excel('database.xlsx', index=None)