Project Description:

Bank Management System Overview:

The Bank Management System is a Java program that simulates the functionality of a bank, allowing users to interact with the system as either bank administrators or customers using an ATM. The program provides various features such as creating accounts, checking balances, withdrawing cash, transferring money, and administrative functions like viewing account details and updating PINs.

Key Components:

Main Method:

The main method initializes the program, offering users the choice to enter the bank or use the ATM services.The program operates in a loop, allowing users to perform multiple transactions until they choose to exit.

Bank Method (bank):

This method handles interactions within the bank, distinguishing between administrators and new users. Users can create accounts or access administrative functions. The loop continues until the user decides to exit or encounters an invalid input.

ATM Method (atm):

Simulates the ATM experience, requiring users to input a credit card number and PIN. Provides options for checking balance, withdrawing cash, transferring money, or exiting the ATM. Implements security features, including a maximum number of PIN attempts before a temporary lockout.

Transaction Methods (checkBalance, withdrawCash, transferMoney):

These methods handle specific transactions, updating balances and file records accordingly.The transferMoney method allows users to transfer funds between accounts.

Admin Method (adminBank):

Admin functions include viewing account details, total bank balance, and details of users with maximum or minimum balances.

Admins can also search for specific account details, change PINs, and delete user details.

Utility Methods (isAccountExists, updatePinInFile, deleteUserDetails):

These methods provide utility functions such as checking if an account exists, updating PINs, and deleting user details.

Account Creation Method (accountMaking):

Handles the process of creating new accounts, including capturing user details and generating unique account numbers and credit card numbers.

Account Validation Method (accountNumberExists):

Checks whether an account number already exists, ensuring the uniqueness of account numbers.

File Handling:

The program utilizes file I/O to read and write account details to a text file (AllAccountsDetails.txt).

User details, including account information and transaction history, are stored in this file.

User Interaction:

The program communicates with users through the console, prompting for inputs and displaying transaction results.

Error Handling:

Exception handling is implemented to manage potential issues, such as file not found exceptions or invalid inputs.

Security Measures:

The program incorporates security features like PIN validation and temporary lockout for repeated incorrect attempts.

Overall Structure:

The program is structured with modular methods, promoting code organization and readability.

Loops and conditional statements are used for user interactions and decision-making processes.

This Bank Management System provides a comprehensive set of features for both customers and administrators, offering a simulated banking experience with a focus on user security and data integrity.
