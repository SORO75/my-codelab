'''
Random Number Generation
Create an array random_ages that contains 100 random integers representing ages, with each age being between 1 and 100 years inclusive.
Use the default_rng() function from NumPy's random module to create a random generator with a seed value of 12345 for reproducibility.    
'''

import numpy as np
rng = np.random.default_rng(12345)

random_ages=rng.integers(1, 101, 100)
print(random_ages)


