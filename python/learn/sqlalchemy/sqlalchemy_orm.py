from sqlachemy import Column, Integer, String, creare_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import relationship, sessionmaker

Base = declarative_base()


class User(Base):
    __tablename__ = 'users_table'

    id = Column(Integer, primary_key=True)
    name = Column(String(50))
    email = Column(String(100))

  def __repr__(self):
        return f"<User(id={self.id}, name='{self.name}', email='{self.email}')>"

class Address(Base):
    __tablename__ = 'addresses_table'

    id = Column(Integer, primary_key=True)
    email_address = Column(String(100), nullable=False)
    user_id = Column(Integer, ForeignKey('users_table.id'))
    user = relationship("User", back_populates="addresses")

User.addresses = relationship("Address", order_by=Address.id, back_populates="user")




engine = create_engine('sqlite:///alchemy_tutorial.db', echo=True)
Base.metadata.create_all(engine)

Sessin = sessionmaker(bind=engine)
session = Session()

new_user = User(name='John Doe', email='john.doe@example.com')
session.add(new_user)
session.commit()

users = session.query(User).all()

user = session.query(User).filter_by(name='John Doe').first()
print(user)


#------------------------------
# UPDATE
#-------------------------------

user = sessionquery(User).filter_by(name='John Doe').first()
user.fullname = 'Johnathan Doe'
session.commit()

#------------------------------
# DELETE
#-------------------------------
user = session.query(User).filter_by(name='Johnathan Doe').first()
session.delete(user)
session.commit()


#------------------------------
# Quering with ORM  
#-------------------------------
#Select all users
users = session.query(User).all()
for user in users:
    print(user)

#Select users with specific name
users_named_john = session.query(User).filter_by(name='John Doe').all()
for user in users_named_john:
    print(user)

#ordering results
# Select all users ordered by name
users_ordered = session.query(User).order_by(User.name).all()
for user in users_ordered:
    print(user)

#limiting results
# Select the first 5 users
first_five_users = session.query(User).limit(5).all()
for user in first_five_users:
    print(user)

#counting results
# Count the number of users
user_count = session.query(User).count()
print(f'Total number of users: {user_count}')   

#joining tables
# Select all users and their addresses
users_with_addresses = session.query(User).join(Address).all()
for user in users_with_addresses:
    print(user)
    for address in user.addresses:
        print(f'  {address.email_address}') 

# Aggregation
# Count the number of addresses for each user
from sqlalchemy import func
address_counts = session.query(User.name, func.count(Address.id)).join(Address).group_by(User.name).all()
for name, count in address_counts:
    print(f'{name} has {count} addresses')  

#subqueries
# Select users who have more than 2 addresses
subquery = session.query(Address.user_id, func.count(Address.id).label('address_count')).group_by(Address.user_id).subquery()
users_with_many_addresses = session.query(User).join(subquery, User.id == subquery.c.user_id).filter(subquery.c.address_count > 2).all()
for user in users_with_many_addresses:
    print(user)

#eager loading (to avoid N+1 problem when accessing related objects)
# Select all users and their addresses using eager loading
# This will load all users and their addresses in a single query, avoiding the N+1 problem
users_with_addresses_eager = session.query(User).options(joinedload(User.addresses)).all()
for user in users_with_addresses_eager:
    print(user)
    for address in user.addresses:
        print(f'  {address.email_address}')


#lazy loading (default behavior, related objects are loaded on demand when accessed)
# Select a user and access their addresses (this will trigger a separate query to load the addresses)
user = session.query(User).filter_by(name='John Doe').first()
print(user)
for address in user.addresses:  # This will trigger a separate query to load the addresses
    print(f'  {address.email_address}') 
    








session.close() # Close the session when done to release database connections and resources 


