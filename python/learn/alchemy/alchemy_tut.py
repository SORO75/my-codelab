import sqlalchemy 
from sqlalchemy import create_engine, Column, Integer, String
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker


print(f"SQLalchemy version: {sqlalchemy.__version__}")

engine = create_engine('sqlite:///:alchemy_tutorial.db', echo=True)

# Create a decralative base class
Base = declarative_base()

#Define a Book model
class Book(Base):
    __tablename__ = 'books'

    id = Column(Integer, primary_key=True)
    title = Column(String(255), nullable=False)
    author = Column(String(255), nullable=False)
    genre = Column(String(100))

    def __repr__(self) -> str:
        return f"<Book(title='{self.title}', author='{self.author}', genre='{self.genre}')>"

#Create the tables in the database
Base.metadata.create_all(engine)

# Create a session
Session = sessionmaker(bind=engine)
session = Session()

# Create a new book instance
new_book = Book(title='The Great Gatsby', author='F. Scott Fitzgerald', genre='Fiction')

# Add the book to the session and commit
session.add(new_book)
session.commit()

# Query the database
books = session.query(Book).all()
for book in books:
    print(book)

session.close()    


