def xor(b1, b2):
	if b2:
		return not b1
	return b1


def factorial(n):
	if n == 0:
		return 1
	else:
		return n*factorial(n-1)


def printFibonacci(n):
	def getFib(i):
		if i <= 1:
			return 1
		return getFib(i-1)+getFib(i-2)
	
	
	i = 0
	while i < n:
		print(getFib(i))
		i = i+1


x = 4
print("Starting value:")
print(x)
x = factorial(x)
print("Value becomes factorial of value. New value:")
print(x)
b1 = x >= 120
b2 = x >= 5040
if xor(b1, b2):
	print("Value exists between 120 and 5040!")
else:
	print("Value does not exist between the expected parameters.")
print("Now applying modulo 10 to value and printing the fibonacci sequence where n = the value.")
printFibonacci(x%10)
z = 5
if not (factorial(z) > 10**z)  and  b1 == xor(b1, b2)  or  b2:
	fl = factorial(10-z%4)/z
print(fl)
testInt = 1
testFloat = 2
testFloat = testInt
