from sqlalchemy import Table, Column, Integer, String, MetaData, select, desc, asc

metadata = MetaData()
users_table = Table('users', metadata,
    Column('id', Integer, primary_key=True),
    Column('name', String(255), nullable=False),
    Column('surname', String(255), nullable=False),
    Column('email', String(255), unique=True)
)


# SELECT * FROM users
stmt = select(users_table)
print(stmt)

# SELECT name, surname FROM users WHERE name = 'ed'
stmt = select(users_table.c.name, users_table.c.surname).where(users_table.c.name == 'ed')
print(stmt)

address_table = Table('addresses', metadata,
    Column('id', Integer, primary_key=True),
    Column('user_id', Integer),
    Column('email_address', String(255), nullable=False)
)

# Join users and addresses
stmt = select(users_table, address_table).join(address_table, users_table.c.id == address_table.c.user_id)  
print(stmt)

# Order by name ascending
stmt = select(users_table).order_by(asc(users_table.c.name))
print(stmt)

# Order by surname descending
stmt = select(users_table).order_by(desc(users_table.c.surname))
print(stmt)

