from sqlalchemy import create_engine, MetaData, Table, Column, Integer, String, text
from sqlalchemy import inspect


#Initialize the engine for a locla SQLite database
engine = create_engine('sqlite:///:alchemy_ddl.db', echo=True)