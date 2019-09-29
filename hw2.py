# program greeting and initialization

print('This program will tell you your zodiac sign from your day of birth.')
m = int(input('Enter birth month (1-12): '))

#check for valid month and day

while m < 1 or m > 12:
    m = int(input('INVALID MONTH. Enter month (1-12): '))

d = int(input('Enter birth day (1-31): '))

while d < 1 or d > 31 or (m == 2 and d > 29) or ((m == 4 or m == 9 or m == 11) and d > 30):
    print('INVALID DAY. DAY DOES NOT EXIST FOR ENTERED MONTH.')
    d = int(input('Enter a valid day (1-31): '))

# determining zodiac sign
# determine simutaneously from month (m) and day (d) inputs

if (m == 1 and d >= 20) or (m == 2 and d <= 18):
    print('You are an Aquarius.')
elif (m == 2 and d > 18) or (m == 3 and d <= 20):
    print('You are a Pisces.')
elif (m == 3 and d > 20 ) or (m == 4 and d <= 19):
    print('You are an Aries.')
elif (m == 4 and d > 19 ) or (m == 5 and d <= 20):
    print('You are a Taurus.')
elif (m == 5 and d > 20 ) or (m == 6 and d <= 20):
    print('You are a Gemini.')
elif (m == 6 and d > 20 ) or (m == 7 and d <= 22):
    print('You are a Cancer.')
elif (m == 7 and d > 22 ) or (m == 8 and d <= 22):
    print('You are a Leo.')
elif (m == 8 and d > 22 ) or (m == 9 and d <= 22):
    print('You are a Virgo.')
elif (m == 9 and d > 22 ) or (m == 10 and d <= 22):
    print('You are a Libra.')
elif (m == 10 and d > 22 ) or (m == 11 and d <= 21):
    print('You are a Scorpio.')
elif (m == 11 and d > 21 ) or (m == 12 and d <= 21):
    print('You are a Sagittarius.')
else:
    print('You are a Capricorn.')
