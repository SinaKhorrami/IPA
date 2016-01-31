from interests import *


class InterestManager():
    def __init__(self, data, a_id):
	print(data)
        self.data = data
        self.userInterests = Interests(a_id).getInterests()
        self.interests = ['sports', 'news', 'travel', 'food and drinks', 'events', 'places', 
                          'movies and tv', 'music']
        self.data = data.lower()
        self.spl = data.split()
        self.new_data = ""
        if ("search" in self.spl):
            self.searchBasedOnInterests()
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
