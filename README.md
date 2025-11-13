# ChimiDeli Console Application

This project is a beginner friendly console based ordering system inspired by a real deli workflow. It lets users build sandwiches, add drinks or chips, view their cart, and print a clean receipt. Everything runs inside the terminal so you can focus on understanding Java logic step by step.

---

## 📷 Screenshots

### Main Menu
<img width="327" height="220" alt="CLI" src="https://github.com/user-attachments/assets/fe02ca10-25bd-4547-9dd7-78a7f14984f7" />


### Cart Screen
<img width="573" height="221" alt="Cart Screen" src="https://github.com/user-attachments/assets/7d37c4db-0fed-4c97-9707-43e9dacdc8b3" />


### Receipt Example
<img width="363" height="800" alt="Reciept txt file in reciepts folder " src="https://github.com/user-attachments/assets/b8066ba6-a8f5-400d-88c3-68480a44c636" />


---

## 🎯 What This Project Teaches

ChimiDeli is a perfect starter project if you want to really understand how Java applications work. It's simple enough for beginners, but structured the way real programs are built.

You’ll learn how to:

1. Structure a Java Application
You see how files like UserInterface, Cart, Sandwich, and ReceiptManager each have their own responsibilities. This helps you understand clean architecture early.

2. Use Classes and Objects Effectively
Sandwiches, drinks, chips, and cart items all become objects.
You learn how to store data inside them, update them, and pass them around your program.

3. Work with User Input (Scanner)
The console menu teaches you how to read numbers, strings, and handle mistakes with try/catch.

4. Print Clean, Formatted Output
Your cart screen and printed receipts help you practice layout, spacing, and readable text formatting.

5. Use Loops, Conditionals, and Try/Catch Blocks
Every menu interaction is a real example of flow control.

6. Break Large Problems into Simple Methods
Instead of giant messy classes, you learn how to break each task into its own method.
This builds real programming discipline.

🧠 Additional Java Concepts You’ll Practice

You asked for inheritance and interfaces — so here’s how they fit into your project in a clean, beginner-friendly way.

Inheritance
You can create a base class like MenuItem with properties such as name and price.
Then Sandwich, Drink, and Chips can extend it.

This gives you:

Shared logic across items

Cleaner code

Less repetition

A more professional structure

Example idea (not code you need to copy right now):

class MenuItem {
    protected double price;
    public double getPrice() { return price; }
}

class Sandwich extends MenuItem { ... }
class Drink extends MenuItem { ... }
class Chips extends MenuItem { ... }


This is how real apps scale when you add new item types later.

Interfaces
Interfaces help you create a consistent design.
For example, all items that can be added to the cart could implement:

interface Purchasable {
    double getPrice();
    String getDescription();
}


Then every item in your menu follows the same rules. This makes things like receipts extremely easy.

Why this matters for beginners:
It teaches you how to organize code like a professional long before you start building GUI applications or bigger projects.

📺 Additional Resources

I used this video heavily when building the GUI and understanding how menus and user input should flow:

Java Full Course for Beginners — Bro Code
https://www.youtube.com/watch?v=Kmgo00avvEw&t=5908s

This video covers:

Methods

Classes

Objects

Scanners

GUI basics

Action listeners

Panels

Frames

Inheritance

Interfaces

And how to structure your logic step-by-step
