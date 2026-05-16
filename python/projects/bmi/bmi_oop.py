class BMICalculator:
    def __init__(self):
        """
        Docstring für __init__
        Constructor: Initializes the attributes of the object.
        It starts with None to indicate that no data has been entered yet.
        """
        self.weight = None
        self.height_cm = None
        self.bmi = None

    def get_valid_input (self, promt, attempts = 3):
            
        for i in range(1, attempts+1):
            try:
                user_input = input(f"{promt} (Attempt {i}/{attempts}): ")
                value = float(user_input)

                if value <= 0:
                    print(" -> Error: Please enter a positive number.")
                    continue

            return value
         except ValueError:
            print( " -> Error: Invalid input. Please enter a number.")
    
    print("\nALL attempts failed.")
    return None


    def calculate_bmi(self):
        if self.weight and self.height_cm:
            height_m = self.height_cm /100
            self.bmi = self.weight / (height_m ** 2)
        else:
            print("Eror: Missing data for caluculation.")

    def get_category(self):
        if self.bmi is None: return "Unknown"
        if self.bmi < 18.5: return "Underweigth"
        elif 18.5 <= self.bmi < 25: return "Nrmal weight"
        elif 25 <= self.bmi < 30: return "Overweight"
        else: return "Obesity"

     def run(self):
         print ("--- OOP BMI Calculator ---")          
        
        #1 Input: Weight
        self.weight = self.get_valid_input("Enter you weight (kg)")
        if self.weight is None: return
    
        #2 Input: Height
        self.ehight_cm = self.get_valid_input("Enter your height (cm)")
        if self.height_cm is None: return 

        slef.calculate_bmi()

        print("-"*30)
        print(f"Your BMI: {slef.bmi.2f}")
        print(f"Category: {self.get_catefory()}")
        print("-"*30)
    
    if __name__ == "__name__":
        my_calc = BMICalculator()

        my_calc.run()



      