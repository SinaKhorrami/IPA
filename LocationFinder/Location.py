import urllib.request as req
from bs4 import BeautifulSoup
import re
import json

class Location_finder:

	def __init__(self, exp):
		if(exp == "where am I" or exp == "where am i"):
			self.phrase = exp
		else:
			splitted_exp = exp.split(' ')
			if(splitted_exp[0] == "where" and splitted_exp[1] == "is"):
				self.phrase = ' '.join(splitted_exp[2:]).replace(' ', '%20')
			elif(splitted_exp[0] == "find"):
				self.phrase = ' '.join(splitted_exp[1:]).replace(' ', '%20')
			else:
				return 0

	def find(self):
		temp = {}
		if(self.phrase == "where am I" or self.phrase == "where am i"):
		    request = req.Request("http://mylocation.org/")
		    html = req.urlopen(request)

		    soup = BeautifulSoup(html , "html.parser")


		    latitude = soup('td')[3].text
		    longitude = soup('td')[5].text


		    temp['lat'] = latitude
		    temp['long'] = longitude


		else:
			request = req.Request("http://maps.googleapis.com/maps/api/geocode/json?address="+self.phrase)
			html = req.urlopen(request).read().decode('utf8')

			j = json.loads(html)

			latitude = j['results'][0]['geometry']['bounds']['southwest']['lat']
			longitude = j['results'][0]['geometry']['bounds']['southwest']['lng']

			temp['lat'] = latitude
			temp['long'] = longitude

		return temp
