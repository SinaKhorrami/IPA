from interests import *


class InterestManager():
    def __init__(self, data, a_id):
        print("@1")
        self.data = data
        print("@2")
        self.userInterests = Interests(a_id).getInterests()
        print("@3")
        self.interests = ['sports', 'news', 'travel', 'food and drinks', 'events', 'places', 
                          'movies and tv', 'music']
        print("@4")
        self.data = data.lower()
        print("@5")
        self.spl = data.split()
        print("@6")
        self.new_data = ""
        print("@7")
        if ("search" in self.spl):
            print(self.data)
            self.searchBasedOnInterests()
            print(self.new_data)
        else:
            print ("Not Found!")
        
    def searchBasedOnInterests(self):
        for word in self.spl:
            for interest in self.interests:
                if word in interest:
                    try :
                        for i in list(self.userInterests.keys()):
                            if (word in i):
                                self.new_data = self.data.replace(word, self.userInterests[i][0])
                    except KeyError:
                        print ("OK!")
    def locationBasedOnInterests(self):
        pass
        
        
        
        
#a = InterestManager("search latest drink news", "123")
#print(a.userInterests)
#print(a.new_data)
