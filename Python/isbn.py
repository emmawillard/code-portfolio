# program greeting and initialization

terminate = False
print("This program will determine a 9 digit integer's checksum digit \nand output its 10-digit ISBN number.")

while not terminate:

    digit = str(input('Enter a 9 digit integer: '))
    
    # check if input is valid

    while len(digit) != 9:
        digit = str(input('Invalid! Must be 9-digits. Enter a 9-digit integer: '))

        
    if len(digit) == 9:
        
        # separating each individual digit without use of indexing
        
        digit2 = int(digit)
        
        i = ((digit2 // 10 ** 8) * 10)
        h = (((digit2 // 10 ** 7) % 10) * 9)
        g = (((digit2 // 10 ** 6) % 10) * 8)
        f = (((digit2 // 10 ** 5) % 10) * 7)
        e = (((digit2 // 10 ** 4) % 10) * 6)
        d = (((digit2 // 10 ** 3) % 10) * 5)
        c = (((digit2 // 10 ** 2) % 10) * 4)
        b = (((digit2 // 10 ** 1) % 10) * 3)
        a = (((digit2 // 1) % 10)* 2)

        # checksum equation

        total = i + h + g + f + e + d + c + b + a
        j = total % 11
        checksum = 11 - j

        # format to make sure that the final ISBN number will have ten total digits
        
        if checksum == 10:
            print('ISBN with checksum: ')
            print('{0:0>9}'.format(digit) + 'X')
        
        else:
            print('ISBN with checksum:')
            print('{0:0>9}'.format(digit) + str(checksum))
            

    # execute again?

    answer = input('Would you like to continue? (y/n)\n')
    
    if answer == 'n':
        print('Goodbye!')
        terminate = True

