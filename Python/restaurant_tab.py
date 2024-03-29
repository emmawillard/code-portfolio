# Restaurant Tab Calculation Program
# This program will calculate a restaurant tab with a gift certificate

# program greeting
print('This program will calculate a restaurant tab for a couple with')
print('a gift certificate.')

# input tax by user

tax = float(input('\nEnter local tax amount (e.g. enter 7.5% as 7.5): '))

# greeting with tax percentage

print('Now, this program will calculate a restaurant tab for a couple with')
print('a gift certificate, with a restaurant tax of', tax, '%\n')

# get amount of gift certificate
amt_certificate = float(input('Enter amount of the gift certificate: '))

# cost of ordered items
print('Enter ordered items for person 1')

appetizer_per1 = float(input('Appetizer: '))
entree_per1 = float(input('Entree: '))
drinks_per1 = float(input('Drinks: '))
dessert_per1 = float(input('Dessert: '))

print('\nEnter ordered items for person 2')

appetizer_per2 = float(input('Appetizer: '))
entree_per2 = float(input('Entree: '))
drinks_per2 = float(input('Drinks: '))
dessert_per2 = float(input('Dessert: '))

# total items
amt_person1 = appetizer_per1 + entree_per1 + drinks_per1 + dessert_per1
amt_person2 = appetizer_per2 + entree_per2 + drinks_per2 + dessert_per2

# compute tab with tax

tax = tax * .01
items_cost = amt_person1 + amt_person2
tab = items_cost + items_cost * tax

# display amount owe
print('\nOrdered items: $', format(items_cost, '.2f'))
print('\nRestaurant tax: $', format(items_cost * tax, '.2f'))
print('Tab: $', format(tab - amt_certificate, '.2f'))
print('(negative amount indicates unused amount of gift certificate)')
