import sqlite3
from fpdf import FPDF

#Establish a connection and create a cursor
con = sqlite3.connect('database.db')
cur = con.cursor()


cur.execute("PRAGMA table_info(ips)")
columns = cur.fetchall()
print(columns)


pdf = FPDF(orientation='P', unit='pt', format='A4')
pdf.add_page()

for column in columns:
    pdf.set_font(family='Times', style='B', size=14)
    pdf.cell(w=100, h=25, txt=column[1], border=1)

pdf.ln()

cur.execute("SELECT * FROM 'ips'")
rows = cur.fetchall()

for row in rows:
    for element in row:
        pdf.set_font(family='Times', size=14)
        pdf.cell(w=100, h=30, txt=str(element), border=1)
    pdf.ln()

pdf.output('output.pdf')