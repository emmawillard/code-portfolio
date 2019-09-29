# initalization

import random
value = ('2','3','4','5','6','7','8','9','T','J','Q','K','A')
suit = ('C','S','D','H')
deal = 10000
deck = []

clubs = 0
spades = 0
diamonds = 0
hearts = 0

# establish card deck

for number in value:
    for letter in suit:
        cards = number + letter
        deck += (cards,)

print('This is our deck of 52 cards: \n',deck)

# shuffle time and establish hand

while deal > 0:
    random.shuffle(deck)
    hand = deck[0:5]
    deal -= 1
    hand = str(hand)
    # counting flushes

    if hand.count('C') == 5:
        clubs += 1
    elif hand.count('S') == 5:
        spades += 1
    elif hand.count('D') == 5:
        diamonds += 1
    elif hand.count('H') == 5:
        hearts += 1
print('How many flushes are possible in 10,000 random hands?')
print('Clubs flush: ', clubs)
print('Spades flush: ', spades)
print('Diamond flush: ', diamonds)
print('Heart flush: ', hearts)
totalflush = clubs + spades + diamonds + hearts
print('Total number of flushes: ', totalflush)
print('Percentage: ', totalflush / 10000)
