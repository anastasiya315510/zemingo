## Development Exercise
LLD for this project you can check by the [link](https://docs.google.com/document/d/1bHBhxli7y5aSB3AIfpzY2FW7lRK0p_JOYe4ufSWnv54/edit?usp=drive_link)

### Important Notes
1. Google and web resources are allowed (no AI usage, please).
2. Code must be written in Java.
3. It is **not allowed** to use any built-in data structures in Java.

### Requirements

You are asked to implement the following:

#### 1. `QuickPopDataStructure`
- **Push** – O(n)
- **Pop** – O(1)

#### 2. `QuickPushDataStructure`
- **Push** – O(1)
- **Pop** – O(n)

> For both classes, a `Pop` operation should always return the **maximum value** in the data structure.  
> Example: If the following elements are inserted: `3, 6, 7, 2, 4`  
> Consecutive `Pop` operations should return: `7, 6, 4, 3, 2`

Additional constraints:
- Both classes must support any type of objects (e.g., integers or complex objects such as `Person`).
- A single instance of one class can contain objects of **only one type** (e.g., either integers or `Person` objects, but not both).
- Both classes must work in a **multi-threaded environment**, where different threads may perform `Push` and `Pop` operations simultaneously.

You are required to write a **test application** that validates all of the above requirements.

### Design Stage
- Present the **algorithmic solution** for each data structure.
- Provide a **software design** that satisfies the functional requirements.
- Show how the **test application** will verify that all requirements are met.

