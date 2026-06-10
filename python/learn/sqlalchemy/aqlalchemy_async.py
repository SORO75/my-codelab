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

asyncio.run(main())



