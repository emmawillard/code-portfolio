#Part 1: common vechicle data

class Vehicle:
    def __init__(self, make, model, year, mileage, price):
        self.make = make
        self.model = model
        self.year = year
        self.mileage = mileage
        self.price = price
    def getMake(self):
        return self.make
    def setMake(self, newmake):
        self.make = newmake
    def getModel(self):
        return self.model
    def setModel(self, newmodel):
        self.model = newmodel
    def getYear(self):
        return self.year
    def setYear(self, newyear):
        self.year = newyear
    def getMileage(self):
        return self.mileage
    def setMileage(self, newmileage):
        self.mileage = newmileage
    def getPrice(self):
        return self.price
    def setPrice(self, newprice):
        self.price = newprice
    def Display(self):
        thedisplay = 'Make: ' + self.make\n + 'Year: ' + self.year\n + 'Model: ' + self.model\n + 'Miles: ' + self.mileage\n + 'Price: ' + self.price
        return thedisplay
    
#Part 2 and 3

class Car(Vehicle):
    def __init__(self, door, make, model, year, mileage, price):
        super().__init__(make, model, year, mileage, price)
        self.door = door
    def getDoor(self):
        return self.door
    def setDoor(self, newdoor):
        self.door = newdoor
    def Display(self):
        cardisplay = 'Inventory Unit: Car'\n + 'Number of doors: ' + self.door\n
        super().Display()
        
        
class Truck(Vehicle):
    def __init__(self, drive):
        super().__init__(make, model, year, mileage, price)
        self.drive = drive
    def getDrive(self):
        return self.drive
    def setDrive(self, newdrive):
        self.door = newdrive
    def Display(self):
        print('Inventory Unit: Truck')
        super().Display()
        print('Drive type: ', self.drive)
        
class SUV(Vehicle):
    def __init__(self, capacity):
        super().__init__(make, model, year, mileage, price)
        self.capacity = capacity
    def getCapacity(self):
        return self.capacity
    def setCapacity(self, newcapacity):
        self.capacity = newcapacity
    def Display(self):
        print('Inventory Unit: SUV')
        super().Display()
        print('Passenger capacity: ', self.capacity)
        
#Part 4

class Inventory(Vehicle):
    def __init__(self, inventory = []):
        self.inventory = inventory
    def addVehicle(self, vehicle):
        self.inventory.append(vehicle)
    def Display(self):
        for vec in vehicles:
            print(vec)
            print(\n * 2)

#Part 5
def main():
    inv = Inventory()
    types = input('Enter a vehicle type (car, truck, or SUV): ')
    types = types.lower()
    if types != '':
        if types == 'car':
            car = Car()
            cd = car.Display()
            inv.addVehicle(car)
            main()
        elif types == 'truck':
            truck = Truck()
            td = truck.Display()
            inv.addVehicle(truck)
            main()
        elif types == 'suv':
            suv = SUV()
            sd = suv.Display()
            inv.addVehicle(suv)
            main()
    else:
        #display whole vehicle inventory
        inv.Display()
main()
        
    
    
    
