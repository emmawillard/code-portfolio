# enter unitTest() into python shell
# it will compress the hard-coded list
# within the unitTest() function, which will call the rlEncode() function
# and print the compressed list

def unitTest():
    mylist = [1, 1, 1, 1, 1, 3, 3, 5, 5, 5, 5, 5, 5, 6, 8, 8, 1, 1, 1, 5, 5, 5, 5, 13, 14, 14]
    print('Original list: ', mylist)
    rlEncode(mylist)
    
    
# compresses list

def rlEncode(mylist):
    go = 1
    encoded = []
    for n in range(1, len(mylist)):
        if mylist[n] == mylist[n - 1]:
            go += 1
            
        else:
            encoded = encoded + [mylist[n - 1], go]
            go = 1
            
        if n == len(mylist) - 1:
            encoded = encoded + [mylist[n], go]
            
    return encoded
    

