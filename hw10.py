# Part A

class Stack:
    def __init__(self):
        self.stacklist = []
        
        #init method

    def push(self, element):
       self.stacklist.append(element)
        #mutator method
        #single obj
        #push into stack

    def pop(self):
        if not self.isEmpty():
            top = self.stacklist[len(self.stacklist) -1]
            del self.stacklist[len(self.stacklist) -1]
            return top
        else:
            return None
        #mutator method
        #remove and return top stack element
        #None if empty

    def isEmpty(self):
        string = str(self.stacklist())
        if len(string) == 0:
            return (self.stacklist == [])
        
        #Is empty? True/False return

    def _repr_(self):
        self.stacklist = ','.join(self,stacklist)
        return repr(self.things)
    
        #return string that contains all stack elements
        #separated by commas (ordered top to bottom)

def testStack(test):
    thelist = []

    for ch in test:
        thelist.append(ch)
    i = len(thelist) - 1
    stackcl = Stack()
    
    while i >= 0:
        stackcl.push(thelist[i])
        i -= 1
    newstr = stackcl.__repr__()
    newstr = newstr.replace(',',' ')
    print(newstr)
    
    #print string's char in reverse order
    #separate by single space


# Part B

def convertToRPN(infix):
    rpnstr = ''
    op = {'+':1, '/':2,'-':1,'*':2}
    ops = ['+', '/','-','*']
    stackcl = Stack()
    dopush = stackcl.push()
    for ch in infix:
        if ch in '1234567890.':
            rpnstr + ch
            rpnstr = rpnstr + ' '
        if ch == '(':
            stackcl.push(ch)
        if ch in op:
            true = False
            if stackcl.isEmpty():
                stackcl.push(ch)
        while not True:
            if ops[ch] > dopush[0]:
                i = stackcl.pop()
                rpnstr = rpnstr + i + ' '
            stackcl.pop()
        if ch == ')':
            while stackcl.pop() != '(':
                i = stackcl.pop()
                rpnstr = rpnstr + k + ' '
    while stackcl.pop()!= None and not stackcl.isEmpty():
        if stackcl.pop() == '(' or stackcl.pop() == ')':
            stacklcl.pop()
        else:
            i = stackcl.pop()
            rpnstr = ''.join(rpnstr)

    return rpnstr
    #accepts string containing math expression (infix form)
    #converts infix to postfix expression as a string

    
def main():
    preconvert = input('Enter an arithmetic expression: ')
    a = convertToRPN(preconvert)
    print(a)
    #calls convertToRPN with user argument
    #displays result/postfix



