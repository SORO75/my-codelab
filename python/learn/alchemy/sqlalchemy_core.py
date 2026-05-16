from sqlalchemy import create_engine, Column, Integer, String, MetaData, Table, select
from sqlalchemy.orm import sessionmaker, declarative_base
# Create an engine that stores data in the local directory's
# sqlalchemy_example
engine = create_engine('sqlite:///sqlite/sqlalchemy_example.db')

#Base = declarative_base()


'''
Workflow 
Datenbank anlegen
Datenbank check
Datenbank definieren
Datenbankverbindung chek
Tabellen mit SQL erstellen
Tabellen check
Insert von 3 Werten über Variablen
Tabellencheck
Tabelleänderung 
Tabelle check
Typ ändern - check
Werte selecktieren - check
werte updaten - check
werte löschen - check
automatische Füllung den Tabellen mit Fake
tabellen check

Transaction
alle werte löschen -check
alle Tabellen löschen -check



Table mit Metadate
SQL Expression
Joins
Order by ; Group By
Update Table
Reflecting existing Table

Execute()
somplited Statement
Insereting Data
Deleting Data
Transactions
 Insert..ReTURNING
 CTE

Basis model definition
CRUD Operation with ORM
Querying with ORM
limiting Results
subqueries
One-to-Many Relationships
Many-to-Many Relationships
inheritnace Mapping

Joined Table inheritance
Eageer Loading
LazyLoading
Joins Optimisation
Query cashing
query execution plan
bulk operation
Proper indexing
connection pooling
profiling queries
Session management
Transactions management
concurrence and isolation levels

migration with Alembic

asynchron I/O
CRUD with Async

Best practices for Async

Testing with SQLalchemy
    Units Tests
    Transactions Tests
    Integration Tests
Mocking  Database Calls
FastAPI Integration
FastAPI with SQLAlchemy Async
Pydantic Integration
Design Pattern best Practices
    Unit of Work Pattern
    Data Mapper Pattern
    Query Object Pattern
    Lazy Loading Pattern
Perfomance Monitoring and Optimization
Profiling  Database Calls
    Unit of Work Pattern
    using SQL Echo
    Time Logging
    Profiling Extension
    N+1 Query Problem
Connection Pooling and Management
Monitoring
Security Best Practices
Preventing SQL Injection
Authentication and Authorization
Application Scaling
    Vertical
    Horizontal
    Load Balancing
    Sharding
    ReadReplicas
    Cashing
    RestAPI with SQLAlchemy
    Simpel Bog engine
    Data Analysis APplication
Debugging
    Debug Logging
Handling Connections Fehler

Aus einer Datenbank Klassen generieren



'''