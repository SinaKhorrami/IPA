import xml.etree.ElementTree as ET

class Interests:
    
    def __init__(self,a_id):
        self.path = "../Interests/data.xml"
        self.id = a_id
        self.dic = {}
        try:
            tree = ET.parse(self.path)
        except IOError as e:
            print(e.errno)
            print(e.strerror)
        self.root = tree.getroot()
        self.setUser()
        
    def setUser(self):
        flag = False
        new_user = ""
        for user in self.root:
            if user.attrib['id'] == self.id:
                flag = True
        if flag == False:
            new_user = ET.Element('user', {'id' : self.id})
            self.root.append(new_user)
            tree= ET.ElementTree(self.root)
            tree.write(self.path)
                
    def setInterests(self, data):
        data = dict((k.lower(), v) for k,v in data.iteritems())
        flag = False
        for user in self.root:
            if user.attrib['id'] == self.id:
                for type_ in user:
                    if(type_.attrib['title'] == list(data.keys())[0]):
                        flag = True
                        new_child = ET.Element('value', {'value': list(data.values())[0]})
                        type_.append(new_child)
                        tree = ET.ElementTree(self.root)
                        tree.write(self.path)
                if(flag == False):
                    new_child = ET.Element('value', {'value': list(data.values())[0]})
                    new_element =  ET.Element('type', {'title' : list(data.keys())[0]})
                    new_element.append(new_child)
                    user.append(new_element)
                    tree = ET.ElementTree(self.root)
                    tree.write(self.path)
                    break
    def getInterests(self):
        for user in self.root:
            if user.attrib['id'] == self.id:
                for type_ in user:
                    attr = type_.attrib['title']
                    self.dic[attr]= []
                    for value in type_:
                        self.dic[attr].append(value.attrib['value'])
        return (self.dic)
        
    def deleteInterests(self, data):
        data = dict((k.lower(), v) for k,v in data.iteritems())
        flag = False
        for user in self.root:
            if (user.attrib['id'] == self.id) and flag == False:
                for type_ in user:
                    if (type_.attrib['title'] == list(data.keys())[0]) and flag == False:
                        for value in type_:
                            if (value.attrib['value'] == list(data.values())[0]) and flag == False:
                                type_.remove(value)
                                flag = True
                        if len(type_) == 0:
                            user.remove(type_)
                        tree = ET.ElementTree(self.root)
                        tree.write(self.path)


#user = Interests("1247")
#user.getInterests()
#data = {"Movie" : "A Seperation"}
#user.deleteInterests(data)
