import os
from dotenv import load_dotenv
from sqlalchemy import create_engine
from sqlalchemy.orm import DeclarativeBase

class Model(DeclarativeBase):
    pass

load_dotenv()
engine = create_engine(os.getenv("DATABASE_URL"))
print('Database URL:', os.environ.get('DATABASE_URL'))