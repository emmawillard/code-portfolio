def main():
    tries = 3
    q = False
    while tries != 0 and not q:
        try: 
            
            filename = input('Enter the filename: ')
            openfile = open(filename, 'r')
            q = True
            file = openfile.read()
            file = file.split()
            
            # keys already alphabetized in list/sequence
            
            keylist = ['and','as','assert','break','class','continue','def','del',\
                       'elif','else','except','False','finally','for','from','global',\
                       'if','import','in','is','lambda','None','nonlocal','not',\
                       'or','pass','raise','return','True','try','while','with','yield']
            iterate = 0
            print('Here is the keyword frequency in alphabetic order: ')
            
            while iterate != len(keylist) - 1:
                
                key = keylist[iterate]
                iterate += 1
                freq = keyfreq(file, key)
                if freq != 0:
                    print(key, freq)
            
            openfile.close()
            
        # if file does not exist
        except FileNotFoundError:
            if tries > 0:
                print('Check file name and try again.')
                tries -= 1
    
    if tries ==0:
        print('Out of tries. Program will terminate.')
        quit()
        
def keyfreq(file, key):
    # counts frequency throughout file
    for x in file:
        occur = file.count(key)
        
    return occur

# run program
main()
