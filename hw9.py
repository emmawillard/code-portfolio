def main():
    # phone alphabet/number dictionary
    alphaToNum = {'A':2, 'B':2, 'C':2, 'D':3, 'E':3, 'F':3, 'G':4,\
              'H':4, 'I':4, 'J':5, 'K':5, 'L':5, 'M':6, 'N':6,\
              'O':6, 'P':7, 'Q':7, 'R':7, 'S':8, 'T':8, 'U':8,\
              'V':9, 'W':9, 'X':9, 'Y':9, 'Z':9}
    phonenumber = input('Enter a telephone number: ')

    # if user hits enter, aka empty string, program stops allowing
    # more phone numbers to be converted
    
    while phonenumber != '':
        for ch in ".()!,?@#$%^&*/:;'-_=+\{}[]<> ":
            phonenumber = phonenumber.replace(ch, "")
        for ch in '"':
            phonenumber = phonenumber.replace(ch, "")
        phonenumber = phonenumber.upper()
        idx = 0

        # checking every part of the phone number for letters and converting
        for i in phonenumber:
            if phonenumber[idx] in alphaToNum:
                convert = str(alphaToNum[phonenumber[idx]])
                phonenumber = phonenumber.replace(phonenumber[idx], convert)
                idx += 1
                
            else:
                # if no letter, move to next index
                idx += 1

        # looks at phone number after getting rid of excess punctuation
        # and converting letters, to check if it fits a regular phone number length
        while validate(phonenumber):
            
            if len(phonenumber) == 7:
                phonenumber = phonenumber[:3] + '-' + phonenumber[3:]
                
            elif len(phonenumber) == 10:
                phonenumber = phonenumber[:3] + '-' + phonenumber[3:]
                phonenumber = phonenumber[:7] + '-' + phonenumber[7:]
                
            newphonenumber = print(phonenumber)
            return main()

        # allows user to try again
        while not validate(phonenumber):
            print('Invalid phone number length. Must have 7 to 10 digits or letters.')
            return main()
        
def validate(phonenumber):
    try:
        return len(phonenumber) == 7 or len(phonenumber) == 10
    except ValueError:
        return False
main()
    
