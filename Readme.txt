1. Add any Testcase as required in Template-TestCase.xlsx file
2. Make sure last two columns Actual Output and Result are Empty
3. Open Command Prompt in Project Root
4. Run the command to compile the code - javac -d out -cp "lib/*" src/MovieBookingTest.java
5. Run the code to execute the test cases - java -cp "out;lib/*" MovieBookingTest