from sqlalchemy.ext.asyncio import create_async_engine, AsyncSession
from sqlalchemy.orm import sessionmaker
from sqlalchemy import select

from learn.sqlalchemy.sqlalchemy_orm import User






# Create async engine
engine = create_async_engine('postgresql+asyncpg://postgres:1234@localhost:5432/postgres', echo=True)

#Create async session
async_session = sessionmaker(engine, expire_on_commit=False, class_=AsyncSession)

async def get_user( user_id:int) -> User:
    async with async_session() as session:
        stmt = select(User).where(User.id == user_id)
        result = await session.execute(stmt)
        return result.scalar_one_or_none()

#Using the async function
import asyncio

async def main():
    user = await get_user(1)
    print(user.name if user else "User not found")


# CRUD Operations with Async SQLALlhemy

# Create
async  def create_user(name:str, email:str) -> User:
    async with async_session() as session:
        new_user = User(name=name, email=email)
        session.add(new_user)
        await session.commit()
        await session.refresh(new_user)
        return new_user

# Usage create
async def get_user_by_email(email:str) -> User:

    user = await create_user("user1", "user1@exmale.com")
    print(f"Created user: {user.name}")

asyncio.run(get_user_by_email(""))

# Read
async def get_user_by_id(user_id: int) -> User:
    async with async_session() as session:
        stmt = select(User).where(User.id == user_id)
        result = await session.execute(stmt)
        return result.scalar_one_or_none()

# Usage read
pass

# Update
async def update_user_email(user_id:int, new_email:str) -> User:
    async with async_session() as session:
        stmt = select(User).where(User.id == user_id)
        result = await session.execute(stmt)
        updated_user = result.scalar_one_or_none()
        if updated_user:
            updated_user.email = new_email
            await session.commit()
            return updated_user
        return None
#Usage update
pass

# Delete
async def delete_user(user_id:int) -> User:
    async with async_session() as session:
        stmt = select(User).where(User.id == user_id)
        result = await session.execute(stmt)
        deleted_user = result.scalar_one_or_none()
        if deleted_user:
            await session.delete(deleted_user)
            await session.commit()
            return True
        return False

#Usage Delete
pass


#Querying with async SQLAlchemy

async def get_users_with_addresses():
    async with async_session() as session:
        stmt = select(User, Address).join(Address)
        result = await session.execute(stmt)
        return result.all()

async def count_users():
    Querying with Async SQLAlchemy
#Async SQLAlchemy supports complex queries, including joins and aggregations:async with async_session() as session:
        stmt = select(func.count(User.id))
        result = await session.execute(stmt)
        return result.scalar_one()


# Usage
async def main():
    users_with_addresses = await get_users_with_addresses()
    for user, address in users_with_addresses:
        print(f"User: {user.name}, Address: {address.email}")

    user_count = await count_users()
    print(f"Total users: {user_count}")

#Best oractices and considereations for aync usage
'''
1. Use async with for session management: This ensures proper handling of async contexts.
2. Be mindful of the event loop: Avoid mixing sync and async code within the same function.
3. Use connection pooling: Async SQLAlchemy uses connection pooling by default, which is crucial for managing database connections efficiently.
4. Handle concurrency carefully: While async allows for concurrent operations, be cautious about race conditions and data integrity.
5. Profiling and monitoring: Use tools like asyncio.create_task() and asyncio.gather() to manage and monitor multiple async operations.
6. Error handling: Use try/except blocks to handle database errors gracefully in an async context.
7. Pagination for large result sets: When dealing with large datasets, implement pagination to avoid loading too much data into memory at once.


async def get_users_paginated(page: int, per_page: int):
    async with async_session() as session:
        stmt = select(User).limit(per_page).offset((page - 1) * per_page)
        result = await session.execute(stmt)
        return result.scalars().all()
'''










asyncio.run(main())



