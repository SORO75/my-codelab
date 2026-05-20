from sqlachemy import Column, Integer, String, creare_engine
from sqlalchemy.ext.declarative import declarative_base

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