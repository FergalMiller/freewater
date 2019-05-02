A realtively small personally conceived "while" language with a small compiler using ANTLr.

The program compiles my personal language into Python code.

Features of language:
Strongly typed variables including tpyes:
- void
- int
- float
- boolean
- string
Control flow statements with dedicated execution blocks
Functions with parameterised arguments, dedicated execution blocks, and types, including void functions
Printing calls
Commenting

Features of compiler:
Tokenisation and parsing of an input text file (using ANTLr)
Parse tree compilation into abstract syntax tree structure
Static type checking with tree-structured heirarchy symbol lookup tables that check:
- type
- scope
- exceptions
Translation of abstract syntax tree to target language (Python)

3rd party tools: 
- ANTLr intelliJ plug-in