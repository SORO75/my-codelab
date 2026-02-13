def calculate_bmi():
    print ("--- Your Personal BMI Calculator---")

    try:
        weigt_kg = float(input("Enter your wight in kg (e.g., 80.1): "))

        height_cm = float(input("Enter your height in cm (e.g., 180): "))

        bmi = weigt_kg / (height_cm /100)**2

        print( f"\nYour BMI is: {bmi:.2f}")
       
    except ValueError:
        # Handle cases where input is not a valid number
        print("Error: Please enter numeric values only (use dots for decimals).")

if __name__ == "__main__":
    calculate_bmi()