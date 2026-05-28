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




    