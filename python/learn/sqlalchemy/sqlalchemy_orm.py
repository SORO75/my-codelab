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


