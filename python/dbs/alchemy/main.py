from sqlalchemy import create_engine, Column, Integer, String, ForeignKey
from sqlalchemy.orm import sessionmaker, declarative_base
# Create an engine that stores data in the local directory's
# sqlalchemy_example
engine = create_engine('sqlite:///sqlalchemy_example.db')

Base = declarative_base()

# Define the Book model
class Book(Base):
    __tablename__ = 'books'
    id = Column(Integer, primary_key=True)
    title = Column(String(250), nullable=False)
    author = Column(String(250), nullable=False)
    genre = Column(String(100))

    def __repr__(self):
        return f"<Book(title ='{self.title}', author ='{self.author}', genre ='{self.genre}')>"

# Create all tables in the engine
Base.metadata.create_all(engine)

# Create a session
Session = sessionmaker(bind=engine)
session = Session()

# Add a book
new_book = Book(title="To Kill a Mockngbird", author="Harper Lee", genre="Fiction")
session.add(new_book)
session.commit()

# Query the book
book = session.query(Book).filter_by(title="To Kill a Mockngbird").first()
print(book)

# Close the session
session.close()

