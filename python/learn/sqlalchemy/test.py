from sqlalchemy import Column, Integer, String, create_engine, MetaData, Table, insert
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.exc import SQLAlchemyError  
from sqlalchemy.orm import relationship


Base = declarative_base()

#one-to-many relationship example

class Parent (Base):
    __tablename__ = 'parent'
    id = Column(Integer, primary_key=True)
    name = Column(String(50))
    children = relationship("Child", back_populates="parent")

class Child (Base):
    __tablename__ = 'child'
    id = Column(Integer, primary_key=True)
    name = Column(String(50))
    parent_id = Column(Integer, ForeignKey('parent.id'))
    parent = relationship("Parent", back_populates="children")


    # Create a parent with two children
parent = Parent(name='Parent Name')
child1 = Child(name='Child 1', parent=parent)
child2 = Child(name='Child 2', parent=parent)   
parent.children = [child1, child2]


session.add(parent)
session.commit()

#Query the parent and its children
queried_parent = session.query(Parent).filter_by(name='Parent Name').first()
print(f"Parent: {queried_parent.name}")
for child in queried_parent.children:
    print(f"Child: {child.name}")


#Many-to-many relationship example

#Associatiion table
student_course = Table('student_course', Base.metadata,
    Column('student_id', Integer, ForeignKey('student.id')),
    Column('course_id', Integer, ForeignKey('course.id'))
)

class Student(Base):
    __tablename__ = 'student'
    id = Column(Integer, primary_key=True)
    name = Column(String(50))
    courses = relationship("Course", secondary=student_course, back_populates="students")   


class Course(Base):
    __tablename__ = 'course'
    id = Column(Integer, primary_key=True)
    name = Column(String(50))
    students = relationship("Student", secondary=student_course, back_populates="courses")  


# Create a student and two courses
student1 = Student(name='Student Name1')
student2 = Student(name='Student Name2')
course1 = Course(name='Course 1')
course2 = Course(name='Course 2')

enroll students in courses
student1.courses = [course1, course2]
student2.courses = [course1]

session.add_all([student1, student2, course1, course2])
session.commit()

#Query the student and their courses
queried_student = session.query(Student).filter_by(name='Student Name1').first()
print(f"Student: {queried_student.name}")
for course in queried_student.courses:
    print(f"Course: {course.name}")


# Inheritance example

class Employee (Base):
    __tablename__ = 'employees'
    id = Column(Integer, primary_key=True)
    name = Column(String(50))
    type = Column(String(50))

    __mapper_args__ = {
        'polymorphic_identity': 'employee',
        'polymorphic_on': type
    }

class Manager(Employee):
    department = Column(String(50))
    __mapper_args__ = {
        'polymorphic_identity': 'manager'
    }

class Engineer(Employee):
    programming_language = Column(String(50))
    __mapper_args__ = {
        'polymorphic_identity': 'engineer'
    }

# Create a manager and an engineer

manager = Manager(name='Manager Name', department='Sales')    
engineer = Engineer(name='Engineer Name', programming_language='Python')    

session.add_all([manager, engineer])
session.commit()

#Query all employees
employees = session.query(Employee).all()
for employee in employees:
    if isinstance(employee, Manager):
        print(f"Manager: {employee.name}, Department: {employee.department}")
    elif isinstance(employee, Engineer):
        print(f"Engineer: {employee.name}, Programming Language: {employee.programming_language}")

'''delete tabele employees and all its data'''
session.query(Employee).delete()
session.commit()

 #Joinded Table inheritance example

class Employee(Base):
    __tablename__ = 'employee'
    id = Column(Integer, primary_key=True)
    name = Column(String(50))
    type = Column(String(50))

    __mapper_args__ = {
        'polymorphic_identity': 'employee',
        'polymorphic_on': type
    }

class Manager(Employee):
    __tablename__ = 'manager'
    id = Column(Integer, ForeignKey('employee.id'), primary_key=True)
    department = Column(String(50))
    
    __mapper_args__ = {
        'polymorphic_identity': 'manager'
    }

class Engineer(Employee):
    __tablename__ = 'engineer'
    id = Column(Integer, ForeignKey('employee.id'), primary_key=True)
    programming_language = Column(String(50))
    
    __mapper_args__ = {
        'polymorphic_identity': 'engineer'
    }


# Lazy Loading example
user = Seddion.query (User).all()
for user in users:
    print(f"User: {user.name}")
    
#Eager Loading example
# Joined eager loading
user = session.query(User).options(joinedload(User.addresses)).all()
for user in users:
    print(f"User: {user.name}")
    for address in user.addresses:
        print(f"Address: {address.email_address}")

# Subquery eager loading
users = session.query(User).options(subqueryload(User.addresses)).all()
for user in users:
    print(f"User: {user.name}")
    for address in user.addresses:
        print(f"Address: {address.email_address}")

# Select in eager loading
users = session.query(User).options(selectinload(User.addresses)).all()
for user in users:
    print(f"User: {user.name}")
    for address in user.addresses:
        print(f"Address: {address.email_address}")

# Caching example
#Query Caching with Dogpile Cache
region = make_region().configure('dogpile.cache.memory', expiration_time=3600, arguments={'url': ['localhost:11211']})

@region.cache_on_arguments()
def get_user_by_name(name):
    return session.query(User).filter_by(name=name).first()

user = get_user_by_name('John Doe')
print(f"User: {user.name}")


# Result Set Caching example
import pickle
from functools import lru_cache



@lru_cache(maxsize=128)
def get_all_users():
    users = session.query(User).all()
    return pickle.dumps([user.__dict__ for user in users])


# Usage
users = pickle.loads(get_all_users())


#Bulk Operations example

# Inefficient: Individual updates
for user in users:
    user.status = 'active'
    session.add(user)

# Efficient: Bulk update
session.query(User).filter(User.id.in_([u.id for u in users])).update({"status": "active"}, synchronize_session=False)


#proper indexing example
from sqlalchemy import Index

class User(Base):
    __tablename__ = 'users'
    id = Column(Integer, primary_key=True)
    name = Column(String(50))
    email = Column(String(50))

    # Create an index on the email column
    __table_args__ = (Index('ix_user_email', 'email'),)


# Profiling and Optimization example
from sqlalchemy import event
from sqlalchemy.engine import Engine

@event.listens_for(engine, "before_cursor_execute")
def before_cursor_execute(conn, cursor, statement, parameters, context, executemany):
    conn.info.setdefault('query_start_time', []).append(time.time())
    print("Start Query: %s" % statement)


@event.listens_for(engine, "after_cursor_execute")
def after_cursor_execute(conn, cursor, statement, parameters, context, executemany):
    total = time.time() - conn.info['query_start_time'].pop(-1)
    print("Query Complete!")
    print("Total Time: %f" % total)

#####
#Session Lifecycle
session = Session()
try:
    #Add a new user
    new_user = User (name="John Doe", email="jondoe@example.com")
    session.add(new_user)

    #Modify an existing user
    user = session.query(User).filter_by(name="Bob").first()
    user.email = 'bob_new@exampel.com'

    #Commit the transaction
    session.commit()
except:
    #If an error occurs, rollback the change
    session.rollback()
    raise
finally:
    #Close the session
    session.close()


#Transaction Management
#Commiting
session.add(user1)
session.add(user2)
session.commit()

#Rolling Back
try:
    session.add(user1)
    session.add(user2)
    session.commit()
except:
    session.rollback()
    raise
finally:
    session.close()

