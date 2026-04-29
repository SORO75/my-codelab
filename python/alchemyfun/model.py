from sqlalchemy import String
from sqlalchemy.orm import Mapped, mapped_column
from db import Model

class Product(Model):
    __tablename__ = "product"

    id: Mapped [int] = mapped_column(primary_key=True)




    name = Column(String)