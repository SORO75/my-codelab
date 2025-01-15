# Import the pandas library and assign it its "pd" alias

# Create a list with 4 countries - United States, France, Germany, Italy
# Create a new Series by passing in the list of countries
# Assign the Series to a "countries" variable


# Create a list with 3 colors - red, green, blue
# Create a new Series by passing in the list of colors
# Assign the Series to a "colors" variable


# Given the "recipe" dictionary below,
# create a new Series by passing in the dictionary as the data source
# Assign the resulting Series to a "series_dict" variable


import pandas as pd

recipe = {
  "Flour": True,
  "Sugar": True,
  "Salt": False
}


countries = pd.Series(["United States", "France", "Germany", "Italy"])

colors = pd.Series(["red", "green", "blue"])



series_dict =pd.Series(recipe)
