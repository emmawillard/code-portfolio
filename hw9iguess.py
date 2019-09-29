def main():
    phonenumber = input('Enter a telephone number: ')
    phonenumber = phonenumber.upper()
    empty = ''
    if phonenumber != empty:
        for ch in phonenumber:
            if ch in ".()!,?@#$%^&*/:;'-_=+\{}[]<> ":
                phonenumber = phonenumber.replace(ch, "")
        print(phonenumber)
main()
