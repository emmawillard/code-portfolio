import turtle
import random


def chooseword():
    # randomize word
    wordlist = ['catastrophe','xylophone','centaur','mermaid','software','legendary','announcement','betrayal','careful','konnichiwa','mathematics','keyboard','hardware','australia','rainboots','sweatshirt','specialize','aesthetic','dungeon','dragon']
    randomindex = random.randint(0, len(wordlist) - 1)
    randomword = wordlist[randomindex]
    return randomword
def main():
    #set up hang thing
    myturtle = turtle.Turtle()
    myturtle.hideturtle()
    myturtle.speed(0)
    myturtle.penup()
    myturtle.setposition(0,140)
    myturtle.pendown
    myturtle.left(90)
    myturtle.penup()
    myturtle.forward(40)
    myturtle.pendown()
    myturtle.forward(40)
    myturtle.right(90)
    myturtle.forward(150)
    myturtle.right(90)
    myturtle.forward(350)
    myturtle.penup()
    myturtle.left(90)
    myturtle.setposition(0,100)
    myturtle.pendown()
    
    randomword = chooseword()
    letterturtle = turtle.Turtle()
    letterturtle.hideturtle()
    letterturtle.speed(0)
    letterturtle.penup()
    letterturtle.setposition(0, -150)
    letterturtle.pendown()

    # making gallows

    randomstr = list(randomword)
    spaces = ['_ ' for char in randomword]
    spaces = str(spaces)
    for ch in ",'[]":
        newspaces = spaces.replace(ch,'')
        spaces = spaces.replace(ch,'')
    letterturtle.write(spaces, align="center", font=("Arial", 25, "normal"))
    letterturtle.write(newspaces, align="center", font=("Arial", 25, "normal"))

    # fill in word letter by letter
    
    count = 0
    let_total = 0
    spaces = list(spaces)
    while count != 6:
        if let_total == len(randomstr):
            letterturtle.penup()
            letterturtle.setposition(0,-200)
            letterturtle.write("You win!",align="Center",font=("Arial",25,"normal"))
            quits = turtle.textinput("You Win!","type 'q' to exit")

            if quits == 'q':
                turtle.bye()
                
        guess = turtle.textinput('Hangman game!', 'Guess a letter: ')
        i = 0
        while i < len(randomstr) and len(guess) == 1:
            for n in randomstr:
                if n == guess:
                    spaces[i*3] = n
                    let_total += 1
                i += 1
        something = ""
        for q in spaces:
            something = something + q
        for num in range(1):
            letterturtle.undo()
        letterturtle.write(something, move=False, align="center", font=("Arial", 25, "normal"))
        if guess not in randomstr:
            count = count + 1
            if count == 1:
                #hangman head
                myturtle.penup()
                myturtle.setposition(0,100)
                myturtle.pendown()
                myturtle.circle(40)   
                myturtle.right(90)
            if count == 2:
                #hangman body
                myturtle.forward(150)
            if count == 3:
                #hangman arm
                myturtle.backward(100)
                myturtle.right(130)
                myturtle.forward(70)
                myturtle.backward(70)
                myturtle.left(130)
            if count == 4:
                #hangman arm2
                myturtle.left(130)
                myturtle.forward(70)
                myturtle.backward(70)
                myturtle.right(130)
            if count == 5:
                #hangman leg
                myturtle.forward(100)
                myturtle.right(60)
                myturtle.forward(70)
                myturtle.backward(70)
                myturtle.left(60)
    #hangman leg2 and losing game
    myturtle.left(60)
    myturtle.forward(70)
    myturtle.backward(70)
    myturtle.right(60)
    letterturtle.penup()
    letterturtle.setposition(0,-200)
    letterturtle.write("You lose!",align="Center",font=("Arial",30,"bold"))
    quits = turtle.textinput("You Lose!","type 'q' to exit")
    if quits == 'q':
        turtle.bye()

#run main()
main()

